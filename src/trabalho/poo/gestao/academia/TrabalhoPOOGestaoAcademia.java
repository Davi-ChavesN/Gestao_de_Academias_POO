/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabalho.poo.gestao.academia;

import javax.swing.JOptionPane;

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
        
        GUI gui = new GUI();
        AcademiaDAO academiaDAO = new AcademiaDAO();
        PessoaDAO pessoaDAO = new PessoaDAO();
        
        int exit = 0;
        int menuExit = 0;
        int opc;
        
        while(exit != -1)
        {
            opc = gui.menuInicial();
            switch (opc) {
                case 1:
                    Pessoa usuarioLogado = gui.login();
                    menuExit = 0;
                    
                    if(usuarioLogado.getTipoUser().equals("administrador"))
                    {
                        while(menuExit != -1)
                        {
                            menuExit = gui.menuAdm(usuarioLogado);
                            switch (menuExit) 
                            {
                                case 1:
                                    
                                    break;

                                case 2:
                                    
                                    break;

                                case 3:
                                    
                                    break;

                                case 4:
                                    
                                    break;

                                case 5:
                                    
                                    break;

                                case 6:
                                    
                                    break;

                                case 7:
                                    
                                    break;

                                case 8:
                                    
                                    break;

                                case 0:
                                    menuExit = -1;
                                    break;
                            
                                default:
                                    menuExit = 0;
                                    break;
                            }
                        }
                    }
                    else if(usuarioLogado.getTipoUser().equals("instrutor"))
                    {
                        while(menuExit != -1)
                        {
                            menuExit = gui.menuInstrutor(usuarioLogado);

                            switch (menuExit) {
                                case 1:
                                    
                                    break;

                                case 2:
                                    
                                    break;

                                case 3:
                                    
                                    break;

                                case 4:
                                    
                                    break;

                                case 5:
                                    
                                    break;

                                case 0:
                                    menuExit = -1;
                                    break;
                            
                                default:
                                    menuExit = 0;
                                    break;
                            }
                        }
                    }
                    else if(usuarioLogado.getTipoUser().equals("aluno"))
                    {
                        while(menuExit != -1)
                        {
                            menuExit = gui.menuAluno(usuarioLogado);

                            switch (menuExit) {
                                case 1:
                                    
                                    break;

                                case 2:
                                    
                                    break;

                                case 0:
                                    menuExit = -1;
                                    break;
                            
                                default:
                                    menuExit = 0;
                                    break;
                            }
                        }
                    }
                    break;
                    
                case 2:
                    exit = -1;
                    break;
                    
                default:
                    exit = 0;
            }
        }
        
        
                        
    }
}
