package trabalho.poo.gestao.academia;

import java.time.LocalDate;

/**
 *
 * @author Davi Chaves
 *         Luan Sousa
 */
public class Exercicio {
    private long id;
    private String nome;
    private String descricao;
    private String grupo_muscular;
    private LocalDate data_criacao;
    private LocalDate data_modificacao;
    private static long serial = 0;


    void setNomeExercicio(String nome)
    {
        this.nome = nome;
    }

    void setDescricaoExercicio(String descricao)
    {
        this.descricao = descricao;
    }

    void setGrupoMuscular(String grupo_muscular)
    {
        this.grupo_muscular = grupo_muscular;
    }

    void setDataID()
    {
        this.data_criacao = LocalDate.now();
        this.data_modificacao = LocalDate.now();
        this.id = ++Exercicio.serial;
    }

    void setModData()
    {
        this.data_modificacao = LocalDate.now();
    }


    public long getIDExercicio()
    {
        return this.id;
    }

    public String getNomeExercicio()
    {
        return this.nome;
    }

    public String getDescricao()
    {
        return this.descricao;
    }

    public String getGrupoMuscular()
    {
        return this.grupo_muscular;
    }
}
