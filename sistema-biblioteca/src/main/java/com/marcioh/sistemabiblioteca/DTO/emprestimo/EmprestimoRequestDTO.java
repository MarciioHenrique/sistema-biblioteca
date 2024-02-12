package com.marcioh.sistemabiblioteca.DTO.emprestimo;

import com.marcioh.sistemabiblioteca.DTO.itemEmprestimo.ItemEmprestimoRequestDTO;
import com.marcioh.sistemabiblioteca.model.Aluno;

import java.util.Date;
import java.util.List;

public record EmprestimoRequestDTO(
        Date dataEmprestimo,
        List<ItemEmprestimoRequestDTO> itensEmprestimo,
        Aluno aluno
) {
}
