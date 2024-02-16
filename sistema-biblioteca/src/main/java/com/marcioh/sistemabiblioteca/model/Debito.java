package com.marcioh.sistemabiblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "debitos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Debito {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private float valor;
    private Date data;
    @ManyToOne
    @JoinColumn(name = "aluno_matricula", referencedColumnName = "matricula")
    private Aluno aluno;
}
