/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.JOptionPane;

import model.Academia;
import model.AcademiaDAO;
import model.Pessoa;
import model.PessoaDAO;

/**
 *
 * @author Davi Chaves
 *         Luan Sousa
 */
public class GUI {
    
    AcademiaDAO academiaDAO = new AcademiaDAO();
    Academia academia = academiaDAO.retornaAcademia();
    Pessoa usuarioLogado = new Pessoa();

    public StringBuilder headerMenuUser(Pessoa usuario)
    {
        StringBuilder builder = new StringBuilder("---------- ----- ----------");
        builder.append("\nUsuário: " + usuario.getNome());
        builder.append("\n" + usuario.getTipoUser());
        builder.append("\n" + academia.getCalend() + "\n");
        builder.append("---------- ----- ----------");

        return builder;
    }

    public int menuInicial()
    {
        int opc_menu = -1;
        StringBuilder builder = new StringBuilder("");
        

        builder.append("------ Menu de Acesso ------");
        builder.append("\n1. Logar");
        builder.append("\n0. Sair");
        
        opc_menu = Integer.parseInt(JOptionPane.showInputDialog(builder));
        
        
        return opc_menu;
    }
    
    public Pessoa login()
    {
        PessoaDAO pessoaDAO = new PessoaDAO();
        Pessoa p = null;
        StringBuilder builder = new StringBuilder("");
        
        while(p == null)
        {
            builder = new StringBuilder("");
            builder.append("------ Menu de Login ------");
            builder.append("\nInforme seu login");
            String login = JOptionPane.showInputDialog(builder);

            builder = new StringBuilder("");
            builder.append("------ Menu de Login ------");
            builder.append("\nInforme sua senha");
            String senha = JOptionPane.showInputDialog(builder);

            p = pessoaDAO.verificaUsuario(login, senha);
            if(p == null)
            {
                builder = new StringBuilder("\nUsuário ou senha inválidos!");
                JOptionPane.showMessageDialog(null, builder);
            }
        }
        
        return p;
    }

