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

    public void adicionarPessoa(Pessoa p)
    {
        int i = 0;
        while(pessoa[i] != null && i < pessoa.length)
        {
            i++;
        }

        if(i < pessoa.length)
        {
            if(pessoa[i] == null)
            {
                pessoa[i] = p;
            }
        }
        else
        {
            StringBuilder builder = new StringBuilder("Deu ruim");
            JOptionPane.showMessageDialog(null, builder);
        }
    }

    public StringBuilder mostrarUsuarios(Pessoa usuario)
    {
        StringBuilder builder = new StringBuilder("\n");
        String tipoUser = usuario.getTipoUser();
        if(tipoUser.equals("administrador"))
        {
            for(Pessoa p : pessoa)
            {
                if(p != null)
                {
                    builder.append("\nID: " + p.getID());
                    builder.append(" - Nome: " + p.getNome());
                }
            }
        }
        else if(tipoUser.equals("instrutor"))
        {
            for(Pessoa p : pessoa)
            {
                if(p != null)
                {
                    if(p.getTipoUser().equals("aluno"))
                    {
                        builder.append("\nID: " + p.getID());
                        builder.append(" - Nome: " + p.getNome());
                    }
                }
            }
        }

        return builder;
    }
    
}
