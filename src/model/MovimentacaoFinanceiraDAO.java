package model;

import javax.swing.JOptionPane;

public class MovimentacaoFinanceiraDAO {

    MovimentacaoFinanceira[] movimentacaoFinanceira = new MovimentacaoFinanceira[100];

    public MovimentacaoFinanceiraDAO()
    {

    }

    public StringBuilder createMovimentacaoFinanceira(String valor, int tipo, String descricao)
    {
        StringBuilder builder = new StringBuilder();
        MovimentacaoFinanceira mf = new MovimentacaoFinanceira();
        
        mf.setValorMovimentacaoFinanceiro(valor);
        if(tipo == 1)
        {
            mf.setTipoMovimentacaoFinanceira("Entrada");
        }
        else if(tipo == 2)
        {
            mf.setTipoMovimentacaoFinanceira("Saída");
        }
        mf.setDescricaoMovimentacaoFinanceira(descricao);
        mf.setDataID();

        int i = 0;
        while(movimentacaoFinanceira[i] != null && i < movimentacaoFinanceira.length-1)
        {
            i++;
        }

        if(i < movimentacaoFinanceira.length)
        {
            if(movimentacaoFinanceira[i] == null)
            {
                movimentacaoFinanceira[i] = mf;
                builder.append("Movimentação Financeira adicionada com sucesso!");
            }
            else
            {
                builder.append("Não é possível adicionar mais movimentações financeiras!");
                JOptionPane.showMessageDialog(null, builder);
            }
        }

        return builder;
    }

    public StringBuilder readMovimentacaoFinanceira()
    {
        StringBuilder builder = new StringBuilder();

        for (MovimentacaoFinanceira mf : movimentacaoFinanceira)
        {
            if(mf != null)
            {
                builder.append("\nID: " + mf.getIDMovimentacaoFinanceira());
                builder.append("\nValor: " + mf.getValorMovimentacaoFinanceiro());
                builder.append("\nTipo: " + mf.getTipoMovimentacaoFinanceira());
                builder.append("\nDescrição: " + mf.getDescricaoMovimentacaoFinanceira());
                builder.append("\n");
            }    
        }

        return builder;
    }

    public StringBuilder updateMovimentacaoFinanceira(Long id, String valor, String tipo, String descricao)
    {
        StringBuilder builder = new StringBuilder("");
        int i = 0;
        while(movimentacaoFinanceira[i] != null && movimentacaoFinanceira[i].getIDMovimentacaoFinanceira() != id)
        {
            i++;
        }

        if(movimentacaoFinanceira[i] != null && movimentacaoFinanceira[i].getIDMovimentacaoFinanceira() == id)
        {
            if(!valor.equals(""))
            {
                movimentacaoFinanceira[i].setValorMovimentacaoFinanceiro(valor);
            }

            if(!tipo.equals(""))
            {
                movimentacaoFinanceira[i].setTipoMovimentacaoFinanceira(tipo);
            }

            if(!descricao.equals(""))
            {
                movimentacaoFinanceira[i].setDescricaoMovimentacaoFinanceira(descricao);
            }

            movimentacaoFinanceira[i].setModData();

            builder.append("\nMensalidade Vigente atualizada com sucesso!");
            return builder;
        }

        builder.append("\nMensalidade Vigente não foi atualizada!");
        return builder;
    }

    public StringBuilder deleteMovimentacaoFinanceira(Long id)
    {
        StringBuilder builder = new StringBuilder("");
        int i = 0;
        while(movimentacaoFinanceira[i] != null && movimentacaoFinanceira[i].getIDMovimentacaoFinanceira() != id)
        {
            i++;
        }

        if(i < movimentacaoFinanceira.length)
        {
            if(movimentacaoFinanceira[i].getIDMovimentacaoFinanceira() == id)
            {
                movimentacaoFinanceira[i] = null;
                builder.append("\nMensalidade Vigente deletada com sucesso!");
                return builder;
            }
        }

        builder.append("\nMensalidade Vigente não encontrada!");
        return builder;
    }

}
