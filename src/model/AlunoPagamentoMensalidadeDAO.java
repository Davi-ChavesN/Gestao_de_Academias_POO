package model;

public class AlunoPagamentoMensalidadeDAO {

    AlunoPagamentoMensalidade[] alunoPagamentoMensalidade = new AlunoPagamentoMensalidade[100];

    public AlunoPagamentoMensalidadeDAO()
    {

    }


    public StringBuilder createAlunoPagamentoMensalidade(MensalidadeVigente mv, String data_vencimento, String data_pagamento, String valor_pago, Pessoa p, int modalidade)
    {
        AlunoPagamentoMensalidade apm = new AlunoPagamentoMensalidade();
        apm.setMensalidadeVigenteIntoAlunoPagamentoMensalidade(mv);
        apm.setDataVencimento(data_vencimento);
        apm.setDataPagamento(data_pagamento);
        apm.setValorPago(valor_pago);
        apm.setData();
        apm.setPessoaIntoAlunoPagamentoMensalidade(p);
        apm.setModalidade(modalidade);
        apm.setDataID();

        int i = 0;
        while(alunoPagamentoMensalidade[i] != null && i < alunoPagamentoMensalidade.length-1)
        {
            i++;
        }

        if(i < alunoPagamentoMensalidade.length)
        {
            if(alunoPagamentoMensalidade[i] == null)
            {
                alunoPagamentoMensalidade[i] = apm;
                StringBuilder builder = new StringBuilder("\nPagamento da Mensalidade do Aluno adicionada com sucesso!");
                return builder;
            }
            else
            {
                StringBuilder builder = new StringBuilder("\nNão foi possivel adicionar a nova Pagamento da Mensalidade do Aluno!");
                return builder;
            }
        }
        StringBuilder builder = new StringBuilder("\nDeu ruim");
        return builder;
    }

    public StringBuilder readAlunoPagamentoMensalidade()
    {
        StringBuilder builder = new StringBuilder();

        for (AlunoPagamentoMensalidade apm : alunoPagamentoMensalidade)
        {
            if(apm != null)
            {
                builder.append(apm.toString());
            }    
        }

        return builder;
    }

    public StringBuilder updateAlunoPagamentoMensalidade(Long id, String data_vencimento, String data_pagamento, String valor_pago, int modalidade)
    {
        StringBuilder builder = new StringBuilder("");
        int i = 0;
        while(alunoPagamentoMensalidade[i] != null && alunoPagamentoMensalidade[i].getIDAlunoPagamentoMensalidade() != id)
        {
            i++;
        }

        if(alunoPagamentoMensalidade[i] != null && alunoPagamentoMensalidade[i].getIDAlunoPagamentoMensalidade() == id)
        {
            if(!data_vencimento.equals(""))
            {
                alunoPagamentoMensalidade[i].setDataVencimento(data_vencimento);
            }

            if(!data_pagamento.equals(""))
            {
                alunoPagamentoMensalidade[i].setDataPagamento(data_pagamento);
            }

            if(!valor_pago.equals(""))
            {
                alunoPagamentoMensalidade[i].setValorPago(valor_pago);
            }

            if(modalidade == 1 || modalidade == 2 || modalidade == 3 || modalidade == 4)
            {
                alunoPagamentoMensalidade[i].setModalidade(modalidade);
            }

            alunoPagamentoMensalidade[i].setModData();

            builder.append("\nPagamento da Mensalidade do Aluno atualizada com sucesso!");
            return builder;
        }

        builder.append("\nPagamento da Mensalidade do Aluno não foi atualizada!");
        return builder;
    }

    public StringBuilder deleteAlunoPagamentoMensalidade(Long id)
    {
        StringBuilder builder = new StringBuilder("");
        int i = 0;
        while(alunoPagamentoMensalidade[i] != null && alunoPagamentoMensalidade[i].getIDAlunoPagamentoMensalidade() != id)
        {
            i++;
        }

        if(i < alunoPagamentoMensalidade.length)
        {
            if(alunoPagamentoMensalidade[i].getIDAlunoPagamentoMensalidade() == id)
            {
                alunoPagamentoMensalidade[i] = null;
                builder.append("\nMensalidade Vigente deletada com sucesso!");
                return builder;
            }
        }

        builder.append("\nMensalidade Vigente não encontrada!");
        return builder;
    }

    public AlunoPagamentoMensalidade getAlunoPagamentoMensalidade(Long id)
    {
        AlunoPagamentoMensalidade apm = new AlunoPagamentoMensalidade();

        int i = 0;
        while(alunoPagamentoMensalidade[i] != null && alunoPagamentoMensalidade[i].getIDAlunoPagamentoMensalidade() != id)
        {
            i++;
        }

        if(alunoPagamentoMensalidade[i] != null)
        {
            apm = alunoPagamentoMensalidade[i];
        }

        return apm;
    }

}
