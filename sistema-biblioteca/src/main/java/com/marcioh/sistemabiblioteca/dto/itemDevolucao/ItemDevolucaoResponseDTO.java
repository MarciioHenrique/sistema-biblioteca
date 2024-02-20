package com.marcioh.sistemabiblioteca.dto.itemDevolucao;

import com.marcioh.sistemabiblioteca.model.Livro;

import java.util.Date;

public record ItemDevolucaoResponseDTO(
        Date dataDevolucao,
        int diasAtraso,
        float valor,
        Livro  livro
) {
}
