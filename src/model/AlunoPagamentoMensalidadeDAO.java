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

import model.MensalidadeVigenteDAO;
import model.PessoaDAO;

public class AlunoPagamentoMensalidadeDAO {

    AlunoPagamentoMensalidade[] alunoPagamentoMensalidade = new AlunoPagamentoMensalidade[100];

    public AlunoPagamentoMensalidadeDAO()
    {

    }

    MensalidadeVigenteDAO mensalidadeVigenteDAO = new MensalidadeVigenteDAO();
    PessoaDAO pessoaDAO = new PessoaDAO();

    /* Uso do Banco */
    public AlunoPagamentoMensalidade adicionaPagamentoMensalidadeBanco(AlunoPagamentoMensalidade elemento) { //Criar entrada no Banco de Dados
        String sql = "insert into pagamento_mensalidade "
                + "(id_mensalidade_vigente,data_vencimento,data_pagamento,valor_pago,data,id_pessoa,modalidade,data_criacao,data_modificacao)" 
                + " values (?,?,?,?,?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            // seta os valores
            stmt.setLong(1, elemento.getMensalidadeVigenteFromAlunoPagamentoMensalidade().getIDMensalidadeVigente());
            stmt.setDate(2, java.sql.Date.valueOf(elemento.getDataVencimentoAlunoPagamentoMensalidade()));
            stmt.setDate(3, java.sql.Date.valueOf(elemento.getDataPagamentoAlunoPagamentoMensalidade()));
            stmt.setBigDecimal(4, elemento.getValorPagoAlunoPagamentoMensalidade());
            stmt.setDate(5, java.sql.Date.valueOf(elemento.getDataAlunoPagamentoMensalidade()));
            stmt.setLong(6, elemento.getPessoaFromAlunoPagamentoMensalidade().getID());
            stmt.setString(7, elemento.getModalidadeAlunoPagamentoMensalidade());
            stmt.setDate(8, java.sql.Date.valueOf(elemento.getDataCriacao()));
            stmt.setDate(9, java.sql.Date.valueOf(elemento.getDataModificacao()));
            
            stmt.execute();
            
            System.out.println("Elemento inserido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //na verdade deveria retornar o elemento que foi inserido agora
        return elemento;
    }

    public List<AlunoPagamentoMensalidade> showPagamentosMensalidades(AlunoPagamentoMensalidade elemento) {  //Adicionar todas linhas do Banco em uma Lista
        String sql = "select * from pagamento_mensalidade";

        DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");

        List<AlunoPagamentoMensalidade> pagamentosMensalidades = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                Long id_mensalidade_vigente = rs.getLong("id_mensalidade_vigente");
                Date data_vencimento = rs.getDate("data_vencimento");
                Date data_pagamento = rs.getDate("data_pagamento");
                Float valor_pago = rs.getFloat("valor_pago");
                Date data = rs.getDate("data");
                Long id_pessoa = rs.getLong("id_pessoa");
                String modalidade = rs.getString("modalidade");

                AlunoPagamentoMensalidade pagamentoMensalidade = new AlunoPagamentoMensalidade();
                pagamentoMensalidade.setId(id);
                pagamentoMensalidade.setMensalidadeVigenteIntoAlunoPagamentoMensalidade(mensalidadeVigenteDAO.buscaPorCriterioAlternativa1(id_mensalidade_vigente));
                pagamentoMensalidade.setDataVencimento(dtf.format(data_vencimento));
                pagamentoMensalidade.setDataVencimento(dtf.format(data_pagamento));
                pagamentoMensalidade.setValorPago(Float.toString(valor_pago));
                pagamentoMensalidade.setDataFromBanco(dtf.format(data));
                pagamentoMensalidade.setPessoaIntoAlunoPagamentoMensalidade(pessoaDAO.buscaPorCriterioAlternativa1(id_pessoa));
                if(modalidade.equals("dinheiro"))
                {
                    pagamentoMensalidade.setModalidade(1);
                }
                else if(modalidade.equals("pix"))
                {
                    pagamentoMensalidade.setModalidade(2);
                }
                else if(modalidade.equals("automático"))
                {
                    pagamentoMensalidade.setModalidade(3);
                }
                else if(modalidade.equals("pagamento recorrente"))
                {
                    pagamentoMensalidade.setModalidade(4);
                }

                pagamentosMensalidades.add(pagamentoMensalidade);
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        // itera no ResultSet
        return pagamentosMensalidades;
    }
    
    public AlunoPagamentoMensalidade buscaPorCriterioAlternativa1(Long code) { /* Buscar um dado especifico no banco */
        try (Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement ps = createPreparedStatement(connection, code);
            ResultSet rs = ps.executeQuery()) {
            
            DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
            while (rs.next()) {
                Long id = rs.getLong("id");
                Long id_mensalidade_vigente = rs.getLong("id_mensalidade_vigente");
                Date data_vencimento = rs.getDate("data_vencimento");
                Date data_pagamento = rs.getDate("data_pagamento");
                Float valor_pago = rs.getFloat("valor_pago");
                Date data = rs.getDate("data");
                Long id_pessoa = rs.getLong("id_pessoa");
                String modalidade = rs.getString("modalidade");

                AlunoPagamentoMensalidade pagamentoMensalidade = new AlunoPagamentoMensalidade();
                pagamentoMensalidade.setId(id);
                pagamentoMensalidade.setMensalidadeVigenteIntoAlunoPagamentoMensalidade(mensalidadeVigenteDAO.buscaPorCriterioAlternativa1(id_mensalidade_vigente));
                pagamentoMensalidade.setDataVencimento(dtf.format(data_vencimento));
                pagamentoMensalidade.setDataPagamento(dtf.format(data_pagamento));
                pagamentoMensalidade.setValorPago(Float.toString(valor_pago));
                pagamentoMensalidade.setDataFromBanco(dtf.format(data));
                pagamentoMensalidade.setPessoaIntoAlunoPagamentoMensalidade(pessoaDAO.buscaPorCriterioAlternativa1(id_pessoa));
                if(modalidade.equals("dinheiro"))
                {
                    pagamentoMensalidade.setModalidade(1);
                }
                else if(modalidade.equals("pix"))
                {
                    pagamentoMensalidade.setModalidade(2);
                }
                else if(modalidade.equals("automático"))
                {
                    pagamentoMensalidade.setModalidade(3);
                }
                else if(modalidade.equals("pagamento recorrente"))
                {
                    pagamentoMensalidade.setModalidade(4);
                }
                
                return pagamentoMensalidade;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    private PreparedStatement createPreparedStatement(Connection con, long id) throws SQLException {
        String sql = "select * from pagamento_mensalidade where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }

    public AlunoPagamentoMensalidade updatePagamentoMensalidadeBanco(AlunoPagamentoMensalidade elemento) { //Update no Banco de Dados
        String sql = "update pagamento_mensalidade set id_mensalidade_vigente = ?, data_vencimento = ?, data_pagamento = ?, valor_pago = ?, data = ?, id_pessoa = ?, modalidade = ?, data_modificacao = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, elemento.getMensalidadeVigenteFromAlunoPagamentoMensalidade().getIDMensalidadeVigente());
            stmt.setDate(2, java.sql.Date.valueOf(elemento.getDataVencimentoAlunoPagamentoMensalidade()));
            stmt.setDate(3, java.sql.Date.valueOf(elemento.getDataPagamentoAlunoPagamentoMensalidade()));
            stmt.setBigDecimal(4, elemento.getValorPagoAlunoPagamentoMensalidade());
            stmt.setDate(5, java.sql.Date.valueOf(elemento.getDataAlunoPagamentoMensalidade()));
            stmt.setLong(6, elemento.getPessoaFromAlunoPagamentoMensalidade().getID());
            stmt.setString(7, elemento.getModalidadeAlunoPagamentoMensalidade());
            stmt.setDate(8, java.sql.Date.valueOf(elemento.getDataModificacao()));
            stmt.setLong(9, elemento.getIDAlunoPagamentoMensalidade());
            
            stmt.execute();
            
            System.out.println("Elemento alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    public AlunoPagamentoMensalidade excluiPagamentoMensalidadeBanco(AlunoPagamentoMensalidade elemento) {// Exclusão no Banco de Dados
        String sql = "delete from pagamento_mensalidade where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, elemento.getIDAlunoPagamentoMensalidade());
            
            stmt.execute();
            
            System.out.println("Elemento excluído com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    /*  */
    public StringBuilder createAlunoPagamentoMensalidade(MensalidadeVigente mv, String data_vencimento, String data_pagamento, String valor_pago, Pessoa p, int modalidade)
    {
        AlunoPagamentoMensalidade apm = new AlunoPagamentoMensalidade();
        apm.setMensalidadeVigenteIntoAlunoPagamentoMensalidade(mv);
        apm.setDataVencimento(data_vencimento);
        apm.setDataPagamento(data_pagamento);
        apm.setValorPago(valor_pago);
        apm.setData();
        apm.setPessoaIntoAlunoPagamentoMensalidade(p);
        apm.setModalidade(modalidade);
        apm.setDataID();

        // int i = 0;
        // while(alunoPagamentoMensalidade[i] != null && i < alunoPagamentoMensalidade.length-1)
        // {
        //     i++;
        // }

        // if(i < alunoPagamentoMensalidade.length)
        // {
        //     if(alunoPagamentoMensalidade[i] == null)
        //     {
        //         alunoPagamentoMensalidade[i] = apm;
        //         StringBuilder builder = new StringBuilder("\nPagamento da Mensalidade do Aluno adicionada com sucesso!");
        //         return builder;
        //     }
        //     else
        //     {
        //         StringBuilder builder = new StringBuilder("\nNão foi possivel adicionar a nova Pagamento da Mensalidade do Aluno!");
        //         return builder;
        //     }
        // }
        // StringBuilder builder = new StringBuilder("\nDeu ruim");
        adicionaPagamentoMensalidadeBanco(apm);
        StringBuilder builder = new StringBuilder("\nPagamento da Mensalidade do Aluno adicionada com sucesso!");

        return builder;
    }

    public StringBuilder readAlunoPagamentoMensalidade()
    {
        StringBuilder builder = new StringBuilder();
        List<AlunoPagamentoMensalidade> pagamentosMensalidades = showPagamentosMensalidades(null);

        for (AlunoPagamentoMensalidade apm : pagamentosMensalidades)
        {
            if(apm != null)
            {
                builder.append("\nID: " + apm.getIDAlunoPagamentoMensalidade());
                builder.append("\nValor pago: R$" + apm.getValorPagoAlunoPagamentoMensalidade().toPlainString());
                builder.append("\nValor da mensalidade: R$" + apm.getMensalidadeVigenteFromAlunoPagamentoMensalidade().getValorMensalidadeVigente().toPlainString());
                builder.append("\nPessoa: " + apm.getPessoaFromAlunoPagamentoMensalidade().getNome());
                builder.append("\n");
            }    
        }

        return builder;
    }

    public StringBuilder updateAlunoPagamentoMensalidade(AlunoPagamentoMensalidade apm, String data_vencimento, String data_pagamento, String valor_pago, int modalidade)
    {
        StringBuilder builder = new StringBuilder("");
        // int i = 0;
        // while(alunoPagamentoMensalidade[i] != null && alunoPagamentoMensalidade[i].getIDAlunoPagamentoMensalidade() != id)
        // {
        //     i++;
        // }

        // if(alunoPagamentoMensalidade[i] != null && alunoPagamentoMensalidade[i].getIDAlunoPagamentoMensalidade() == id)
        // {
            if(!data_vencimento.equals(""))
            {
                apm.setDataVencimento(data_vencimento);
            }

            if(!data_pagamento.equals(""))
            {
                apm.setDataPagamento(data_pagamento);
            }

            if(!valor_pago.equals(""))
            {
                apm.setValorPago(valor_pago);
            }

            if(modalidade == 1 || modalidade == 2 || modalidade == 3 || modalidade == 4)
            {
                apm.setModalidade(modalidade);
            }
            apm.setModData();
            updatePagamentoMensalidadeBanco(apm);

        //     builder.append("\nPagamento da Mensalidade do Aluno atualizada com sucesso!");
        //     return builder;
        // }

        // builder.append("\nPagamento da Mensalidade do Aluno não foi atualizada!");
        return builder;
    }

    public StringBuilder deleteAlunoPagamentoMensalidade(Long id)
    {
        StringBuilder builder = new StringBuilder("");
        // int i = 0;
        // while(alunoPagamentoMensalidade[i] != null && alunoPagamentoMensalidade[i].getIDAlunoPagamentoMensalidade() != id)
        // {
        //     i++;
        // }

        // if(i < alunoPagamentoMensalidade.length)
        // {
        //     if(alunoPagamentoMensalidade[i].getIDAlunoPagamentoMensalidade() == id)
        //     {
        //         alunoPagamentoMensalidade[i] = null;
        //         builder.append("\nMensalidade Vigente deletada com sucesso!");
        //         return builder;
        //     }
        // }
        excluiPagamentoMensalidadeBanco(buscaPorCriterioAlternativa1(id));

        // builder.append("\nMensalidade Vigente não encontrada!");
        return builder;
    }

    public AlunoPagamentoMensalidade getAlunoPagamentoMensalidade(Long id)
    {
        AlunoPagamentoMensalidade apm = new AlunoPagamentoMensalidade();

        int i = 0;
        while(alunoPagamentoMensalidade[i] != null && alunoPagamentoMensalidade[i].getIDAlunoPagamentoMensalidade() != id)
        {
            i++;
        }

        if(alunoPagamentoMensalidade[i] != null)
        {
            apm = alunoPagamentoMensalidade[i];
        }

        return apm;
    }

}
