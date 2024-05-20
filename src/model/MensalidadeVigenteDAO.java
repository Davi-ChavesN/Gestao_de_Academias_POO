package model;

public class MensalidadeVigenteDAO {

    MensalidadeVigente[] mensalidadeVigente = new MensalidadeVigente[100];

    public MensalidadeVigenteDAO()
    {
        MensalidadeVigente mv = new MensalidadeVigente();
        mv.setValor("90.00");
        mv.setInicio("01/01/2024");
        mv.setInicio("31/12/2024");
        mv.setDataID();
        mensalidadeVigente[0] = mv;

        mv = new MensalidadeVigente();
        mv.setValor("140.00");
        mv.setInicio("01/01/2024");
        mv.setInicio("31/12/2024");
        mv.setDataID();
        mensalidadeVigente[1] = mv;

        mv = new MensalidadeVigente();
        mv.setValor("200.00");
        mv.setInicio("01/01/2024");
        mv.setInicio("31/12/2024");
        mv.setDataID();
        mensalidadeVigente[2] = mv;
    }

    public StringBuilder createMensalidadeVigente(String valor, String inicio, String termino)
    {
        MensalidadeVigente mv = new MensalidadeVigente();
        mv.setValor(valor);
        mv.setInicio(inicio);
        mv.setTermino(termino);
        mv.setDataID();

        int i = 0;
        while(mensalidadeVigente[i] != null && i < mensalidadeVigente.length-1)
        {
            i++;
        }

        if(i < mensalidadeVigente.length)
        {
            if(mensalidadeVigente[i] == null)
            {
                mensalidadeVigente[i] = mv;
                StringBuilder builder = new StringBuilder("\nMensalidade Vigente adicionada com sucesso!");
                return builder;
            }
            else
            {
                StringBuilder builder = new StringBuilder("\nNão foi possivel adicionar a nova Mensalidade Vigente!");
                return builder;
            }
        }
        StringBuilder builder = new StringBuilder("\nDeu ruim");
        return builder;
    }

    public StringBuilder readMensalidadeVigente()
    {
        StringBuilder builder = new StringBuilder();

        for (MensalidadeVigente mv : mensalidadeVigente)
        {
            if(mv != null)
            {
                builder.append("\nID: " + mv.getIDMensalidadeVigente());
                builder.append("\nValor: " + mv.getValorMensalidadeVigente());
                builder.append("\nData de Inicio: " + mv.getInicioMensalidadeVigente());
                builder.append("\nData de Termino: " + mv.getTerminoMensalidadeVigente());
                builder.append("\n");
            }    
        }

        return builder;
    }

    public StringBuilder updateMensalidadeVigente(Long id, String valor, String inicio, String termino)
    {
        StringBuilder builder = new StringBuilder("");
        int i = 0;
        while(mensalidadeVigente[i] != null && mensalidadeVigente[i].getIDMensalidadeVigente() != id)
        {
            i++;
        }

        if(mensalidadeVigente[i] != null && mensalidadeVigente[i].getIDMensalidadeVigente() == id)
        {
            if(!valor.equals(""))
            {
                mensalidadeVigente[i].setValor(valor);;
            }

            if(!inicio.equals(""))
            {
                mensalidadeVigente[i].setInicio(inicio);
            }

            if(!termino.equals(""))
            {
                mensalidadeVigente[i].setTermino(termino);
            }

            mensalidadeVigente[i].setModData();

            builder.append("\nMensalidade Vigente atualizada com sucesso!");
            return builder;
        }

        builder.append("\nMensalidade Vigente não foi atualizada!");
        return builder;
    }

    public StringBuilder deleteMensalidadeVigente(Long id)
    {
        StringBuilder builder = new StringBuilder("");
        int i = 0;
        while(mensalidadeVigente[i] != null && mensalidadeVigente[i].getIDMensalidadeVigente() != id)
        {
            i++;
        }

        if(i < mensalidadeVigente.length)
        {
            if(mensalidadeVigente[i].getIDMensalidadeVigente() == id)
            {
                mensalidadeVigente[i] = null;
                builder.append("\nMensalidade Vigente deletada com sucesso!");
                return builder;
            }
        }

        builder.append("\nMensalidade Vigente não encontrada!");
        return builder;
    }

    public MensalidadeVigente getMensalidadeVigente(Long id)
    {
        MensalidadeVigente mv = new MensalidadeVigente();

        int i = 0;
        while(mensalidadeVigente[i] != null && mensalidadeVigente[i].getIDMensalidadeVigente() != id)
        {
            i++;
        }

        if(mensalidadeVigente[i] != null)
        {
            mv = mensalidadeVigente[i];
        }

        return mv;
    }
}
