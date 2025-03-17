# Composite

## Motivação
Imagine um sistema de gestão de tarefas/projetos que precisa lidar com diferentes tipos de tarefas: algumas são simples, outras têm prazos específicos, e outras são consideradas prioritárias. Todas devem ser tratadas de forma uniforme (por exemplo, exibidas e organizadas na mesma árvore de projeto), sem que o código cliente precise distinguir manualmente cada tipo de tarefa.

O padrão Composite permite agrupar tanto tarefas simples (folhas) quanto subprojetos (composite) em uma mesma hierarquia. Além disso, ao introduzir várias classes de folhas (cada uma com comportamentos/atributos distintos), ainda assim conseguimos manter o mesmo método de exibição (exibirTarefa) para todo mundo, graças à interface comum.

```java
@startuml
title Estrutura Expandida com Várias Folhas

interface Tarefa {
  + exibirTarefa(nivel: int)
}

class TarefaSimples {
  - nome: String
  --
  + exibirTarefa(nivel: int)
}

class TarefaComPrazo {
  - nome: String
  - prazo: LocalDate
  --
  + exibirTarefa(nivel: int)
}

class TarefaPrioritaria {
  - nome: String
  - prioridade: int
  --
  + exibirTarefa(nivel: int)
}

class Projeto {
  - nome: String
  - tarefas: List<Tarefa>
  --
  + adicionarTarefa(Tarefa): void
  + exibirTarefa(nivel: int): void
}

Tarefa <|.. TarefaSimples
Tarefa <|.. TarefaComPrazo
Tarefa <|.. TarefaPrioritaria
Tarefa <|.. Projeto
@enduml
```

### Tarefa (Component)
- Declara a operação comum exibirTarefa(nivel: int) que todos os tipos de tarefa (folhas e composite) devem implementar.

### TarefaSimples, TarefaComPrazo, TarefaPrioritaria (Leaf)
- Diferentes tipos de tarefas sem filhos, cada qual com atributos e comportamentos específicos.

### TarefaSimples: apenas um nome.
### TarefaComPrazo: nome + data limite (LocalDate).
### TarefaPrioritaria: nome + nível de prioridade (inteiro).

### Projeto (Composite)
- Representa um agrupamento de tarefas (podendo ser simples ou outros projetos).

Mantém uma lista de Tarefa.
Implementa exibirTarefa(nivel: int) de forma a chamar o método de cada filho recursivamente.


## Implementação em Java
```java
Interface Tarefa (Component)

public interface Tarefa {
    // Exibe as informações da tarefa, usando "nivel" para indentação
    void exibirTarefa(int nivel);
}
```


```java
Classes de Folha
1) TarefaSimples

public class TarefaSimples implements Tarefa {
    private String nome;

    public TarefaSimples(String nome) {
        this.nome = nome;
    }

    @Override
    public void exibirTarefa(int nivel) {
        String indent = " ".repeat(nivel * 2);
        System.out.println(indent + "- " + nome + " (Tarefa Simples)");
    }
}
```

```java
2) TarefaComPrazo

import java.time.LocalDate;

public class TarefaComPrazo implements Tarefa {
    private String nome;
    private LocalDate prazo;

    public TarefaComPrazo(String nome, LocalDate prazo) {
        this.nome = nome;
        this.prazo = prazo;
    }

    @Override
    public void exibirTarefa(int nivel) {
        String indent = " ".repeat(nivel * 2);
        System.out.println(indent + "- " + nome 
                           + " (Tarefa com Prazo: " + prazo + ")");
    }
}
```

```java
3) TarefaPrioritaria

public class TarefaPrioritaria implements Tarefa {
    private String nome;
    private int prioridade; // 1 = mais alta, 2 = média, etc.

    public TarefaPrioritaria(String nome, int prioridade) {
        this.nome = nome;
        this.prioridade = prioridade;
    }

    @Override
    public void exibirTarefa(int nivel) {
        String indent = " ".repeat(nivel * 2);
        System.out.println(indent + "- " + nome 
                           + " (Tarefa Prioritária: " + prioridade + ")");
    }
}
```

```java
Classe Projeto (Composite)

import java.util.ArrayList;
import java.util.List;

public class Projeto implements Tarefa {
    private String nome;
    private List<Tarefa> tarefas;

    public Projeto(String nome) {
        this.nome = nome;
        this.tarefas = new ArrayList<>();
    }

    public void adicionarTarefa(Tarefa t) {
        tarefas.add(t);
    }

    @Override
    public void exibirTarefa(int nivel) {
        String indent = " ".repeat(nivel * 2);
        System.out.println(indent + "* " + nome + " (Projeto)");

        for (Tarefa t : tarefas) {
            t.exibirTarefa(nivel + 1);
        }
    }
}
```

```java
Classe de Demonstração (Client)
import java.time.LocalDate;

public class TarefaCompositeDemo {
    public static void main(String[] args) {
        // Cria vários tipos de tarefas (folhas)
        TarefaSimples ts1 = new TarefaSimples("Estudar Padrão Composite");
        TarefaComPrazo tc1 = new TarefaComPrazo("Entregar relatório", LocalDate.of(2025, 4, 10));
        TarefaPrioritaria tp1 = new TarefaPrioritaria("Corrigir bugs críticos", 1);

        TarefaSimples ts2 = new TarefaSimples("Ler documentação");
        TarefaComPrazo tc2 = new TarefaComPrazo("Enviar e-mail para equipe", LocalDate.of(2025, 4, 15));
        TarefaPrioritaria tp2 = new TarefaPrioritaria("Atualizar servidor de produção", 2);

        // Cria um subprojeto que agrupa algumas tarefas
        Projeto subProjeto = new Projeto("Subprojeto de Infraestrutura");
        subProjeto.adicionarTarefa(tp1);
        subProjeto.adicionarTarefa(ts2);

        // Cria o projeto principal que agrupa tudo
        Projeto projetoPrincipal = new Projeto("Projeto Principal");
        projetoPrincipal.adicionarTarefa(ts1);
        projetoPrincipal.adicionarTarefa(tc1);
        projetoPrincipal.adicionarTarefa(subProjeto);
        projetoPrincipal.adicionarTarefa(tc2);
        projetoPrincipal.adicionarTarefa(tp2);

        // Exibe a estrutura completa
        projetoPrincipal.exibirTarefa(0);
    }
}
Estrutura Hierárquica

```
