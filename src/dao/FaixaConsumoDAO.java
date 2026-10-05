package dao;

import java.sql.*;
import model.FaixaConsumo;

// Classe DAO (Data Access Object) responsável por consultar as faixas tarifárias no banco de dados.
public class FaixaConsumoDAO {

    // Método de Consulta (Read)
    // Busca as regras tarifárias específicas de um estado utilizando sua sigla.
    public FaixaConsumo buscarPorEstado(String sigla) {
        String sql = "SELECT * FROM faixa_consumo WHERE estado_idestado = ?";

        // Bloco try-with-resources
        // Garante o fechamento automático das conexões JDBC (AutoCloseable), prevenindo vazamento de recursos.
        try (Connection connect = Conexao.getConexao();
             PreparedStatement statement = connect.prepareStatement(sql)) {

            statement.setString(1, sigla);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                // Instanciação
                // Cria e retorna um objeto 'FaixaConsumo' mapeando os dados recebidos pelo ResultSet via construtor.
                return new FaixaConsumo(
                        result.getString("estado_idestado"),
                        result.getDouble("fixo"),
                        result.getInt("vol_inc"),
                        result.getDouble("step_1"),
                        result.getInt("faixa_1"),
                        result.getDouble("step_2"),
                        result.getInt("faixa_2"),
                        result.getDouble("step_3"),
                        result.getInt("faixa_3"),
                        result.getDouble("step_4"),
                        result.getInt("faixa_4"),
                        result.getDouble("step_5"),
                        result.getInt("faixa_5"),
                        result.getString("concessionaria")
                );
            }
        } catch (SQLException e) {
            // Tratamento de Exceções
            // Intercepta falhas de sintaxe ou de comunicação com o banco durante a consulta.
            System.out.println("Erro na FaixaConsumoDAO: " + e.getMessage());
        }
        return null;
    }
}