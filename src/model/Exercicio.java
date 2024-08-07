package model;

import java.time.LocalDate;

/**
 *
 * @author Davi Chaves
 *         Luan Sousa
 */
public class Exercicio {
    private long id;
    private String nome;
    private String descricao;
    private String grupo_muscular;
    private LocalDate data_criacao;
    private LocalDate data_modificacao;
    private static long serial_exercicio = 0;


    /* Funções de Set */
    public void setId(Long id)
    {
        this.id = id;
    }

    public void setNomeExercicio(String nome)
    {
        this.nome = nome;
    }

    public void setDescricaoExercicio(String descricao)
    {
        this.descricao = descricao;
    }

    public void setGrupoMuscular(String grupo_muscular)
    {
        this.grupo_muscular = grupo_muscular;
    }

    public void setDataID()
    {
        this.data_criacao = LocalDate.now();
        this.data_modificacao = LocalDate.now();
        this.id = ++Exercicio.serial_exercicio;
    }

    public void setModData()
    {
        this.data_modificacao = LocalDate.now();
    }


    /* Funções de Get */
    public long getIDExercicio()
    {
        return this.id;
    }

    public String getNomeExercicio()
    {
        return this.nome;
    }

    public String getDescricao()
    {
        return this.descricao;
    }

    public String getGrupoMuscular()
    {
        return this.grupo_muscular;
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
