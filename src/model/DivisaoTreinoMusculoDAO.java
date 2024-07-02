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

import model.DivisaoTreinoDAO;

public class DivisaoTreinoMusculoDAO {

    DivisaoTreinoMusculo[] divisaoTreinoMusculo = new DivisaoTreinoMusculo[100];

    public DivisaoTreinoMusculoDAO(DivisaoTreino dt)
    {
        DivisaoTreinoMusculo dtm = new DivisaoTreinoMusculo();
        dtm.setDivisaoTreinoIntoDivisaoTreinoMusculo(dt);
        dtm.setDescricaoDivisaoTreinoMusculo("A = Peito, Ombro, Triceps");
        dtm.setDataID();
        divisaoTreinoMusculo[0] = dtm;

        dtm = new DivisaoTreinoMusculo();
        dtm.setDivisaoTreinoIntoDivisaoTreinoMusculo(dt);
        dtm.setDescricaoDivisaoTreinoMusculo("B = Costa, Bíceps");
        dtm.setDataID();
        divisaoTreinoMusculo[1] = dtm;

        dtm = new DivisaoTreinoMusculo();
        dtm.setDivisaoTreinoIntoDivisaoTreinoMusculo(dt);
        dtm.setDescricaoDivisaoTreinoMusculo("C = Perna");
        dtm.setDataID();
        divisaoTreinoMusculo[2] = dtm;
    }

    DivisaoTreinoDAO divisaoTreinoDAO = new DivisaoTreinoDAO();

