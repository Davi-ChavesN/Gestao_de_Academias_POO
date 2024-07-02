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
                    builder = gui.headerMenuUser(usuarioLogado);
                    builder.append(movimentacaoFinanceiraDAO.readMovimentacaoFinanceira());
                    JOptionPane.showMessageDialog(null, builder);
                    break;

                case 3: //update
                    builder = gui.headerMenuUser(usuarioLogado);
                    builder.append("\nInforme o ID da Movimentação Financeira a ser atualizada");
                    long IDatt = Long.parseLong(JOptionPane.showInputDialog(builder));

                    builder = gui.headerMenuUser(usuarioLogado);
                    builder.append("\nInforme o novo valor da Movimentação Financeira");
                    String valorAtt = JOptionPane.showInputDialog(builder);

                    builder = gui.headerMenuUser(usuarioLogado);
                    builder.append("\nInforme o tipo da Movimentação Financeira");
                    int MF = Integer.parseInt(JOptionPane.showInputDialog(builder));
                    String tipoAtt = "";
                    if(MF == 1)
                    {
                        tipoAtt = "Entrada";
                    }
                    else if(MF == 2)
                    {
                        tipoAtt = "Saida";
                    }

                    builder = gui.headerMenuUser(usuarioLogado);
                    builder.append("\nInforme a descrição da Movimentação Financeira");
                    String descricaoAtt = JOptionPane.showInputDialog(builder);

                    builder = gui.headerMenuUser(usuarioLogado);
                    builder.append(movimentacaoFinanceiraDAO.updateMovimentacaoFinanceira(IDatt, valorAtt, tipoAtt, descricaoAtt));

                    JOptionPane.showMessageDialog(null, builder);
                    break;

                case 4: //delete
                    builder = gui.headerMenuUser(usuarioLogado);
                    builder.append("\nInforme o ID da Movimentação Financeira a ser deletada");
                    long IDdel = Long.parseLong(JOptionPane.showInputDialog(builder));

                    builder = gui.headerMenuUser(usuarioLogado);
                    builder.append(movimentacaoFinanceiraDAO.deleteMovimentacaoFinanceira(IDdel));

                    JOptionPane.showMessageDialog(null, builder);
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
