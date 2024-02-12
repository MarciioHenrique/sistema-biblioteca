package com.marcioh.sistemabiblioteca.controller;

import com.marcioh.sistemabiblioteca.model.Livro;
import com.marcioh.sistemabiblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/livro")
public class LivroController {
    @Autowired
    private LivroService service;

    @GetMapping
    public ResponseEntity<List<Livro>> listarLivros() {
        return ResponseEntity.ok(service.listarLivros());
    }

    @PostMapping
    public ResponseEntity<Livro> cadastrarLivro(@RequestBody Livro livro) {
        return ResponseEntity.ok(service.cadastrarLivro(livro));
    }
}
