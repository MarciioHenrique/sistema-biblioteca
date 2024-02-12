package com.marcioh.sistemabiblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "livros")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean disponivel;
    private boolean exemplarBiblioteca;
    @ManyToOne
    @JoinColumn(name = "titulo_id", referencedColumnName = "id")
    private Titulo titulo;

}
