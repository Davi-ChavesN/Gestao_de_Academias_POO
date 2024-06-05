package controller;

import javax.swing.JOptionPane;

import model.AcademiaDAO;
import model.EntradaAlunoDAO;
import model.Pessoa;
import view.GUI;

public class ControllerMenuAcademia {

    GUI gui = new GUI();
    AcademiaDAO academiaDAO = new AcademiaDAO();
    EntradaAlunoDAO entradaAlunoDAO = new EntradaAlunoDAO();

    public Pessoa loginAcademia()
    {
        Pessoa p = new Pessoa();

        p = gui.login();

        //if(p.getTipoUser().equals("aluno"))
        {
            entradaAlunoDAO.createEntradaAluno(p);
        }

        return p;
    }

    public void menuAcademia(Pessoa usuarioLogado) 
    {
        int opc_menu_academia = 0;

        String att = "";
        while(opc_menu_academia!=-1)
        {
            StringBuilder builder = new StringBuilder();
            opc_menu_academia = gui.menuAcademia(usuarioLogado);

            if(opc_menu_academia == 1)
            {
                builder = gui.headerMenuUser(usuarioLogado);
                builder.append(academiaDAO.editAcademia(opc_menu_academia, att));
                JOptionPane.showMessageDialog(null, builder);
            }
            else if(opc_menu_academia == 2)
            {
                builder.append("\nInforme o novo nome da academia");
                
                att = JOptionPane.showInputDialog(builder);

                builder = gui.headerMenuUser(usuarioLogado);
                builder.append(academiaDAO.editAcademia(opc_menu_academia, att));
                JOptionPane.showMessageDialog(null, builder);
            }
            else if(opc_menu_academia == 3)
            {
                builder.append("\nInforme o novo endereço da academia");

                att = JOptionPane.showInputDialog(builder);

                builder = gui.headerMenuUser(usuarioLogado);
                builder.append(academiaDAO.editAcademia(opc_menu_academia, att));
                JOptionPane.showMessageDialog(null, builder);
            }
            else if(opc_menu_academia == 4)
            {
                builder.append("\nInforme quantos dias deseja avançar no Calendário");

                int dias = Integer.parseInt(JOptionPane.showInputDialog(builder));
                academiaDAO.editCalend(dias);
            }
            else if(opc_menu_academia == 5)
            {
                builder.append(academiaDAO.pegarCalend());
            }
            else if(opc_menu_academia == 6)
            {
                builder.append(entradaAlunoDAO.readEntradaAluno());
                JOptionPane.showMessageDialog(null, builder);
            }
            else if(opc_menu_academia == 0)
            {
                opc_menu_academia = -1;
            }
            else
            {
                opc_menu_academia = 0;
            }
        }
    }
}

    