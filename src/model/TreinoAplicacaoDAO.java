package model;

public class TreinoAplicacaoDAO {

    TreinoAplicacao[] treinoAplicacao = new TreinoAplicacao[100];

    public TreinoAplicacaoDAO()
    {
        
    }

    public StringBuilder createTreinoAplicacao(Treino treino, Exercicio exercicio, ExercicioAplicacao exercicioAplicacao, DivisaoTreino divisaoTreino, DivisaoTreinoMusculo divisaoTreinoMusculo)
    {
        TreinoAplicacao ta = new TreinoAplicacao();

        ta.setTreinoIntoTreinoAplicacao(treino);
        ta.setExercicioIntoTreinoAplicacao(exercicio);
        ta.setExercicioAplicacaoIntoTreinoAplicacao(exercicioAplicacao);
        ta.setDivisaoTreinoIntoTreinoAplicacao(divisaoTreino);
        ta.setDivisaoTreinoMusculoIntoTreinoAplicacao(divisaoTreinoMusculo);
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
                StringBuilder builder = new StringBuilder("\nNão foi possivel adicionar a nova Aplicação de Treino!");
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
