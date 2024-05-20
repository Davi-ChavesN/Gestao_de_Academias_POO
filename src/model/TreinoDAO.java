package model;

public class TreinoDAO {

    Treino[] treino = new Treino[100];

    public TreinoDAO(DivisaoTreino dt)
    {
        Treino t = new Treino();
        t.setObjetivoTreino("Hipertrofia");
        t.setDataInicioTreino("01/01/2024");
        t.setDataTerminoTreino("31/12/2024");
        t.setDivisaoTreinoIntoTreino(dt);
        t.setDataID();
        treino[0] = t;
    }

    public StringBuilder createTreino(DivisaoTreino dt, String objetivo, String dt_inicio, String dt_termino)
    {
        Treino t = new Treino();

        t.setDataInicioTreino(dt_inicio);
        t.setDataTerminoTreino(dt_termino);
        t.setObjetivoTreino(objetivo);
        t.setDivisaoTreinoIntoTreino(dt);
        t.setDataID();

        int i = 0;
        while(treino[i] != null && i < treino.length-1)
        {
            i++;
        }

        if(i < treino.length)
        {
            if(treino[i] == null)
            {
                treino[i] = t;
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

    public StringBuilder readTreino()
    {
        StringBuilder builder = new StringBuilder();

        for (Treino t : treino)
        {
            if(t != null)
            {
                builder.append("\nID: " + t.getIDTreino());
                builder.append("\nObjetivo: " + t.getObjetivoTreino());
                builder.append("\nData de Inicio: " + t.getDataInicioTreino());
                builder.append("\nData de Termino: " + t.getDataTerminoTreino());
                builder.append("\n");
            }    
        }

        return builder;
    }

    public StringBuilder updateTreino(Long id, String objetivo, String dt_inicio, String dt_termino)
    {
        StringBuilder builder = new StringBuilder("");
        int i = 0;
        while(treino[i] != null && treino[i].getIDTreino() != id)
        {
            i++;
        }

        if(treino[i] != null && treino[i].getIDTreino() == id)
        {
            if(!objetivo.equals(""))
            {
                treino[i].setObjetivoTreino(objetivo);
            }

            if(!dt_inicio.equals(""))
            {
                treino[i].setDataInicioTreino(dt_inicio);
            }

            if(!dt_termino.equals(""))
            {
                treino[i].setDataTerminoTreino(dt_termino);
            }

            treino[i].setModData();

            builder.append("\nTreino atualizada com sucesso!");
            return builder;
        }

        builder.append("\nTreino não foi atualizada!");
        return builder;
    }

    public StringBuilder deleteTreino(Long id)
    {
        StringBuilder builder = new StringBuilder("");
        int i = 0;
        while(treino[i] != null && treino[i].getIDTreino() != id)
        {
            i++;
        }

        if(i < treino.length)
        {
            if(treino[i].getIDTreino() == id)
            {
                treino[i] = null;
                builder.append("\nTreino deletada com sucesso!");
                return builder;
            }
        }

        builder.append("\nTreino não encontrada!");
        return builder;
    }

    public Treino getTreino(Long id)
    {
        Treino t = new Treino();

        int i = 0;
        while(treino[i] != null && treino[i].getIDTreino() != id)
        {
            i++;
        }

        if(treino[i] != null)
        {
            t = treino[i];
        }

        return t;
    }


}
