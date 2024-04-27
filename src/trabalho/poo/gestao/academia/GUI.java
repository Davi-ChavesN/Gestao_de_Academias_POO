/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.poo.gestao.academia;

import javax.swing.JOptionPane;

/**
 *
 * @author Davi Chaves
 *         Luan Sousa
 */
public class GUI {
    
    Pessoa usuarioLogado = new Pessoa();

    public StringBuilder headerMenuUser(Pessoa usuario)
    {
        StringBuilder builder = new StringBuilder("------ Menu de Usuário ------");
        builder.append("\nUsuário: ");
        builder.append(usuario.getNome());
        builder.append("\n" + usuario.getTipoUser() + "\n");

        return builder;
    }

    public int menuInicial()
    {
        int opc_menu;
        StringBuilder builder = new StringBuilder("");
        
        builder.append("------ Menu de Acesso ------");
        builder.append("\n1. Logar");
        builder.append("\n0. Sair");
        
        opc_menu = Integer.parseInt(JOptionPane.showInputDialog(builder));
        
        return opc_menu;
    }
    
    public Pessoa login(PessoaDAO pessoaDAO)
    {
        StringBuilder builder1 = new StringBuilder("");
        
        builder1.append("------ Menu de Login ------");
        builder1.append("\nInforme seu login");
        String login = JOptionPane.showInputDialog(builder1);

        StringBuilder builder2 = new StringBuilder("");
        
        builder2.append("------ Menu de Login ------");
        builder2.append("\nInforme sua senha");
        String senha = JOptionPane.showInputDialog(builder2);

        Pessoa p = pessoaDAO.verificaUsuario(login, senha);
        
        return p;
    }

    public int menuAdm(Pessoa usuario)
    {
        StringBuilder builder = headerMenuUser(usuario);
        builder.append("\n1. Editar academia");
        builder.append("\n2. CRUD Pessoa");
        builder.append("\n3. CRUD Exercício");
        builder.append("\n4. CRUD Treino");
        builder.append("\n5. Avaliação Física");
        builder.append("\n6. CRUD Mensalidade");
        builder.append("\n7. Movimentação Financeira");
        builder.append("\n8. Relatórios");
        builder.append("\n0. Sair");

        return Integer.parseInt(JOptionPane.showInputDialog(builder));
    }

    public int menuInstrutor(Pessoa usuario)
    {
        StringBuilder builder = headerMenuUser(usuario);
        builder.append("\n1. CRUD Aluno");
        builder.append("\n2. CRUD Exercício");
        builder.append("\n3. CRUD Treino");
        builder.append("\n4. Avaliação Física");
        builder.append("\n5. CRUD Mensalidade");
        builder.append("\n0. Sair");

        return Integer.parseInt(JOptionPane.showInputDialog(builder));
    }

    public int menuAluno(Pessoa usuario)
    {
        StringBuilder builder = headerMenuUser(usuario);
        builder.append("\n1. Ficha de Treino");
        builder.append("\n2. Ver Avaliação Física");
        builder.append("\n0. Sair");

        return Integer.parseInt(JOptionPane.showInputDialog(builder));
    }

