package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EntradaAluno {

    private long id;
    private LocalDateTime date_time;
    private Pessoa pessoa;
    private LocalDate data_criacao;
    private LocalDate data_modificacao;
    private static long serial = 0;


    /* Funções de set */
    public void setId(Long id)
    {
        this.id = id;
    }

    public void setDateTime(LocalDateTime date_time)
    {
        this.date_time = date_time;
    }
    public void setDateTimeFromBanco(String data)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        this.date_time = LocalDateTime.parse(data, dtf);
    }

    public void setPessoaIntoEntradaAluno(Pessoa p)
    {
        this.pessoa = p;
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        this.date_time.format(dtf);
        
        return this.date_time;
    }

    public Pessoa getPessoaFromEntradaAluno()
    {
        return this.pessoa;
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
