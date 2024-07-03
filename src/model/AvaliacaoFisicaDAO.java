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

import model.PessoaDAO;

public class AvaliacaoFisicaDAO {

    AvaliacaoFisica[] avalicao = new AvaliacaoFisica[100];

    PessoaDAO pessoaDAO = new PessoaDAO();

    /* Uso do Banco */
    public AvaliacaoFisica adicionaAvaliacaoFisicaBanco(AvaliacaoFisica elemento) { //Criar entrada no Banco de Dados
        String sql = "insert into avaliacao_fisica "
                + "(id_pessoa,ultimo_treino,peso,altura,imc,resultado_imc,nota,data_criacao,data_modificacao)" 
                + " values (?,?,?,?,?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            // seta os valores
            stmt.setLong(1, elemento.getPessoa().getID());
            try{
                stmt.setDate(2, java.sql.Date.valueOf(elemento.getUltimoTreino()));
            } catch(NullPointerException e)
            {
                stmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            }
            stmt.setFloat(3, elemento.getPeso());
            stmt.setFloat(4, elemento.getAltura());
            stmt.setFloat(5, elemento.getIMC());
            stmt.setString(6, elemento.getImcResult());
            stmt.setFloat(7, elemento.getResultRating());
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

    public List<AvaliacaoFisica> showAvaliacoesFisicas(AvaliacaoFisica elemento) {  //Adicionar todas linhas do Banco em uma Lista
        String sql = "select * from avaliacao_fisica";

        DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");

        List<AvaliacaoFisica> avaliacoesFisicas = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                Long id_pessoa = rs.getLong("id_pessoa");
                Date ultimo_treino = rs.getDate("ultimo_treino");
                Float peso = rs.getFloat("peso");
                Float altura = rs.getFloat("altura");
                Float imc = rs.getFloat("imc");
                String resultado_imc = rs.getString("resultado_imc");
                Float nota = rs.getFloat("nota");

                AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
                avaliacaoFisica.setId(id);
                avaliacaoFisica.setPessoa(pessoaDAO.buscaPorCriterioAlternativa1(id_pessoa));
                avaliacaoFisica.setUltimoTreino(dtf.format(ultimo_treino));
                avaliacaoFisica.setPeso(peso);
                avaliacaoFisica.setAltura(altura);
                avaliacaoFisica.setIMC(imc);
                avaliacaoFisica.setImcResult(resultado_imc);
                avaliacaoFisica.setResultRating(nota);

                avaliacoesFisicas.add(avaliacaoFisica);
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        // itera no ResultSet
        return avaliacoesFisicas;
    }
    
    public AvaliacaoFisica buscaPorCriterioAlternativa1(Long code) { /* Buscar um dado especifico no banco */
        try (Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement ps = createPreparedStatement(connection, code);
            ResultSet rs = ps.executeQuery()) {
            
            DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
            while (rs.next()) {
                Long id = rs.getLong("id");
                Long id_pessoa = rs.getLong("id_pessoa");
                Date ultimo_treino = rs.getDate("ultimo_treino");
                Float peso = rs.getFloat("peso");
                Float altura = rs.getFloat("altura");
                Float imc = rs.getFloat("imc");
                String resultado_imc = rs.getString("resultado_imc");
                Float nota = rs.getFloat("nota");

                AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
                avaliacaoFisica.setId(id);
                avaliacaoFisica.setPessoa(pessoaDAO.buscaPorCriterioAlternativa1(id_pessoa));
                avaliacaoFisica.setUltimoTreino(dtf.format(ultimo_treino));
                avaliacaoFisica.setPeso(peso);
                avaliacaoFisica.setAltura(altura);
                avaliacaoFisica.setIMC(imc);
                avaliacaoFisica.setImcResult(resultado_imc);
                avaliacaoFisica.setResultRating(nota);
                
                return avaliacaoFisica;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    private PreparedStatement createPreparedStatement(Connection con, long id) throws SQLException {
        String sql = "select * from avaliacao_fisica where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }

    public AvaliacaoFisica updateAvalicaoFisicaBanco(AvaliacaoFisica elemento) { //Update no Banco de Dados
        String sql = "update avaliacao_fisica set id_pessoa = ?, ultimo_treino = ?, peso = ?, altura = ?, imc = ?, resultado_imc = ?, nota = ?, data_modificacao = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, elemento.getPessoa().getID());
            try{
                stmt.setDate(2, java.sql.Date.valueOf(elemento.getUltimoTreino()));
            } catch(NullPointerException e)
            {
                stmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            }
            stmt.setFloat(3, elemento.getPeso());
            stmt.setFloat(4, elemento.getAltura());
            stmt.setFloat(5, elemento.getIMC());
            stmt.setString(6, elemento.getImcResult());
            stmt.setFloat(7, elemento.getResultRating());
            stmt.setDate(8, java.sql.Date.valueOf(elemento.getDataModificacao()));
            stmt.setLong(9, elemento.getID());
            
            stmt.execute();
            
            System.out.println("Elemento alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    public AvaliacaoFisica excluiAvaliacaoFisicaBanco(AvaliacaoFisica elemento) {// Exclusão no Banco de Dados
        String sql = "delete from avaliacao_fisica where id = ?";

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
    public StringBuilder readAvaliacaoFisica(Pessoa usuario)
    {
        int cont = 0;
        StringBuilder builder = new StringBuilder();
        List<AvaliacaoFisica> avaliacoesFisicas = showAvaliacoesFisicas(null);

        for (AvaliacaoFisica af : avaliacoesFisicas)
        {
            if(af != null && af.getPessoa().getID() == usuario.getID())
            {
                builder.append("\nID: " + af.getID());
                builder.append(" - Nome: " + usuario.getNome());
                builder.append(" - Peso: " + af.getPeso());
                builder.append(" - Altura: " + af.getAltura());
                builder.append(" - IMC: " + (String.format("%.2f", af.getIMC())));
                builder.append(" - Nota: " + af.getResultRating());
                builder.append(" - Resultado: " + af.getImcResult());
                builder.append("\n");
                
                cont++;
            }
        }

        if(cont == 0)
        {
            builder.append("\nAvaliações Físicas não encontradas para esse usuário");
        }
        return builder;
    }

    public StringBuilder readAllAvaliacoesFisicas()
    {
        StringBuilder builder = new StringBuilder();
        int cont = 0;
        List<AvaliacaoFisica> avaliacoesFisicas = showAvaliacoesFisicas(null);

        for (AvaliacaoFisica af : avaliacoesFisicas)
        {
            if(af != null)
            {
                builder.append("\nID: " + af.getID());
                builder.append(" - Peso: " + af.getPeso());
                builder.append(" - Altura: " + af.getAltura());
                builder.append(" - IMC: " + (String.format("%.2f", af.getIMC())));
                builder.append(" - Nota: " + af.getResultRating());
                builder.append(" - Resultado: " + af.getImcResult());
                builder.append("\n");
                cont++;
            }
        }

        if(cont == 0)
        {
            builder.append("\nNenhuma avaliação encontrada");
        }
        return builder;
    }

    public AvaliacaoFisica createAvaliacaoFisica(Pessoa usuarioAF, float peso, float altura)
    {
        AvaliacaoFisica af = new AvaliacaoFisica();
        float imc = peso / (altura * altura);

        af.setDataID();
        af.setPessoa(usuarioAF);
        af.setPeso(peso);
        af.setAltura(altura);
        af.setIMC(imc);

        if(af.getIMC() < 16.9)
        {
            af.setImcResult("Muito abaixo do peso");
        }
        else if(af.getIMC() >= 17 && af.getIMC() < 18.5)
        {
            af.setImcResult("Abaixo do peso");
        }
        else if(af.getIMC() >= 18.5 && af.getIMC() < 25)
        {
            af.setImcResult("Peso normal");
        }
        else if(af.getIMC() >= 25 && af.getIMC() < 30)
        {
            af.setImcResult("Acima do peso");
        }
        else if(af.getIMC() >= 30 && af.getIMC() < 35)
        {
            af.setImcResult("Obesidade Grau I");
        }
        else if(af.getIMC() >= 35 && af.getIMC() <= 40)
        {
            af.setImcResult("Obesidade Grau II");
        }
        else if(af.getIMC() > 40)
        {
            af.setImcResult("Obesidade Grau III");
        }

        // int i = 0;
        // while(avalicao[i] != null && i < avalicao.length-1)
        // {
        //     i++;
        // }
        // if(i < avalicao.length)
        // {
        //     if(avalicao[i] == null)
        //     {
        //         avalicao[i] = af;
        //     }
        //     else
        //     {
        //         return null;
        //     }
        // }       

        return af;
    }

    public StringBuilder readLastAvaliacaoFisica(AvaliacaoFisica af, Pessoa usuario)
    {
        StringBuilder builder = new StringBuilder();

        builder.append("\nID: " + af.getID());
        builder.append(" - Nome: " + usuario.getNome());
        builder.append(" - Peso: " + af.getPeso());
        builder.append(" - Altura: " + af.getAltura());
        builder.append(" - IMC: " + (String.format("%.2f", af.getIMC())));
        builder.append(" - Nota: " + af.getResultRating());
        builder.append(" - Resultado: " + af.getImcResult());

        return builder;
    }

    public void notaAvaliacaoFisica(AvaliacaoFisica af, float nota)
    {
        af.setResultRating(nota);
        adicionaAvaliacaoFisicaBanco(af);
    }

}
