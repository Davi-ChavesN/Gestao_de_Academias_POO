package controller;


import javax.swing.JOptionPane;

import model.AlunoPagamentoMensalidadeDAO;
import model.MensalidadeVigente;
import model.MensalidadeVigenteDAO;
import model.PagamentoRecorrenteDAO;
import model.Pessoa;
import model.PessoaDAO;
import view.GUI;

public class ControllerMenuMensalidade {

    GUI gui = new GUI();
    MensalidadeVigenteDAO mensalidadeVigenteDAO = new MensalidadeVigenteDAO();
    AlunoPagamentoMensalidadeDAO alunoPagamentoMensalidadeDAO = new AlunoPagamentoMensalidadeDAO();
    PagamentoRecorrenteDAO pagamentoRecorrenteDAO = new PagamentoRecorrenteDAO();
    PessoaDAO pessoaDAO = new PessoaDAO();
    

    public void menuMensalidade(Pessoa usuarioLogado)
    {
        int opc_menu_mensalidade = 0;
        int opc_crud = 0;

        while(opc_menu_mensalidade != -1)
        {
            opc_menu_mensalidade = gui.menuFinanceiro(usuarioLogado);

            if(opc_menu_mensalidade == 1)//CRUD Exercicio
            {
                opc_crud = 0;

                while(opc_crud != -1)
                {
                    opc_crud = gui.crudMensalidadeVigente(usuarioLogado);

                    if(opc_crud == 1)//CREATE
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme o valor da nova Mensalidade Vigente");
                        String valor = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a data de inicio da nova Mensalidade Vigente dd/mm/yyyy");
                        String data_inicio = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a data de termino da nova Mensalidade Vigente dd/mm/yyyy");
                        String data_termino = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(mensalidadeVigenteDAO.createMensalidadeVigente(valor, data_inicio, data_termino));
                    
                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 2)//READ
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(mensalidadeVigenteDAO.readMensalidadeVigente());

                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 3)//UPDATE
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme o ID da Mensalidade Vigente a ser atualizada");
                        long IDatt = Long.parseLong(JOptionPane.showInputDialog(builder));

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme o novo valor da Mensalidade Vigente");
                        String valorAtt = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a nova data de inicio da Mensalidade Vigente");
                        String inicioAtt = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a nova data de termino da Mensalidade Vigente");
                        String terminoAtt = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(mensalidadeVigenteDAO.updateMensalidadeVigente(IDatt, valorAtt, inicioAtt, terminoAtt));

                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 4)//DELETE
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme o ID da Mensalidade Vigente a ser deletada");
                        long IDdel = Long.parseLong(JOptionPane.showInputDialog(builder));

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(mensalidadeVigenteDAO.deleteMensalidadeVigente(IDdel));

                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 0)
                    {
                        opc_crud = -1;
                    }
                    else
                    {
                        opc_crud = 0;
                    }
                }
            }
            else if(opc_menu_mensalidade == 2)
            {
                opc_crud = 0;

                while(opc_crud != -1)
                {
                    opc_crud = gui.crudPagamentoMensalidadeAluno(usuarioLogado);

                    if(opc_crud == 1)//CREATE
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(mensalidadeVigenteDAO.readMensalidadeVigente());
                        builder.append("\nInforme o ID da Mensalidade Vigente a ser utilizada");
                        Long idMv = Long.parseLong(JOptionPane.showInputDialog(builder));

                        MensalidadeVigente mv = mensalidadeVigenteDAO.getMensalidadeVigente(idMv);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a data de vencimento do Pagamento de Mensalidade do Aluno dd/mm/yyyy");
                        String dt_vencimento = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a data de pagamento do Pagamento de Mensalidade do Aluno dd/mm/yyyy");
                        String dt_pagamento = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme o valor pago");
                        String valor_pago = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(pessoaDAO.mostrarUsuarios(usuarioLogado));
                        builder.append("\nInforme o ID da pessoa a ser utilizada");
                        Long idP = Long.parseLong(JOptionPane.showInputDialog(builder));

                        Pessoa p = pessoaDAO.pegaUsuario(idP);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a modalidade de pagamento");
                        builder.append("\n1. Dinheiro");
                        builder.append("\n2. Pix");
                        builder.append("\n3. Débito automático");
                        builder.append("\n4. Pagamento recorrente");
                        int modalidade = Integer.parseInt(JOptionPane.showInputDialog(builder));

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(alunoPagamentoMensalidadeDAO.createAlunoPagamentoMensalidade(mv, dt_vencimento, dt_pagamento, valor_pago, p, modalidade));
                    
                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 2)//READ
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(alunoPagamentoMensalidadeDAO.readAlunoPagamentoMensalidade());

                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 3)//UPDATE
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme o ID do Pagamento de Mensalidade a ser atualizada");
                        long IDatt = Long.parseLong(JOptionPane.showInputDialog(builder));

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a nova data de vencimento dd/mm/yyyy");
                        String dt_vencimento = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a nova data de pagamento dd/mm/yyyy");
                        String dt_pagamento = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme o valor pago");
                        String valor_pago = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme o valor pago");
                        int modalidade = Integer.parseInt(JOptionPane.showInputDialog(builder));

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(alunoPagamentoMensalidadeDAO.updateAlunoPagamentoMensalidade(IDatt, dt_vencimento, dt_pagamento, valor_pago, modalidade));

                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 4)//DELETE
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme o ID do Pagamento de Mensalidade a ser deletado");
                        long IDdel = Long.parseLong(JOptionPane.showInputDialog(builder));

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(alunoPagamentoMensalidadeDAO.deleteAlunoPagamentoMensalidade(IDdel));

                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 0)
                    {
                        opc_crud = -1;
                    }
                    else
                    {
                        opc_crud = 0;
                    }
                }
            }
            else if(opc_menu_mensalidade == 3)
            {
                opc_crud = 0;

                while(opc_crud != -1)
                {
                    opc_crud = gui.crudPagamentoRecorrente(usuarioLogado);

                    if(opc_crud == 1)//CREATE
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(pessoaDAO.mostrarUsuarios(usuarioLogado));
                        builder.append("\nInforme o ID da pessoa a ser utilizada");
                        Long idP = Long.parseLong(JOptionPane.showInputDialog(builder));

                        Pessoa p = pessoaDAO.pegaUsuario(idP);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme o cartão a ser utilizado para o Pagamento Recorrente");
                        String cartao = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme o valor do Pagamento Recorrente");
                        String valor = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a data de inicio do Pagamento Recorrente");
                        String data_inicio = JOptionPane.showInputDialog(builder);                        

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a quantidade de meses autorizados para o Pagamento Recorrente");
                        int meses_aut = Integer.parseInt(JOptionPane.showInputDialog(builder));

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(pagamentoRecorrenteDAO.createPagamentoRecorrente(p, data_inicio, cartao, valor, data_inicio, meses_aut));
                    
                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 2)//READ
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(pagamentoRecorrenteDAO.readPagamentoRecorrente());

                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 3)//UPDATE
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme o ID do Pagamento Recorrente a ser atualizado");
                        long IDatt = Long.parseLong(JOptionPane.showInputDialog(builder));

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme o novo valor do Pagamento Recorrente");
                        String valor = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a nova data de inicio do Pagamento Recorrente dd/mm/yyyy");
                        String dt_inicio = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a quantidade de meses autorizados para o Pagamento Recorrente");
                        int meses_aut = Integer.parseInt(JOptionPane.showInputDialog(builder));

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(pagamentoRecorrenteDAO.updatePagamentoRecorrente(IDatt, valor, dt_inicio, meses_aut));

                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 4)//DELETE
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme o ID do Pagamento Recorrente a ser deletado");
                        long IDdel = Long.parseLong(JOptionPane.showInputDialog(builder));

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(pagamentoRecorrenteDAO.deletePagamentoRecorrente(IDdel));

                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 0)
                    {
                        opc_crud = -1;
                    }
                    else
                    {
                        opc_crud = 0;
                    }
                }
            }
            else if(opc_menu_mensalidade == 0)
            {
                opc_menu_mensalidade = -1;
            }
            else
            {
                opc_menu_mensalidade = 0;
            }
        }
    }

}
