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

public class ExercicioAplicacaoDAO {

    ExercicioAplicacao[] exercicioAplicacao = new ExercicioAplicacao[100];

    public ExercicioAplicacaoDAO()
    {
        ExercicioAplicacao ea = new ExercicioAplicacao();
        ea.setDescricaoExercicioAplicacao("4x12");
        ea.setDataID();
        exercicioAplicacao[0] = ea;

        ea = new ExercicioAplicacao();
        ea.setDescricaoExercicioAplicacao("4x10");
        ea.setDataID();
        exercicioAplicacao[1] = ea;

        ea = new ExercicioAplicacao();
        ea.setDescricaoExercicioAplicacao("12 reps com rest pause");
        ea.setDataID();
        exercicioAplicacao[2] = ea;

        ea = new ExercicioAplicacao();
        ea.setDescricaoExercicioAplicacao("5x5");
        ea.setDataID();
        exercicioAplicacao[3] = ea;
    }

    /* Uso do Banco */
    public ExercicioAplicacao adicionaExercicioAplicacaoBanco(ExercicioAplicacao elemento) { //Criar entrada no Banco de Dados
        String sql = "insert into exercicio_aplicacao "
                + "(descricao,data_criacao,data_modificacao)" 
                + " values (?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, elemento.getDescricaoExercicioAplicacao());
            stmt.setDate(2, java.sql.Date.valueOf(elemento.getDataCriacao()));
            stmt.setDate(3, java.sql.Date.valueOf(elemento.getDataModificacao()));
            
            stmt.execute();
            
            System.out.println("Elemento inserido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //na verdade deveria retornar o elemento que foi inserido agora
        return elemento;
    }

    public List<ExercicioAplicacao> showExerciciosAplicacao(ExercicioAplicacao elemento) {  //Adicionar todas linhas do Banco em uma Lista
        String sql = "select * from exercicio_aplicacao";

        DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");

        List<ExercicioAplicacao> exercicios_aplicacao = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String descricao = rs.getString("descricao");

                ExercicioAplicacao exercicioAplicacao = new ExercicioAplicacao();
                exercicioAplicacao.setId(id);
                exercicioAplicacao.setDescricaoExercicioAplicacao(descricao);
                
                exercicios_aplicacao.add(exercicioAplicacao);
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        // itera no ResultSet
        return exercicios_aplicacao;
    }
    
    public ExercicioAplicacao buscaPorCriterioAlternativa1(Long code) { /* Buscar um dado especifico no banco */
        try (Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement ps = createPreparedStatement(connection, code);
            ResultSet rs = ps.executeQuery()) {
            
            DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
            while (rs.next()) {
                Long id = rs.getLong("id");
                String descricao = rs.getString("descricao");

                ExercicioAplicacao exercicioAplicacao = new ExercicioAplicacao();
                exercicioAplicacao.setId(id);
                exercicioAplicacao.setDescricaoExercicioAplicacao(descricao);
                
                return exercicioAplicacao;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    private PreparedStatement createPreparedStatement(Connection con, long id) throws SQLException {
        String sql = "select * from exercicio_aplicacao where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }

    public ExercicioAplicacao updateExercicioAplicacaoBanco(ExercicioAplicacao elemento) { //Update no Banco de Dados
        String sql = "update exercicio_aplicacao set descricao = ?, data_modificacao = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, elemento.getDescricaoExercicioAplicacao());
            stmt.setDate(2, java.sql.Date.valueOf(elemento.getDataModificacao()));
            stmt.setLong(3, elemento.getIDExercicioAplicacao());
            
            stmt.execute();
            
            System.out.println("Elemento alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    public ExercicioAplicacao excluiExercicioAplicacaoBanco(ExercicioAplicacao elemento) {// Exclusão no Banco de Dados
        String sql = "delete from exercicio_aplicacao where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, elemento.getIDExercicioAplicacao());
            
            stmt.execute();
            
            System.out.println("Elemento excluído com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    /*  */
    public StringBuilder createExercicioAplicacao(String desc)
    {
        ExercicioAplicacao ea = new ExercicioAplicacao();
        ea.setDescricaoExercicioAplicacao(desc);
        ea.setDataID();
        StringBuilder builder = new StringBuilder();

        // int i = 0;
        // while(exercicioAplicacao[i] != null && i < exercicioAplicacao.length-1)
        // {
        //     i++;
        // }

        // if(i < exercicioAplicacao.length)
        // {
        //     if(exercicioAplicacao[i] == null)
        //     {
        //         exercicioAplicacao[i] = ea;
        //         StringBuilder builder = new StringBuilder("\nAplicação de Exercício adicionada com sucesso!");
        //         return builder;
        //     }
        //     else
        //     {
        //         StringBuilder builder = new StringBuilder("\nNão foi possivel adicionar a nova Aplicação de Exercício!");
        //         return builder;
        //     }
        // }
        // StringBuilder builder = new StringBuilder("\nDeu ruim");

        adicionaExercicioAplicacaoBanco(ea);
        builder.append("\nAplicação de Exercício adicionada com sucesso!");
        return builder;
    }

    public StringBuilder readExercicioAplicacao()
    {
        StringBuilder builder = new StringBuilder();

        List<ExercicioAplicacao> exercicios_aplicacao = showExerciciosAplicacao(null);
        for (ExercicioAplicacao ea : exercicios_aplicacao)
        {
            if(ea != null)
            {
                builder.append("\nID: " + ea.getIDExercicioAplicacao());
                builder.append(" - Descrição: " + ea.getDescricaoExercicioAplicacao());
                // builder.append("\n");
            }    
        }

        return builder;
    }

    public StringBuilder updateExercicioAplicacao(Long id, ExercicioAplicacao ea)
    {
        StringBuilder builder = new StringBuilder("");
        // int i = 0;
        // while(exercicioAplicacao[i] != null && exercicioAplicacao[i].getIDExercicioAplicacao() != id)
        // {
        //     i++;
        // }

        // if(exercicioAplicacao[i] != null && exercicioAplicacao[i].getIDExercicioAplicacao() == id)
        // {
        //     if(!desc.equals(""))
        //     {
        //         exercicioAplicacao[i].setDescricaoExercicioAplicacao(desc);
        //     }

        //     exercicioAplicacao[i].setModData();

        //     builder.append("\nAplicação de Exercicio atualizada com sucesso!");
        //     return builder;
        // }
        ea.setModData();
        updateExercicioAplicacaoBanco(ea);
        builder.append("\nAplicação de Exercicio atualizada com sucesso!");

        // builder.append("\nAplicação de Exercicio não foi atualizada!");
        return builder;
    }

    public StringBuilder deleteExercicioAplicacao(Long id)
    {
        StringBuilder builder = new StringBuilder("");
        // int i = 0;
        // while(exercicioAplicacao[i] != null && exercicioAplicacao[i].getIDExercicioAplicacao() != id)
        // {
        //     i++;
        // }

        // if(i < exercicioAplicacao.length)
        // {
        //     if(exercicioAplicacao[i].getIDExercicioAplicacao() == id)
        //     {
        //         exercicioAplicacao[i] = null;
        //         builder.append("\nAplicação de Exercicio deletada com sucesso!");
        //         return builder;
        //     }
        // }
        ExercicioAplicacao ea = buscaPorCriterioAlternativa1(id);
        excluiExercicioAplicacaoBanco(ea);
        builder.append("\nAplicação de Exercicio deletada com sucesso!");

        // builder.append("\nAplicação de Exercicio não encontrada!");
        return builder;
    }

    public ExercicioAplicacao getExercicioAplicacao(Long id)
    {
        ExercicioAplicacao ea = new ExercicioAplicacao();

        int i = 0;
        while(exercicioAplicacao[i] != null && exercicioAplicacao[i].getIDExercicioAplicacao() != id)
        {
            i++;
        }

        if(exercicioAplicacao[i] != null)
        {
            ea = exercicioAplicacao[i];
        }

        return ea;
    }
}
