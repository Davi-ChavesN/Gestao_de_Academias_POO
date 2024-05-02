package model;


/**
 *
 * @author Davi Chaves
 *         Luan Sousa
 */
public class ExercicioDAO {

    Exercicio[] exercicio = new Exercicio[100];

    public ExercicioDAO()
    {
        /*
            Grupos musculares:
            Peito
            Costas
            Biceps
            Triceps
            Ombro
            Perna - Quadriceps
            Perna - Posterior
            Glúteo
            Panturrilha
            Interior da Coxa
        */
        Exercicio e = new Exercicio();
        e.setNomeExercicio("Supino Reto");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Peito");
        e.setDataID();
        exercicio[0] = e;

        e = new Exercicio();
        e.setNomeExercicio("Supino Inclinado");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Peito");
        e.setDataID();
        exercicio[1] = e;

        e = new Exercicio();
        e.setNomeExercicio("Fly Frontal");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Peito");
        e.setDataID();
        exercicio[2] = e;

        e = new Exercicio();
        e.setNomeExercicio("Crucifixo Reto");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Peito");
        e.setDataID();
        exercicio[3] = e;

        e = new Exercicio();
        e.setNomeExercicio("Crucifixo Inclinado");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Peito");
        e.setDataID();
        exercicio[4] = e;

        e = new Exercicio();
        e.setNomeExercicio("Puxada Frontal");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Costas");
        e.setDataID();
        exercicio[5] = e;
        
        e = new Exercicio();
        e.setNomeExercicio("Pulley Aticulado");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Costas");
        e.setDataID();
        exercicio[6] = e;

        e = new Exercicio();
        e.setNomeExercicio("Remada Alta");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Costas");
        e.setDataID();
        exercicio[7] = e;

        e = new Exercicio();
        e.setNomeExercicio("Fly Inverso");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Costas");
        e.setDataID();
        exercicio[8] = e;

        e = new Exercicio();
        e.setNomeExercicio("Remada Baixa");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Costas");
        e.setDataID();
        exercicio[9] = e;

        e = new Exercicio();
        e.setNomeExercicio("Biceps Rosca Direta");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Biceps");
        e.setDataID();
        exercicio[10] = e;

        e = new Exercicio();
        e.setNomeExercicio("Biceps Rosca Alternada");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Biceps");
        e.setDataID();
        exercicio[11] = e;

        e = new Exercicio();
        e.setNomeExercicio("Rosca Scott");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Biceps");
        e.setDataID();
        exercicio[12] = e;

        e = new Exercicio();
        e.setNomeExercicio("Rosca Concentrada");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Biceps");
        e.setDataID();
        exercicio[13] = e;

        e = new Exercicio();
        e.setNomeExercicio("Triceps Corda");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Triceps");
        e.setDataID();
        exercicio[14] = e;

        e = new Exercicio();
        e.setNomeExercicio("Triceps Barrinha");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Triceps");
        e.setDataID();
        exercicio[15] = e;

        e = new Exercicio();
        e.setNomeExercicio("Triceps Banco");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Triceps");
        e.setDataID();
        exercicio[16] = e;

        e = new Exercicio();
        e.setNomeExercicio("Elevação Lateral");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Ombro");
        e.setDataID();
        exercicio[17] = e;

        e = new Exercicio();
        e.setNomeExercicio("Elevação Frontal");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Ombro");
        e.setDataID();
        exercicio[18] = e;

        e = new Exercicio();
        e.setNomeExercicio("Crucifixo Inverso");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Ombro");
        e.setDataID();
        exercicio[19] = e;

        e = new Exercicio();
        e.setNomeExercicio("Arnold Press");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Ombro");
        e.setDataID();
        exercicio[20] = e;

        e = new Exercicio();
        e.setNomeExercicio("Agachamento");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Perna - Quadriceps");
        e.setDataID();
        exercicio[21] = e;

        e = new Exercicio();
        e.setNomeExercicio("Afundo");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Perna - Quadriceps");
        e.setDataID();
        exercicio[22] = e;

        e = new Exercicio();
        e.setNomeExercicio("Agachamento Búlgaro");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Perna - Quadriceps");
        e.setDataID();
        exercicio[23] = e;

        e = new Exercicio();
        e.setNomeExercicio("Cadeira Extensora");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Perna - Quadriceps");
        e.setDataID();
        exercicio[24] = e;

        e = new Exercicio();
        e.setNomeExercicio("Leg Press");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Perna - Quadriceps");
        e.setDataID();
        exercicio[25] = e;

        e = new Exercicio();
        e.setNomeExercicio("Stiff");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Perna - Posterior");
        e.setDataID();
        exercicio[26] = e;

        e = new Exercicio();
        e.setNomeExercicio("Levantamento Terra");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Perna - Posterior");
        e.setDataID();
        exercicio[27] = e;

        e = new Exercicio();
        e.setNomeExercicio("Agachamento Sumo");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Perna - Posterior");
        e.setDataID();
        exercicio[28] = e;

        e = new Exercicio();
        e.setNomeExercicio("Cadeira Flexora");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Perna - Posterior");
        e.setDataID();
        exercicio[29] = e;

        e = new Exercicio();
        e.setNomeExercicio("Elevação Pélvica");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Glúteo");
        e.setDataID();
        exercicio[30] = e;

        e = new Exercicio();
        e.setNomeExercicio("Cadeira Abdutora");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Glúteo");
        e.setDataID();
        exercicio[31] = e;

        e = new Exercicio();
        e.setNomeExercicio("Panturrilha Banco");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Panturrilha");
        e.setDataID();
        exercicio[32] = e;

        e = new Exercicio();
        e.setNomeExercicio("Cadeira Adutora");
        e.setDescricaoExercicio(null);
        e.setGrupoMuscular("Interior da Coxa");
        e.setDataID();
        exercicio[33] = e;
    }

