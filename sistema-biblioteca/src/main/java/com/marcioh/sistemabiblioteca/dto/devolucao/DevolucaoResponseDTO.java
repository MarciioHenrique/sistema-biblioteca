package com.marcioh.sistemabiblioteca.dto.devolucao;

import com.marcioh.sistemabiblioteca.dto.itemDevolucao.ItemDevolucaoResponseDTO;

import java.util.List;

public record DevolucaoResponseDTO(
        boolean atraso,
        float valorTotal,
        List<ItemDevolucaoResponseDTO> itensDevolucao
) {
}
