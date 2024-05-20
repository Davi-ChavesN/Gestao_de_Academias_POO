package controller;


import javax.swing.JOptionPane;

import model.Pessoa;
import model.Treino;
import model.TreinoAplicacaoDAO;
import model.TreinoDAO;
import model.AcademiaDAO;
import model.DivisaoTreino;
import model.DivisaoTreinoDAO;
import model.DivisaoTreinoMusculo;
import model.DivisaoTreinoMusculoDAO;
import model.Exercicio;
import model.ExercicioAplicacao;
import model.ExercicioDAO;
import model.ExercicioAplicacaoDAO;

import view.GUI;


public class ControllerMenuTreino {

    ExercicioDAO exercicioDAO = new ExercicioDAO();
    ExercicioAplicacaoDAO exercicioAplicacaoDAO = new ExercicioAplicacaoDAO();
    DivisaoTreinoDAO divisaoTreinoDAO = new DivisaoTreinoDAO();
    DivisaoTreinoMusculoDAO divisaoTreinoMusculoDAO = new DivisaoTreinoMusculoDAO(divisaoTreinoDAO.getDivisaoTreino(Long.parseLong("1")));
    TreinoDAO treinoDAO = new TreinoDAO(divisaoTreinoDAO.getDivisaoTreino(Long.parseLong("1")));
    TreinoAplicacaoDAO treinoAplicacaoDAO = new TreinoAplicacaoDAO();
    AcademiaDAO academiaDAO = new AcademiaDAO();
    
    GUI gui = new GUI();

    public void menuTreino(Pessoa usuarioLogado)
    {
        int opc_menu_treino = 0;
        int opc_crud = 0;

        while(opc_menu_treino != -1)
        {
            opc_menu_treino = gui.menuTreino(usuarioLogado);

            if(opc_menu_treino == 1)//CRUD Divisão Treino
            {
                opc_crud = 0;
                while (opc_crud != -1)
                {
                    opc_crud = gui.crudDivisaoTreino(usuarioLogado);

                    if(opc_crud == 1)//CREATE
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme o nome da nova Divisão de Treino");
                        String nomeDivisaoTreino = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a descrição da nova Divisão de Treino");
                        String descDivisaoTreino = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(divisaoTreinoDAO.createDivisaoTreino(nomeDivisaoTreino, descDivisaoTreino));
                    
                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 2)//READ
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(divisaoTreinoDAO.readDivisaoTreino());

                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 3)//UPDATE
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme o ID da Divisão de Treino a ser atualizada");
                        long IDatt = Long.parseLong(JOptionPane.showInputDialog(builder));

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme o novo nome da Divisão de Treino");
                        String nomeAtt = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a nova descrição da Divisão de Treino");
                        String descAtt = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(divisaoTreinoDAO.updateDivisaoTreino(IDatt, nomeAtt, descAtt));

                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 4)//DELETE
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme o ID da Divisão de Treino a ser deletada");
                        long IDdel = Long.parseLong(JOptionPane.showInputDialog(builder));

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(divisaoTreinoDAO.deleteDivisaoTreino(IDdel));

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
            else if(opc_menu_treino == 2)//CRUD Divisão Treino Musculo
            {
                opc_crud = 0;
                while(opc_crud != -1)
                {
                    opc_crud = gui.crudDivisaoTreinoMusculo(usuarioLogado);

                    if(opc_crud == 1)//CREATE
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(divisaoTreinoDAO.readDivisaoTreino());
                        builder.append("\nInforme qual Divisão de Treino irá usar");

                        DivisaoTreino dt = new DivisaoTreino();
                        dt = null;
                        
                        while(dt == null)
                        {
                            Long id = Long.parseLong(JOptionPane.showInputDialog(builder));
                            dt = divisaoTreinoDAO.getDivisaoTreino(id);
                        }

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a descrição da Divisão de Treino-Musculo");
                        String desc = JOptionPane.showInputDialog(builder);

                        divisaoTreinoMusculoDAO.createDivisaoTreinoMusculo(dt, desc);
                    }
                    else if(opc_crud == 2)//READ
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(divisaoTreinoMusculoDAO.readDivisaoTreinoMusculo());

                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 3)//UPDATE
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(divisaoTreinoMusculoDAO.readDivisaoTreinoMusculo());

                        builder.append("\nInforme o ID do Treino-Musculo a ser atualizado");
                        Long idAtt = Long.parseLong(JOptionPane.showInputDialog(builder));

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a nova descrição");
                        String descAtt = JOptionPane.showInputDialog(builder);

                        divisaoTreinoMusculoDAO.updateDivisaoTreinoMusculo(idAtt, descAtt);

                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 4)//DELETE
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(divisaoTreinoMusculoDAO.readDivisaoTreinoMusculo());

