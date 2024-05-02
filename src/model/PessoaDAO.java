/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

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
        p.setNomePessoa("Sett");
        p.setSexo('M');
        p.setNascimento("11/09/2001");
        p.setLogin("O Chefe");
        p.setSenha("mamis");
        p.setTipoUser(1);
        p.setDataID();
        pessoa[0] = p;
        
        p = new Pessoa();
        p.setNomePessoa("Ahri");
        p.setSexo('F');
        p.setNascimento("15/12/2002");
        p.setLogin("Kitsune");
        p.setSenha("raposa");
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
        while(pessoa[i] != null && i < pessoa.length-1)
        {
            i++;
        }

        if(i < pessoa.length)
        {
            if(pessoa[i] == null)
            {
                pessoa[i] = p;
            }
            else
            {
                StringBuilder builder = new StringBuilder("Não é possível adicionar mais pessoas!");
                JOptionPane.showMessageDialog(null, builder);
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

    public StringBuilder editUser(int id_edit, long ID, String att)
    {
        StringBuilder builder = new StringBuilder();
        boolean atualizado = false;
        for (Pessoa p : pessoa)
        {
            if(p != null && p.getID() == ID)
            {
                if(id_edit == 1)
                {
                    p.setNomePessoa(att);
                }
                else if(id_edit == 2)
                {
                    p.setSexo(att.charAt(0));
                }
                else if(id_edit == 3)
                {
                    p.setNascimento(att);
                }
                else if(id_edit == 4)
                {
                    p.setLogin(att);
                }
                else if(id_edit == 5)
                {
                    p.setSenha(att);
                }
                else if(id_edit == 6)
                {
                    p.setTipoUser(Integer.parseInt(att));
                }
                p.setModData();
                atualizado = true;
            }
        }

        if(atualizado == true)
        {
            builder.append("\nUsuário atualizado com sucesso!");
        }
        else
        {
            builder.append("\nUsuário não encontrado!");
        }

        return builder;
    }

    public StringBuilder delUser(long ID)
    {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        boolean deleted = false;
        for (Pessoa p : pessoa)
        {
            if(p != null && p.getID() == ID)
            {
                pessoa[i] = null;
                deleted = true;
            }
            i++;
        }

        if(deleted == true)
        {
            builder.append("\nUsuário deletado com sucesso!");
        }
        else
        {
            builder.append("\nUsuário não encontrado!");
        }        

        return builder;
    }
    
    public Pessoa pegaUsuario(long id)
    {
        Pessoa usuarioAF = new Pessoa();

        for (Pessoa p : pessoa)
        {
            if(p != null && p.getID() == id)
            {
                usuarioAF = p;
            }
        }

        return usuarioAF;
    }

}
