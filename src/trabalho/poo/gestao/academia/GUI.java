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
        int opc;
        String tipoUser = usuario.getTipoUser();
        StringBuilder builder = headerMenuUser(usuario);

        if(tipoUser.equals("administrador"))
        {
            builder.append("\n1. Criar novo usuário");
            builder.append("\n2. Ver usuários");
            builder.append("\n3. Atualizar usuário");
            builder.append("\n4. Deletar usuário");
        }
        else if(tipoUser.equals("instrutor"))
        {
            builder.append("\n1. Criar novo aluno");
            builder.append("\n2. Ver alunos");
            builder.append("\n3. Atualizar aluno");
            builder.append("\n4. Deletar aluno");
        }
        

        opc = Integer.parseInt(JOptionPane.showInputDialog(builder));

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
            builder.append(pessoaDAO.mostrarUsuarios(usuario));
            JOptionPane.showMessageDialog(null, builder);
        }
        else if(opc == 3)
        {
            builder = headerMenuUser(usuario);
        }
        else if(opc == 4)
        {
            builder = headerMenuUser(usuario);
        }
        else
        {
            builder = headerMenuUser(usuario);
            builder.append("Opção inválida!");
            JOptionPane.showMessageDialog(null, builder);
        }
        
    }
}