    public StringBuilder adicionarExercicio(Exercicio e)
    {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while(exercicio[i] != null && i < exercicio.length-1)
        {
            i++;
        }

        if(i < exercicio.length)
        {
            if(exercicio[i] == null)
            {
                exercicio[i] = e;
                builder.append("Exercício adicionado com sucesso!");
            }
            else
            {
                builder.append("Não é possível adicionar mais exercícios!");
            }
        }
        else
        {
            builder.append("Deu ruim");
        }

        return builder;
    }
    
    public StringBuilder mostrarExercicios(Pessoa usuario)
    {
        StringBuilder builder = new StringBuilder("\n");
        
        for(Exercicio e : exercicio)
        {
            if(e != null)
            {
                builder.append("\nID: " + e.getIDExercicio());
                builder.append(" - Nome: " + e.getNomeExercicio());
                builder.append(" - Grupo Muscular: " + e.getGrupoMuscular());
            }
        }

        return builder;
    }

    public StringBuilder editExercicio(int id_edit, long ID, String att)
    {
        StringBuilder builder = new StringBuilder();
        boolean atualizado = false;
        for (Exercicio e : exercicio)
        {
            if(e != null && e.getIDExercicio() == ID)
            {
                if(id_edit == 1)
                {
                    e.setNomeExercicio(att);
                }
                else if(id_edit == 2)
                {
                    e.setDescricaoExercicio(att);
                }
                else if(id_edit == 3)
                {
                    e.setGrupoMuscular(att);
                }
                e.setModData();
                atualizado = true;
            }
        }

        if(atualizado == true)
        {
            builder.append("\nExercício atualizado com sucesso!");
        }
        else
        {
            builder.append("\nExercício não encontrado!");
        }

        return builder;
    }

    public StringBuilder delExercicio(long ID)
    {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        boolean deleted = false;
        for (Exercicio e : exercicio)
        {
            if(e != null && e.getIDExercicio() == ID)
            {
                exercicio[i] = null;
                deleted = true;
            }
            i++;
        }

        if(deleted == true)
        {
            builder.append("\nExercício deletado com sucesso!");
        }
        else
        {
            builder.append("\nExercício não encontrado!");
        }        

        return builder;
    }

}
