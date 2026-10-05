package dao;

import model.Usuario;
import model.Consumo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Classe DAO (Data Access Object) responsável pelas operações de banco de dados da entidade Consumo.
public class ConsumoDAO {

    // Método de Inserção (Create)
    public void registrarConsumo(Consumo consumo) {
        String sql = "INSERT INTO consumo (m3_gastos, data_leitura, usuario_idusuario) VALUES (?,?,?)";

        // Bloco try-with-resources
        // Garante o fechamento automático das conexões JDBC (AutoCloseable), evitando vazamento de memória.
        try (Connection connect = Conexao.getConexao();
             PreparedStatement statement = connect.prepareStatement(sql)) {

            statement.setDouble(1, consumo.getM3Gastos());
            statement.setString(2, consumo.getDataLeitura());
            statement.setInt(3, consumo.getUsuarioIdUsuario());

            statement.executeUpdate();
            System.out.println("Consumo registrado!");
        } catch (SQLException e) {
            // Tratamento de Exceções para falhas de sintaxe ou violações no banco.
            System.out.println("Erro ao registrar consumo:" + e.getMessage());
        }
    }

    // Método de Consulta (Read) com aplicação de Polimorfismo
    public List<Consumo> listarPorUsuario(int idUsuario) {
        String sql = "SELECT * FROM consumo WHERE usuario_idusuario = ? ORDER BY data_leitura DESC";
        
        // Polimorfismo de subtipagem
        // Utiliza a interface abstrata 'List' para instanciar e retornar o objeto concreto 'ArrayList'.
        List<Consumo> lista = new ArrayList<>();

        try (Connection connect = Conexao.getConexao();
             PreparedStatement statement = connect.prepareStatement(sql)) {

            statement.setInt(1, idUsuario);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                // Instanciação: Cria objetos 'Consumo' mapeando os dados recebidos pelo ResultSet.
                lista.add(new Consumo(
                        result.getInt("idconsumo"),
                        result.getDouble("m3_gastos"),
                        result.getString("data_leitura"),
                        result.getInt("usuario_idusuario")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar consumos: " + e.getMessage());
        }
        return lista;
    }

    // Método de Exclusão (Delete)
    public void deletarConsumo(int idConsumo) {
        String sql = "DELETE FROM consumo WHERE idconsumo = ?";

        try (Connection connect = Conexao.getConexao();
             PreparedStatement statement = connect.prepareStatement(sql)) {

            statement.setInt(1, idConsumo);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar consumo: " + e.getMessage());
        }
    }

    // Método de Atualização (Update)
    public void atualizarConsumo(Consumo consumo) {
        String sql = "UPDATE consumo SET m3_gastos = ?, data_leitura = ? WHERE idconsumo = ?";

        try (Connection connect = Conexao.getConexao();
             PreparedStatement statement = connect.prepareStatement(sql)) {

            statement.setDouble(1, consumo.getM3Gastos());
            statement.setString(2, consumo.getDataLeitura());
            statement.setInt(3, consumo.getIdConsumo());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar consumo: " + e.getMessage());
        }
    }
}