    /* Uso do Banco */
    public DivisaoTreinoMusculo adicionaDivisaoTreinoMusculoBanco(DivisaoTreinoMusculo elemento) { //Criar entrada no Banco de Dados
        String sql = "insert into divisao_treino_musculo "
                + "(descricao,id_divisao_treino,data_criacao,data_modificacao)" 
                + " values (?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, elemento.getDescricaoDivisaoTreinoMusculo());
            stmt.setLong(2, elemento.getDivisaoTreinoFromDivisaoTreinoMusculo().getIDDivisaoTreino());
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

    public List<DivisaoTreinoMusculo> showDivisaoTreinoMusculos(DivisaoTreinoMusculo elemento) {  //Adicionar todas linhas do Banco em uma Lista
        String sql = "select * from divisao_treino_musculo";

        DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");

        List<DivisaoTreinoMusculo> divisaoTreinoMusculos = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String descricao = rs.getString("descricao");
                Long id_divisao_treino = rs.getLong("id_divisao_treino");

                DivisaoTreinoMusculo divisaoTreinoMusculo = new DivisaoTreinoMusculo();
                divisaoTreinoMusculo.setId(id);
                divisaoTreinoMusculo.setDescricaoDivisaoTreinoMusculo(descricao);
                divisaoTreinoMusculo.setDivisaoTreinoIntoDivisaoTreinoMusculo(divisaoTreinoDAO.buscaPorCriterioAlternativa1(id_divisao_treino));
                
                divisaoTreinoMusculos.add(divisaoTreinoMusculo);
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        // itera no ResultSet
        return divisaoTreinoMusculos;
    }
    
    public DivisaoTreinoMusculo buscaPorCriterioAlternativa1(Long code) { /* Buscar um dado especifico no banco */
        try (Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement ps = createPreparedStatement(connection, code);
            ResultSet rs = ps.executeQuery()) {
            
            DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
            while (rs.next()) {
                Long id = rs.getLong("id");
                String descricao = rs.getString("descricao");
                Long id_divisao_treino = rs.getLong("id_divisao_treino");

                DivisaoTreinoMusculo divisaoTreinoMusculo = new DivisaoTreinoMusculo();
                divisaoTreinoMusculo.setId(id);
                divisaoTreinoMusculo.setDescricaoDivisaoTreinoMusculo(descricao);
                divisaoTreinoMusculo.setDivisaoTreinoIntoDivisaoTreinoMusculo(divisaoTreinoDAO.buscaPorCriterioAlternativa1(id_divisao_treino));
                
                return divisaoTreinoMusculo;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    private PreparedStatement createPreparedStatement(Connection con, long id) throws SQLException {
        String sql = "select * from divisao_treino_musculo where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }

    public DivisaoTreinoMusculo updateDivisaoTreinoMusculoBanco(DivisaoTreinoMusculo elemento) { //Update no Banco de Dados
        String sql = "update divisao_treino_musculo set descricao = ?, id_divisao_treino = ?, data_modificacao = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, elemento.getDescricaoDivisaoTreinoMusculo());
            stmt.setLong(2, elemento.getDivisaoTreinoFromDivisaoTreinoMusculo().getIDDivisaoTreino());
            stmt.setDate(3, java.sql.Date.valueOf(elemento.getDataModificacao()));
            stmt.setLong(4, elemento.getIDDivisaoTreinoMusculo());
            
            stmt.execute();
            
            System.out.println("Elemento alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    public DivisaoTreinoMusculo excluiDivisaoTreinoMusculoBanco(DivisaoTreinoMusculo elemento) {// Exclusão no Banco de Dados
        String sql = "delete from divisao_treino_musculo where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, elemento.getIDDivisaoTreinoMusculo());
            
            stmt.execute();
            
            System.out.println("Elemento excluído com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    /*  */
    public StringBuilder createDivisaoTreinoMusculo(DivisaoTreino dt, String desc)
    {
        DivisaoTreinoMusculo dtm = new DivisaoTreinoMusculo();
        dtm.setDescricaoDivisaoTreinoMusculo(desc);
        dtm.setDivisaoTreinoIntoDivisaoTreinoMusculo(dt);
        dtm.setDataID();

        // int i = 0;
        // while(divisaoTreinoMusculo[i] != null && i < divisaoTreinoMusculo.length-1)
        // {
        //     i++;
        // }

        // if(i < divisaoTreinoMusculo.length)
        // {
        //     if(divisaoTreinoMusculo[i] == null)
        //     {
        //         divisaoTreinoMusculo[i] = dtm;
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
        adicionaDivisaoTreinoMusculoBanco(dtm);
        StringBuilder builder = new StringBuilder("\nDivisão de Treino adicionada com sucesso!");

        return builder;
    }

    public StringBuilder readDivisaoTreinoMusculo()
    {
        StringBuilder builder = new StringBuilder();
        List<DivisaoTreinoMusculo> divisaoTreinoMusculos = showDivisaoTreinoMusculos(null);

        for (DivisaoTreinoMusculo dtm : divisaoTreinoMusculos)
        {
            if(dtm != null)
            {
                builder.append("\nTreino " + dtm.getDivisaoTreinoFromDivisaoTreinoMusculo().getNomeDivisaoTreino());
                builder.append(" (id = " + dtm.getDivisaoTreinoFromDivisaoTreinoMusculo().getIDDivisaoTreino() + ")");
                builder.append(" - " + dtm.getDescricaoDivisaoTreinoMusculo());
                builder.append(" (id = " + dtm.getIDDivisaoTreinoMusculo() + ")");
            }    
        }

        return builder;
    }

    public StringBuilder updateDivisaoTreinoMusculo(DivisaoTreinoMusculo dtm, String desc)
    {
        StringBuilder builder = new StringBuilder("");
        int i = 0;
        // while(divisaoTreinoMusculo[i] != null && divisaoTreinoMusculo[i].getIDDivisaoTreinoMusculo() != id)
        // {
        //     i++;
        // }

        // if(divisaoTreinoMusculo[i] != null && divisaoTreinoMusculo[i].getIDDivisaoTreinoMusculo() == id)
        // {
            if(desc.equals(""))
            {
                dtm.setDescricaoDivisaoTreinoMusculo(dtm.getDescricaoDivisaoTreinoMusculo());
            }
            else
            {
                dtm.setDescricaoDivisaoTreinoMusculo(desc);
            }
            dtm.setModData();
            updateDivisaoTreinoMusculoBanco(dtm);

            builder.append("\nDivisão de Treino-Musculo atualizada com sucesso!");
            return builder;
        // }

        // builder.append("\nDivisão de Treino-Musculo não foi atualizada!");
        // return builder;
    }

    public StringBuilder deleteDivisaoTreinoMusculo(Long id)
    {
        StringBuilder builder = new StringBuilder("");
        // int i = 0;
        // while(divisaoTreinoMusculo[i] != null && divisaoTreinoMusculo[i].getIDDivisaoTreinoMusculo() != id)
        // {
        //     i++;
        // }

        // if(i < divisaoTreinoMusculo.length)
        // {
        //     if(divisaoTreinoMusculo[i].getIDDivisaoTreinoMusculo() == id)
        //     {
        //         divisaoTreinoMusculo[i] = null;
        //         builder.append("\nDivisão de Treino-Musculo deletada com sucesso!");
        //         return builder;
        //     }
        // }
        DivisaoTreinoMusculo dtm = buscaPorCriterioAlternativa1(id);
        excluiDivisaoTreinoMusculoBanco(dtm);
        builder.append("\nDivisão de Treino deletada com sucesso!");

        // builder.append("\nDivisão de Treino-Musculo não encontrada!");
        return builder;
    }

    public DivisaoTreinoMusculo getDivisaoTreinoMusculo(Long id)
    {
        DivisaoTreinoMusculo dtm = new DivisaoTreinoMusculo();

        int i = 0;
        while(divisaoTreinoMusculo[i] != null && divisaoTreinoMusculo[i].getIDDivisaoTreinoMusculo() != id)
        {
            i++;
        }

        if(divisaoTreinoMusculo[i] != null)
        {
            dtm = divisaoTreinoMusculo[i];
        }

        return dtm;
    }
}
