package model;

// Classe de modelo que representa a entidade Estado.
public class Estado {

    // Encapsulamento
    // Atributos privados para proteger o estado interno do objeto.
    private String idEstado;
    private String nome;
    private double coef_Esg;
    private double consumoMedio;

    // Método Construtor
    // Inicializa o objeto e utiliza o operador 'this' para diferenciar atributos de parâmetros.
    public Estado(String idEstado, String nome, double coef_Esg, double consumoMedio) {
        this.idEstado = idEstado;
        this.nome = nome;
        this.coef_Esg = coef_Esg;
        this.consumoMedio = consumoMedio;
    }

    // Métodos Acessores (Getters)
    // Permitem a leitura controlada dos atributos privados.
    public String getIdEstado() {
        return idEstado;
    }

    public String getNome() {
        return nome;
    }

    public double getCoef_Esg() {
        return coef_Esg;
    }

    public double getConsumoMedio() {
        return consumoMedio;
    }

    // Métodos Modificadores (Setters)
    // Permitem a alteração segura dos atributos privados.
    public void setNomeEstado(String nomeEstado) {
        this.nome = nomeEstado; 
    }

    public void setCoef_Esg(double coef_Esg) {
        this.coef_Esg = coef_Esg;
    }

    public void setConsumoMedio(double consumoMedio) {
        this.consumoMedio = consumoMedio;
    }
}