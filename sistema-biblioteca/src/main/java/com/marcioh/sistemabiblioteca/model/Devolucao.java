package com.marcioh.sistemabiblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity(name = "devolucoes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Devolucao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dataDevoculao;
    private boolean atraso;
    private float valorTotal;
    @OneToOne
    private Emprestimo emprestimo;

}
