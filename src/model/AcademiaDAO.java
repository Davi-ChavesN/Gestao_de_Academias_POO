/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author Davi Chaves
 *         Luan Sousa
 */
public class AcademiaDAO {
    
    Academia acad = new Academia();
    
    public AcademiaDAO()
    {
        acad.setNomeAcad("Gym Ionia");
        acad.setEnderecoAcad("Ionia");
        acad.setDataID();
        acad.setCalend(LocalDate.now());
    }

    public StringBuilder editAcademia(int opc, String att)
    {
        StringBuilder builder = new StringBuilder();

        if(opc == 1)
        {
            builder.append("\nNome: " + acad.getNomeAcad());
            builder.append(" - Endereço: " + acad.getEnderecoAcad());

            return builder;
        }
        else if(opc == 2)
        {
            acad.setNomeAcad(att);
            builder.append("\nDados da academia atualizados com sucesso!");
        }
        else if(opc == 3)
        {
            acad.setEnderecoAcad(att);
            builder.append("\nDados da academia atualizados com sucesso!");
        }
        acad.setModData();        

        return builder;
    }

    public void editCalend(int dias)
    {
        acad.setCalend(acad.getCalend().plusDays(dias));
    }

    public LocalDate pegarCalend()
    {
        return acad.getCalend();
    }

    public Academia retornaAcademia()
    {
        return acad;
    }
}
