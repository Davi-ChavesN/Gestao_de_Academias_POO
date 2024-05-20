package model;

public class ExercicioAplicacaoDAO {

    ExercicioAplicacao[] exercicioAplicacao = new ExercicioAplicacao[100];

    public ExercicioAplicacaoDAO()
    {
        ExercicioAplicacao ea = new ExercicioAplicacao();
        ea.setDescricaoExercicioAplicacao("4x12");
        ea.setDataID();
        exercicioAplicacao[0] = ea;

        ea = new ExercicioAplicacao();
        ea.setDescricaoExercicioAplicacao("4x10");
        ea.setDataID();
        exercicioAplicacao[1] = ea;

        ea = new ExercicioAplicacao();
        ea.setDescricaoExercicioAplicacao("12 reps com rest pause");
        ea.setDataID();
        exercicioAplicacao[2] = ea;

        ea = new ExercicioAplicacao();
        ea.setDescricaoExercicioAplicacao("5x5");
        ea.setDataID();
        exercicioAplicacao[3] = ea;
    }

    public StringBuilder createExercicioAplicacao(String desc)
    {
        ExercicioAplicacao ea = new ExercicioAplicacao();
        ea.setDescricaoExercicioAplicacao(desc);
        ea.setDataID();

        int i = 0;
        while(exercicioAplicacao[i] != null && i < exercicioAplicacao.length-1)
        {
            i++;
        }

        if(i < exercicioAplicacao.length)
        {
            if(exercicioAplicacao[i] == null)
            {
                exercicioAplicacao[i] = ea;
                StringBuilder builder = new StringBuilder("\nAplicação de Exercício adicionada com sucesso!");
                return builder;
            }
            else
            {
                StringBuilder builder = new StringBuilder("\nNão foi possivel adicionar a nova Aplicação de Exercício!");
                return builder;
            }
        }
        StringBuilder builder = new StringBuilder("\nDeu ruim");
        return builder;
    }

    public StringBuilder readExercicioAplicacao()
    {
        StringBuilder builder = new StringBuilder();

        for (ExercicioAplicacao ea : exercicioAplicacao)
        {
            if(ea != null)
            {
                builder.append("\nID: " + ea.getIDExercicioAplicacao());
                builder.append(" - Descrição: " + ea.getDescricaoExercicioAplicacao());
                builder.append("\n");
            }    
        }

        return builder;
    }

    public StringBuilder updateExercicioAplicacao(Long id, String desc)
    {
        StringBuilder builder = new StringBuilder("");
        int i = 0;
        while(exercicioAplicacao[i] != null && exercicioAplicacao[i].getIDExercicioAplicacao() != id)
        {
            i++;
        }

        if(exercicioAplicacao[i] != null && exercicioAplicacao[i].getIDExercicioAplicacao() == id)
        {
            if(!desc.equals(""))
            {
                exercicioAplicacao[i].setDescricaoExercicioAplicacao(desc);
            }

            exercicioAplicacao[i].setModData();

            builder.append("\nAplicação de Exercicio atualizada com sucesso!");
            return builder;
        }

        builder.append("\nAplicação de Exercicio não foi atualizada!");
        return builder;
    }

    public StringBuilder deleteExercicioAplicacao(Long id)
    {
        StringBuilder builder = new StringBuilder("");
        int i = 0;
        while(exercicioAplicacao[i] != null && exercicioAplicacao[i].getIDExercicioAplicacao() != id)
        {
            i++;
        }

        if(i < exercicioAplicacao.length)
        {
            if(exercicioAplicacao[i].getIDExercicioAplicacao() == id)
            {
                exercicioAplicacao[i] = null;
                builder.append("\nAplicação de Exercicio deletada com sucesso!");
                return builder;
            }
        }

        builder.append("\nAplicação de Exercicio não encontrada!");
        return builder;
    }

    public ExercicioAplicacao getExercicioAplicacao(Long id)
    {
        ExercicioAplicacao ea = new ExercicioAplicacao();

        int i = 0;
        while(exercicioAplicacao[i] != null && exercicioAplicacao[i].getIDExercicioAplicacao() != id)
        {
            i++;
        }

        if(exercicioAplicacao[i] != null)
        {
            ea = exercicioAplicacao[i];
        }

        return ea;
    }
}
