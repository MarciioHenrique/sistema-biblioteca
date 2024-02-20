package com.marcioh.sistemabiblioteca.dto.devolucao;

import com.marcioh.sistemabiblioteca.model.Livro;

import java.util.Date;
import java.util.List;

public record DevolucaoRequestDTO(
        Date dataDevolucao,
        String alunoMatricula,
        List<Livro> itensDevolucao
) {
}
