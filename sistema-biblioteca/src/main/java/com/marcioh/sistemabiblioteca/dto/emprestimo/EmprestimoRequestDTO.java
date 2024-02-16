package com.marcioh.sistemabiblioteca.dto.emprestimo;

import com.marcioh.sistemabiblioteca.dto.itemEmprestimo.ItemEmprestimoRequestDTO;
import com.marcioh.sistemabiblioteca.model.Aluno;

import java.util.Date;
import java.util.List;

public record EmprestimoRequestDTO(
        Date dataEmprestimo,
        List<ItemEmprestimoRequestDTO> itensEmprestimo,
        String alunoMatricula
) {
}
