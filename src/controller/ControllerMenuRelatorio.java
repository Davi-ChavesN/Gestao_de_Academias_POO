package controller;

import model.Pessoa;
import view.GUI;

import model.RelatorioDAO;

public class ControllerMenuRelatorio {

    GUI gui = new GUI();
    RelatorioDAO relatorioDAO = new RelatorioDAO();

    public void menuRelatorios(Pessoa usuarioLogado)
    {
        int opc_menu_relatorio = 0;

        while(opc_menu_relatorio != -1)
        {
            StringBuilder builder = new StringBuilder();
            opc_menu_relatorio = gui.crudRelatorios(usuarioLogado);

            switch (opc_menu_relatorio)
            {
                case 1:
                    
                    break;

                case 2:
                    relatorioDAO.createRelatorioFinanceiro();
                    break;

                case 0:
                    opc_menu_relatorio = -1;
                    break;
            
                default:
                opc_menu_relatorio = 0;
                    break;
            }
        }
    }

}
