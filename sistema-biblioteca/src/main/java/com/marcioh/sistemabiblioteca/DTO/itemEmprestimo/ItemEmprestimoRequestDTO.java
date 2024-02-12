package com.marcioh.sistemabiblioteca.DTO.itemEmprestimo;

import com.marcioh.sistemabiblioteca.model.Livro;

public record ItemEmprestimoRequestDTO(
        Livro livro
) {
}
