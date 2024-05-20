package model;

public class PagamentoRecorrenteDAO {

    PagamentoRecorrente[] pagamentoRecorrente = new PagamentoRecorrente[100];

    public PagamentoRecorrenteDAO()
    {

    }

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

        int i = 0;
        while(pagamentoRecorrente[i] != null && i < pagamentoRecorrente.length-1)
        {
            i++;
        }

        if(i < pagamentoRecorrente.length)
        {
            if(pagamentoRecorrente[i] == null)
            {
                pagamentoRecorrente[i] = pr;
                StringBuilder builder = new StringBuilder("\nPagamento Recorrente adicionado com sucesso!");
                return builder;
            }
            else
            {
                StringBuilder builder = new StringBuilder("\nNão foi possivel adicionar o novo Pagamento Recorrente!");
                return builder;
            }
        }
        StringBuilder builder = new StringBuilder("\nDeu ruim");
        return builder;
    }

    public StringBuilder readPagamentoRecorrente()
    {
        StringBuilder builder = new StringBuilder();

        for (PagamentoRecorrente pr : pagamentoRecorrente)
        {
            if(pr != null)
            {
                builder.append("\nID: " + pr.getIDPagamentoRecorrente());
                builder.append("\nNome: " + pr.getPessoaFromPagamentoRecorrente().getNome());
                builder.append("\nValor: " + pr.getValorPagamentoRecorrente());
                builder.append("\nData de Inicio: " + pr.getDataInicioPagamentoRecorrente());
                builder.append("\nMeses Autorizados: " + pr.getMesesAutPagamentoRecorrente());
                builder.append("\n");
            }    
        }

        return builder;
    }

    public StringBuilder updatePagamentoRecorrente(Long id, String valor, String data_inicio, int meses_aut)
    {
        StringBuilder builder = new StringBuilder("");
        int i = 0;
        while(pagamentoRecorrente[i] != null && pagamentoRecorrente[i].getIDPagamentoRecorrente() != id)
        {
            i++;
        }

        if(pagamentoRecorrente[i] != null && pagamentoRecorrente[i].getIDPagamentoRecorrente() == id)
        {
            if(!valor.equals(""))
            {
                pagamentoRecorrente[i].setValorPagamentoRecorrente(null);
            }

            if(!data_inicio.equals(""))
            {
                pagamentoRecorrente[i].setDataInicioPagamentoRecorrente(data_inicio);
            }

            if(meses_aut != pagamentoRecorrente[i].getMesesAutPagamentoRecorrente())
            {
                pagamentoRecorrente[i].setMesesAutorizadoPagamentoRecorrente(meses_aut);
            }

            pagamentoRecorrente[i].setModData();

            builder.append("\nPagamento Recorrente atualizado com sucesso!");
            return builder;
        }

        builder.append("\nPagamento Recorrente não foi atualizada!");
        return builder;
    }

    public StringBuilder deletePagamentoRecorrente(Long id)
    {
        StringBuilder builder = new StringBuilder("");
        int i = 0;
        while(pagamentoRecorrente[i] != null && pagamentoRecorrente[i].getIDPagamentoRecorrente() != id)
        {
            i++;
        }

        if(i < pagamentoRecorrente.length)
        {
            if(pagamentoRecorrente[i].getIDPagamentoRecorrente() == id)
            {
                pagamentoRecorrente[i] = null;
                builder.append("\nPagamento Recorrente deletada com sucesso!");
                return builder;
            }
        }

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