                        builder.append("\nInforme o ID da Divisão de Treino-Musculo a ser deletada");
                        long IDdel = Long.parseLong(JOptionPane.showInputDialog(builder));

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(divisaoTreinoMusculoDAO.deleteDivisaoTreinoMusculo(IDdel));

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
            else if(opc_menu_treino == 3)//CRUD Treino
            {
                opc_crud = 0;
                while(opc_crud != -1)
                {
                    opc_crud = gui.crudTreino(usuarioLogado);

                    if(opc_crud == 1)//CREATE
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(divisaoTreinoDAO.readDivisaoTreino());
                        builder.append("\nInforme qual Divisão de Treino irá usar");

                        DivisaoTreino dt = new DivisaoTreino();
                        dt = null;
                        
                        while(dt == null)
                        {
                            Long id = Long.parseLong(JOptionPane.showInputDialog(builder));
                            dt = divisaoTreinoDAO.getDivisaoTreino(id);
                        }

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme o objetivo do Treino");
                        String objetivo = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a data de inicio do Treino dd/mm/yyyy");
                        String dt_inicio = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a data de termino do Treino dd/mm/yyyy");
                        String dt_termino = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(treinoDAO.createTreino(dt, objetivo, dt_inicio, dt_termino));
                        
                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 2)//READ
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(treinoDAO.readTreino());

                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 3)//UPDATE
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(treinoDAO.readTreino());

                        builder.append("\nInforme o ID do Treino a ser atualizado");
                        Long idAtt = Long.parseLong(JOptionPane.showInputDialog(builder));

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme o novo objetivo do Treino");
                        String objetivoAtt = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a nova data de inicio do Treino");
                        String dt_inicio = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a nova data de termino do Treino");
                        String dt_termino = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(treinoDAO.updateTreino(idAtt, objetivoAtt, dt_inicio, dt_termino));
                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 4)//DELETE
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(treinoDAO.readTreino());

                        builder.append("\nInforme o ID do Treino a ser deletado");
                        long IDdel = Long.parseLong(JOptionPane.showInputDialog(builder));

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(treinoDAO.deleteTreino(IDdel));

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
            else if(opc_menu_treino == 4)//CRUD Treino Aplicação
            {
                opc_crud = 0;
                while(opc_crud != -1)
                {
                    opc_crud = gui.crudTreinoAplicacao(usuarioLogado);

                    if(opc_crud == 1)//CREATE
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(treinoDAO.readTreino());
                        builder.append("\nInforme qual Treino irá usar");

                        Treino t = new Treino();
                        t = null;
                        while(t == null)
                        {
                            Long id = Long.parseLong(JOptionPane.showInputDialog(builder));
                            t = treinoDAO.getTreino(id);
                        }


                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(exercicioDAO.mostrarExercicios(usuarioLogado));
                        builder.append("\nInforme qual Exercicio irá usar");

                        Exercicio e = new Exercicio();
                        e = null;
                        while(e == null)
                        {
                            Long id = Long.parseLong(JOptionPane.showInputDialog(builder));
                            e = exercicioDAO.getExercicio(id);
                        }


                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(exercicioAplicacaoDAO.readExercicioAplicacao());
                        builder.append("\nInforme qual Aplicação de Exercicio irá usar");

                        ExercicioAplicacao ea = new ExercicioAplicacao();
                        ea = null;
                        while(ea == null)
                        {
                            Long id = Long.parseLong(JOptionPane.showInputDialog(builder));
                            ea = exercicioAplicacaoDAO.getExercicioAplicacao(id);
                        }


                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(divisaoTreinoDAO.readDivisaoTreino());
                        builder.append("\nInforme qual Divisão de Treino irá usar");

                        DivisaoTreino dt = new DivisaoTreino();
                        dt = null;
                        while(dt == null)
                        {
                            Long id = Long.parseLong(JOptionPane.showInputDialog(builder));
                            dt = divisaoTreinoDAO.getDivisaoTreino(id);
                        }


                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(divisaoTreinoMusculoDAO.readDivisaoTreinoMusculo());
                        builder.append("\nInforme qual Divisão de Treino-Musculo irá usar");

                        DivisaoTreinoMusculo dtm = new DivisaoTreinoMusculo();
                        dtm = null;
                        while(dtm == null)
                        {
                            Long id = Long.parseLong(JOptionPane.showInputDialog(builder));
                            dtm = divisaoTreinoMusculoDAO.getDivisaoTreinoMusculo(id);
                        }


                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(treinoAplicacaoDAO.createTreinoAplicacao(t, e, ea, dt, dtm));
                        
                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 2)//READ
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(treinoAplicacaoDAO.readTreinoAplicacao());

                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 3)//UPDATE
                    {
                        
                    }
                    else if(opc_crud == 4)//DELETE
                    {
                        
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
            else if(opc_menu_treino == 0)
            {
                opc_menu_treino = -1;
            }
            else
            {
                opc_menu_treino = 0;
            }
        }
    }
}
