package controller;

import javax.swing.JOptionPane;

import model.Pessoa;
import model.PessoaDAO;
import view.GUI;

public class ControllerMenuPessoa {

    GUI gui = new GUI();
    PessoaDAO pessoaDAO = new PessoaDAO();

    public void menuPessoa(Pessoa usuario)
    {
        StringBuilder builder = new StringBuilder();
        long ID;
        int opc_menu_pessoa = 0;

        while(opc_menu_pessoa != -1)
        {
            opc_menu_pessoa = gui.menuCrudPessoa(usuario);

            switch (opc_menu_pessoa)
            {
                case 1: //Create Pessoa
                    builder = gui.headerMenuUser(usuario);
                    builder.append("\nInforme o nome do(a) novo(a) usuário(a)");
                    String nome = JOptionPane.showInputDialog(builder);

                    builder = gui.headerMenuUser(usuario);
                    builder.append("\nInforme o sexo do usuário");
                    builder.append("\n1. Masculino");
                    builder.append("\n2. Feminino");
                    String sexo = " ";
                    int sexo_num = Integer.parseInt(JOptionPane.showInputDialog(builder));
                    if(sexo_num == 1)
                    {
                        sexo = "M";
                    }
                    else if(sexo_num == 2)
                    {
                        sexo = "F";
                    }

                    builder = gui.headerMenuUser(usuario);
                    builder.append("\nInforme a data de nascimento <dd/mm/yyyy>");
                    String dt_nasc = JOptionPane.showInputDialog(builder);

                    builder = gui.headerMenuUser(usuario);
                    builder.append("\nInforme o login do usuário");
                    String login = JOptionPane.showInputDialog(builder);

                    builder = gui.headerMenuUser(usuario);
                    builder.append("\nInforme a senha do usuário");
                    String senha = JOptionPane.showInputDialog(builder);

                    int tipo_user = 0;
                    if(usuario.getTipoUser().equals("administrador"))
                    {
                        builder = gui.headerMenuUser(usuario);
                        builder.append("\nInforme o nível de acesso do usuário");
                        builder.append("\n1. Administrador");
                        builder.append("\n2. Instrutor");
                        builder.append("\n3. Aluno");
                        tipo_user = Integer.parseInt(JOptionPane.showInputDialog(builder));
                    }
                    else
                    {
                        tipo_user = 3;
                    }

                    pessoaDAO.createPessoa(nome, sexo, dt_nasc, login, senha, tipo_user);
                    break;

                case 2: //Read Pessoa
                    builder = gui.headerMenuUser(usuario);
                    builder.append(pessoaDAO.readPessoa(usuario));

                    JOptionPane.showMessageDialog(null, builder);
                    break;

                case 3: //Update Pessoa
                    int continuar_edicao = 0;
                    String att = "";
                    builder = gui.headerMenuUser(usuario);
                    builder.append(pessoaDAO.readPessoa(usuario));
                    builder.append("\nInforme o ID da pessoa que deseja editar");

                    try
                    {
                        ID = Long.parseLong(JOptionPane.showInputDialog(builder));
                        Pessoa updatepPessoa = pessoaDAO.buscaPorCriterioAlternativa1(ID);

                        while(continuar_edicao != -1)
                        {
                            builder = gui.headerMenuUser(usuario);
                            builder.append("\nInforme o que deseja atualizar");
                            builder.append("\n1. Editar nome");
                            builder.append("\n2. Editar sexo");
                            builder.append("\n3. Editar data de nascimento");
                            builder.append("\n4. Editar login");
                            builder.append("\n5. Editar senha");
                            builder.append("\n6. Editar nivel de acesso do usuário");
                            builder.append("\n0. Voltar");
                            int opc_edit = Integer.parseInt(JOptionPane.showInputDialog(builder));

                            switch (opc_edit)
                            {
                                case 1: //Nome
                                    builder = gui.headerMenuUser(usuario);
                                    builder.append("\nInforme o novo nome do usuário");
                                    att = JOptionPane.showInputDialog(builder);
                                    break;

                                case 2: //Sexo
                                    builder = gui.headerMenuUser(usuario);
                                    builder.append("\nInforme o sexo do usuário");
                                    builder.append("\n1. Masculino");
                                    builder.append("\n2. Feminino");
                                    int sexo_att = Integer.parseInt(JOptionPane.showInputDialog(builder));

                                    if(sexo_att == 1)
                                    {
                                        att = "M";
                                    }
                                    else if(sexo_att == 2)
                                    {
                                        att = "F";
                                    }
                                    break;

                                case 3: //Data de Nascimento
                                    builder = gui.headerMenuUser(usuario);
                                    builder.append("\nInforme a data de nascimento do usuário <dd/mm/yyyy>");
                                    att = JOptionPane.showInputDialog(builder);
                                    break;

                                case 4: //Login
                                    builder = gui.headerMenuUser(usuario);
                                    builder.append("\nInforme o novo login do usuário");
                                    att = JOptionPane.showInputDialog(builder);
                                    break;

                                case 5: //Senha
                                    builder = gui.headerMenuUser(usuario);
                                    builder.append("\nInforme a nova senha do usuário");
                                    att = JOptionPane.showInputDialog(builder);
                                    break;

                                case 6: //Nivel de acesso
                                    if(usuario.getTipoUser().equals("administrador"))
                                    {
                                        builder = gui.headerMenuUser(usuario);
                                        builder.append("\nInforme o novo nivel de acesso do usuário");
                                        builder.append("\n1. Administrador");
                                        builder.append("\n2. Instrutor");
                                        builder.append("\n3. Aluno");
                                        att = JOptionPane.showInputDialog(builder);
                                    }
                                    break;

                                case 0:
                                    continuar_edicao = -1;
                                    break;
                            
                                default:
                                    opc_edit = 0;
                                    break;
                            }

                            if(opc_edit != 0)
                            {
                                builder = gui.headerMenuUser(usuario);
                                builder.append(pessoaDAO.updatePessoa(opc_edit, updatepPessoa, att));
                                JOptionPane.showMessageDialog(null, builder);
                            }
                        }
                    }
                    catch (NumberFormatException e) 
                    {
                        JOptionPane.showMessageDialog(null, "O ID informado não é um número válido.");
                    }
                    
                    break;

                case 4: //Delete Pessoa
                    builder = gui.headerMenuUser(usuario);
                    builder.append(pessoaDAO.readPessoa(usuario));
                    builder.append("\nInforme o ID da pessoa que deseja deletar");
                    ID = Long.parseLong(JOptionPane.showInputDialog(builder));

                    builder = gui.headerMenuUser(usuario);
                    builder.append(pessoaDAO.deletePessoa(ID));
                    break;

                case 0: //Voltar
                    opc_menu_pessoa = -1;
                    break;
            
                default:
                    opc_menu_pessoa = 0;
                    break;
            }
        }
    }

}
