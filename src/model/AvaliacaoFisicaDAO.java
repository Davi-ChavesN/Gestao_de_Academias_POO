package model;


public class AvaliacaoFisicaDAO {

    AvaliacaoFisica[] avalicao = new AvaliacaoFisica[100];


    public StringBuilder mostrarAvaliacaoFisica(Pessoa usuario)
    {
        int cont = 0;
        StringBuilder builder = new StringBuilder();

        for (AvaliacaoFisica af : avalicao)
        {
            if(af != null && af.getPessoa() == usuario)
            {
                builder.append("\nID: " + af.getID());
                builder.append(" - Nome: " + usuario.getNome());
                builder.append(" - Peso: " + af.getPeso());
                builder.append(" - Altura: " + af.getAltura());
                builder.append(" - IMC: " + (String.format("%.2f", af.getIMC())));
                builder.append(" - Nota: " + af.getResultRating());

                if(af.getIMC() < 16.9)
                {
                    builder.append("\nMuito abaixo do peso");
                }
                else if(af.getIMC() >= 17 && af.getIMC() < 18.5)
                {
                    builder.append("\nAbaixo do peso");
                }
                else if(af.getIMC() >= 18.5 && af.getIMC() < 25)
                {
                    builder.append("\nPeso normal");
                }
                else if(af.getIMC() >= 25 && af.getIMC() < 30)
                {
                    builder.append("\nAcima do peso");
                }
                else if(af.getIMC() >= 30 && af.getIMC() < 35)
                {
                    builder.append("\nObesidade Grau I");
                }
                else if(af.getIMC() >= 35 && af.getIMC() <= 40)
                {
                    builder.append("\nObesidade Grau II");
                }
                else if(af.getIMC() > 40)
                {
                    builder.append("\nObesidade Grau III");
                }

                cont++;
            }
            builder.append("\n");
        }

        if(cont == 0)
        {
            builder.append("\nAvaliações Físicas não encontradas para esse usuário");
        }
        return builder;
    }

    public StringBuilder mostrarTodasAvaliacoesFisicas()
    {
        StringBuilder builder = new StringBuilder();
        int cont = 0;

        for (AvaliacaoFisica af : avalicao)
        {
            if(af != null)
            {
                builder.append("\nID: " + af.getID());
                builder.append(" - Peso: " + af.getPeso());
                builder.append(" - Altura: " + af.getAltura());
                builder.append(" - IMC: " + (String.format("%.2f", af.getIMC())));
                builder.append(" - Nota: " + af.getResultRating());
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

    public AvaliacaoFisica novaAvaliacaoFisica(Pessoa usuarioAF, float peso, float altura)
    {
        AvaliacaoFisica af = new AvaliacaoFisica();
        float imc = peso / (altura * altura);

        af.setDataID();
        af.setPessoa(usuarioAF);
        af.setPeso(peso);
        af.setAltura(altura);
        af.setIMC(imc);

        int i = 0;
        while(avalicao[i] != null && i < avalicao.length-1)
        {
            i++;
        }
        if(i < avalicao.length)
        {
            if(avalicao[i] == null)
            {
                avalicao[i] = af;
            }
            else
            {
                return null;
            }
        }       
        
        return af;
    }

    public void notaAvaliacaoFisica(AvaliacaoFisica af, float nota)
    {
        af.setResultRating(nota);
    }

}
