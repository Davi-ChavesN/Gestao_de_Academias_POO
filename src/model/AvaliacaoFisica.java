package model;

import java.time.LocalDate;

public class AvaliacaoFisica {

    private long id;
    private Pessoa pessoa;
    private LocalDate ultimo_treino;
    private float peso;
    private float altura;
    private float imc;
    private float result_rating;
    private LocalDate data_criacao;
    private LocalDate data_modificacao;
    private static long serial = 0;


    public void setPessoa(Pessoa p)
    {
        this.pessoa = p;
    }

    public void setUltimoTreino(LocalDate data)
    {
        this.ultimo_treino = data;
    }

    public void setPeso(float peso)
    {
        this.peso = peso;
    }

    public void setAltura(float altura)
    {
        this.altura = altura;
    }

    public void setIMC(float imc)
    {
        this.imc = imc;
    }

    public void setResultRating(float rating)
    {
        this.result_rating = rating;
    }

    public void setDataID()
    {
        this.data_criacao = LocalDate.now();
        this.data_modificacao = LocalDate.now();
        this.id = ++AvaliacaoFisica.serial;
    }


    public long getID()
    {
        return this.id;
    }

    public Pessoa getPessoa()
    {
        return this.pessoa;
    }

    public LocalDate getUltimoTreino()
    {
        return this.ultimo_treino;
    }

    public float getPeso()
    {
        return this.peso;
    }

    public float getAltura()
    {
        return this.altura;
    }

    public float getIMC()
    {
        return this.imc;
    }

    public float getResultRating()
    {
        return this.result_rating;
    }
}
