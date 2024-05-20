package model;

import java.time.LocalDate;

public class ExercicioAplicacao {
    private long id;
    private String descricao;
    private LocalDate data_criacao;
    private LocalDate data_modificacao;
    private static long serial = 0;


    public void setDescricaoExercicioAplicacao(String descricao)
    {
        this.descricao = descricao;
    }

    public void setDataID()
    {
        this.data_criacao = LocalDate.now();
        this.data_modificacao = LocalDate.now();
        this.id = ++ExercicioAplicacao.serial;
    }

    public void setModData()
    {
        this.data_modificacao = LocalDate.now();
    }


    public long getIDExercicioAplicacao()
    {
        return this.id;
    }

    public String getDescricaoExercicioAplicacao()
    {
        return this.descricao;
    }
}
