package model;

public class DivisaoTreinoMusculoDAO {

    DivisaoTreinoMusculo[] divisaoTreinoMusculo = new DivisaoTreinoMusculo[100];

    public DivisaoTreinoMusculoDAO(DivisaoTreino dt)
    {
        DivisaoTreinoMusculo dtm = new DivisaoTreinoMusculo();
        dtm.setDivisaoTreinoIntoDivisaoTreinoMusculo(dt);
        dtm.setDescricaoDivisaoTreinoMusculo("A = Peito, Ombro, Triceps");
        dtm.setDataID();
        divisaoTreinoMusculo[0] = dtm;

        dtm = new DivisaoTreinoMusculo();
        dtm.setDivisaoTreinoIntoDivisaoTreinoMusculo(dt);
        dtm.setDescricaoDivisaoTreinoMusculo("B = Costa, Bíceps");
        dtm.setDataID();
        divisaoTreinoMusculo[1] = dtm;

        dtm = new DivisaoTreinoMusculo();
        dtm.setDivisaoTreinoIntoDivisaoTreinoMusculo(dt);
        dtm.setDescricaoDivisaoTreinoMusculo("C = Perna");
        dtm.setDataID();
        divisaoTreinoMusculo[2] = dtm;
    }

    public StringBuilder createDivisaoTreinoMusculo(DivisaoTreino dt, String desc)
    {
        DivisaoTreinoMusculo dtm = new DivisaoTreinoMusculo();
        dtm.setDescricaoDivisaoTreinoMusculo(desc);
        dtm.setDivisaoTreinoIntoDivisaoTreinoMusculo(dt);
        dtm.setDataID();

        int i = 0;
        while(divisaoTreinoMusculo[i] != null && i < divisaoTreinoMusculo.length-1)
        {
            i++;
        }

        if(i < divisaoTreinoMusculo.length)
        {
            if(divisaoTreinoMusculo[i] == null)
            {
                divisaoTreinoMusculo[i] = dtm;
                StringBuilder builder = new StringBuilder("\nDivisão de Treino adicionada com sucesso!");
                return builder;
            }
            else
            {
                StringBuilder builder = new StringBuilder("\nNão foi possivel adicionar a nova Divisão de Treino!");
                return builder;
            }
        }
        StringBuilder builder = new StringBuilder("\nDeu ruim");
        return builder;
    }

    public StringBuilder readDivisaoTreinoMusculo()
    {
        StringBuilder builder = new StringBuilder();

        for (DivisaoTreinoMusculo dtm : divisaoTreinoMusculo)
        {
            if(dtm != null)
            {
                builder.append("\nTreino " + dtm.getDivisaoTreinoFromDivisaoTreinoMusculo().getNomeDivisaoTreino());
                builder.append(" (id = " + dtm.getDivisaoTreinoFromDivisaoTreinoMusculo().getIDDivisaoTreino() + ")");
                builder.append(" - " + dtm.getDescricaoDivisaoTreinoMusculo());
                builder.append(" (id = " + dtm.getIDDivisaoTreinoMusculo() + ")");
            }    
        }

        return builder;
    }

    public StringBuilder updateDivisaoTreinoMusculo(Long id, String desc)
    {
        StringBuilder builder = new StringBuilder("");
        int i = 0;
        while(divisaoTreinoMusculo[i] != null && divisaoTreinoMusculo[i].getIDDivisaoTreinoMusculo() != id)
        {
            i++;
        }

        if(divisaoTreinoMusculo[i] != null && divisaoTreinoMusculo[i].getIDDivisaoTreinoMusculo() == id)
        {
            if(desc.equals(""))
            {
                divisaoTreinoMusculo[i].setDescricaoDivisaoTreinoMusculo(divisaoTreinoMusculo[i].getDescricaoDivisaoTreinoMusculo());
            }
            else
            {
                divisaoTreinoMusculo[i].setDescricaoDivisaoTreinoMusculo(desc);
            }

            divisaoTreinoMusculo[i].setModData();

            builder.append("\nDivisão de Treino-Musculo atualizada com sucesso!");
            return builder;
        }

        builder.append("\nDivisão de Treino-Musculo não foi atualizada!");
        return builder;
    }

    public StringBuilder deleteDivisaoTreinoMusculo(Long id)
    {
        StringBuilder builder = new StringBuilder("");
        int i = 0;
        while(divisaoTreinoMusculo[i] != null && divisaoTreinoMusculo[i].getIDDivisaoTreinoMusculo() != id)
        {
            i++;
        }

        if(i < divisaoTreinoMusculo.length)
        {
            if(divisaoTreinoMusculo[i].getIDDivisaoTreinoMusculo() == id)
            {
                divisaoTreinoMusculo[i] = null;
                builder.append("\nDivisão de Treino-Musculo deletada com sucesso!");
                return builder;
            }
        }

        builder.append("\nDivisão de Treino-Musculo não encontrada!");
        return builder;
    }

    public DivisaoTreinoMusculo getDivisaoTreinoMusculo(Long id)
    {
        DivisaoTreinoMusculo dtm = new DivisaoTreinoMusculo();

        int i = 0;
        while(divisaoTreinoMusculo[i] != null && divisaoTreinoMusculo[i].getIDDivisaoTreinoMusculo() != id)
        {
            i++;
        }

        if(divisaoTreinoMusculo[i] != null)
        {
            dtm = divisaoTreinoMusculo[i];
        }

        return dtm;
    }
}
