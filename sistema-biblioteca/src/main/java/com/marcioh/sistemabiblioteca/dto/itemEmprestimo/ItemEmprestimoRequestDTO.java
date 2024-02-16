package com.marcioh.sistemabiblioteca.dto.itemEmprestimo;

import com.marcioh.sistemabiblioteca.model.Livro;

public record ItemEmprestimoRequestDTO(
        Livro livro
) {
}
