import java.util.ArrayList;
import java.util.List;

public class Departamento implements Componente {
    String nome;
    String sigla;
    String descricao;
    private List<Componente> componentes = new ArrayList<>();

    public Departamento(String nome, String sigla, String descricao) {
        this.nome = nome;
        this.sigla = sigla;
        this.descricao = descricao;
    }

    public void adicionar(Componente componente) {
        componentes.add(componente);
    }
    

    @Override
    public void exibirDetalhe() {

        System.out.println("Departamento: " + nome);
        System.out.println("Sigla: " + sigla);
        System.out.println("Descrição: " + descricao);
    
        for (Componente componente : componentes) {
            componente.exibirDetalhe();
        }
    }
    
}
