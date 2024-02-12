package com.marcioh.sistemabiblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "alunos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Aluno {
    @Id
    @Column(unique = true)
    private String matricula;

    private String nome;

    @Column(unique = true)
    private String cpf;

    private String endereco;

    //funções que vão ser implementadas - verificarDebito, realizarEmprestimo
}
