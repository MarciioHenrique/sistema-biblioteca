package com.marcioh.sistemabiblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "itens_devolucao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemDevolucao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dataDevolucao;
    private int diasAtraso;
    private float valor;
    private float multa;
    @ManyToOne
    @JoinColumn(name = "livro_id", referencedColumnName = "id")
    private Livro livro;
    @ManyToOne
    @JoinColumn(name = "devolucao_id", referencedColumnName = "id")
    private Devolucao devolucao;

}
