/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.poo.gestao.academia;

/**
 *
 * @author Davi Chaves
 *         Luan Sousa
 */
public class PessoaDAO {
    
    Pessoa[] pessoa = new Pessoa[50];
    
    public PessoaDAO()
    {      
        Pessoa p = new Pessoa();
        p.setNomePessoa("Draven");
        p.setSexo('M');
        p.setNascimento("11/09/2001");
        p.setLogin("Draven");
        p.setSenha("Draven");
        p.setTipoUser(1);
        p.setDataID();
        pessoa[0] = p;
        
        p = new Pessoa();
        p.setNomePessoa("Katarina");
        p.setSexo('F');
        p.setNascimento("15/12/2002");
        p.setLogin("Kat");
        p.setSenha("Garen");
        p.setTipoUser(2);
        p.setDataID();
        pessoa[1] = p;
        
        p = new Pessoa();
        p.setNomePessoa("Briar");
        p.setSexo('F');
        p.setNascimento("10/10/2007");
        p.setLogin("Briar");
        p.setSenha("fome");
        p.setTipoUser(3);
        p.setDataID();
        pessoa[2] = p;
    }

    public Pessoa verificaUsuario(String login, String senha)
    {
        for(Pessoa p : pessoa)
        {
            if(p != null && p.getLogin().equals(login) && p.getSenha().equals(senha))
            {
                return p;
            }
        }
        return null;
    }
    
}
