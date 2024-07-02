package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import data_base_connector.ConnectionFactory;

public class DivisaoTreinoDAO {

    DivisaoTreino[] divisaoTreino = new DivisaoTreino[100];

    public DivisaoTreinoDAO()
    {
        DivisaoTreino dt = new DivisaoTreino();
        dt.setNomeDivisaoTreino("ABC");
        dt.setDescricaoDivisaoTreino("ABC 2x e descansa 1x");
        dt.setDataID();
        divisaoTreino[0] = dt;

        dt = new DivisaoTreino();
        dt.setNomeDivisaoTreino("ABC");
        dt.setDescricaoDivisaoTreino("ABC descansa 1x ABC descansa 1x");
        dt.setDataID();
        divisaoTreino[1] = dt;
    }

    /* Uso do Banco */
    public DivisaoTreino adicionaDivisaoTreinoBanco(DivisaoTreino elemento) { //Criar entrada no Banco de Dados
        String sql = "insert into divisao_treino "
                + "(nome,descricao,data_criacao,data_modificacao)" 
                + " values (?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, elemento.getNomeDivisaoTreino());
            stmt.setString(2, elemento.getDescricaoDivisaoTreino());
            stmt.setDate(3, java.sql.Date.valueOf(elemento.getDataCriacao()));
            stmt.setDate(4, java.sql.Date.valueOf(elemento.getDataModificacao()));
            
            stmt.execute();
            
            System.out.println("Elemento inserido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //na verdade deveria retornar o elemento que foi inserido agora
        return elemento;
    }

    public List<DivisaoTreino> showDivisaoTreino(DivisaoTreino elemento) {  //Adicionar todas linhas do Banco em uma Lista
        String sql = "select * from divisao_treino";

        DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");

        List<DivisaoTreino> divisaoTreinos = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");

                DivisaoTreino divisaoTreino = new DivisaoTreino();
                divisaoTreino.setId(id);
                divisaoTreino.setNomeDivisaoTreino(nome);
                divisaoTreino.setDescricaoDivisaoTreino(descricao);
                
                divisaoTreinos.add(divisaoTreino);
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        // itera no ResultSet
        return divisaoTreinos;
    }
    
    public DivisaoTreino buscaPorCriterioAlternativa1(Long code) { /* Buscar um dado especifico no banco */
        try (Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement ps = createPreparedStatement(connection, code);
            ResultSet rs = ps.executeQuery()) {
            
            DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");

                DivisaoTreino divisaoTreino = new DivisaoTreino();
                divisaoTreino.setId(id);
                divisaoTreino.setNomeDivisaoTreino(nome);
                divisaoTreino.setDescricaoDivisaoTreino(descricao);
                
                return divisaoTreino;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    private PreparedStatement createPreparedStatement(Connection con, long id) throws SQLException {
        String sql = "select * from divisao_treino where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }

    public DivisaoTreino updateDivisaoTreinoBanco(DivisaoTreino elemento) { //Update no Banco de Dados
        String sql = "update divisao_treino set nome = ?, descricao = ?, data_modificacao = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, elemento.getNomeDivisaoTreino());
            stmt.setString(2, elemento.getDescricaoDivisaoTreino());
            stmt.setDate(3, java.sql.Date.valueOf(elemento.getDataModificacao()));
            stmt.setLong(4, elemento.getIDDivisaoTreino());
            
            stmt.execute();
            
            System.out.println("Elemento alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    public DivisaoTreino excluiDivisaoTreinoBanco(DivisaoTreino elemento) {// Exclusão no Banco de Dados
        String sql = "delete from divisao_treino where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, elemento.getIDDivisaoTreino());
            
            stmt.execute();
            
            System.out.println("Elemento excluído com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    /*  */
    public StringBuilder createDivisaoTreino(String nome, String desc)
    {
        DivisaoTreino dt = new DivisaoTreino();
        dt.setNomeDivisaoTreino(nome);
        dt.setDescricaoDivisaoTreino(desc);
        dt.setDataID();

        // int i = 0;
        // while(divisaoTreino[i] != null && i < divisaoTreino.length-1)
        // {
        //     i++;
        // }

        // if(i < divisaoTreino.length)
        // {
        //     if(divisaoTreino[i] == null)
        //     {
        //         divisaoTreino[i] = dt;
        //         StringBuilder builder = new StringBuilder("\nDivisão de Treino adicionada com sucesso!");
        //         return builder;
        //     }
        //     else
        //     {
        //         StringBuilder builder = new StringBuilder("\nNão foi possivel adicionar a nova Divisão de Treino!");
        //         return builder;
        //     }
        // }
        // StringBuilder builder = new StringBuilder("\nDeu ruim");
        adicionaDivisaoTreinoBanco(dt);
        StringBuilder builder = new StringBuilder("\nDivisão de Treino adicionada com sucesso!");

        return builder;
    }

    public StringBuilder readDivisaoTreino()
    {
        StringBuilder builder = new StringBuilder();
        List<DivisaoTreino> divisaoTreinos = showDivisaoTreino(null);

        for (DivisaoTreino dt : divisaoTreinos)
        {
            if(dt != null)
            {
                builder.append("\nID: " + dt.getIDDivisaoTreino());
                builder.append("\nNome: " + dt.getNomeDivisaoTreino());
                builder.append("\nDescrição: " + dt.getDescricaoDivisaoTreino());
                builder.append("\n");
            }    
        }

        return builder;
    }

    public StringBuilder updateDivisaoTreino(DivisaoTreino dt, String nome, String desc)
    {
        StringBuilder builder = new StringBuilder("");
        // int i = 0;
        // while(divisaoTreino[i] != null && divisaoTreino[i].getIDDivisaoTreino() != id)
        // {
        //     i++;
        // }

        // if(divisaoTreino[i] != null && divisaoTreino[i].getIDDivisaoTreino() == id)
        // {
            if(nome.equals(""))
            {
                dt.setNomeDivisaoTreino(dt.getNomeDivisaoTreino());
            }
            else
            {
                dt.setNomeDivisaoTreino(nome);
            }

            if(desc.equals(""))
            {
                dt.setDescricaoDivisaoTreino(dt.getDescricaoDivisaoTreino());
            }
            else
            {
                dt.setDescricaoDivisaoTreino(desc);
            }
            dt.setModData();
            updateDivisaoTreinoBanco(dt);

            builder.append("\nDivisão de Treino atualizada com sucesso!");
            return builder;
        // }

        // builder.append("\nDivisão de Treino não foi atualizada!");
        // return builder;
    }

    public StringBuilder deleteDivisaoTreino(Long id)
    {
        StringBuilder builder = new StringBuilder("");
        // int i = 0;
        // while(divisaoTreino[i] != null && divisaoTreino[i].getIDDivisaoTreino() != id)
        // {
        //     i++;
        // }

        // if(i < divisaoTreino.length)
        // {
        //     if(divisaoTreino[i].getIDDivisaoTreino() == id)
        //     {
        //         divisaoTreino[i] = null;
        //         builder.append("\nDivisão de Treino deletada com sucesso!");
        //         return builder;
        //     }
        // }
        DivisaoTreino dt = buscaPorCriterioAlternativa1(id);
        excluiDivisaoTreinoBanco(dt);
        builder.append("\nDivisão de Treino deletada com sucesso!");

        // builder.append("\nDivisão de Treino não encontrada!");
        return builder;
    }

    public DivisaoTreino getDivisaoTreino(Long id)
    {
        DivisaoTreino dt = new DivisaoTreino();

        int i = 0;
        while(divisaoTreino[i] != null && divisaoTreino[i].getIDDivisaoTreino() != id)
        {
            i++;
        }

        if(divisaoTreino[i] != null)
        {
            dt = divisaoTreino[i];
        }

        return dt;
    }
}