    public void crudPessoa(Pessoa usuario, PessoaDAO pessoaDAO)
    {
        int opc = 0;
        String tipoUser = usuario.getTipoUser();
        StringBuilder menuBuilder = headerMenuUser(usuario);
        StringBuilder builder = headerMenuUser(usuario);

        if(tipoUser.equals("administrador"))
        {
            menuBuilder.append("\n1. Criar novo usuário");
            menuBuilder.append("\n2. Ver usuários");
            menuBuilder.append("\n3. Atualizar usuário");
            menuBuilder.append("\n4. Deletar usuário");
            menuBuilder.append("\n0. Voltar");
        }
        else if(tipoUser.equals("instrutor"))
        {
            menuBuilder.append("\n1. Criar novo aluno");
            menuBuilder.append("\n2. Ver alunos");
            menuBuilder.append("\n3. Atualizar aluno");
            menuBuilder.append("\n4. Deletar aluno");
            menuBuilder.append("\n0. Voltar");
        }
        

        while(opc != -1)
        {
            opc = Integer.parseInt(JOptionPane.showInputDialog(menuBuilder));

            if(opc == 1)
            {
                Pessoa p = new Pessoa();
                builder = headerMenuUser(usuario);
                builder.append("\nInforme o nome do novo usuário");
                p.setNomePessoa(JOptionPane.showInputDialog(builder));


                int sex;
                builder = headerMenuUser(usuario);
                builder.append("\nInforme o sexo do novo usuário");
                builder.append("\n1. Feminino");
                builder.append("\n2. Masculino");
                sex = Integer.parseInt(JOptionPane.showInputDialog(builder));
                if(sex == 1)
                {
                    p.setSexo('F');
                }
                else if(sex == 2)
                {
                    p.setSexo('M');
                }


                builder = headerMenuUser(usuario);
                builder.append("\nInforme a data de nascimento do novo usuário");
                p.setNascimento(JOptionPane.showInputDialog(builder));


                builder = headerMenuUser(usuario);
                builder.append("\nInforme o login do novo usuário");
                p.setLogin(JOptionPane.showInputDialog(builder));


                builder = headerMenuUser(usuario);
                builder.append("\nInforme a senha do novo usuário");
                p.setSenha(JOptionPane.showInputDialog(builder));

                if(tipoUser.equals("administrador"))
                {
                    builder = headerMenuUser(usuario);
                    builder.append("\nInforme o nivel de acesso do novo usuário");
                    builder.append("\n1. Administrador");
                    builder.append("\n2. Instrutor");
                    builder.append("\n3. Aluno");
                    p.setTipoUser(Integer.parseInt(JOptionPane.showInputDialog(builder)));
                }
                else if(tipoUser.equals("instrutor"))
                {
                    p.setTipoUser(3);
                }


                p.setDataID();

                pessoaDAO.adicionarPessoa(p);
            }
            else if(opc == 2)
            {
                builder = headerMenuUser(usuario);
                builder.append("\nUsuarios cadastrados");
                builder.append(pessoaDAO.mostrarUsuarios(usuario));
                JOptionPane.showMessageDialog(null, builder);
            }
            else if(opc == 3)
            {
                builder = headerMenuUser(usuario);
                builder.append("\nInforme o ID do usuário a ser editado");
                long id_edit = Long.parseLong(JOptionPane.showInputDialog(builder));

                builder = headerMenuUser(usuario);
                builder.append("\n--- Editar Usuários ---\n");
                builder.append("\n1. Editar nome");
                builder.append("\n2. Editar sexo");
                builder.append("\n3. Editar data de nascimento");
                builder.append("\n4. Editar login");
                builder.append("\n5. Editar senha");
                
                if(usuario.getTipoUser().equals("administrador"))
                {
                    builder.append("\n6. Editar tipo de usuário");
                }

                builder.append("\n0. Sair");

                int opc_edit = 0;
                while(opc_edit != -1)
                {
                    opc_edit = Integer.parseInt(JOptionPane.showInputDialog(builder));
                    StringBuilder editValue = headerMenuUser(usuario);

                    if (opc_edit == 1)
                    {
                        editValue.append("Informe o novo nome!");
                        String novo_nome = JOptionPane.showInputDialog(editValue);
                        pessoaDAO.editUser(opc_edit, id_edit, novo_nome);
                    }
                    else if(opc_edit == 2)
                    {
                        editValue.append("Informe o sexo!");
                        editValue.append("\n1. Masculino");
                        editValue.append("\n2. Feminino");
                        int sexOpc = Integer.parseInt(JOptionPane.showInputDialog(editValue));
                        if(sexOpc == 1)
                        {
                            String novo_sexo = "M";
                            pessoaDAO.editUser(opc_edit, id_edit, novo_sexo);
                        }
                        else if(sexOpc == 2)
                        {
                            String novo_sexo = "F";
                            pessoaDAO.editUser(opc_edit, id_edit, novo_sexo);
                        }
                        else
                        {
                            JOptionPane.showConfirmDialog(null, "Valor inválido!");
                        }
                    }
                    else if(opc_edit == 3)
                    {
                        editValue.append("Informe a data de nascimento! dd/mm/yyyy");
                        String nova_dt_nasc = JOptionPane.showInputDialog(editValue);
                        pessoaDAO.editUser(opc_edit, id_edit, nova_dt_nasc);
                    }
                    else if(opc_edit == 4)
                    {
                        editValue.append("Informe o novo login!");
                        String novo_login = JOptionPane.showInputDialog(editValue);
                        pessoaDAO.editUser(opc_edit, id_edit, novo_login);
                    }
                    else if(opc_edit == 5)
                    {
                        editValue.append("Informe a nova senha!");
                        String nova_senha = JOptionPane.showInputDialog(editValue);
                        pessoaDAO.editUser(opc_edit, id_edit, nova_senha);
                    }
                    else if(opc_edit == 6 && usuario.getTipoUser().equals("administrador"))
                    {
                        editValue.append("Informe a nova senha!");
                        editValue.append("\n1. Administrador");
                        editValue.append("\n2. Instrutor");
                        editValue.append("\n3. Aluno");
                        String novo_tipo_user = JOptionPane.showInputDialog(editValue);
                        pessoaDAO.editUser(opc_edit, id_edit, novo_tipo_user);
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
            else if(opc == 4)
            {
                builder = headerMenuUser(usuario);
                builder.append("\n--- Deletar Usuários ---\n");
                builder.append("\nInforme o ID do usuário a ser deletado");
                long IDdel = Long.parseLong(JOptionPane.showInputDialog(builder));
                while(IDdel == usuario.getID())
                {
                    builder = headerMenuUser(usuario);
                    builder.append("\nVocê não pode deletar seu próprio usuário!");
                    builder.append("\nInforme o ID do usuário a ser deletado");
                    IDdel = Long.parseLong(JOptionPane.showInputDialog(builder));
                }
                builder = headerMenuUser(usuario);
                builder.append(pessoaDAO.delUser(IDdel));
                JOptionPane.showMessageDialog(null, builder);
            }
            else if(opc == 0)
            {
                opc = -1;
            }
            else
            {
                StringBuilder builder2 = new StringBuilder();
                builder2 = headerMenuUser(usuario);
                builder2.append("Opção inválida!");
                JOptionPane.showMessageDialog(null, builder2);
            }
        }
    }

    
}
