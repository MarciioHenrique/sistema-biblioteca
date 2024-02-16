package com.marcioh.sistemabiblioteca.dto.emprestimo;

import com.marcioh.sistemabiblioteca.dto.itemEmprestimo.ItemEmprestimoResponseDTO;
import com.marcioh.sistemabiblioteca.model.Aluno;
import com.marcioh.sistemabiblioteca.model.Devolucao;

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
