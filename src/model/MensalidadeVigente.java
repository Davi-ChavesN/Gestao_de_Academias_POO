package model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MensalidadeVigente {

    private Long id;
    private BigDecimal valor = new BigDecimal(0);
    private LocalDate inicio;
    private LocalDate termino;
    private LocalDate data_criacao;
    private LocalDate data_modificacao;
    private static long serial = 0;

    MathContext context = new MathContext(2, RoundingMode.FLOOR);

    /* Funções de Set */
    public void setValor(String valor)
    {
        this.valor = new BigDecimal(valor, context);
    }

    public void setInicio(String inicio)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.inicio = LocalDate.parse(inicio, dtf);
    }

    public void setTermino(String termino)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.termino = LocalDate.parse(termino, dtf);
    }

    public void setDataID()
    {
        this.data_criacao = LocalDate.now();
        this.data_modificacao = LocalDate.now();
        this.id = ++MensalidadeVigente.serial;
    }

    public void setModData()
    {
        this.data_modificacao = LocalDate.now();
    }


    /* Funções de Get */
    public Long getIDMensalidadeVigente()
    {
        return this.id;
    }

    public BigDecimal getValorMensalidadeVigente()
    {
        return this.valor;
    }
    
    public LocalDate getInicioMensalidadeVigente()
    {
        return this.inicio;
    }

    public LocalDate getTerminoMensalidadeVigente()
    {
        return this.termino;
    }
}
