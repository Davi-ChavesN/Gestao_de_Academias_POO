package model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PagamentoRecorrente {

    private long id;
    private Pessoa pessoa;
    private LocalDate data;
    private String cartao_credito;
    private BigDecimal valor = new BigDecimal(0);
    private LocalDate data_inicio;
    private int meses_aut;
    private LocalDate data_criacao;
    private LocalDate data_modificacao;
    private static long serial = 0;


    MathContext context = new MathContext(2, RoundingMode.FLOOR);

    /* Funções de Set */
    public void setPessoaIntoPagamentoRecorrente(Pessoa p)
    {
        this.pessoa = p;
    }

    public void setDataPagamentoRecorrente()
    {
        this.data = LocalDate.now();
    }

    public void setCartaoCreditoPagamentoRecorrente(String cartao)
    {
        this.cartao_credito = cartao;
    }

    public void setValorPagamentoRecorrente(String valor)
    {
        this.valor = new BigDecimal(valor, context);
    }

    public void setDataInicioPagamentoRecorrente(String data_inicio)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data_inicio = LocalDate.parse(data_inicio, dtf);
    }

    public void setMesesAutorizadoPagamentoRecorrente(int meses_aut)
    {
        this.meses_aut = meses_aut;
    }

    public void setDataID()
    {
        this.data_criacao = LocalDate.now();
        this.data_modificacao = LocalDate.now();
        this.id = ++PagamentoRecorrente.serial;
    }

    public void setModData()
    {
        this.data_modificacao = LocalDate.now();
    }


    /* Funções de Get */
    public Long getIDPagamentoRecorrente()
    {
        return this.id;
    }

    public Pessoa getPessoaFromPagamentoRecorrente()
    {
        return this.pessoa;
    }

    public LocalDate getDataPagamentoRecorrente()
    {
        return this.data;
    }

    public String getCartaoPagamentoRecorrente()
    {
        return this.cartao_credito;
    }

    public BigDecimal getValorPagamentoRecorrente()
    {
        return this.valor;
    }

    public LocalDate getDataInicioPagamentoRecorrente()
    {
        return this.data_inicio;
    }

    public int getMesesAutPagamentoRecorrente()
    {
        return this.meses_aut;
    }

}
