

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controller;

import javax.swing.JOptionPane;

import model.AcademiaDAO;
import model.AvaliacaoFisica;
import model.AvaliacaoFisicaDAO;
import model.EntradaAlunoDAO;
import model.Pessoa;
import model.PessoaDAO;

import view.GUI;


/**
 *
 * @author Davi Chaves
 *         Luan Sousa
 */
public class TrabalhoPOOGestaoAcademia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Criação de objeto GUI, o qual será utilizado para chamar as opções de I/O do programa
        GUI gui = new GUI();

        //Inicialização das DAOs, populando alguns dos objetos que serão criados
        ControllerMenuTreino controllerMenuTreino = new ControllerMenuTreino();
        ControllerMenuExercicio controllerMenuExercicio = new ControllerMenuExercicio();
        ControllerMenuMensalidade controllerMenuMensalidade = new ControllerMenuMensalidade();
        ControllerMenuAcademia controllerMenuAcademia = new ControllerMenuAcademia();

        PessoaDAO pessoaDAO = new PessoaDAO();
        AvaliacaoFisicaDAO avaliacaoFisicaDAO = new AvaliacaoFisicaDAO();
        EntradaAlunoDAO entradaAlunoDAO = new EntradaAlunoDAO();
        
        //Variáveis de controle do menu:
        //  exit ➞ variável de saída do programa
        //  menuExit ➞ variável de saída dos menus de usuários
        int exit = 0;
        int menuExit = 0;
        int opc_menu_inicial;
        
        //Loop do programa
        while(exit != -1)
        {
            //Receber a opção escolhida pelo usuário ao iniciar o programa
            opc_menu_inicial = gui.menuInicial();
            switch (opc_menu_inicial) {
                //Realizar o login do usuário, informando login e senha
                case 1:
                    //Objeto receptor do usuário que logou no sistema
                    Pessoa usuarioLogado = gui.login(pessoaDAO);
                    if(usuarioLogado.getTipoUser().equals("aluno"))
                    {
                        entradaAlunoDAO.createEntradaAluno();
                    }
                    //Variável de saída do menu, precisa ser iniciada com algum valor != -1 antes de entrar nos loops
                    menuExit = 0;
                    
                    //Menu do usuário com acesso de Administrador
                    if(usuarioLogado.getTipoUser().equals("administrador"))
                    {
                        while(menuExit != -1)
                        {
                            menuExit = gui.menuAdm(usuarioLogado);
                            switch (menuExit) 
                            {
                                case 1://Crud Academia
                                    controllerMenuAcademia.menuAcademia(usuarioLogado);
                                    break;

                                case 2://Crud Pessoa
                                    int opc_crud_pessoa = 0;
                                    while (opc_crud_pessoa != -1)
                                    {
                                        StringBuilder builder = new StringBuilder();
                                        String tipoUser = usuarioLogado.getTipoUser();
                                        opc_crud_pessoa = gui.crudPessoa(usuarioLogado);
                                        if(opc_crud_pessoa == 1)//Criar Usuário
                                        {
                                            Pessoa p = new Pessoa();
                                            builder = gui.headerMenuUser(usuarioLogado);
                                            builder.append("\nInforme o nome do novo usuário");
                                            p.setNomePessoa(JOptionPane.showInputDialog(builder));


                                            int sex;
                                            builder = gui.headerMenuUser(usuarioLogado);
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


                                            builder = gui.headerMenuUser(usuarioLogado);
                                            builder.append("\nInforme a data de nascimento do novo usuário");
                                            p.setNascimento(JOptionPane.showInputDialog(builder));


                                            builder = gui.headerMenuUser(usuarioLogado);
                                            builder.append("\nInforme o login do novo usuário");
                                            p.setLogin(JOptionPane.showInputDialog(builder));


                                            builder = gui.headerMenuUser(usuarioLogado);
                                            builder.append("\nInforme a senha do novo usuário");
                                            p.setSenha(JOptionPane.showInputDialog(builder));

                                            if(tipoUser.equals("administrador"))
                                            {
                                                builder = gui.headerMenuUser(usuarioLogado);
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
                                        else if(opc_crud_pessoa == 2)//Mostrar Usuários
                                        {
                                            builder = gui.headerMenuUser(usuarioLogado);
                                            builder.append("\nUsuarios cadastrados");
                                            builder.append(pessoaDAO.mostrarUsuarios(usuarioLogado));
                                            JOptionPane.showMessageDialog(null, builder);
                                        }
                                        else if(opc_crud_pessoa == 3)//Atualizar Usuário
                                        {
                                            builder = gui.headerMenuUser(usuarioLogado);
                                            builder.append("\nInforme o ID do usuário a ser editado");
                                            long id_edit = Long.parseLong(JOptionPane.showInputDialog(builder));

                                            builder = gui.headerMenuUser(usuarioLogado);
                                            builder.append("\n--- Editar Usuários ---\n");
                                            builder.append("\n1. Editar nome");
                                            builder.append("\n2. Editar sexo");
                                            builder.append("\n3. Editar data de nascimento");
                                            builder.append("\n4. Editar login");
                                            builder.append("\n5. Editar senha");
                                            
                                            if(usuarioLogado.getTipoUser().equals("administrador"))
                                            {
                                                builder.append("\n6. Editar tipo de usuário");
                                            }

                                            builder.append("\n0. Sair");

                                            int opc_edit = 0;
                                            while(opc_edit != -1)
                                            {
                                                opc_edit = Integer.parseInt(JOptionPane.showInputDialog(builder));
                                                StringBuilder editValue = gui.headerMenuUser(usuarioLogado);

                                                if (opc_edit == 1)//Editar nome do Usuário
                                                {
                                                    editValue.append("\nInforme o novo nome!");
                                                    String novo_nome = JOptionPane.showInputDialog(editValue);
                                                    editValue = gui.headerMenuUser(usuarioLogado);
                                                    editValue.append(pessoaDAO.editUser(opc_edit, id_edit, novo_nome));
                                                    JOptionPane.showMessageDialog(null, editValue);
                                                }
                                                else if(opc_edit == 2)//Editar sexo do Usuário
                                                {
                                                    editValue.append("\nInforme o sexo!");
                                                    editValue.append("\n1. Masculino");
                                                    editValue.append("\n2. Feminino");
                                                    int sexOpc = Integer.parseInt(JOptionPane.showInputDialog(editValue));
                                                    if(sexOpc == 1)
                                                    {
                                                        String novo_sexo = "M";
                                                        editValue = gui.headerMenuUser(usuarioLogado);
                                                        editValue.append(pessoaDAO.editUser(opc_edit, id_edit, novo_sexo));
                                                    }
                                                    else if(sexOpc == 2)
                                                    {
                                                        String novo_sexo = "F";
                                                        editValue = gui.headerMenuUser(usuarioLogado);
                                                        editValue.append(pessoaDAO.editUser(opc_edit, id_edit, novo_sexo));
                                                    }
                                                    else
                                                    {
                                                        JOptionPane.showConfirmDialog(null, "Valor inválido!");
                                                    }
                                                }
                                                else if(opc_edit == 3)//Editar data de nascimento do Usuário
                                                {
                                                    editValue.append("\nInforme a data de nascimento! dd/mm/yyyy");
                                                    String nova_dt_nasc = JOptionPane.showInputDialog(editValue);
                                                    editValue = gui.headerMenuUser(usuarioLogado);
                                                    editValue.append(pessoaDAO.editUser(opc_edit, id_edit, nova_dt_nasc));
                                                }
                                                else if(opc_edit == 4)//Editar login do Usuário
                                                {
                                                    editValue.append("Informe o novo login!");
                                                    String novo_login = JOptionPane.showInputDialog(editValue);
                                                    editValue = gui.headerMenuUser(usuarioLogado);
                                                    editValue.append(pessoaDAO.editUser(opc_edit, id_edit, novo_login));
                                                }
                                                else if(opc_edit == 5)//Editar senha do Usuário
                                                {
                                                    editValue.append("\nInforme a nova senha!");
                                                    String nova_senha = JOptionPane.showInputDialog(editValue);
                                                    editValue = gui.headerMenuUser(usuarioLogado);
                                                    editValue.append(pessoaDAO.editUser(opc_edit, id_edit, nova_senha));
                                                }
                                                else if(opc_edit == 6 && usuarioLogado.getTipoUser().equals("administrador"))//Editar nivel de acesso do Usuário
                                                {
                                                    editValue.append("\nInforme a nova senha!");
                                                    editValue.append("\n1. Administrador");
                                                    editValue.append("\n2. Instrutor");
                                                    editValue.append("\n3. Aluno");
                                                    String novo_tipo_user = JOptionPane.showInputDialog(editValue);
                                                    editValue = gui.headerMenuUser(usuarioLogado);
                                                    editValue.append(pessoaDAO.editUser(opc_edit, id_edit, novo_tipo_user));
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
                                        else if(opc_crud_pessoa == 4)//Deletar Usuário
                                        {
                                            builder = gui.headerMenuUser(usuarioLogado);
                                            builder.append("\n--- Deletar Usuários ---\n");
                                            builder.append("\nInforme o ID do usuário a ser deletado");
                                            long IDdel = Long.parseLong(JOptionPane.showInputDialog(builder));
                                            while(IDdel == usuarioLogado.getID())
                                            {
                                                builder = gui.headerMenuUser(usuarioLogado);
                                                builder.append("\nVocê não pode deletar seu próprio usuário!");
                                                builder.append("\nInforme o ID do usuário a ser deletado");
                                                IDdel = Long.parseLong(JOptionPane.showInputDialog(builder));
                                            }
                                            builder = gui.headerMenuUser(usuarioLogado);
                                            builder.append(pessoaDAO.delUser(IDdel));
                                            JOptionPane.showMessageDialog(null, builder);
                                        }
                                        else if(opc_crud_pessoa == 0)
                                        {
                                            opc_crud_pessoa = -1;
                                        }
                                        else
                                        {
                                            StringBuilder builder2 = new StringBuilder();
                                            builder2 = gui.headerMenuUser(usuarioLogado);
                                            builder2.append("Opção inválida!");
                                            JOptionPane.showMessageDialog(null, builder2);
                                        }
                                    }
                                    break;

                                case 3://Crud Exercicio
                                    controllerMenuExercicio.menuExercicio(usuarioLogado);
                                    break;

                                case 4://Crud Treino
                                    controllerMenuTreino.menuTreino(usuarioLogado);
                                    break;

                                case 5://Avaliação Física
                                    int opc_avaliacao_fisica = 0;
                                    String tipo_user = usuarioLogado.getTipoUser();

                                    while (opc_avaliacao_fisica != -1)
                                    {
                                        opc_avaliacao_fisica = gui.crudAvaliacaoFisica(usuarioLogado);

                                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);

                                        if(opc_avaliacao_fisica == 1)
                                        {
                                            builder.append(avaliacaoFisicaDAO.mostrarAvaliacaoFisica(usuarioLogado));
                                            JOptionPane.showMessageDialog(null, builder);
                                        }
                                        else if(opc_avaliacao_fisica == 2 && tipo_user != "aluno")
                                        {
                                            builder.append(avaliacaoFisicaDAO.mostrarTodasAvaliacoesFisicas());
                                            JOptionPane.showMessageDialog(null, builder);
                                        }
                                        else if(opc_avaliacao_fisica == 2 && tipo_user == "aluno")
                                        {

                                        }
                                        else if(opc_avaliacao_fisica == 3 && tipo_user != "aluno")
                                        {
                                            Pessoa usuarioAF = new Pessoa();
                                            usuarioAF = null;
                                            while(usuarioAF == null)
                                            {
                                                usuarioAF = pessoaDAO.pegaUsuario(Long.parseLong(JOptionPane.showInputDialog("\nInforme o ID do usuário que realizará a AF")));
                                            }

                                            float peso, altura;
                                            peso = Float.parseFloat(JOptionPane.showInputDialog("\nInforme o peso"));
                                            altura = Float.parseFloat(JOptionPane.showInputDialog("\nInforme a altura"));

                                            AvaliacaoFisica af = (avaliacaoFisicaDAO.novaAvaliacaoFisica(usuarioAF, peso, altura));
                                            builder.append(avaliacaoFisicaDAO.mostrarAvaliacaoFisica(usuarioAF));
                                            JOptionPane.showMessageDialog(null, builder);

                                            float nota = -1;
                                            while(nota < 0 || nota > 10)
                                            {
                                                nota = Float.parseFloat(JOptionPane.showInputDialog("\nDeixe uma nota para a avaliação fisica"));
                                            }
                                            avaliacaoFisicaDAO.notaAvaliacaoFisica(af, nota);
                                        }
                                        else if(opc_avaliacao_fisica == 0)
                                        {
                                            opc_avaliacao_fisica = -1;
                                        }
                                        else
                                        {
                                            opc_avaliacao_fisica = 0;
                                        }
                                    }
                                    break;

                                case 6://Crud Mensalidade
                                    controllerMenuMensalidade.menuMensalidade(usuarioLogado);
                                    break;

                                case 7://Movimentação Financeira
                                    
                                    break;

                                case 8://Relatórios
                                    
                                    break;

                                case 0://Saída do Menu Administrador
                                    menuExit = -1;
                                    break;
                            
                                default:
                                    menuExit = 0;
                                    break;
                            }
                        }
                    }
                    //Menu do usuário com acesso de Instrutor
                    else if(usuarioLogado.getTipoUser().equals("instrutor"))
                    {
                        while(menuExit != -1)
                        {
                            menuExit = gui.menuInstrutor(usuarioLogado);

                            switch (menuExit) {
                                case 1://Crud Aluno
                                    int opc_crud_pessoa = 0;
                                    while (opc_crud_pessoa != -1)
                                    {
                                        StringBuilder builder = new StringBuilder();
                                        String tipoUser = usuarioLogado.getTipoUser();
                                        opc_crud_pessoa = gui.crudPessoa(usuarioLogado);
                                        if(opc_crud_pessoa == 1)//Criar Usuário
                                        {
                                            Pessoa p = new Pessoa();
                                            builder = gui.headerMenuUser(usuarioLogado);
                                            builder.append("\nInforme o nome do novo usuário");
                                            p.setNomePessoa(JOptionPane.showInputDialog(builder));


                                            int sex;
                                            builder = gui.headerMenuUser(usuarioLogado);
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


                                            builder = gui.headerMenuUser(usuarioLogado);
                                            builder.append("\nInforme a data de nascimento do novo usuário");
                                            p.setNascimento(JOptionPane.showInputDialog(builder));


                                            builder = gui.headerMenuUser(usuarioLogado);
                                            builder.append("\nInforme o login do novo usuário");
                                            p.setLogin(JOptionPane.showInputDialog(builder));


                                            builder = gui.headerMenuUser(usuarioLogado);
                                            builder.append("\nInforme a senha do novo usuário");
                                            p.setSenha(JOptionPane.showInputDialog(builder));

                                            if(tipoUser.equals("administrador"))
                                            {
                                                builder = gui.headerMenuUser(usuarioLogado);
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
                                        else if(opc_crud_pessoa == 2)//Mostrar Usuários
                                        {
                                            builder = gui.headerMenuUser(usuarioLogado);
                                            builder.append("\nUsuarios cadastrados");
                                            builder.append(pessoaDAO.mostrarUsuarios(usuarioLogado));
                                            JOptionPane.showMessageDialog(null, builder);
                                        }
                                        else if(opc_crud_pessoa == 3)//Atualizar Usuário
                                        {
                                            builder = gui.headerMenuUser(usuarioLogado);
                                            builder.append("\nInforme o ID do usuário a ser editado");
                                            long id_edit = Long.parseLong(JOptionPane.showInputDialog(builder));

                                            builder = gui.headerMenuUser(usuarioLogado);
                                            builder.append("\n--- Editar Usuários ---\n");
                                            builder.append("\n1. Editar nome");
                                            builder.append("\n2. Editar sexo");
                                            builder.append("\n3. Editar data de nascimento");
                                            builder.append("\n4. Editar login");
                                            builder.append("\n5. Editar senha");
                                            
                                            if(usuarioLogado.getTipoUser().equals("administrador"))
                                            {
                                                builder.append("\n6. Editar tipo de usuário");
                                            }

                                            builder.append("\n0. Sair");

                                            int opc_edit = 0;
                                            while(opc_edit != -1)
                                            {
                                                opc_edit = Integer.parseInt(JOptionPane.showInputDialog(builder));
                                                StringBuilder editValue = gui.headerMenuUser(usuarioLogado);

                                                if (opc_edit == 1)//Editar nome do Usuário
                                                {
                                                    editValue.append("\nInforme o novo nome!");
                                                    String novo_nome = JOptionPane.showInputDialog(editValue);
                                                    editValue = gui.headerMenuUser(usuarioLogado);
                                                    editValue.append(pessoaDAO.editUser(opc_edit, id_edit, novo_nome));
                                                    JOptionPane.showMessageDialog(null, editValue);
                                                }
                                                else if(opc_edit == 2)//Editar sexo do Usuário
                                                {
                                                    editValue.append("\nInforme o sexo!");
                                                    editValue.append("\n1. Masculino");
                                                    editValue.append("\n2. Feminino");
                                                    int sexOpc = Integer.parseInt(JOptionPane.showInputDialog(editValue));
                                                    if(sexOpc == 1)
                                                    {
                                                        String novo_sexo = "M";
                                                        editValue = gui.headerMenuUser(usuarioLogado);
                                                        editValue.append(pessoaDAO.editUser(opc_edit, id_edit, novo_sexo));
                                                    }
                                                    else if(sexOpc == 2)
                                                    {
                                                        String novo_sexo = "F";
                                                        editValue = gui.headerMenuUser(usuarioLogado);
                                                        editValue.append(pessoaDAO.editUser(opc_edit, id_edit, novo_sexo));
                                                    }
                                                    else
                                                    {
                                                        JOptionPane.showConfirmDialog(null, "Valor inválido!");
                                                    }
                                                }
                                                else if(opc_edit == 3)//Editar data de nascimento do Usuário
                                                {
                                                    editValue.append("\nInforme a data de nascimento! dd/mm/yyyy");
                                                    String nova_dt_nasc = JOptionPane.showInputDialog(editValue);
                                                    editValue = gui.headerMenuUser(usuarioLogado);
                                                    editValue.append(pessoaDAO.editUser(opc_edit, id_edit, nova_dt_nasc));
                                                }
                                                else if(opc_edit == 4)//Editar login do Usuário
                                                {
                                                    editValue.append("Informe o novo login!");
                                                    String novo_login = JOptionPane.showInputDialog(editValue);
                                                    editValue = gui.headerMenuUser(usuarioLogado);
                                                    editValue.append(pessoaDAO.editUser(opc_edit, id_edit, novo_login));
                                                }
                                                else if(opc_edit == 5)//Editar senha do Usuário
                                                {
                                                    editValue.append("\nInforme a nova senha!");
                                                    String nova_senha = JOptionPane.showInputDialog(editValue);
                                                    editValue = gui.headerMenuUser(usuarioLogado);
                                                    editValue.append(pessoaDAO.editUser(opc_edit, id_edit, nova_senha));
                                                }
                                                else if(opc_edit == 6 && usuarioLogado.getTipoUser().equals("administrador"))//Editar nivel de acesso do Usuário
                                                {
                                                    editValue.append("\nInforme a nova senha!");
                                                    editValue.append("\n1. Administrador");
                                                    editValue.append("\n2. Instrutor");
                                                    editValue.append("\n3. Aluno");
                                                    String novo_tipo_user = JOptionPane.showInputDialog(editValue);
                                                    editValue = gui.headerMenuUser(usuarioLogado);
                                                    editValue.append(pessoaDAO.editUser(opc_edit, id_edit, novo_tipo_user));
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
                                        else if(opc_crud_pessoa == 4)//Deletar Usuário
                                        {
                                            builder = gui.headerMenuUser(usuarioLogado);
                                            builder.append("\n--- Deletar Usuários ---\n");
                                            builder.append("\nInforme o ID do usuário a ser deletado");
                                            long IDdel = Long.parseLong(JOptionPane.showInputDialog(builder));
                                            while(IDdel == usuarioLogado.getID())
                                            {
                                                builder = gui.headerMenuUser(usuarioLogado);
                                                builder.append("\nVocê não pode deletar seu próprio usuário!");
                                                builder.append("\nInforme o ID do usuário a ser deletado");
                                                IDdel = Long.parseLong(JOptionPane.showInputDialog(builder));
                                            }
                                            builder = gui.headerMenuUser(usuarioLogado);
                                            builder.append(pessoaDAO.delUser(IDdel));
                                            JOptionPane.showMessageDialog(null, builder);
                                        }
                                        else if(opc_crud_pessoa == 0)
                                        {
                                            opc_crud_pessoa = -1;
                                        }
                                        else
                                        {
                                            StringBuilder builder2 = new StringBuilder();
                                            builder2 = gui.headerMenuUser(usuarioLogado);
                                            builder2.append("Opção inválida!");
                                            JOptionPane.showMessageDialog(null, builder2);
                                        }
                                    }
                                    break;

                                case 2://Crud Exercicio
                                    controllerMenuExercicio.menuExercicio(usuarioLogado);
                                    break;

                                case 3://Crud Treino
                                    controllerMenuTreino.menuTreino(usuarioLogado);
                                    break;

                                case 4://Avaliação Física
                                    int opc_avaliacao_fisica = 0;
                                    String tipo_user = usuarioLogado.getTipoUser();

                                    while (opc_avaliacao_fisica != -1)
                                    {
                                        opc_avaliacao_fisica = gui.crudAvaliacaoFisica(usuarioLogado);

                                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);

                                        if(opc_avaliacao_fisica == 1)
                                        {
                                            builder.append(avaliacaoFisicaDAO.mostrarAvaliacaoFisica(usuarioLogado));
                                            JOptionPane.showMessageDialog(null, builder);
                                        }
                                        else if(opc_avaliacao_fisica == 2 && tipo_user != "aluno")
                                        {
                                            builder.append(avaliacaoFisicaDAO.mostrarTodasAvaliacoesFisicas());
                                            JOptionPane.showMessageDialog(null, builder);
                                        }
                                        else if(opc_avaliacao_fisica == 2 && tipo_user == "aluno")
                                        {

                                        }
                                        else if(opc_avaliacao_fisica == 3 && tipo_user != "aluno")
                                        {
                                            Pessoa usuarioAF = new Pessoa();
                                            usuarioAF = null;
                                            while(usuarioAF == null)
                                            {
                                                usuarioAF = pessoaDAO.pegaUsuario(Long.parseLong(JOptionPane.showInputDialog("\nInforme o ID do usuário que realizará a AF")));
                                            }

                                            float peso, altura;
                                            peso = Float.parseFloat(JOptionPane.showInputDialog("\nInforme o peso"));
                                            altura = Float.parseFloat(JOptionPane.showInputDialog("\nInforme a altura"));

                                            AvaliacaoFisica af = (avaliacaoFisicaDAO.novaAvaliacaoFisica(usuarioAF, peso, altura));
                                            builder.append(avaliacaoFisicaDAO.mostrarAvaliacaoFisica(usuarioAF));
                                            JOptionPane.showMessageDialog(null, builder);

                                            float nota = -1;
                                            while(nota < 0 || nota > 10)
                                            {
                                                nota = Float.parseFloat(JOptionPane.showInputDialog("\nDeixe uma nota para a avaliação fisica"));
                                            }
                                            avaliacaoFisicaDAO.notaAvaliacaoFisica(af, nota);
                                        }
                                        else if(opc_avaliacao_fisica == 0)
                                        {
                                            opc_avaliacao_fisica = -1;
                                        }
                                        else
                                        {
                                            opc_avaliacao_fisica = 0;
                                        }
                                    }
                                    break;

                                case 5://Crud Mensalidade
                                    controllerMenuMensalidade.menuMensalidade(usuarioLogado);
                                    break;

                                case 0://Saída do Menu Instrutor
                                    menuExit = -1;
                                    break;
                            
                                default:
                                    menuExit = 0;
                                    break;
                            }
                        }
                    }
                    //Menu do usuário com acesso de Aluno
                    else if(usuarioLogado.getTipoUser().equals("aluno"))
                    {
                        while(menuExit != -1)
                        {
                            menuExit = gui.menuAluno(usuarioLogado);

                            switch (menuExit) {
                                case 1://Ficha de Treino
                                    
                                    break;

                                case 2://Visualização Avaliação Física
                                    int opc_avaliacao_fisica = 0;
                                    String tipo_user = usuarioLogado.getTipoUser();

                                    while (opc_avaliacao_fisica != -1)
                                    {
                                        opc_avaliacao_fisica = gui.crudAvaliacaoFisica(usuarioLogado);

                                        StringBuilder builder = gui.headerMenuUser(usuarioLogado);

                                        if(opc_avaliacao_fisica == 1)
                                        {
                                            builder.append(avaliacaoFisicaDAO.mostrarAvaliacaoFisica(usuarioLogado));
                                            JOptionPane.showMessageDialog(null, builder);
                                        }
                                        else if(opc_avaliacao_fisica == 2 && tipo_user != "aluno")
                                        {
                                            builder.append(avaliacaoFisicaDAO.mostrarTodasAvaliacoesFisicas());
                                            JOptionPane.showMessageDialog(null, builder);
                                        }
                                        else if(opc_avaliacao_fisica == 2 && tipo_user == "aluno")
                                        {

                                        }
                                        else if(opc_avaliacao_fisica == 3 && tipo_user != "aluno")
                                        {
                                            Pessoa usuarioAF = new Pessoa();
                                            usuarioAF = null;
                                            while(usuarioAF == null)
                                            {
                                                usuarioAF = pessoaDAO.pegaUsuario(Long.parseLong(JOptionPane.showInputDialog("\nInforme o ID do usuário que realizará a AF")));
                                            }

                                            float peso, altura;
                                            peso = Float.parseFloat(JOptionPane.showInputDialog("\nInforme o peso"));
                                            altura = Float.parseFloat(JOptionPane.showInputDialog("\nInforme a altura"));

                                            AvaliacaoFisica af = (avaliacaoFisicaDAO.novaAvaliacaoFisica(usuarioAF, peso, altura));
                                            builder.append(avaliacaoFisicaDAO.mostrarAvaliacaoFisica(usuarioAF));
                                            JOptionPane.showMessageDialog(null, builder);

                                            float nota = -1;
                                            while(nota < 0 || nota > 10)
                                            {
                                                nota = Float.parseFloat(JOptionPane.showInputDialog("\nDeixe uma nota para a avaliação fisica"));
                                            }
                                            avaliacaoFisicaDAO.notaAvaliacaoFisica(af, nota);
                                        }
                                        else if(opc_avaliacao_fisica == 0)
                                        {
                                            opc_avaliacao_fisica = -1;
                                        }
                                        else
                                        {
                                            opc_avaliacao_fisica = 0;
                                        }
                                    }
                                    break;

                                case 0://Saída do Menu Aluno
                                    menuExit = -1;
                                    break;
                            
                                default:
                                    menuExit = 0;
                                    break;
                            }
                        }
                    }
                    break;
                    
                //Saída do programa
                case 0:
                    exit = -1;
                    break;
                    
                //Valor DEFAULT mantém o programa rodando até que seja informado o valor correto para a saída
                default:
                    exit = 0;
            }
        }
        
        
                        
    }
}
