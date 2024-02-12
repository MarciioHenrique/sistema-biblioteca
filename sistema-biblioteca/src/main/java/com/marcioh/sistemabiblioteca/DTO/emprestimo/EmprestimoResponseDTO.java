package com.marcioh.sistemabiblioteca.DTO.emprestimo;

import com.marcioh.sistemabiblioteca.DTO.itemEmprestimo.ItemEmprestimoResponseDTO;
import com.marcioh.sistemabiblioteca.model.Aluno;
import com.marcioh.sistemabiblioteca.model.Devolucao;
import com.marcioh.sistemabiblioteca.model.ItemEmprestimo;

import java.util.Date;
import java.util.List;

public record EmprestimoResponseDTO(
        Long id,
        Date dataEmprestimo,
        Date dataPrevista,
        Aluno aluno,
        Devolucao devolucao,
        List<ItemEmprestimoResponseDTO> itensEmprestimo

) {
}
