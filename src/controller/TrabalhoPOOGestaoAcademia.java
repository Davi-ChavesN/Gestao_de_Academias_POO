

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controller;


import data_base_connector.ConnectionFactory;
import model.Pessoa;
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
        
        ControllerMenuAcademia controllerMenuAcademia = new ControllerMenuAcademia();
        ControllerMenuPessoa controllerMenuPessoa = new ControllerMenuPessoa();
        ControllerMenuExercicio controllerMenuExercicio = new ControllerMenuExercicio();
        ControllerMenuTreino controllerMenuTreino = new ControllerMenuTreino();
        ControllerMenuAvaliacaoFisica controllerMenuAvaliacaoFisica = new ControllerMenuAvaliacaoFisica();
        ControllerMenuMensalidade controllerMenuMensalidade = new ControllerMenuMensalidade();
        ControllerMenuMovimentacaoFinanceira controllerMenuMovimentacaoFinanceira = new ControllerMenuMovimentacaoFinanceira();
        ControllerMenuRelatorio controllerMenuRelatorio = new ControllerMenuRelatorio();

        Pessoa usuarioLogado = new Pessoa();

        GUI gui = new GUI();

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.getConnection();

        int opc_menu_inicial = 0;

        while(opc_menu_inicial != -1)
        {
            opc_menu_inicial = gui.menuInicial();

            switch (opc_menu_inicial)
            {
                case 1:
                    usuarioLogado = controllerMenuAcademia.loginAcademia();

                    int opc_menu_usuario = 0;

                    while (opc_menu_usuario != -1)
                    {
                        if(usuarioLogado.getTipoUser().equals("administrador"))//Menu do Administrador
                        {
                            opc_menu_usuario = gui.menuAdm(usuarioLogado);

                            switch (opc_menu_usuario)
                            {
                                case 1: //Editar Academia
                                    controllerMenuAcademia.menuAcademia(usuarioLogado);
                                    break;

                                case 2: //CRUD Pessoa
                                    controllerMenuPessoa.menuPessoa(usuarioLogado);
                                    break;

                                case 3: //Menu de Exercícios
                                    controllerMenuExercicio.menuExercicio(usuarioLogado);
                                    break;

                                case 4: //Menu de Treino
                                    controllerMenuTreino.menuTreino(usuarioLogado);
                                    break;

                                case 5: //Menu de Avaliação Física
                                    controllerMenuAvaliacaoFisica.menuAvaliacaoFisica(usuarioLogado);
                                    break;

                                case 6: //Menu de Mensalidade
                                    controllerMenuMensalidade.menuMensalidade(usuarioLogado);
                                    break;

                                case 7: //Menu de Movimentação Financeira
                                    controllerMenuMovimentacaoFinanceira.menuMovimentacaoFinanceira(usuarioLogado);
                                    break;

                                case 8: //Relatórios
                                    controllerMenuRelatorio.menuRelatorios(usuarioLogado);
                                    break;

                                case 0:
                                    opc_menu_usuario = -1;
                                    break;
                            
                                default:
                                    opc_menu_usuario = 0;
                                    break;
                            }
                        }
                        else if(usuarioLogado.getTipoUser().equals("instrutor"))//Menu do Instrutor
                        {
                            opc_menu_usuario = gui.menuInstrutor(usuarioLogado);

                            switch (opc_menu_usuario)
                            {
                                case 1:
                                    controllerMenuPessoa.menuPessoa(usuarioLogado);
                                    break;

                                case 2: //Menu de Exercícios
                                    controllerMenuExercicio.menuExercicio(usuarioLogado);
                                    break;

                                case 3: //Menu de Treino
                                    controllerMenuTreino.menuTreino(usuarioLogado);
                                    break;

                                case 4: //Menu de Avaliação Física
                                    controllerMenuAvaliacaoFisica.menuAvaliacaoFisica(usuarioLogado);
                                    break;

                                case 5: //Menu de Mensalidade
                                    controllerMenuMensalidade.menuMensalidade(usuarioLogado);
                                    break;

                                case 0:
                                    opc_menu_usuario = -1;
                                    break;
                            
                                default:
                                    opc_menu_usuario = 0;
                                    break;
                            }
                        }
                        else if(usuarioLogado.getTipoUser().equals("aluno"))//Menu do Aluno
                        {
                            opc_menu_usuario = gui.menuAluno(usuarioLogado);

                            switch (opc_menu_usuario)
                            {
                                case 1:
                                    
                                    break;

                                case 2:
                                    controllerMenuAvaliacaoFisica.menuAvaliacaoFisica(usuarioLogado);
                                    break;

                                case 0:
                                    opc_menu_usuario = -1;
                                    break;
                            
                                default:
                                    opc_menu_usuario = 0;
                                    break;
                            }
                        }
                    }
                    break;

                case 0:
                    opc_menu_inicial = -1;
                    break;
            
                default:
                opc_menu_inicial = 0;
                    break;
            }
        }
        
    }
}
