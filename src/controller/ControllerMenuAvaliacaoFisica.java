package controller;

import javax.swing.JOptionPane;

import model.AvaliacaoFisica;
import model.AvaliacaoFisicaDAO;
import model.Pessoa;
import view.GUI;

public class ControllerMenuAvaliacaoFisica {

    GUI gui = new GUI();
    AvaliacaoFisicaDAO avaliacaoFisicaDAO = new AvaliacaoFisicaDAO();

    public void menuAvaliacaoFisica(Pessoa usuarioLogado)
    {
        int opc_menu_avaliacao_fisica = 0;
        StringBuilder builder = new StringBuilder();

        while(opc_menu_avaliacao_fisica != -1)
        {
            opc_menu_avaliacao_fisica = gui.crudAvaliacaoFisica(usuarioLogado);
            switch (opc_menu_avaliacao_fisica)
            {
                case 1:
                    builder = gui.headerMenuUser(usuarioLogado);
                    builder.append(avaliacaoFisicaDAO.readAvaliacaoFisica(usuarioLogado));
                    
                    JOptionPane.showMessageDialog(null, builder);
                    break;
                
                case 2:
                    if(!usuarioLogado.getTipoUser().equals("aluno"))
                    {
                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(avaliacaoFisicaDAO.readAllAvaliacoesFisicas());

                        JOptionPane.showMessageDialog(null, builder);
                    }
                    break;

                case 3:
                    if(!usuarioLogado.getTipoUser().equals("aluno"))
                    {
                        AvaliacaoFisica af = new AvaliacaoFisica();

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("Informe o peso <xx.xx>");
                        float peso = Float.parseFloat(JOptionPane.showInputDialog(builder));

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("Informe a altura <x.xx>");
                        float altura = Float.parseFloat(JOptionPane.showInputDialog(builder));

                        builder = gui.headerMenuUser(usuarioLogado);
                        af = avaliacaoFisicaDAO.createAvaliacaoFisica(usuarioLogado, peso, altura);
                        builder.append(avaliacaoFisicaDAO.readLastAvaliacaoFisica(af, usuarioLogado));
                        builder.append("Informe a nota da Avaliação Física");

                        float nota = Float.parseFloat(JOptionPane.showInputDialog(builder));

                        avaliacaoFisicaDAO.notaAvaliacaoFisica(af, nota);
                    }
                    break;
            
                case 0:
                    opc_menu_avaliacao_fisica = -1;
                    break;

                default:
                    opc_menu_avaliacao_fisica = 0;
                    break;
            }
        }
    }

}
