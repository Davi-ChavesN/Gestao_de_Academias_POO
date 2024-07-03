package model;

import java.util.List;

public class TreinoAplicacaoDAO {

    TreinoAplicacao[] treinoAplicacao = new TreinoAplicacao[100];

    public TreinoAplicacaoDAO()
    {
        
    }

    public StringBuilder createTreinoAplicacao(Treino treino, List<Exercicio> exercicio, List<ExercicioAplicacao> exercicioAplicacao, DivisaoTreino divisaoTreino, List<DivisaoTreinoMusculo> divisaoTreinoMusculo)
    {
        TreinoAplicacao ta = new TreinoAplicacao();

        ta.setTreinoIntoTreinoAplicacao(treino);
        // Aqui assumimos que a lista de exercícios, exercício_aplicacao e divisao_treino_musculo têm apenas um item. 
        // Caso contrário, será necessário um tratamento adequado.
        if (exercicio.size() == 1) {
            ta.setExercicioIntoTreinoAplicacao(exercicio.get(0));
        } else {
            // Lidar com a situação onde há mais de um exercício
        }

        if (exercicioAplicacao.size() == 1) {
            ta.setExercicioAplicacaoIntoTreinoAplicacao(exercicioAplicacao.get(0));
        } else {
            // Lidar com a situação onde há mais de uma aplicação de exercício
        }

        ta.setDivisaoTreinoIntoTreinoAplicacao(divisaoTreino);

        if (divisaoTreinoMusculo.size() == 1) {
            ta.setDivisaoTreinoMusculoIntoTreinoAplicacao(divisaoTreinoMusculo.get(0));
        } else {
            // Lidar com a situação onde há mais de uma divisão de treino-musculo
        }

        ta.setDataID();

        int i = 0;
        while(treinoAplicacao[i] != null && i < treinoAplicacao.length-1)
        {
            i++;
        }

        if(i < treinoAplicacao.length)
        {
            if(treinoAplicacao[i] == null)
            {
                treinoAplicacao[i] = ta;
                StringBuilder builder = new StringBuilder("\nAplicação de Treino adicionada com sucesso!");
                return builder;
            }
            else
            {
                StringBuilder builder = new StringBuilder("\nNão foi possível adicionar a nova Aplicação de Treino!");
                return builder;
            }
        }
        StringBuilder builder = new StringBuilder("\nDeu ruim");
        return builder;
    }

    public StringBuilder readTreinoAplicacao()
    {
        StringBuilder builder = new StringBuilder();

        for (TreinoAplicacao ta : treinoAplicacao)
        {
            if(ta != null)
            {
                builder.append("\nDivisão de Treino: " + ta.getDivisaoTreinoFromTreinoAplicacao().getNomeDivisaoTreino());
                builder.append("\nInicio: " + ta.getTreinoFromTreinoAplicacao().getDataInicioTreino());
                builder.append(" Termino: " + ta.getTreinoFromTreinoAplicacao().getDataTerminoTreino());
                
                builder.append(ta.getDivisaoTreinoMusculoFromTreinoAplicacao().getDescricaoDivisaoTreinoMusculo());
                builder.append("\n");
            }    
        }

        return builder;
    }

    public void updateTreinoAplicacao()
    {

    }

    public void deleteTreinoAplicacao()
    {

    }
}
