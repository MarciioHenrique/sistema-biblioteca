package com.marcioh.sistemabiblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "itens_emprestimo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemEmprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dataPrevista;
    private Date dataDevolucao;

    @ManyToOne
    @JoinColumn(name = "livro_id", referencedColumnName = "id")
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "emprestimo_id", referencedColumnName = "id")
    private Emprestimo emprestimo;
}
