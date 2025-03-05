
public class Funcionario implements Componente {
    String nome;
    String cargo;
    double salario;
    String dataAdmissao;

    public Funcionario(String nome, String cargo, double salario, String dataAdmissao) {
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
        this.dataAdmissao = dataAdmissao;
    }

    @Override
    public void exibirDetalhe() {
        System.out.printf("Nome: %s, Cargo: %s, Salário: %.2f, Data de Admissão: %s\n", 
                        nome, cargo, salario, dataAdmissao);
    }

    
}
