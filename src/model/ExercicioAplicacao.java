package model;

import java.time.LocalDate;

public class ExercicioAplicacao {
    private long id;
    private String descricao;
    private LocalDate data_criacao;
    private LocalDate data_modificacao;
    private static long serialExercicioAplicacao = 0;


    /* Funções de Set */
    public void setId(Long id)
    {
        this.id = id;
    }

    public void setDescricaoExercicioAplicacao(String descricao)
    {
        this.descricao = descricao;
    }

    public void setDataID()
    {
        this.data_criacao = LocalDate.now();
        this.data_modificacao = LocalDate.now();
        this.id = ++ExercicioAplicacao.serialExercicioAplicacao;
    }

    public void setModData()
    {
        this.data_modificacao = LocalDate.now();
    }


    /* Funções de Get */
    public long getIDExercicioAplicacao()
    {
        return this.id;
    }

    public String getDescricaoExercicioAplicacao()
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
