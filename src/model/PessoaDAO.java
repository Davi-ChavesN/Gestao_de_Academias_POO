/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import data_base_connector.ConnectionFactory;

/**
 *
 * @author Davi Chaves
 *         Luan Sousa
 */
public class PessoaDAO {
    
    Pessoa[] pessoa = new Pessoa[50];
    
    public PessoaDAO()
    {     
        /* 
        Pessoa p = new Pessoa();
        p.setNomePessoa("Sett");
        p.setSexo("M");
        p.setNascimento("11/09/2001");
        p.setLogin("O Chefe");
        p.setSenha("mamis");
        p.setTipoUser(1);
        p.setDataID();
        pessoa[0] = p;
        
        p = new Pessoa();
        p.setNomePessoa("Ahri");
        p.setSexo("F");
        p.setNascimento("15/12/2002");
        p.setLogin("Kitsune");
        p.setSenha("raposa");
        p.setTipoUser(2);
        p.setDataID();
        pessoa[1] = p;
        
        p = new Pessoa();
        p.setNomePessoa("Briar");
        p.setSexo("F");
        p.setNascimento("10/10/2007");
        p.setLogin("Briar");
        p.setSenha("fome");
        p.setTipoUser(3);
        p.setDataID();
        pessoa[2] = p;
        */
    }

