package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Treino {
    private long id;
    private String objetivo;
    private LocalDate data_inicio;
    private LocalDate data_termino;
    private DivisaoTreino divisao_treino;
    private LocalDate data_criacao;
    private LocalDate data_modificacao;
    private static long serial = 0;


    /* Funções de Set */
    public void setObjetivoTreino(String objetivo)
    {
        this.objetivo = objetivo;
    }

    public void setDataInicioTreino(String data_inicio)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data_inicio = LocalDate.parse(data_inicio, dtf);
    }

    public void setDataTerminoTreino(String data_termino)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data_termino = LocalDate.parse(data_termino, dtf);
    }

    public void setDivisaoTreinoIntoTreino(DivisaoTreino divisao_treino)
    {
        this.divisao_treino = divisao_treino;
    }

    public void setDataID()
    {
        this.data_criacao = LocalDate.now();
        this.data_modificacao = LocalDate.now();
        this.id = ++Treino.serial;
    }

    public void setModData()
    {
        this.data_modificacao = LocalDate.now();
    }


    /* Funções de Get */
    public long getIDTreino()
    {
        return this.id;
    }

    public String getObjetivoTreino()
    {
        return this.objetivo;
    }

    public LocalDate getDataInicioTreino()
    {
        return this.data_inicio;
    }

    public LocalDate getDataTerminoTreino()
    {
        return this.data_termino;
    }
    
    public DivisaoTreino getDivisaoTreinoFromTreino()
    {
        return this.divisao_treino;
    }
}
