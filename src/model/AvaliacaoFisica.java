package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AvaliacaoFisica {

    private long id;
    private Pessoa pessoa;
    private LocalDate ultimo_treino;
    private float peso;
    private float altura;
    private float imc;
    private String imc_result;
    private float result_rating;
    private LocalDate data_criacao;
    private LocalDate data_modificacao;
    private static long serial_avaliacao_fisica = 0;


    /* Funções de Set */
    public void setId(Long id)
    {
        this.id = id;
    }

    public void setPessoa(Pessoa p)
    {
        this.pessoa = p;
    }

    public void setUltimoTreino(String data)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.ultimo_treino = LocalDate.parse(data, dtf);
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

    public void setImcResult(String result)
    {
        this.imc_result = result;
    }

    public void setResultRating(float rating)
    {
        this.result_rating = rating;
    }

    public void setDataID()
    {
        this.data_criacao = LocalDate.now();
        this.data_modificacao = LocalDate.now();
        this.id = ++AvaliacaoFisica.serial_avaliacao_fisica;
    }


    /* Funções de Get */
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

    public String getImcResult()
    {
        return this.imc_result;
    }

    public float getResultRating()
    {
        return this.result_rating;
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
