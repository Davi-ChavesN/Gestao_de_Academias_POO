package model;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import data_base_connector.ConnectionFactory;

import model.PessoaDAO;


public class EntradaAlunoDAO {

    EntradaAluno[] entradaAluno = new EntradaAluno[100];

    public EntradaAlunoDAO()
    {

    }

    PessoaDAO pessoaDAO = new PessoaDAO();

    /* Uso do Banco */
    public EntradaAluno adicionaEntradaAlunoBanco(EntradaAluno elemento) { //Criar entrada no Banco de Dados
        String sql = "insert into entrada_aluno "
                + "(date_time,id_pessoa,data_criacao,data_modificacao)" 
                + " values (?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            // seta os valores
            stmt.setTimestamp(1, java.sql.Timestamp.valueOf(elemento.getDateTimeEntradaAluno()));
            stmt.setLong(2, elemento.getPessoaFromEntradaAluno().getID());
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

    public List<EntradaAluno> showEntradasAlunos(EntradaAluno elemento) {  //Adicionar todas linhas do Banco em uma Lista
        String sql = "select * from entrada_aluno";

        DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");

        List<EntradaAluno> entradasAlunos = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                Timestamp date_time = rs.getTimestamp("date_time");
                Long id_pessoa = rs.getLong("id_pessoa");

                EntradaAluno entradaAluno = new EntradaAluno();
                entradaAluno.setId(id);
                entradaAluno.setDateTime(date_time.toLocalDateTime());
                entradaAluno.setPessoaIntoEntradaAluno(pessoaDAO.buscaPorCriterioAlternativa1(id_pessoa));

                entradasAlunos.add(entradaAluno);
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        // itera no ResultSet
        return entradasAlunos;
    }
    
    public EntradaAluno buscaPorCriterioAlternativa1(Long code) { /* Buscar um dado especifico no banco */
        try (Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement ps = createPreparedStatement(connection, code);
            ResultSet rs = ps.executeQuery()) {
            
            DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
            while (rs.next()) {
                Long id = rs.getLong("id");
                Date date_time = rs.getDate("date_time");
                Long id_pessoa = rs.getLong("id_pessoa");

                EntradaAluno entradaAluno = new EntradaAluno();
                entradaAluno.setId(id);
                entradaAluno.setDateTimeFromBanco(dtf.format(date_time));
                entradaAluno.setPessoaIntoEntradaAluno(pessoaDAO.buscaPorCriterioAlternativa1(id_pessoa));
                
                return entradaAluno;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    private PreparedStatement createPreparedStatement(Connection con, long id) throws SQLException {
        String sql = "select * from entrada_aluno where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }

    public EntradaAluno updatePagamentoRecorrenteBanco(EntradaAluno elemento) { //Update no Banco de Dados
        String sql = "update entrada_aluno set date_time = ?, id_pessoa = ?, data_modificacao = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setTimestamp(1, java.sql.Timestamp.valueOf(elemento.getDateTimeEntradaAluno()));
            stmt.setLong(2, elemento.getPessoaFromEntradaAluno().getID());
            stmt.setDate(3, java.sql.Date.valueOf(elemento.getDataModificacao()));
            stmt.setLong(4, elemento.getIDEntradaAluno());
            
            stmt.execute();
            
            System.out.println("Elemento alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    public EntradaAluno excluiPagamentoRecorrenteBanco(EntradaAluno elemento) {// Exclusão no Banco de Dados
        String sql = "delete from entrada_aluno where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, elemento.getIDEntradaAluno());
            
            stmt.execute();
            
            System.out.println("Elemento excluído com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    /*  */
    public void createEntradaAluno(Pessoa p)
    {
        EntradaAluno entA = new EntradaAluno();
        entA.setPessoaIntoEntradaAluno(p);
        entA.setDataID();
        entA.setDateTime(LocalDateTime.now());

        // int i = 0;
        // while(entradaAluno[i] != null && i < entradaAluno.length-1)
        // {
        //     i++;
        // }

        // if(i < entradaAluno.length)
        // {
        //     if(entradaAluno[i] == null)
        //     {
        //         entradaAluno[i] = entA;
        //     }
        // }
        adicionaEntradaAlunoBanco(entA);
    }

    public StringBuilder readEntradaAluno()
    {
        StringBuilder builder = new StringBuilder();
        List<EntradaAluno> entradasAlunos = showEntradasAlunos(null);

        for (EntradaAluno entA : entradasAlunos)
        {
            if(entA != null)
            {
                builder.append("\nID: " + entA.getIDEntradaAluno());
                builder.append("\nNome: " + entA.getPessoaFromEntradaAluno().getNome());
                builder.append("\nData e Horário: " + entA.getDateTimeEntradaAluno());
                builder.append("\n");
            }    
        }

        return builder;
    }
}
