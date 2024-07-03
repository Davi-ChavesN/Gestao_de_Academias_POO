package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import data_base_connector.ConnectionFactory;

public class MensalidadeVigenteDAO {

    MensalidadeVigente[] mensalidadeVigente = new MensalidadeVigente[100];

    public MensalidadeVigenteDAO()
    {
        MensalidadeVigente mv = new MensalidadeVigente();
        mv.setValor("90.00");
        mv.setInicio("01/01/2024");
        mv.setInicio("31/12/2024");
        mv.setDataID();
        mensalidadeVigente[0] = mv;

        mv = new MensalidadeVigente();
        mv.setValor("140.00");
        mv.setInicio("01/01/2024");
        mv.setInicio("31/12/2024");
        mv.setDataID();
        mensalidadeVigente[1] = mv;

        mv = new MensalidadeVigente();
        mv.setValor("200.00");
        mv.setInicio("01/01/2024");
        mv.setInicio("31/12/2024");
        mv.setDataID();
        mensalidadeVigente[2] = mv;
    }


    /* Uso do Banco */
    public MensalidadeVigente adicionaMensalidadeVigenteBanco(MensalidadeVigente elemento) { //Criar entrada no Banco de Dados
        String sql = "insert into mensalidade_vigente "
                + "(valor,inicio,termino,data_criacao,data_modificacao)" 
                + " values (?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            // seta os valores
            stmt.setBigDecimal(1, elemento.getValorMensalidadeVigente());
            stmt.setDate(2, java.sql.Date.valueOf(elemento.getInicioMensalidadeVigente()));
            stmt.setDate(3, java.sql.Date.valueOf(elemento.getTerminoMensalidadeVigente()));
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

    public List<MensalidadeVigente> showMensalidadesVigentes(MensalidadeVigente elemento) {  //Adicionar todas linhas do Banco em uma Lista
        String sql = "select * from mensalidade_vigente";

        DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");

        List<MensalidadeVigente> mensalidadesVigentes = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                Float valor = rs.getFloat("valor");
                Date inicio = rs.getDate("inicio");
                Date termino = rs.getDate("termino");

                MensalidadeVigente mensalidadeVigente = new MensalidadeVigente();
                mensalidadeVigente.setId(id);
                mensalidadeVigente.setValor(Float.toString(valor));
                mensalidadeVigente.setInicio(dtf.format(inicio));
                mensalidadeVigente.setTermino(dtf.format(termino));

                mensalidadesVigentes.add(mensalidadeVigente);
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        // itera no ResultSet
        return mensalidadesVigentes;
    }
    
    public MensalidadeVigente buscaPorCriterioAlternativa1(Long code) { /* Buscar um dado especifico no banco */
        try (Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement ps = createPreparedStatement(connection, code);
            ResultSet rs = ps.executeQuery()) {
            
            DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
            while (rs.next()) {
                Long id = rs.getLong("id");
                Float valor = rs.getFloat("valor");
                Date inicio = rs.getDate("inicio");
                Date termino = rs.getDate("termino");

                MensalidadeVigente mensalidadeVigente = new MensalidadeVigente();
                mensalidadeVigente.setId(id);
                mensalidadeVigente.setValor(Float.toString(valor));
                mensalidadeVigente.setInicio(dtf.format(inicio));
                mensalidadeVigente.setTermino(dtf.format(termino));
                
                return mensalidadeVigente;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    private PreparedStatement createPreparedStatement(Connection con, long id) throws SQLException {
        String sql = "select * from mensalidade_vigente where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }

    public MensalidadeVigente updateMensalidadeVigenteBanco(MensalidadeVigente elemento) { //Update no Banco de Dados
        String sql = "update mensalidade_vigente set valor = ?, inicio = ?, termino = ?, data_modificacao = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setBigDecimal(1, elemento.getValorMensalidadeVigente());
            stmt.setDate(2, java.sql.Date.valueOf(elemento.getInicioMensalidadeVigente()));
            stmt.setDate(3, java.sql.Date.valueOf(elemento.getTerminoMensalidadeVigente()));
            stmt.setDate(4, java.sql.Date.valueOf(elemento.getDataModificacao()));
            stmt.setLong(5, elemento.getIDMensalidadeVigente());
            
            stmt.execute();
            
            System.out.println("Elemento alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    public MensalidadeVigente excluiMensalidadeVigenteBanco(MensalidadeVigente elemento) {// Exclusão no Banco de Dados
        String sql = "delete from mensalidade_vigente where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, elemento.getIDMensalidadeVigente());
            
            stmt.execute();
            
            System.out.println("Elemento excluído com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    /*  */
    public StringBuilder createMensalidadeVigente(String valor, String inicio, String termino)
    {
        MensalidadeVigente mv = new MensalidadeVigente();
        mv.setValor(valor);
        mv.setInicio(inicio);
        mv.setTermino(termino);
        mv.setDataID();

        // int i = 0;
        // while(mensalidadeVigente[i] != null && i < mensalidadeVigente.length-1)
        // {
        //     i++;
        // }

        // if(i < mensalidadeVigente.length)
        // {
        //     if(mensalidadeVigente[i] == null)
        //     {
        //         mensalidadeVigente[i] = mv;
        //         StringBuilder builder = new StringBuilder("\nMensalidade Vigente adicionada com sucesso!");
        //         return builder;
        //     }
        //     else
        //     {
        //         StringBuilder builder = new StringBuilder("\nNão foi possivel adicionar a nova Mensalidade Vigente!");
        //         return builder;
        //     }
        // }
        // StringBuilder builder = new StringBuilder("\nDeu ruim");
        adicionaMensalidadeVigenteBanco(mv);
        StringBuilder builder = new StringBuilder("\nMensalidade Vigente adicionada com sucesso!");

        return builder;
    }

    public StringBuilder readMensalidadeVigente()
    {
        StringBuilder builder = new StringBuilder();
        List<MensalidadeVigente> mensalidadesVigentes = showMensalidadesVigentes(null);

        for (MensalidadeVigente mv : mensalidadesVigentes)
        {
            if(mv != null)
            {
                builder.append("\nID: " + mv.getIDMensalidadeVigente());
                builder.append("\nValor: R$" + mv.getValorMensalidadeVigente().toPlainString());
                builder.append("\nData de Inicio: " + mv.getInicioMensalidadeVigente());
                builder.append("\nData de Termino: " + mv.getTerminoMensalidadeVigente());
                builder.append("\n");
            }    
        }

        return builder;
    }

    public StringBuilder updateMensalidadeVigente(MensalidadeVigente mv, String valor, String inicio, String termino)
    {
        StringBuilder builder = new StringBuilder("");
        // int i = 0;
        // while(mensalidadeVigente[i] != null && mensalidadeVigente[i].getIDMensalidadeVigente() != id)
        // {
        //     i++;
        // }

        // if(mv != null && mv.getIDMensalidadeVigente() == id)
        // {
            if(!valor.equals(""))
            {
                mv.setValor(valor);;
            }
            if(!inicio.equals(""))
            {
                mv.setInicio(inicio);
            }
            if(!termino.equals(""))
            {
                mv.setTermino(termino);
            }
            mv.setModData();
            updateMensalidadeVigenteBanco(mv);

            builder.append("\nMensalidade Vigente atualizada com sucesso!");
        //     return builder;
        // }

        // builder.append("\nMensalidade Vigente não foi atualizada!");
        return builder;
    }

    public StringBuilder deleteMensalidadeVigente(Long id)
    {
        StringBuilder builder = new StringBuilder("");
        // int i = 0;
        // while(mensalidadeVigente[i] != null && mensalidadeVigente[i].getIDMensalidadeVigente() != id)
        // {
        //     i++;
        // }

        // if(i < mensalidadeVigente.length)
        // {
        //     if(mensalidadeVigente[i].getIDMensalidadeVigente() == id)
        //     {
        //         mensalidadeVigente[i] = null;
        //         builder.append("\nMensalidade Vigente deletada com sucesso!");
        //         return builder;
        //     }
        // }
        excluiMensalidadeVigenteBanco(buscaPorCriterioAlternativa1(id));
        builder.append("\nMensalidade Vigente deletada com sucesso!");

        // builder.append("\nMensalidade Vigente não encontrada!");
        return builder;
    }

    public MensalidadeVigente getMensalidadeVigente(Long id)
    {
        MensalidadeVigente mv = new MensalidadeVigente();

        int i = 0;
        while(mensalidadeVigente[i] != null && mensalidadeVigente[i].getIDMensalidadeVigente() != id)
        {
            i++;
        }

        if(mensalidadeVigente[i] != null)
        {
            mv = mensalidadeVigente[i];
        }

        return mv;
    }
}
