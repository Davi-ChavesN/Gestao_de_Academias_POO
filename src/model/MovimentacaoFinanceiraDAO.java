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

    public void updateMovimentacaoFinanceira()
    {

    }

    public void deleteMovimentacaoFinanceira()
    {

    }

}
