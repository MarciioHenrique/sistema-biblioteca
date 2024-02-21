package com.marcioh.sistemabiblioteca.dto.emprestimo;

import com.marcioh.sistemabiblioteca.model.ItemEmprestimo;
import com.marcioh.sistemabiblioteca.model.Livro;

import java.util.List;

public record EmprestimoAlunoResponseDTO(
        List<Livro> livrosEmprestimo
) {
}
