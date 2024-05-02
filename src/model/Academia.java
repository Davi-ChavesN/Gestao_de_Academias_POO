/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Davi Chaves
 *         Luan Sousa
 */
public class Academia {
    private long id;
    private String nome;
    private String endereco;
    private LocalDate data_criacao;
    private LocalDate data_modificacao;
    private static long serial = 0;

    @Override
    public String toString() {
        return "Academia{" + "id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", data_criacao=" + data_criacao + ", data_modificacao=" + data_modificacao + '}';
    }
    
    
    public void setNomeAcad(String nome_acad)
    {
        this.nome = nome_acad;
    }
    
    public void setEnderecoAcad(String endereco)
    {
        this.endereco = endereco;
    }
    
    public void setDataID()
    {
        this.data_criacao = LocalDate.now();
        this.data_modificacao = LocalDate.now();
        this.id = ++Academia.serial;
    }

    public void setModData()
    {
        this.data_modificacao = LocalDate.now();
    }
    

    public String getNomeAcad()
    {
        return this.nome;
    }

    public String getEnderecoAcad()
    {
        return this.endereco;
    }
}
