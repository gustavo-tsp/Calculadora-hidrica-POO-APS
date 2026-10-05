package model;

// Classe de modelo que representa a entidade Consumo.
public class Consumo {

    // Encapsulamento
    // Atributos privados para proteger o estado interno do objeto.
    private int idConsumo;
    private double m3Gastos;
    private String dataLeitura;
    private int usuarioIdUsuario;

    // Método Construtor
    // Inicializa o objeto e utiliza o operador 'this' para diferenciar atributos de parâmetros.
    public Consumo(int idConsumo, double m3Gastos, String dataLeitura, int usuarioIdUsuario) {
        this.idConsumo = idConsumo;
        this.m3Gastos = m3Gastos;
        this.dataLeitura = dataLeitura;
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    // Métodos Acessores (Getters)
    // Permitem a leitura controlada dos atributos privados.
    public int getIdConsumo() {
        return idConsumo;
    }

    public double getM3Gastos() {
        return m3Gastos;
    }

    public String getDataLeitura() {
        return dataLeitura;
    }

    public int getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    // Métodos Modificadores (Setters)
    // Permitem a alteração segura dos atributos privados.
    public void setM3Gastos(double m3Gastos) {
        this.m3Gastos = m3Gastos;
    }

    public void setDataLeitura(String dataLeitura) {
        this.dataLeitura = dataLeitura;
    }
}