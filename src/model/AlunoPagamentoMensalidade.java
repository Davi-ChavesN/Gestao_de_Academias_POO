package model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AlunoPagamentoMensalidade {

    private Long id;
    private MensalidadeVigente mensalidadeVigente;
    private LocalDate data_vencimento;
    private LocalDate data_pagamento;
    private BigDecimal valor_pago = new BigDecimal(0);
    private LocalDate data;
    private Pessoa pessoa;
    private String modalidade;
    private LocalDate data_criacao;
    private LocalDate data_modificacao;
    private static long serial = 0;

    MathContext context = new MathContext(2, RoundingMode.FLOOR);

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }


    /* Funções de Set */
    public void setMensalidadeVigenteIntoAlunoPagamentoMensalidade(MensalidadeVigente mv)
    {
        this.mensalidadeVigente = mv;
    }

    public void setDataVencimento(String data_vencimento)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data_vencimento = LocalDate.parse(data_vencimento, dtf);
    }

    public void setDataPagamento(String data_pagamento)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data_vencimento = LocalDate.parse(data_pagamento, dtf);
    }

    public void setData()
    {
        this.data = LocalDate.now();
    }

    public void setPessoaIntoAlunoPagamentoMensalidade(Pessoa p)
    {
        this.pessoa = p;
    }

    public void setModalidade(int modalidade)
    {
        if(modalidade == 1)
        {
            this.modalidade = "dinheiro";
        }
        else if(modalidade == 2)
        {
            this.modalidade = "pix";
        }
        else if(modalidade == 3)
        {
            this.modalidade = "débito automático";
        }
        else if(modalidade == 4)
        {
            this.modalidade = "pagamento recorrente";
        }
    }

    public void setValorPago(String valor)
    {
        this.valor_pago = new BigDecimal(valor, context);
    }

    public void setDataID()
    {
        this.data_criacao = LocalDate.now();
        this.data_modificacao = LocalDate.now();
        this.id = ++AlunoPagamentoMensalidade.serial;
    }

    public void setModData()
    {
        this.data_modificacao = LocalDate.now();
    }


    /* Funções de Get */
    public Long getIDAlunoPagamentoMensalidade()
    {
        return this.id;
    }

    public MensalidadeVigente getMensalidadeVigenteFromAlunoPagamentoMensalidade()
    {
        return this.mensalidadeVigente;
    }

    public LocalDate getDataVencimentoAlunoPagamentoMensalidade()
    {
        return this.data_vencimento;
    }

    public LocalDate getDataPagamentoAlunoPagamentoMensalidade()
    {
        return this.data_pagamento;
    }

    public BigDecimal getValorPagoAlunoPagamentoMensalidade()
    {
        return this.valor_pago;
    }

    public LocalDate getDataAlunoPagamentoMensalidade()
    {
        return this.data;
    }

    public Pessoa getPessoaFromAlunoPagamentoMensalidade()
    {
        return this.pessoa;
    }

    public String getModalidadeAlunoPagamentoMensalidade()
    {
        return this.modalidade;
    }

}
