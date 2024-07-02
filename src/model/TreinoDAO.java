package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import data_base_connector.ConnectionFactory;

import model.DivisaoTreinoDAO;

public class TreinoDAO {

    Treino[] treino = new Treino[100];

    public TreinoDAO(DivisaoTreino dt)
    {
        Treino t = new Treino();
        t.setObjetivoTreino("Hipertrofia");
        t.setDataInicioTreino("01/01/2024");
        t.setDataTerminoTreino("31/12/2024");
        t.setDivisaoTreinoIntoTreino(dt);
        t.setDataID();
        treino[0] = t;
    }

    DivisaoTreinoDAO divisaoTreinoDAO = new DivisaoTreinoDAO();

    /* Uso do Banco */
    public Treino adicionaDivisaoTreinoBanco(Treino elemento) { //Criar entrada no Banco de Dados
        String sql = "insert into treino "
                + "(objetivo,data_inicio,data_termino,id_divisao_treino,data_criacao,data_modificacao)" 
                + " values (?,?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, elemento.getObjetivoTreino());
            stmt.setDate(2, java.sql.Date.valueOf(elemento.getDataInicioTreino()));
            stmt.setDate(3, java.sql.Date.valueOf(elemento.getDataTerminoTreino()));
            stmt.setLong(4, elemento.getDivisaoTreinoFromTreino().getIDDivisaoTreino());
            stmt.setDate(5, java.sql.Date.valueOf(elemento.getDataCriacao()));
            stmt.setDate(6, java.sql.Date.valueOf(elemento.getDataModificacao()));
            
            stmt.execute();
            
            System.out.println("Elemento inserido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //na verdade deveria retornar o elemento que foi inserido agora
        return elemento;
    }

    public List<Treino> showTreino(Treino elemento) {  //Adicionar todas linhas do Banco em uma Lista
        String sql = "select * from treino";

        DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");

        List<Treino> treinos = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String objetivo = rs.getString("objetivo");
                Date data_inicio = rs.getDate("data_inicio");
                Date data_termino = rs.getDate("data_termino");
                Long id_divisao_treino = rs.getLong("id_divisao_treino");

                Treino treino = new Treino();
                treino.setId(id);
                treino.setObjetivoTreino(objetivo);
                treino.setDataInicioTreino(dtf.format(data_inicio));
                treino.setDataTerminoTreino(dtf.format(data_termino));
                treino.setDivisaoTreinoIntoTreino(divisaoTreinoDAO.buscaPorCriterioAlternativa1(id_divisao_treino));
                
                treinos.add(treino);
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        // itera no ResultSet
        return treinos;
    }
    
    public Treino buscaPorCriterioAlternativa1(Long code) { /* Buscar um dado especifico no banco */
        try (Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement ps = createPreparedStatement(connection, code);
            ResultSet rs = ps.executeQuery()) {
            
            DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
            while (rs.next()) {
                Long id = rs.getLong("id");
                String objetivo = rs.getString("objetivo");
                Date data_inicio = rs.getDate("data_inicio");
                Date data_termino = rs.getDate("data_termino");
                Long id_divisao_treino = rs.getLong("id_divisao_treino");

                Treino treino = new Treino();
                treino.setId(id);
                treino.setObjetivoTreino(objetivo);
                treino.setDataInicioTreino(dtf.format(data_inicio));
                treino.setDataTerminoTreino(dtf.format(data_termino));
                treino.setDivisaoTreinoIntoTreino(divisaoTreinoDAO.buscaPorCriterioAlternativa1(id_divisao_treino));
                
                return treino;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    private PreparedStatement createPreparedStatement(Connection con, long id) throws SQLException {
        String sql = "select * from treino where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }

    public Treino updateTreinoBanco(Treino elemento) { //Update no Banco de Dados
        String sql = "update treino set objetivo = ?, data_inicio = ?, data_termino = ?, id_divisao_treino = ?, data_modificacao = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, elemento.getObjetivoTreino());
            stmt.setDate(2, java.sql.Date.valueOf(elemento.getDataInicioTreino()));
            stmt.setDate(3, java.sql.Date.valueOf(elemento.getDataTerminoTreino()));
            stmt.setLong(4, elemento.getDivisaoTreinoFromTreino().getIDDivisaoTreino());
            stmt.setDate(5, java.sql.Date.valueOf(elemento.getDataModificacao()));
            stmt.setLong(6, elemento.getIDTreino());
            
            stmt.execute();
            
            System.out.println("Elemento alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    public Treino excluiDivisaoTreinoBanco(Treino elemento) {// Exclusão no Banco de Dados
        String sql = "delete from treino where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, elemento.getIDTreino());
            
            stmt.execute();
            
            System.out.println("Elemento excluído com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    /*  */
    public StringBuilder createTreino(DivisaoTreino dt, String objetivo, String dt_inicio, String dt_termino)
    {
        Treino t = new Treino();

        t.setDataInicioTreino(dt_inicio);
        t.setDataTerminoTreino(dt_termino);
        t.setObjetivoTreino(objetivo);
        t.setDivisaoTreinoIntoTreino(dt);
        t.setDataID();

        // int i = 0;
        // while(treino[i] != null && i < treino.length-1)
        // {
        //     i++;
        // }

        // if(i < treino.length)
        // {
        //     if(treino[i] == null)
        //     {
        //         treino[i] = t;
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
        adicionaDivisaoTreinoBanco(t);
        StringBuilder builder = new StringBuilder("\nDivisão de Treino adicionada com sucesso!");

        return builder;
    }

    public StringBuilder readTreino()
    {
        StringBuilder builder = new StringBuilder();
        List<Treino> treinos = showTreino(null);

        for (Treino t : treinos)
        {
            if(t != null)
            {
                builder.append("\nID: " + t.getIDTreino());
                builder.append("\nObjetivo: " + t.getObjetivoTreino());
                builder.append("\nData de Inicio: " + t.getDataInicioTreino());
                builder.append("\nData de Termino: " + t.getDataTerminoTreino());
                builder.append("\n");
            }    
        }

        return builder;
    }

    public StringBuilder updateTreino(Treino t, String objetivo, String dt_inicio, String dt_termino)
    {
        StringBuilder builder = new StringBuilder("");
        int i = 0;
        // while(treino[i] != null && treino[i].getIDTreino() != id)
        // {
        //     i++;
        // }

        // if(treino[i] != null && treino[i].getIDTreino() == id)
        // {
            if(!objetivo.equals(""))
            {
                t.setObjetivoTreino(objetivo);
            }

            if(!dt_inicio.equals(""))
            {
                t.setDataInicioTreino(dt_inicio);
            }

            if(!dt_termino.equals(""))
            {
                t.setDataTerminoTreino(dt_termino);
            }
            t.setModData();
            updateTreinoBanco(t);

            builder.append("\nTreino atualizada com sucesso!");
            return builder;
        // }

        // builder.append("\nTreino não foi atualizada!");
        // return builder;
    }

    public StringBuilder deleteTreino(Long id)
    {
        StringBuilder builder = new StringBuilder("");
        // int i = 0;
        // while(treino[i] != null && treino[i].getIDTreino() != id)
        // {
        //     i++;
        // }

        // if(i < treino.length)
        // {
        //     if(treino[i].getIDTreino() == id)
        //     {
        //         treino[i] = null;
        //         builder.append("\nTreino deletada com sucesso!");
        //         return builder;
        //     }
        // }
        excluiDivisaoTreinoBanco(buscaPorCriterioAlternativa1(id));
        builder.append("\nTreino deletada com sucesso!");

        // builder.append("\nTreino não encontrada!");
        return builder;
    }

    public Treino getTreino(Long id)
    {
        Treino t = new Treino();

        int i = 0;
        while(treino[i] != null && treino[i].getIDTreino() != id)
        {
            i++;
        }

        if(treino[i] != null)
        {
            t = treino[i];
        }

        return t;
    }


}
