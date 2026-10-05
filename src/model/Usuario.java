package model;

// Classe de modelo que representa a entidade Usuário.
public class Usuario {

    // Encapsulamento
    // Atributos privados para proteger o estado interno do objeto.
    private int idUsuario;
    private String nome;
    private int numResidentes;
    private String idEstado;

    // Método Construtor
    // Inicializa o objeto e utiliza o operador 'this' para diferenciar atributos de parâmetros.
    public Usuario(int idUsuario, String nome, int numResidentes, String idEstado) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.numResidentes = numResidentes;
        this.idEstado = idEstado;
    }

    // Métodos Acessores (Getters)
    // Permitem a leitura controlada dos atributos privados.
    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public int getNumResidentes() {
        return numResidentes;
    }

    public String getIdEstado() {
        return idEstado;
    }

    // Métodos Modificadores (Setters)
    // Permitem a alteração segura dos atributos privados.
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumResidentes(int numResidentes) {
        this.numResidentes = numResidentes;
    }

}