    /* Uso do Banco */
    public Pessoa adicionaPessoaBanco(Pessoa elemento) { //Criar entrada no Banco de Dados
        String sql = "insert into pessoa "
                + "(nome,sexo,nascimento,tipo_usuario,login,senha,data_criacao,data_modificacao)" 
                + " values (?,?,?,?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, elemento.getNome());
            stmt.setString(2, elemento.getSexo());
            stmt.setDate(3, java.sql.Date.valueOf(elemento.getNascimento()));
            stmt.setString(4, elemento.getTipoUser());
            stmt.setString(5, elemento.getLogin());
            stmt.setString(6, elemento.getSenha());
            stmt.setDate(7, java.sql.Date.valueOf(elemento.getDataCriacao()));
            stmt.setDate(8, java.sql.Date.valueOf(elemento.getDataModificacao()));
            
            stmt.execute();
            
            System.out.println("Elemento inserido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //na verdade deveria retornar o elemento que foi inserido agora
        return elemento;
    }

    public List<Pessoa> showPessoas(Pessoa elemento) {  //Adicionar todas linhas do Banco em uma Lista
        String sql = "select * from pessoa";

        DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");

        List<Pessoa> pessoas = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String sexo = rs.getString("sexo");
                Date nascimento = rs.getDate("nascimento");
                String tipo_user = rs.getString("tipo_usuario");
                String login = rs.getString("login");
                String senha = rs.getString("senha");

                Pessoa pessoa = new Pessoa();
                pessoa.setId(id);
                pessoa.setNomePessoa(nome);
                pessoa.setNascimento(dtf.format(nascimento));
                if(tipo_user.equals("administrador"))
                {
                    pessoa.setTipoUser(1);
                }
                else if(tipo_user.equals("instrutor"))
                {
                    pessoa.setTipoUser(2);
                }
                else
                {
                    pessoa.setTipoUser(3);
                }
                pessoa.setLogin(login);
                pessoa.setSenha(senha);
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        // itera no ResultSet
        return pessoas;
    }
    
    public Pessoa buscaPorCriterioAlternativa1(Long code) { /* Buscar um dado especifico no banco */
        try (Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement ps = createPreparedStatement(connection, code);
            ResultSet rs = ps.executeQuery()) {
            
            DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String sexo = rs.getString("sexo");
                Date nascimento = rs.getDate("nascimento");
                String tipo_user = rs.getString("tipo_usuario");
                String login = rs.getString("login");

                Pessoa pessoa = new Pessoa();
                pessoa.setId(id);
                pessoa.setNomePessoa(nome);
                pessoa.setNascimento(dtf.format(nascimento));
                if(tipo_user.equals("administrador"))
                {
                    pessoa.setTipoUser(1);
                }
                else if(tipo_user.equals("instrutor"))
                {
                    pessoa.setTipoUser(2);
                }
                else
                {
                    pessoa.setTipoUser(3);
                }
                pessoa.setLogin(login);
                
                return pessoa;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    private PreparedStatement createPreparedStatement(Connection con, long id) throws SQLException {
        String sql = "select * from pessoa where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }

    public Pessoa updatePessoaBanco(Pessoa elemento) { //Update no Banco de Dados
        String sql = "update pessoa set nome = ?, sexo = ?, nascimento = ?, tipo_usuario = ?, login = ?, senha = ?, data_modificacao = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, elemento.getNome());
            stmt.setString(2, elemento.getSexo());
            stmt.setDate(3, java.sql.Date.valueOf(elemento.getNascimento()));
            stmt.setString(4, elemento.getTipoUser());
            stmt.setString(5, elemento.getLogin());
            stmt.setString(6, elemento.getSenha());
            stmt.setDate(7, java.sql.Date.valueOf(elemento.getDataModificacao()));
            stmt.setLong(8, elemento.getID());
            
            stmt.execute();
            
            System.out.println("Elemento alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    public Pessoa excluiPessoaBanco(Pessoa elemento) {// Exclusão no Banco de Dados
        String sql = "delete from pessoa where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, elemento.getID());
            
            stmt.execute();
            
            System.out.println("Elemento excluído com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    /*  */
    public Pessoa verificaUsuario(String login, String senha)
    {
        List<Pessoa> pessoas = showPessoas(null);
        for(Pessoa p : pessoas)
        {
            if(p != null && p.getLogin().equals(login) && p.getSenha().equals(senha))
            {
                return p;
            }
        }
        return null;
    }

    public void createPessoa(String nome, String sexo, String dt_nasc, String login, String senha, int tipo_user)
    {
        StringBuilder builder = new StringBuilder();

        Pessoa p = new Pessoa();
        p.setNomePessoa(nome);
        p.setSexo(sexo);
        p.setNascimento(dt_nasc);
        p.setLogin(login);
        p.setSenha(senha);
        p.setTipoUser(tipo_user);
        p.setDataID();

        adicionaPessoaBanco(p);

        // int i = 0;
        // while(pessoa[i] != null && i < pessoa.length-1)
        // {
        //     i++;
        // }

        // if(i < pessoa.length)
        // {
        //     if(pessoa[i] == null)
        //     {
        //         pessoa[i] = p;
        //     }
        //     else
        //     {
        //         builder.append("Não é possível adicionar mais pessoas!");
        //         JOptionPane.showMessageDialog(null, builder);
        //     }
        // }
        // else
        // {
        //     builder.append("Deu ruim");
        //     JOptionPane.showMessageDialog(null, builder);
        // }
    }

    public StringBuilder readPessoa(Pessoa usuario)
    {
        StringBuilder builder = new StringBuilder("\n");
        String tipoUser = usuario.getTipoUser();

        List<Pessoa> pessoas = showPessoas(usuario);
        if(tipoUser.equals("administrador"))
        {
            for(Pessoa p : pessoas)
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
            for(Pessoa p : pessoas)
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

    public StringBuilder updatePessoa(int opc_edit, Pessoa p, String att)
    {
        StringBuilder builder = new StringBuilder();
        boolean atualizado = false;
        // for (Pessoa p : pessoa)
        // {
        //     if(p != null && p.getID() == ID)
        //     {
                if(opc_edit == 1)
                {
                    p.setNomePessoa(att);
                }
                else if(opc_edit == 2)
                {
                    p.setSexo(att);
                }
                else if(opc_edit == 3)
                {
                    p.setNascimento(att);
                }
                else if(opc_edit == 4)
                {
                    p.setLogin(att);
                }
                else if(opc_edit == 5)
                {
                    p.setSenha(att);
                }
                else if(opc_edit == 6)
                {
                    p.setTipoUser(Integer.parseInt(att));
                }
                p.setModData();
                atualizado = true;
        //     }
        // }

        if(atualizado == true)
        {
            builder.append("\nUsuário atualizado com sucesso!");
            updatePessoaBanco(p);
        }
        else
        {
            builder.append("\nUsuário não encontrado!");
        }

        return builder;
    }

    public StringBuilder deletePessoa(long ID)
    {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        boolean deleted = false;
        // for (Pessoa p : pessoa)
        // {
        //     if(p != null && p.getID() == ID)
        //     {
        //         pessoa[i] = null;
        //         deleted = true;
        //     }
        //     i++;
        // }
        Pessoa p = buscaPorCriterioAlternativa1(ID);
        excluiPessoaBanco(p);
        deleted = true;

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
