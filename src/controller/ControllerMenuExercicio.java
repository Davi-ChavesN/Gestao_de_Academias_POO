package controller;


import javax.swing.JOptionPane;

import model.Exercicio;
import model.ExercicioAplicacao;
import model.ExercicioDAO;
import model.ExercicioAplicacaoDAO;
import model.Pessoa;

import view.GUI;


public class ControllerMenuExercicio {

    GUI gui = new GUI();
    ExercicioDAO exercicioDAO = new ExercicioDAO();
    ExercicioAplicacaoDAO exercicioAplicacaoDAO = new ExercicioAplicacaoDAO();

    public void menuExercicio(Pessoa usuarioLogado)
    {
        int opc_menu_exercicio = 0;
        int opc_crud = 0;

        while(opc_menu_exercicio != -1)
        {
            opc_menu_exercicio = gui.menuExercicio(usuarioLogado);

            if(opc_menu_exercicio == 1)//CRUD Exercicio
            {
                opc_crud = 0;
                while(opc_crud != -1)
                {
                    StringBuilder builder = new StringBuilder();
                    opc_crud = gui.crudExercicio(usuarioLogado);

                    if(opc_crud == 1)//Adicionar Exercicio
                    {
                        Exercicio e = new Exercicio();
                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme o nome do exercício");
                        e.setNomeExercicio(JOptionPane.showInputDialog(builder));

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a descrição do exercício");
                        e.setDescricaoExercicio(JOptionPane.showInputDialog(builder));

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme o grupo muscular do exercício");
                        e.setGrupoMuscular(JOptionPane.showInputDialog(builder));

                        e.setDataID();

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(exercicioDAO.adicionarExercicio(e));
                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 2)//Ver Exercicios
                    {
                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nExercicios cadastrados");
                        builder.append(exercicioDAO.mostrarExercicios(usuarioLogado));
                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 3)//Atualizar Exercicio
                    {
                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(exercicioDAO.mostrarExercicios(usuarioLogado));
                        builder.append("\nInforme o ID do exercício a ser editado");

                        try
                        {
                            long id_edit = Long.parseLong(JOptionPane.showInputDialog(builder));
                            Exercicio updateExercicio = exercicioDAO.buscaPorCriterioAlternativa1(id_edit);
                        

                            builder = gui.headerMenuUser(usuarioLogado);
                            builder.append("\n--- Editar Exercício ---\n");
                            builder.append("\n1. Editar nome");
                            builder.append("\n2. Editar descrição");
                            builder.append("\n3. Editar grupo muscular");
                            builder.append("\n0. Sair");

                            int opc_edit = 0;
                            while(opc_edit != -1)
                            {
                                opc_edit = Integer.parseInt(JOptionPane.showInputDialog(builder));
                                StringBuilder editValue = gui.headerMenuUser(usuarioLogado);

                                if (opc_edit == 1)//Editar nome do exercício
                                {
                                    editValue.append("\nInforme o novo nome!");
                                    String novo_nome = JOptionPane.showInputDialog(editValue);
                                    editValue = gui.headerMenuUser(usuarioLogado);
                                    editValue.append(exercicioDAO.editExercicio(opc_edit, updateExercicio, novo_nome));
                                }
                                else if(opc_edit == 2)//Editar descrição do exercício
                                {
                                    editValue.append("\nInforme a nova descição!");
                                    String nova_descricao = JOptionPane.showInputDialog(editValue);
                                    editValue = gui.headerMenuUser(usuarioLogado);
                                    editValue.append(exercicioDAO.editExercicio(opc_edit, updateExercicio, nova_descricao));
                                }
                                else if(opc_edit == 3)//Editar grupo muscular do exercício
                                {
                                    editValue.append("\nInforme o grupo muscular!");
                                    String novo_grupo_muscular = JOptionPane.showInputDialog(editValue);
                                    editValue = gui.headerMenuUser(usuarioLogado);
                                    editValue.append(exercicioDAO.editExercicio(opc_edit, updateExercicio, novo_grupo_muscular));
                                }
                                else if(opc_edit == 0)
                                {
                                    opc_edit = -1;
                                }
                                else
                                {
                                    opc_edit = 0;
                                }
                            }
                        }
                        catch (NumberFormatException e) 
                        {
                            JOptionPane.showMessageDialog(null, "O ID informado não é um número válido.");
                        }
                    }
                    else if(opc_crud == 4)//Deletar Exercicio
                    {
                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\n--- Deletar Exercício ---\n");
                        builder.append(exercicioDAO.mostrarExercicios(usuarioLogado));
                        builder.append("\nInforme o ID do exercício a ser deletado");
                        long IDdel = Long.parseLong(JOptionPane.showInputDialog(builder));
                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(exercicioDAO.delExercicio(IDdel));
                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 0)//Saída do Menu de CRUD de Exercicio
                    {
                        opc_crud = -1;
                    }
                    else
                    {
                        opc_crud = 0;
                    }
                }
            }
            else if(opc_menu_exercicio == 2)//CRUD Exercicio Aplicação
            {
                opc_crud = 0;
                while(opc_crud != -1)
                {
                    opc_crud = gui.crudExercicioAplicacao(usuarioLogado);

                    if(opc_crud == 1)//CREATE
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append("\nInforme a descrição da nova aplicação de exercício");
                        String desc = JOptionPane.showInputDialog(builder);

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(exercicioAplicacaoDAO.createExercicioAplicacao(desc));
                    
                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 2)//READ
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(exercicioAplicacaoDAO.readExercicioAplicacao());

                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 3)//UPDATE
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(exercicioAplicacaoDAO.readExercicioAplicacao());
                        builder.append("\nInforme o ID da Aplicação de Exercicio a ser atualizada");
                        
                        try
                        {
                            Long IDatt = Long.parseLong(JOptionPane.showInputDialog(builder));
                            ExercicioAplicacao updateEA = exercicioAplicacaoDAO.buscaPorCriterioAlternativa1(IDatt);

                            builder = gui.headerMenuUser(usuarioLogado);
                            builder.append("\nInforme a nova descrição da Aplicação de Exercicio");
                            String descAtt = JOptionPane.showInputDialog(builder);
                            updateEA.setDescricaoExercicioAplicacao(descAtt);

                            builder = gui.headerMenuUser(usuarioLogado);
                            builder.append(exercicioAplicacaoDAO.updateExercicioAplicacao(IDatt, updateEA));
                        }
                        catch (NumberFormatException e) 
                        {
                            JOptionPane.showMessageDialog(null, "O ID informado não é um número válido.");
                        }                        

                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 4)//DELETE
                    {
                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(exercicioAplicacaoDAO.readExercicioAplicacao());
                        builder.append("\nInforme o ID da Aplicação de Exercício a ser deletada");
                        long IDdel = Long.parseLong(JOptionPane.showInputDialog(builder));

                        builder = gui.headerMenuUser(usuarioLogado);
                        builder.append(exercicioAplicacaoDAO.deleteExercicioAplicacao(IDdel));

                        JOptionPane.showMessageDialog(null, builder);
                    }
                    else if(opc_crud == 0)//Voltar
                    {
                        opc_crud = -1;
                    }
                    else
                    {
                        opc_crud = 0;
                    }
                }
            }
            else if(opc_menu_exercicio == 0)
            {
                opc_menu_exercicio = -1;
            }
            else
            {
                opc_menu_exercicio = 0;
            }
        }
    }
}
