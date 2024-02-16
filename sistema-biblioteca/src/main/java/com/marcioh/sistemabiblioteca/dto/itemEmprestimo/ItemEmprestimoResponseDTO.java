package com.marcioh.sistemabiblioteca.dto.itemEmprestimo;

import com.marcioh.sistemabiblioteca.model.Livro;

import java.util.Date;

public record ItemEmprestimoResponseDTO(
        Long id,
        Date dataPrevista,
        Livro livro
) {
}
