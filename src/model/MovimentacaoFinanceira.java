package model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;

public class MovimentacaoFinanceira {

    private long id;
    private BigDecimal valor = new BigDecimal("0");
    private String tipo;
    private String descricao;
    private LocalDate data_criacao;
    private LocalDate data_modificacao;
    private static long movimentacao_financera_serial = 0;

    MathContext context = new MathContext(2, RoundingMode.FLOOR);

    /* Funções de set */
    public void setValorMovimentacaoFinanceiro(String valor)
    {
        this.valor = new BigDecimal(valor, context);
    }

    public void setTipoMovimentacaoFinanceira(String tipo)
    {
        this.tipo = tipo;
    }

    public void setDescricaoMovimentacaoFinanceira(String descricao)
    {
        this.descricao = descricao;
    }

    public void setDataID()
    {
        this.data_criacao = LocalDate.now();
        this.data_modificacao = LocalDate.now();
        this.id = ++MovimentacaoFinanceira.movimentacao_financera_serial;
    }

    public void setModData()
    {
        this.data_modificacao = LocalDate.now();
    }


    /* Funções de Get */
    public long getIDMovimentacaoFinanceira()
    {
        return this.id;
    }

    public BigDecimal getValorMovimentacaoFinanceiro()
    {
        return this.valor;
    }

    public String getTipoMovimentacaoFinanceira()
    {
        return this.tipo;
    }

    public String getDescricaoMovimentacaoFinanceira()
    {
        return this.descricao;
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
