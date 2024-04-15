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
    
    PessoaDAO pessoaDAO = new PessoaDAO();
    Pessoa usuarioLogado = new Pessoa();

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
    
    public Pessoa login()
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
        StringBuilder builder = new StringBuilder("");
        builder.append("\nUsuário: ");
        builder.append(usuario.getNome());
        builder.append("\n" + usuario.getTipoUser() + "\n");
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
        StringBuilder builder = new StringBuilder("");
        builder.append("\nUsuário: ");
        builder.append(usuario.getNome());
        builder.append("\n" + usuario.getTipoUser() + "\n");
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
        StringBuilder builder = new StringBuilder("");
        builder.append("\nUsuário: ");
        builder.append(usuario.getNome());
        builder.append("\n" + usuario.getTipoUser() + "\n");
        builder.append("\n1. Ficha de Treino");
        builder.append("\n2. Ver Avaliação Física");
        builder.append("\n0. Sair");

        return Integer.parseInt(JOptionPane.showInputDialog(builder));
    }
}
