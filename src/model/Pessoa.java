/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Davi Chaves
 *         Luan Sousa
 */
public class Pessoa {
    private long id;
    private String nome;
    private String sexo;
    private LocalDate nascimento;
    private String login;
    private String senha;
    private String tipo_user;
    private LocalDate data_criacao;
    private LocalDate data_modificacao;
    private static long pessoa_serial = 0;

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", nome=" + nome + ", sexo=" + sexo + ", nascimento=" + nascimento + ", login=" + login + ", tipo_user=" + tipo_user + '}';
    }
    
    
    /* Funções de Set */
    public void setId(Long id)
    {
        this.id = id;
    }

    public void setNomePessoa(String nome_pessoa)
    {
        this.nome = nome_pessoa;
    }
    
    public void setSexo(String sexo_pessoa)
    {
        this.sexo = sexo_pessoa;
    }
    
    public void setNascimento(String nascimento)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.nascimento = LocalDate.parse(nascimento, dtf);
    }
    
    public void setLogin(String login)
    {
        this.login = login;
    }
    
    public void setSenha(String senha)
    {
        this.senha = senha;
    }
    
    public void setTipoUser(int nvl_user)
    {
        if(nvl_user == 1)
        {
            this.tipo_user = "administrador";
        }
        else if(nvl_user == 2)
        {
            this.tipo_user = "instrutor";
        }
        else if(nvl_user == 3)
        {
            this.tipo_user = "aluno";
        }
    }
    
    public void setDataID()
    {
        this.data_criacao = LocalDate.now();
        this.data_modificacao = LocalDate.now();
        this.id = ++Pessoa.pessoa_serial;
    }

    public void setModData()
    {
        this.data_modificacao = LocalDate.now();
    }


    /* Funções de Get */
    public long getID()
    {
        return this.id;
    }

    public String getNome()
    {
        return this.nome;
    }

    public String getSexo()
    {
        return this.sexo;
    }

    public LocalDate getNascimento()
    {
        return this.nascimento;
    }

    public String getLogin()
    {
        return this.login;
    }

    public String getSenha()
    {
        return this.senha;
    }

    public String getTipoUser()
    {
        return this.tipo_user;
    }

    public LocalDate getDataCriacao()
    {
        return this.data_criacao;
    }

    public LocalDate getDataModificacao()
    {
        return this.data_modificacao;
    }
}
