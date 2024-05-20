package model;

public class DivisaoTreinoDAO {

    DivisaoTreino[] divisaoTreino = new DivisaoTreino[100];

    public DivisaoTreinoDAO()
    {
        DivisaoTreino dt = new DivisaoTreino();
        dt.setNomeDivisaoTreino("ABC");
        dt.setDescricaoDivisaoTreino("ABC 2x e descansa 1x");
        dt.setDataID();
        divisaoTreino[0] = dt;

        dt = new DivisaoTreino();
        dt.setNomeDivisaoTreino("ABC");
        dt.setDescricaoDivisaoTreino("ABC descansa 1x ABC descansa 1x");
        dt.setDataID();
        divisaoTreino[1] = dt;
    }

    public StringBuilder createDivisaoTreino(String nome, String desc)
    {
        DivisaoTreino dt = new DivisaoTreino();
        dt.setNomeDivisaoTreino(nome);
        dt.setDescricaoDivisaoTreino(desc);
        dt.setDataID();

        int i = 0;
        while(divisaoTreino[i] != null && i < divisaoTreino.length-1)
        {
            i++;
        }

        if(i < divisaoTreino.length)
        {
            if(divisaoTreino[i] == null)
            {
                divisaoTreino[i] = dt;
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

    public StringBuilder readDivisaoTreino()
    {
        StringBuilder builder = new StringBuilder();

        for (DivisaoTreino dt : divisaoTreino)
        {
            if(dt != null)
            {
                builder.append("\nID: " + dt.getIDDivisaoTreino());
                builder.append("\nNome: " + dt.getNomeDivisaoTreino());
                builder.append("\nDescrição: " + dt.getDescricaoDivisaoTreino());
                builder.append("\n");
            }    
        }

        return builder;
    }

    public StringBuilder updateDivisaoTreino(Long id, String nome, String desc)
    {
        StringBuilder builder = new StringBuilder("");
        int i = 0;
        while(divisaoTreino[i] != null && divisaoTreino[i].getIDDivisaoTreino() != id)
        {
            i++;
        }

        if(divisaoTreino[i] != null && divisaoTreino[i].getIDDivisaoTreino() == id)
        {
            if(nome.equals(""))
            {
                divisaoTreino[i].setNomeDivisaoTreino(divisaoTreino[i].getNomeDivisaoTreino());
            }
            else
            {
                divisaoTreino[i].setNomeDivisaoTreino(nome);
            }

            if(desc.equals(""))
            {
                divisaoTreino[i].setDescricaoDivisaoTreino(divisaoTreino[i].getDescricaoDivisaoTreino());
            }
            else
            {
                divisaoTreino[i].setDescricaoDivisaoTreino(desc);
            }

            divisaoTreino[i].setModData();

            builder.append("\nDivisão de Treino atualizada com sucesso!");
            return builder;
        }

        builder.append("\nDivisão de Treino não foi atualizada!");
        return builder;
    }

    public StringBuilder deleteDivisaoTreino(Long id)
    {
        StringBuilder builder = new StringBuilder("");
        int i = 0;
        while(divisaoTreino[i] != null && divisaoTreino[i].getIDDivisaoTreino() != id)
        {
            i++;
        }

        if(i < divisaoTreino.length)
        {
            if(divisaoTreino[i].getIDDivisaoTreino() == id)
            {
                divisaoTreino[i] = null;
                builder.append("\nDivisão de Treino deletada com sucesso!");
                return builder;
            }
        }

        builder.append("\nDivisão de Treino não encontrada!");
        return builder;
    }

    public DivisaoTreino getDivisaoTreino(Long id)
    {
        DivisaoTreino dt = new DivisaoTreino();

        int i = 0;
        while(divisaoTreino[i] != null && divisaoTreino[i].getIDDivisaoTreino() != id)
        {
            i++;
        }

        if(divisaoTreino[i] != null)
        {
            dt = divisaoTreino[i];
        }

        return dt;
    }
}
