package view.controller;

import dao.UsuarioDAO;
import javafx.scene.control.ComboBox;
import model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


 //Classe controladora responsável pelo fluxo de registro e login de usuários.
 
public class RegistrationController {

    // Encapsulamento
    // Os atributos privados sao injetados pelo JavaFX atraves da anotação @FXML, protegendo o estado da View.
    @FXML private TextField nameField;
    @FXML private ComboBox<String> stateComboBox;
    @FXML private TextField residentsField;
    @FXML private Button confirmButton;

    @FXML
    public void initialize() {
        try {
            dao.EstadoDAO estadoDAO = new dao.EstadoDAO();
            java.util.List<model.Estado> estados = estadoDAO.listarTodos();

            javafx.collections.ObservableList<String> siglas = javafx.collections.FXCollections.observableArrayList();
            for (model.Estado e : estados) {
                siglas.add(e.getIdEstado());
            }
            stateComboBox.setItems(siglas);
        } catch (Exception e) {
            // Tratamento de Exceções caso o banco de dados falhe
            System.out.println("Aviso: Falha ao preencher o ComboBox de estados. Preenchendo manualmente.");
            stateComboBox.setItems(javafx.collections.FXCollections.observableArrayList(
                    "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG",
                    "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
            ));
        }
    }

    @FXML
    void handleConfirmAction(ActionEvent event) {
        String nome = nameField.getText().trim();

        // Captura o item selecionado no ComboBox
        String estadoSigla = stateComboBox.getValue() != null ? stateComboBox.getValue().toUpperCase() : "";

        String moradoresTexto = residentsField.getText().trim();

        if (nome.isEmpty() || estadoSigla.isEmpty() || moradoresTexto.isEmpty()) {
            mostrarAlerta("Erro de Validação", "Por favor, preencha todos os campos.");
            return;
        }

        int moradores;
        try {
            moradores = Integer.parseInt(moradoresTexto);
            if (moradores <= 0) {
                mostrarAlerta("Erro de Validação", "O número de residentes deve ser maior que zero.");
                return;
            }
        } catch (NumberFormatException e) {
            // tratamento específico para conversão inválida de texto para número.
            mostrarAlerta("Erro de Validação", "O campo de residentes deve conter apenas números inteiros.");
            return;
        }

        dao.EstadoDAO estadoDAO = new dao.EstadoDAO();
        model.Estado estadoReal = estadoDAO.buscarPorSigla(estadoSigla);

        String idEstado = "";
        if (estadoReal != null) {
            idEstado = estadoReal.getIdEstado();
        } else {
            mostrarAlerta("Erro de Validação", "Estado não encontrado no banco de dados.");
            return;
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // Se o usuário já existe, o sistema atua como um Login automático.
        if (usuarioDAO.listarNomesUsuarios().contains(nome)) {
            mostrarAlerta("Usuário Já Existe", "Este nome de usuário já está cadastrado. Redirecionando...");
            Usuario usuarioExistente = usuarioDAO.buscarPorNome(nome);
            irParaAbaPrincipal(usuarioExistente);
            return;
        }

        // CONCEITO DE POO: Instanciação - Criação de um novo objeto Usuario a partir da classe modelo utilizando construtor.
        Usuario novoUsuario = new Usuario(0, nome, moradores, idEstado);

        try {
            usuarioDAO.registrarUsuario(novoUsuario);

            Usuario usuarioCadastrado = usuarioDAO.buscarPorNome(nome);
            if (usuarioCadastrado != null) {
                irParaAbaPrincipal(usuarioCadastrado);
            } else {
                mostrarAlerta("Erro", "Falha ao recuperar o usuário cadastrado.");
            }
        } catch (Exception e) {
            mostrarAlerta("Erro no Banco", "Não foi possível salvar o usuário no banco de dados.");
            e.printStackTrace();
        }
    }

    // Passagem de Contexto e Injeção de Dependência
    // Método privado responsável por carregar a próxima tela e transferir o objeto Usuario para o novo controlador
    private void irParaAbaPrincipal(Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/UserTab.fxml"));
            Parent root = loader.load();

            UserTabController userTabController = loader.getController();
            userTabController.inicializarComUsuarioReal(usuario); // Injeção do estado ativo

            Scene scene = new Scene(root, 900, 600);
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Erro de Carregamento", "Erro ao tentar abrir a UserTab: Verifique o caminho do arquivo!");
        }
    }

    // Método privado para emissão de alertas.
    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}