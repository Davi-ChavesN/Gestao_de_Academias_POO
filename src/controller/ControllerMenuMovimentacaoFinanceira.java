package controller;

import javax.swing.JOptionPane;

import model.MovimentacaoFinanceiraDAO;
import model.Pessoa;
import view.GUI;

public class ControllerMenuMovimentacaoFinanceira {

    GUI gui = new GUI();
    MovimentacaoFinanceiraDAO movimentacaoFinanceiraDAO = new MovimentacaoFinanceiraDAO();

    public void menuMovimentacaoFinanceira(Pessoa usuarioLogado)
    {
        int opc_menu_movimentacao_financeira = 0;

        while(opc_menu_movimentacao_financeira != -1)
        {
            StringBuilder builder = new StringBuilder();
            opc_menu_movimentacao_financeira = gui.crudMovimentacaoFinanceira(usuarioLogado);

            switch (opc_menu_movimentacao_financeira)
            {
                case 1: //create
                    builder = gui.headerMenuUser(usuarioLogado);
                    builder.append("\nInforme o valor da movimentação financeira realizada");
                    String valor = JOptionPane.showInputDialog(builder);

                    builder = gui.headerMenuUser(usuarioLogado);
                    builder.append("\nInforme o tipo de movimentação realizada");
                    builder.append("\n1. Entrada");
                    builder.append("\n2. Saída");
                    int tipo = Integer.parseInt(JOptionPane.showInputDialog(builder));

                    builder = gui.headerMenuUser(usuarioLogado);
                    builder.append("\nInforme do que se trata esta movimentação financeira");
                    String descricao = JOptionPane.showInputDialog(builder);
                    
                    movimentacaoFinanceiraDAO.createMovimentacaoFinanceira(valor, tipo, descricao);
                    break;

                case 2: //read
                    
                    break;

                case 3: //update
                    
                    break;

                case 4: //delete
                    
                    break;

                case 0: //voltar
                    opc_menu_movimentacao_financeira = -1;
                    break;
            
                default:
                    opc_menu_movimentacao_financeira = 0;
                    break;
            }
        }
    }

}
