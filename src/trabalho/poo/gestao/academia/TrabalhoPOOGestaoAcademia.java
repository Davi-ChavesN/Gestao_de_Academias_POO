

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabalho.poo.gestao.academia;

//import javax.swing.JOptionPane;

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
        AcademiaDAO academiaDAO = new AcademiaDAO();
        PessoaDAO pessoaDAO = new PessoaDAO();
        ExercicioDAO exercicioDAO = new ExercicioDAO();
        
        //Variáveis de controle do menu:
        //  exit ➞ variável de saída do programa
        //  menuExit ➞ variável de saída dos menus de usuários
        int exit = 0;
        int menuExit = 0;
        int opc;
        
        //Loop do programa
        while(exit != -1)
        {
            //Receber a opção escolhida pelo usuário ao iniciar o programa
            opc = gui.menuInicial();
            switch (opc) {
                //Realizar o login do usuário, informando login e senha
                case 1:
                    //Objeto receptor do usuário que logou no sistema
                    Pessoa usuarioLogado = gui.login(pessoaDAO);
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
                                    
                                    break;

                                case 2://Crud Pessoa
                                    gui.crudPessoa(usuarioLogado, pessoaDAO);
                                    break;

                                case 3://Crud Exercicio
                                    gui.crudExercicio(usuarioLogado, exercicioDAO);
                                    break;

                                case 4://Crud Treino
                                    
                                    break;

                                case 5://Avaliação Física
                                    
                                    break;

                                case 6://Crud Mensalidade
                                    
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
                                    gui.crudPessoa(usuarioLogado, pessoaDAO);
                                    break;

                                case 2://Crud Exercicio
                                    gui.crudExercicio(usuarioLogado, exercicioDAO);
                                    break;

                                case 3://Crud Treino
                                    
                                    break;

                                case 4://Avaliação Física
                                    
                                    break;

                                case 5://Crud Mensalidade
                                    
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
