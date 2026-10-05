package calc;

import model.FaixaConsumo;
import model.Estado;
import model.ReferenciaSustentavelOnu;
import dao.ReferenciaSustentavelOnuDAO;
import java.util.List;

// Classe de regra de negócio responsável por centralizar a lógica matemática e as métricas do sistema.
public class CalcAgua {

    // Modularização e Passagem de Parâmetros (Objetos)
    // Recebe objetos das classes FaixaConsumo e Estado para calcular o valor da tarifa de forma progressiva.
    public double Calculo(double consumoMensal, FaixaConsumo faixas, Estado estado){
        double valorAgua = faixas.getFixo();
        double restante = consumoMensal - faixas.getVolInc();

        if (restante > 0) {
            // Utilização de arrays (vetores) para organizar os degraus e limites das faixas tarifárias.
            double[] steps = {faixas.getStep1(), faixas.getStep2(), faixas.getStep3(), faixas.getStep4(), faixas.getStep5()};
            int[] limites = {faixas.getFaixa1(), faixas.getFaixa2(), faixas.getFaixa3(), faixas.getFaixa4(), faixas.getFaixa5()};

            // Algoritmo escalonado para redistribuição volumétrica baseada na regra de negócio.
            for (int i = 0; i < steps.length; i++) {
                if (restante <= 0) break;

                double gastoF = Math.min(restante, limites[i]);
                valorAgua += gastoF * steps[i];
                restante -= gastoF;
            }
        }
        
        // Retorna o valor final acrescido do coeficiente de esgoto específico do estado.
        return valorAgua * (1 + estado.getCoef_Esg());
    }

    // Modularização
    // Método isolado que converte metros cúbicos brutos em litros diários por habitante (per capita).
    public double calcularPerCapita(double consumoM3, int residentes) {
        double consumoLitros = consumoM3 * 1000;
        return consumoLitros / (residentes * 30);
    }

    // Instanciação e Integração (Avaliação ODS 6)
    // Verifica o consumo per capita face às diretrizes da ONU armazenadas na base de dados.
    public String ConsumoONU(double perCapita) {
        
        // Instanciação da classe DAO para acesso direto aos dados.
        ReferenciaSustentavelOnuDAO onuDAO = new ReferenciaSustentavelOnuDAO();
        
        // Uso de Coleções (List) para receber e iterar os dados da base.
        List<ReferenciaSustentavelOnu> diretrizes = onuDAO.listarTodas();

        int metaSustentavel = 110;
        
        // Laço (foreach) iterando sobre a coleção de objetos.
        for (ReferenciaSustentavelOnu ref : diretrizes) {
            if (ref.getNivel_acesso().equalsIgnoreCase("sustentavel") || ref.getNivel_acesso().contains("ideal")) {
                metaSustentavel = ref.getLitro_pessoa();
                break;
            }
        }

        // Estruturas condicionais avaliando o limiar ecológico individual do utilizador.
        if (perCapita < (metaSustentavel * 0.3)) {
            return "Muito abaixo da média recomendada. Atenção: Caso sua residência sofra com a falta de água crônica ou ausência de rede encanada, considere formalizar um pedido de saneamento básico junto aos órgãos governamentais de sua região.";
        } else if (perCapita <= (metaSustentavel * 0.6)) {
            return "Abaixo da média recomendada. Certifique-se de que o consumo atende às necessidades básicas de higiene e saúde de todos os residentes.";
        } else if (perCapita <= metaSustentavel) {
            return "Ideal (Base ONU)";
        } else if (perCapita > (metaSustentavel * 1.5)) {
            return "Muito acima da média recomendada. Recomendações: Reduza o tempo no banho, feche a torneira ao escovar os dentes e reutilize a água da máquina de lavar.";
        } else {
            return "Acima da média recomendada";
        }
    }
}