package com.marcioh.sistemabiblioteca.controller;

import com.marcioh.sistemabiblioteca.dto.devolucao.DevolucaoRequestDTO;
import com.marcioh.sistemabiblioteca.dto.devolucao.DevolucaoResponseDTO;
import com.marcioh.sistemabiblioteca.model.Devolucao;
import com.marcioh.sistemabiblioteca.service.DevolucaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/devolucao")
public class DevolucaoController {
    @Autowired
    private DevolucaoService service;

    @GetMapping
    public ResponseEntity<List<Devolucao>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping ResponseEntity<DevolucaoResponseDTO> save(@RequestBody DevolucaoRequestDTO devolucao) {
        return ResponseEntity.ok(service.save(devolucao));
    }
}
