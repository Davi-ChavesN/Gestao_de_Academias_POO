package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EntradaAluno {

    private long id;
    private LocalDateTime date_time;
    private LocalDate data_criacao;
    private LocalDate data_modificacao;
    private static long serial = 0;


    /* Funções de set */
    public void setDateTime(LocalDateTime date_time)
    {
        this.date_time = date_time;
    }

    public void setDataID()
    {
        this.data_criacao = LocalDate.now();
        this.data_modificacao = LocalDate.now();
        this.id = ++EntradaAluno.serial;
    }

    public void setModData()
    {
        this.data_modificacao = LocalDate.now();
    }


    /* Funções de get */
    public long getIDEntradaAluno()
    {
        return this.id;
    }

    public LocalDateTime getDateTimeEntradaAluno()
    {
        return this.date_time;
    }
}
