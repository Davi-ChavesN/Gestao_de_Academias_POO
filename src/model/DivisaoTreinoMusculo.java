package model;

import java.time.LocalDate;

public class DivisaoTreinoMusculo {
    private long id;
    private String descricao;
    private DivisaoTreino divisao_treino;
    private LocalDate data_criacao;
    private LocalDate data_modificacao;
    private static long serial = 0;


    /* Funções de Set */
    public void setDescricaoDivisaoTreinoMusculo(String descricao)
    {
        this.descricao = descricao;
    }

    public void setDivisaoTreinoIntoDivisaoTreinoMusculo(DivisaoTreino divisao_treino)
    {
        this.divisao_treino = divisao_treino;
    }

    public void setDataID()
    {
        this.data_criacao = LocalDate.now();
        this.data_modificacao = LocalDate.now();
        this.id = ++DivisaoTreinoMusculo.serial;
    }

    public void setModData()
    {
        this.data_modificacao = LocalDate.now();
    }


    /* Funções de Get */
    public long getIDDivisaoTreinoMusculo()
    {
        return this.id;
    }

    public String getDescricaoDivisaoTreinoMusculo()
    {
        return this.descricao;
    }

    public DivisaoTreino getDivisaoTreinoFromDivisaoTreinoMusculo()
    {
        return this.divisao_treino;
    }
}
