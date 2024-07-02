package model;

import java.time.LocalDate;

public class DivisaoTreino {
    private long id;
    private String nome;
    private String descricao;
    private LocalDate data_criacao;
    private LocalDate data_modificacao;
    private static long serial_divisao_treino = 0;


    /* Funções de Set */
    public void setId(Long id)
    {
        this.id = id;
    }

    public void setNomeDivisaoTreino(String nome)
    {
        this.nome = nome;
    }

    public void setDescricaoDivisaoTreino(String descricao)
    {
        this.descricao = descricao;
    }

    public void setDataID()
    {
        this.data_criacao = LocalDate.now();
        this.data_modificacao = LocalDate.now();
        this.id = ++DivisaoTreino.serial_divisao_treino;
    }

    public void setModData()
    {
        this.data_modificacao = LocalDate.now();
    }
    

    /* Funções de Get */
    public long getIDDivisaoTreino()
    {
        return this.id;
    }

    public String getNomeDivisaoTreino()
    {
        return this.nome;
    }

    public String getDescricaoDivisaoTreino()
    {
        return this.descricao;
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
