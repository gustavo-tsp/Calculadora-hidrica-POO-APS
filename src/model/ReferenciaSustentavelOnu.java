package model;

// Classe de modelo que representa a entidade Referência Sustentável da ONU.
public class ReferenciaSustentavelOnu {

    // Encapsulamento
    // Atributos privados para proteger o estado interno do objeto.
    private int idreferencia_sustentavel_onu;
    private String nivel_acesso;
    private int litro_pessoa;

    // Método Construtor
    // Inicializa o objeto e utiliza o operador 'this' para diferenciar atributos de parâmetros.
    public ReferenciaSustentavelOnu(int idreferencia_sustentavel_onu, String nivel_acesso, int litro_pessoa) {
        this.idreferencia_sustentavel_onu = idreferencia_sustentavel_onu;
        this.nivel_acesso = nivel_acesso;
        this.litro_pessoa = litro_pessoa;
    }

    // Métodos Acessores (Getters)
    // Permitem a leitura controlada dos atributos privados.
    public int getIdreferencia_sustentavel_onu() {
        return idreferencia_sustentavel_onu;
    }

    public String getNivel_acesso() {
        return nivel_acesso;
    }

    public int getLitro_pessoa() {
        return litro_pessoa;
    }

    // Métodos Modificadores (Setters)
    // Permitem a alteração segura dos atributos privados.
    public void setIdreferencia_sustentavel_onu(int idreferencia_sustentavel_onu) {
        this.idreferencia_sustentavel_onu = idreferencia_sustentavel_onu;
    }

    public void setNivel_acesso(String nivel_acesso) {
        this.nivel_acesso = nivel_acesso;
    }

    public void setLitro_pessoa(int litro_pessoa) {
        this.litro_pessoa = litro_pessoa;
    }
}