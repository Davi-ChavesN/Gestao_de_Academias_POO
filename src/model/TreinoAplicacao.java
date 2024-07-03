package model;

import java.time.LocalDate;

public class TreinoAplicacao {
    private long id;
    private Treino treino;
    private Exercicio exercicio;
    private ExercicioAplicacao exercicio_aplicacao;
    private DivisaoTreino divisao_treino;
    private DivisaoTreinoMusculo divisao_treino_musculo;
    private LocalDate data_criacao;
    private LocalDate data_modificacao;
    private static long serial = 0;


    /* Funções de Set */
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public void setTreinoIntoTreinoAplicacao(Treino treino)
    {
        this.treino = treino;
    }

    public void setExercicioIntoTreinoAplicacao(Exercicio exercicio)
    {
        this.exercicio = exercicio;
    }

    public void setExercicioAplicacaoIntoTreinoAplicacao(ExercicioAplicacao exercicio_aplicacao)
    {
        this.exercicio_aplicacao = exercicio_aplicacao;
    }

    public void setDivisaoTreinoIntoTreinoAplicacao(DivisaoTreino divisao_treino)
    {
        this.divisao_treino = divisao_treino;
    }

    public void setDivisaoTreinoMusculoIntoTreinoAplicacao(DivisaoTreinoMusculo divisao_treino_musculo)
    {
        this.divisao_treino_musculo = divisao_treino_musculo;
    }

    public void setDataID()
    {
        this.data_criacao = LocalDate.now();
        this.data_modificacao = LocalDate.now();
        this.id = ++TreinoAplicacao.serial;
    }

    public void setModData()
    {
        this.data_modificacao = LocalDate.now();
    }


    /* Funções de Get */
    public long getIDTreinoAplicacao()
    {
        return this.id;
    }

    public Treino getTreinoFromTreinoAplicacao()
    {
        return this.treino;
    }

    public Exercicio getExercicioFromTreinoAplicacao()
    {
        return this.exercicio;
    }

    public ExercicioAplicacao getExercicioAplicacaoFromTreinoAplicacao()
    {
        return this.exercicio_aplicacao;
    }

    public DivisaoTreino getDivisaoTreinoFromTreinoAplicacao()
    {
        return this.divisao_treino;
    }

    public DivisaoTreinoMusculo getDivisaoTreinoMusculoFromTreinoAplicacao()
    {
        return this.divisao_treino_musculo;
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
