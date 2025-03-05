public class CompositeDemo {
    public static void main(String[] args) {
        // Cria alguns funcionários (folhas)
        Funcionario func1 = new Funcionario("Ana", "Analista de Sistemas",1500, "01/01/2010");
        Funcionario func2 = new Funcionario("Bruno", "Desenvolvedor", 3500, "01/01/2015");
        Funcionario func3 = new Funcionario("Carlos", "Designer", 2000, "01/01/2012");

        // Cria um departamento de TI e adiciona funcionários
        Departamento deptTI = new Departamento("Tecnologia Da Informação", "TI", "Departamento de TI");
        deptTI.adicionar(func1);
        deptTI.adicionar(func2);

        // Cria um departamento de Design e adiciona um funcionário
        Departamento deptDesign = new Departamento("Comunicação Visual", "CV", "Departamento de Design");
        deptDesign.adicionar(func3);

        // Cria um departamento principal que agrupa os subdepartamentos
        Departamento empresa = new Departamento("Empresa Falsa","EF", "Empresa não existente" );
        empresa.adicionar(deptTI);
        empresa.adicionar(deptDesign);

        // Exibe a estrutura completa da empresa
        empresa.exibirDetalhe();
    }
}