    public int menuAdm(Pessoa usuario)
    {
        StringBuilder builder = headerMenuUser(usuario);
        builder.append("\n1. Editar academia");
        builder.append("\n2. CRUD Pessoa");
        builder.append("\n3. Menu de Exercícios");
        builder.append("\n4. Menu de Treinos");
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
        builder.append("\n2. Menu de Exercícios");
        builder.append("\n3. Menu de Treinos");
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

    public int menuAcademia(Pessoa usuario)
    {
        StringBuilder menuBuilder = headerMenuUser(usuario);

        menuBuilder.append("\n1. Ver dados da Academia");
        menuBuilder.append("\n2. Alterar nome da Academia");
        menuBuilder.append("\n3. Alterar endereço da Academia");
        menuBuilder.append("\n4. Alterar data Calendario");
        menuBuilder.append("\n5. Mostrar data Calendario");
        menuBuilder.append("\n6. Mostrar log de Entrada de Alunos");
        menuBuilder.append("\n0. Voltar");

        return Integer.parseInt(JOptionPane.showInputDialog(menuBuilder));
    }

    public int menuCrudPessoa(Pessoa usuario)
    {
        String tipoUser = usuario.getTipoUser();
        StringBuilder menuBuilder = headerMenuUser(usuario);

        if(tipoUser.equals("administrador"))
        {
            menuBuilder.append("\n1. Criar novo usuário");
            menuBuilder.append("\n2. Ver usuários");
            menuBuilder.append("\n3. Atualizar usuário");
            menuBuilder.append("\n4. Deletar usuário");
        }
        else if(tipoUser.equals("instrutor"))
        {
            menuBuilder.append("\n1. Criar novo aluno");
            menuBuilder.append("\n2. Ver alunos");
            menuBuilder.append("\n3. Atualizar aluno");
            menuBuilder.append("\n4. Deletar aluno");
        }
        menuBuilder.append("\n0. Voltar");
        
        return Integer.parseInt(JOptionPane.showInputDialog(menuBuilder));
    }

    public int menuExercicio(Pessoa usuario)
    {
        StringBuilder menuBuilder = headerMenuUser(usuario);

        menuBuilder.append("\n1. CRUD Exercicio");
        menuBuilder.append("\n2. CRUD Exercicio Aplicação");
        menuBuilder.append("\n0. Voltar");

        return Integer.parseInt(JOptionPane.showInputDialog(menuBuilder));
    }

    public int crudExercicio(Pessoa usuario)
    {
        StringBuilder menuBuilder = headerMenuUser(usuario);

        menuBuilder.append("\n1. Adicionar exercício");
        menuBuilder.append("\n2. Ver exercícios");
        menuBuilder.append("\n3. Atualizar exercícios");
        menuBuilder.append("\n4. Deletar exercício");
        menuBuilder.append("\n0. Voltar");

        return Integer.parseInt(JOptionPane.showInputDialog(menuBuilder));
    }

    public int crudExercicioAplicacao(Pessoa usuario)
    {
        StringBuilder menuBuilder = headerMenuUser(usuario);

        menuBuilder.append("\n1. Adicionar Aplicação de Exercício");
        menuBuilder.append("\n2. Ver Aplicações dos Exercícios");
        menuBuilder.append("\n3. Atualizar Aplicação de um Exercício");
        menuBuilder.append("\n4. Deletar Aplicação de Exercício");
        menuBuilder.append("\n0. Voltar");

        return Integer.parseInt(JOptionPane.showInputDialog(menuBuilder));
    }
    
    public int crudAvaliacaoFisica(Pessoa usuario)
    {
        StringBuilder menubuilder = new StringBuilder();

        String tipo_user = usuario.getTipoUser();

        menubuilder = headerMenuUser(usuario);
        menubuilder.append("\n1. Ver avaliação física");

        if(tipo_user != "aluno")
        {
            menubuilder.append("\n2. Ver todas as avaliações físicas");
            menubuilder.append("\n3. Iniciar nova avaliação física");
        }
        menubuilder.append("\n0. Voltar");

        return Integer.parseInt(JOptionPane.showInputDialog(menubuilder));
    }

    public int menuTreino(Pessoa usuario)
    {
        StringBuilder menuBuilder = headerMenuUser(usuario);

        menuBuilder.append("\n1. CRUD Divisão de Treino");
        menuBuilder.append("\n2. CRUD Divisão de Treino-Musculo");
        menuBuilder.append("\n3. CRUD Treino");
        menuBuilder.append("\n4. CRUD Treino Aplicação");
        menuBuilder.append("\n0. Voltar");

        return Integer.parseInt(JOptionPane.showInputDialog(menuBuilder));
    }

    public int crudDivisaoTreino(Pessoa usuario)
    {
        StringBuilder menuBuilder = headerMenuUser(usuario);

        menuBuilder.append("\n1. Adicionar Divisão de Treino");
        menuBuilder.append("\n2. Ver Divisões de Treino");
        menuBuilder.append("\n3. Atualizar Divisões de Treino");
        menuBuilder.append("\n4. Excluir Divisão de Treino");
        menuBuilder.append("\n0. Voltar");

        return Integer.parseInt(JOptionPane.showInputDialog(menuBuilder));
    }

    public int crudDivisaoTreinoMusculo(Pessoa usuario)
    {
        StringBuilder menuBuilder = headerMenuUser(usuario);

        menuBuilder.append("\n1. Adicionar Divisão de Treino-Musculo");
        menuBuilder.append("\n2. Ver Divisões de Treino-Musculo");
        menuBuilder.append("\n3. Atualizar Divisões de Treino-Musculo");
        menuBuilder.append("\n4. Excluir Divisão de Treino-Musculo");
        menuBuilder.append("\n0. Voltar");

        return Integer.parseInt(JOptionPane.showInputDialog(menuBuilder));
    }

    public int crudTreino(Pessoa usuario)
    {
        StringBuilder menuBuilder = headerMenuUser(usuario);

        menuBuilder.append("\n1. Adicionar Treino");
        menuBuilder.append("\n2. Ver Treinos");
        menuBuilder.append("\n3. Atualizar Treino");
        menuBuilder.append("\n4. Excluir Treino");
        menuBuilder.append("\n0. Voltar");

        return Integer.parseInt(JOptionPane.showInputDialog(menuBuilder));
    }

    public int crudTreinoAplicacao(Pessoa usuario)
    {
        StringBuilder menuBuilder = headerMenuUser(usuario);

        menuBuilder.append("\n1. Adicionar Aplicação de Treino");
        menuBuilder.append("\n2. Ver Aplicações de Treino");
        menuBuilder.append("\n3. Atualizar Aplicação de Treino");
        menuBuilder.append("\n4. Excluir Alpicação de Treino");
        menuBuilder.append("\n0. Voltar");

        return Integer.parseInt(JOptionPane.showInputDialog(menuBuilder));
    }

    public int menuFinanceiro(Pessoa usuario)
    {
        StringBuilder menuBuilder = headerMenuUser(usuario);

        menuBuilder.append("\n1. CRUD Mensalidade Vigente");
        menuBuilder.append("\n2. CRUD Pagamento Mensalidade Aluno");
        menuBuilder.append("\n3. CRUD Pagamento Recorrente");
        menuBuilder.append("\n0. Voltar");

        return Integer.parseInt(JOptionPane.showInputDialog(menuBuilder));
    }

    public int crudMensalidadeVigente(Pessoa usuario)
    {
        StringBuilder menuBuilder = headerMenuUser(usuario);

        menuBuilder.append("\n1. Adicionar Mensalidade Vigente");
        menuBuilder.append("\n2. Ver Mensalidades Vigente");
        menuBuilder.append("\n3. Atualizar Mensalidade Vigente");
        menuBuilder.append("\n4. Excluir Mensalidade Vigente");
        menuBuilder.append("\n0. Voltar");

        return Integer.parseInt(JOptionPane.showInputDialog(menuBuilder));
    }

    public int crudPagamentoMensalidadeAluno(Pessoa usuario)
    {
        StringBuilder menuBuilder = headerMenuUser(usuario);

        menuBuilder.append("\n1. Adicionar Pagamento da Mensalidade de Aluno");
        menuBuilder.append("\n2. Ver Pagamentos da Mensalidade de Aluno");
        menuBuilder.append("\n3. Atualizar Pagamento da Mensalidade de Aluno");
        menuBuilder.append("\n4. Excluir Pagamento da Mensalidade de Aluno");
        menuBuilder.append("\n0. Voltar");

        return Integer.parseInt(JOptionPane.showInputDialog(menuBuilder));
    }

    public int crudPagamentoRecorrente(Pessoa usuario)
    {
        StringBuilder menuBuilder = headerMenuUser(usuario);

        menuBuilder.append("\n1. Adicionar Pagamento Recorrente");
        menuBuilder.append("\n2. Ver Pagamentos Recorrentes");
        menuBuilder.append("\n3. Atualizar Pagamento Recorrente");
        menuBuilder.append("\n4. Excluir Pagamento Recorrente");
        menuBuilder.append("\n0. Voltar");

        return Integer.parseInt(JOptionPane.showInputDialog(menuBuilder));
    }

    public int crudMovimentacaoFinanceira(Pessoa usuario)
    {
        StringBuilder menuBuilder = headerMenuUser(usuario);

        menuBuilder.append("\n1. Criar movimentação financeira");
        menuBuilder.append("\n2. Ver movimentações financeiras");
        menuBuilder.append("\n3. Atualizar movimentação financeira");
        menuBuilder.append("\n4. Deletar movimentação financeira");
        menuBuilder.append("\n0. Voltar");

        return Integer.parseInt(JOptionPane.showInputDialog(menuBuilder));
    }
}
