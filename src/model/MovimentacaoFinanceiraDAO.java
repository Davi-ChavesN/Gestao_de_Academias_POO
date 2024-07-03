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

import javax.swing.JOptionPane;

import data_base_connector.ConnectionFactory;

public class MovimentacaoFinanceiraDAO {

    MovimentacaoFinanceira[] movimentacaoFinanceira = new MovimentacaoFinanceira[100];

    public MovimentacaoFinanceiraDAO()
    {

    }


    /* Uso do Banco */
    public MovimentacaoFinanceira adicionaMovimentacaoFinanceiraBanco(MovimentacaoFinanceira elemento) { //Criar entrada no Banco de Dados
        String sql = "insert into movimentacao_financeira "
                + "(valor,tipo,descricao,data_criacao,data_modificacao)" 
                + " values (?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            // seta os valores
            stmt.setBigDecimal(1, elemento.getValorMovimentacaoFinanceiroBigD());
            stmt.setString(2, elemento.getTipoMovimentacaoFinanceira());
            stmt.setString(3, elemento.getDescricaoMovimentacaoFinanceira());
            stmt.setDate(4, java.sql.Date.valueOf(elemento.getDataCriacao()));
            stmt.setDate(5, java.sql.Date.valueOf(elemento.getDataModificacao()));
            
            stmt.execute();
            
            System.out.println("Elemento inserido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //na verdade deveria retornar o elemento que foi inserido agora
        return elemento;
    }

    public List<MovimentacaoFinanceira> showMovimentacoesFinanceiras(MovimentacaoFinanceira elemento) {  //Adicionar todas linhas do Banco em uma Lista
        String sql = "select * from movimentacao_financeira";

        DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");

        List<MovimentacaoFinanceira> movimentacoesFinanceiras = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                Float valor = rs.getFloat("valor");
                String tipo = rs.getString("tipo");
                String descricao = rs.getString("descricao");

                MovimentacaoFinanceira movimentacaoFinanceira = new MovimentacaoFinanceira();
                movimentacaoFinanceira.setId(id);
                movimentacaoFinanceira.setValorMovimentacaoFinanceiro(Float.toString(valor));
                movimentacaoFinanceira.setTipoMovimentacaoFinanceira(tipo);
                movimentacaoFinanceira.setDescricaoMovimentacaoFinanceira(descricao);

                movimentacoesFinanceiras.add(movimentacaoFinanceira);
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        // itera no ResultSet
        return movimentacoesFinanceiras;
    }
    
    public MovimentacaoFinanceira buscaPorCriterioAlternativa1(Long code) { /* Buscar um dado especifico no banco */
        try (Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement ps = createPreparedStatement(connection, code);
            ResultSet rs = ps.executeQuery()) {
            
            DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
            while (rs.next()) {
                Long id = rs.getLong("id");
                Float valor = rs.getFloat("valor");
                String tipo = rs.getString("tipo");
                String descricao = rs.getString("descricao");

                MovimentacaoFinanceira movimentacaoFinanceira = new MovimentacaoFinanceira();
                movimentacaoFinanceira.setId(id);
                movimentacaoFinanceira.setValorMovimentacaoFinanceiro(Float.toString(valor));
                movimentacaoFinanceira.setTipoMovimentacaoFinanceira(tipo);
                movimentacaoFinanceira.setDescricaoMovimentacaoFinanceira(descricao);
                
                return movimentacaoFinanceira;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    private PreparedStatement createPreparedStatement(Connection con, long id) throws SQLException {
        String sql = "select * from movimentacao_financeira where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }

    public MovimentacaoFinanceira updateMovimentacaoFinanceiraBanco(MovimentacaoFinanceira elemento) { //Update no Banco de Dados
        String sql = "update movimentacao_financeira set valor = ?, tipo = ?, descricao = ?, data_modificacao = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setBigDecimal(1, elemento.getValorMovimentacaoFinanceiroBigD());
            stmt.setString(2, elemento.getTipoMovimentacaoFinanceira());
            stmt.setString(3, elemento.getDescricaoMovimentacaoFinanceira());
            stmt.setDate(4, java.sql.Date.valueOf(elemento.getDataModificacao()));
            stmt.setLong(5, elemento.getIDMovimentacaoFinanceira());
            
            stmt.execute();
            
            System.out.println("Elemento alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    public MovimentacaoFinanceira excluiMovimentacaoFinanceiraBanco(MovimentacaoFinanceira elemento) {// Exclusão no Banco de Dados
        String sql = "delete from movimentacao_financeira where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, elemento.getIDMovimentacaoFinanceira());
            
            stmt.execute();
            
            System.out.println("Elemento excluído com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    /*  */
    public StringBuilder createMovimentacaoFinanceira(String valor, int tipo, String descricao)
    {
        StringBuilder builder = new StringBuilder();
        MovimentacaoFinanceira mf = new MovimentacaoFinanceira();
        
        mf.setValorMovimentacaoFinanceiro(valor);
        if(tipo == 1)
        {
            mf.setTipoMovimentacaoFinanceira("Entrada");
        }
        else if(tipo == 2)
        {
            mf.setTipoMovimentacaoFinanceira("Saída");
        }
        mf.setDescricaoMovimentacaoFinanceira(descricao);
        mf.setDataID();

        // int i = 0;
        // while(movimentacaoFinanceira[i] != null && i < movimentacaoFinanceira.length-1)
        // {
        //     i++;
        // }

        // if(i < movimentacaoFinanceira.length)
        // {
        //     if(movimentacaoFinanceira[i] == null)
        //     {
        //         movimentacaoFinanceira[i] = mf;
        //         builder.append("Movimentação Financeira adicionada com sucesso!");
        //     }
        //     else
        //     {
        //         builder.append("Não é possível adicionar mais movimentações financeiras!");
        //         JOptionPane.showMessageDialog(null, builder);
        //     }
        // }
        adicionaMovimentacaoFinanceiraBanco(mf);

        return builder;
    }

    public StringBuilder readMovimentacaoFinanceira()
    {
        StringBuilder builder = new StringBuilder();
        List<MovimentacaoFinanceira> movimentacoesFinanceiras = showMovimentacoesFinanceiras(null);

        for (MovimentacaoFinanceira mf : movimentacoesFinanceiras)
        {
            if(mf != null)
            {
                builder.append("\nID: " + mf.getIDMovimentacaoFinanceira());
                builder.append("\nValor: " + mf.getValorMovimentacaoFinanceiro());
                builder.append("\nTipo: " + mf.getTipoMovimentacaoFinanceira());
                builder.append("\nDescrição: " + mf.getDescricaoMovimentacaoFinanceira());
                builder.append("\n");
            }    
        }

        return builder;
    }

    public StringBuilder updateMovimentacaoFinanceira(MovimentacaoFinanceira mf, String valor, String tipo, String descricao)
    {
        StringBuilder builder = new StringBuilder("");
        // int i = 0;
        // while(movimentacaoFinanceira[i] != null && movimentacaoFinanceira[i].getIDMovimentacaoFinanceira() != id)
        // {
        //     i++;
        // }

        // if(movimentacaoFinanceira[i] != null && movimentacaoFinanceira[i].getIDMovimentacaoFinanceira() == id)
        // {
            if(!valor.equals(""))
            {
                mf.setValorMovimentacaoFinanceiro(valor);
            }
            if(!tipo.equals(""))
            {
                mf.setTipoMovimentacaoFinanceira(tipo);
            }
            if(!descricao.equals(""))
            {
                mf.setDescricaoMovimentacaoFinanceira(descricao);
            }
            mf.setModData();
            updateMovimentacaoFinanceiraBanco(mf);

            builder.append("\nMensalidade Vigente atualizada com sucesso!");
            return builder;
        // }

        // builder.append("\nMensalidade Vigente não foi atualizada!");
        // return builder;
    }

    public StringBuilder deleteMovimentacaoFinanceira(Long id)
    {
        StringBuilder builder = new StringBuilder("");
        // int i = 0;
        // while(movimentacaoFinanceira[i] != null && movimentacaoFinanceira[i].getIDMovimentacaoFinanceira() != id)
        // {
        //     i++;
        // }

        // if(i < movimentacaoFinanceira.length)
        // {
        //     if(movimentacaoFinanceira[i].getIDMovimentacaoFinanceira() == id)
        //     {
        //         movimentacaoFinanceira[i] = null;
        //         builder.append("\nMensalidade Vigente deletada com sucesso!");
        //         return builder;
        //     }
        // }
        excluiMovimentacaoFinanceiraBanco(buscaPorCriterioAlternativa1(id));

        builder.append("\nMensalidade Vigente não encontrada!");
        return builder;
    }

}
