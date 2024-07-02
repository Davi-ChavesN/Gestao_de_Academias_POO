/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import data_base_connector.ConnectionFactory;

/**
 *
 * @author Davi Chaves
 *         Luan Sousa
 */
public class AcademiaDAO {
    
    Academia acad = new Academia();
    
    // public AcademiaDAO()
    // {
    //     acad.setNomeAcad("Gym Ionia");
    //     acad.setEnderecoAcad("Ionia");
    //     acad.setDataID();
    //     acad.setCalend(LocalDate.now());
    // }

    public Academia adiciona(Academia elemento) {
        String sql = "insert into academia "
                + "(nome,endereco)" + " values (?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, elemento.getNomeAcad());
            stmt.setString(2, elemento.getEnderecoAcad());
            
            stmt.execute();
            
            System.out.println("Elemento inserido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //na verdade deveria retornar o elemento que foi inserido agora
        return elemento;
    }

    /*  */
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
