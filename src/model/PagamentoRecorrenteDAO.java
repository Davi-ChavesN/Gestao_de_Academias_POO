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

import model.PessoaDAO;

public class PagamentoRecorrenteDAO {

    PagamentoRecorrente[] pagamentoRecorrente = new PagamentoRecorrente[100];

    public PagamentoRecorrenteDAO()
    {

    }

    PessoaDAO pessoaDAO = new PessoaDAO();

    /* Uso do Banco */
    public PagamentoRecorrente adicionaPagamentoRecorrenteBanco(PagamentoRecorrente elemento) { //Criar entrada no Banco de Dados
        String sql = "insert into pagamento_recorrente "
                + "(id_pessoa,data,cartao_credito,valor,data_inicio,meses_autorizados,data_criacao,data_modificacao)" 
                + " values (?,?,?,?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            // seta os valores
            stmt.setLong(1, elemento.getPessoaFromPagamentoRecorrente().getID());
            stmt.setDate(2, java.sql.Date.valueOf(elemento.getDataPagamentoRecorrente()));
            stmt.setString(3, elemento.getCartaoPagamentoRecorrente());
            stmt.setBigDecimal(4, elemento.getValorPagamentoRecorrente());
            stmt.setDate(5, java.sql.Date.valueOf(elemento.getDataInicioPagamentoRecorrente()));
            stmt.setInt(6, elemento.getMesesAutPagamentoRecorrente());
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

    public List<PagamentoRecorrente> showPagamentosRecorrentes(PagamentoRecorrente elemento) {  //Adicionar todas linhas do Banco em uma Lista
        String sql = "select * from pagamento_recorrente";

        DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");

        List<PagamentoRecorrente> pagamentosRecorrentes = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                Long id_pessoa = rs.getLong("id_pessoa");
                Date data = rs.getDate("data");
                String cartao_credito = rs.getString("cartao_credito");
                Float valor = rs.getFloat("valor");
                Date data_inicio = rs.getDate("data_inicio");
                Integer meses_autorizados = rs.getInt("meses_autorizados");

                PagamentoRecorrente pagamentoRecorrente = new PagamentoRecorrente();
                pagamentoRecorrente.setId(id);
                pagamentoRecorrente.setPessoaIntoPagamentoRecorrente(pessoaDAO.buscaPorCriterioAlternativa1(id_pessoa));
                pagamentoRecorrente.setDataPagamentoRecorrenteFromBanco(dtf.format(data));
                pagamentoRecorrente.setCartaoCreditoPagamentoRecorrente(cartao_credito);
                pagamentoRecorrente.setValorPagamentoRecorrente(Float.toString(valor));
                pagamentoRecorrente.setDataInicioPagamentoRecorrente(dtf.format(data_inicio));
                pagamentoRecorrente.setMesesAutorizadoPagamentoRecorrente(meses_autorizados);

                pagamentosRecorrentes.add(pagamentoRecorrente);
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        // itera no ResultSet
        return pagamentosRecorrentes;
    }
    
    public PagamentoRecorrente buscaPorCriterioAlternativa1(Long code) { /* Buscar um dado especifico no banco */
        try (Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement ps = createPreparedStatement(connection, code);
            ResultSet rs = ps.executeQuery()) {
            
            DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
            while (rs.next()) {
                Long id = rs.getLong("id");
                Long id_pessoa = rs.getLong("id_pessoa");
                Date data = rs.getDate("data");
                String cartao_credito = rs.getString("cartao_credito");
                Float valor = rs.getFloat("valor");
                Date data_inicio = rs.getDate("data_inicio");
                Integer meses_autorizados = rs.getInt("meses_autorizados");

                PagamentoRecorrente pagamentoRecorrente = new PagamentoRecorrente();
                pagamentoRecorrente.setId(id);
                pagamentoRecorrente.setPessoaIntoPagamentoRecorrente(pessoaDAO.buscaPorCriterioAlternativa1(id_pessoa));
                pagamentoRecorrente.setDataPagamentoRecorrenteFromBanco(dtf.format(data));
                pagamentoRecorrente.setCartaoCreditoPagamentoRecorrente(cartao_credito);
                pagamentoRecorrente.setValorPagamentoRecorrente(Float.toString(valor));
                pagamentoRecorrente.setDataInicioPagamentoRecorrente(dtf.format(data_inicio));
                pagamentoRecorrente.setMesesAutorizadoPagamentoRecorrente(meses_autorizados);
                
                return pagamentoRecorrente;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    private PreparedStatement createPreparedStatement(Connection con, long id) throws SQLException {
        String sql = "select * from pagamento_recorrente where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }

    public PagamentoRecorrente updatePagamentoRecorrenteBanco(PagamentoRecorrente elemento) { //Update no Banco de Dados
        String sql = "update pagamento_recorrente set id_pessoa = ?, data = ?, cartao_credito = ?, valor = ?, data_inicio = ?, meses_autorizados = ?, data_modificacao = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, elemento.getPessoaFromPagamentoRecorrente().getID());
            stmt.setDate(2, java.sql.Date.valueOf(elemento.getDataPagamentoRecorrente()));
            stmt.setString(3, elemento.getCartaoPagamentoRecorrente());
            stmt.setBigDecimal(4, elemento.getValorPagamentoRecorrente());
            stmt.setDate(5, java.sql.Date.valueOf(elemento.getDataInicioPagamentoRecorrente()));
            stmt.setInt(6, elemento.getMesesAutPagamentoRecorrente());
            stmt.setDate(7, java.sql.Date.valueOf(elemento.getDataModificacao()));
            stmt.setLong(8, elemento.getIDPagamentoRecorrente());
            
            stmt.execute();
            
            System.out.println("Elemento alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    public PagamentoRecorrente excluiPagamentoRecorrenteBanco(PagamentoRecorrente elemento) {// Exclusão no Banco de Dados
        String sql = "delete from pagamento_recorrente where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, elemento.getIDPagamentoRecorrente());
            
            stmt.execute();
            
            System.out.println("Elemento excluído com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    /*  */
    public StringBuilder createPagamentoRecorrente(Pessoa p, String data, String cartao, String valor, String data_inicio, int meses_aut)
    {
        PagamentoRecorrente pr = new PagamentoRecorrente();
        pr.setPessoaIntoPagamentoRecorrente(p);
        pr.setDataPagamentoRecorrente();
        pr.setCartaoCreditoPagamentoRecorrente(cartao);
        pr.setValorPagamentoRecorrente(valor);
        pr.setDataInicioPagamentoRecorrente(data_inicio);
        pr.setMesesAutorizadoPagamentoRecorrente(meses_aut);
        pr.setDataID();

        // int i = 0;
        // while(pagamentoRecorrente[i] != null && i < pagamentoRecorrente.length-1)
        // {
        //     i++;
        // }

        // if(i < pagamentoRecorrente.length)
        // {
        //     if(pagamentoRecorrente[i] == null)
        //     {
        //         pagamentoRecorrente[i] = pr;
        //         StringBuilder builder = new StringBuilder("\nPagamento Recorrente adicionado com sucesso!");
        //         return builder;
        //     }
        //     else
        //     {
        //         StringBuilder builder = new StringBuilder("\nNão foi possivel adicionar o novo Pagamento Recorrente!");
        //         return builder;
        //     }
        // }
        // StringBuilder builder = new StringBuilder("\nDeu ruim");
        adicionaPagamentoRecorrenteBanco(pr);
        StringBuilder builder = new StringBuilder("\nPagamento Recorrente adicionado com sucesso!");

        return builder;
    }

    public StringBuilder readPagamentoRecorrente()
    {
        StringBuilder builder = new StringBuilder();
        List<PagamentoRecorrente> pagamentosRecorrentes = showPagamentosRecorrentes(null);

        for (PagamentoRecorrente pr : pagamentosRecorrentes)
        {
            if(pr != null)
            {
                builder.append("\nID: " + pr.getIDPagamentoRecorrente());
                builder.append("\nNome: " + pr.getPessoaFromPagamentoRecorrente().getNome());
                builder.append("\nValor: R$" + pr.getValorPagamentoRecorrente());
                builder.append("\nData de Inicio: " + pr.getDataInicioPagamentoRecorrente());
                builder.append("\nMeses Autorizados: " + pr.getMesesAutPagamentoRecorrente());
                builder.append("\n");
            }    
        }

        return builder;
    }

    public StringBuilder updatePagamentoRecorrente(PagamentoRecorrente pr, String valor, String data_inicio, int meses_aut)
    {
        StringBuilder builder = new StringBuilder("");
        // int i = 0;
        // while(pagamentoRecorrente[i] != null && pagamentoRecorrente[i].getIDPagamentoRecorrente() != id)
        // {
        //     i++;
        // }

        // if(pagamentoRecorrente[i] != null && pagamentoRecorrente[i].getIDPagamentoRecorrente() == id)
        // {
            if(!valor.equals(""))
            {
                pr.setValorPagamentoRecorrente(valor);
            }

            if(!data_inicio.equals(""))
            {
                pr.setDataInicioPagamentoRecorrente(data_inicio);
            }

            if(meses_aut != pr.getMesesAutPagamentoRecorrente())
            {
                pr.setMesesAutorizadoPagamentoRecorrente(meses_aut);
            }
            pr.setModData();
            updatePagamentoRecorrenteBanco(pr);

            builder.append("\nPagamento Recorrente atualizado com sucesso!");
            return builder;
        // }

        // builder.append("\nPagamento Recorrente não foi atualizada!");
        // return builder;
    }

    public StringBuilder deletePagamentoRecorrente(Long id)
    {
        StringBuilder builder = new StringBuilder("");
        // int i = 0;
        // while(pagamentoRecorrente[i] != null && pagamentoRecorrente[i].getIDPagamentoRecorrente() != id)
        // {
        //     i++;
        // }

        // if(i < pagamentoRecorrente.length)
        // {
        //     if(pagamentoRecorrente[i].getIDPagamentoRecorrente() == id)
        //     {
        //         pagamentoRecorrente[i] = null;
        //         builder.append("\nPagamento Recorrente deletada com sucesso!");
        //         return builder;
        //     }
        // }
        excluiPagamentoRecorrenteBanco(buscaPorCriterioAlternativa1(id));

        builder.append("\nPagamento Recorrente não encontrada!");
        return builder;
    }

    public PagamentoRecorrente getPagamentoRecorrente(Long id)
    {
        PagamentoRecorrente pr = new PagamentoRecorrente();

        int i = 0;
        while(pagamentoRecorrente[i] != null && pagamentoRecorrente[i].getIDPagamentoRecorrente() != id)
        {
            i++;
        }

        if(pagamentoRecorrente[i] != null)
        {
            pr = pagamentoRecorrente[i];
        }

        return pr;
    }

}
