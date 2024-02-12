package com.marcioh.sistemabiblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "titulos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Titulo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int prazo;
    private String isbn;
    private String editora;
}
