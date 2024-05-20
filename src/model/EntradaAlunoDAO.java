package model;

import java.time.LocalDateTime;

public class EntradaAlunoDAO {

    EntradaAluno[] entradaAluno = new EntradaAluno[100];

    public EntradaAlunoDAO()
    {

    }

    public void createEntradaAluno()
    {
        EntradaAluno entA = new EntradaAluno();
        entA.setDataID();
        entA.setDateTime(LocalDateTime.now());

        int i = 0;
        while(entradaAluno[i] != null && i < entradaAluno.length-1)
        {
            i++;
        }

        if(i < entradaAluno.length)
        {
            if(entradaAluno[i] == null)
            {
                entradaAluno[i] = entA;
            }
        }
    }

    public StringBuilder readEntradaAluno()
    {
        StringBuilder builder = new StringBuilder();

        for (EntradaAluno entA : entradaAluno)
        {
            if(entA != null)
            {
                builder.append("\nID: " + entA.getIDEntradaAluno());
                builder.append("\nData e Horário: " + entA.getDateTimeEntradaAluno());
                builder.append("\n");
            }    
        }

        return builder;
    }
}
