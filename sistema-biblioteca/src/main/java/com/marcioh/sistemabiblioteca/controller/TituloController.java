package com.marcioh.sistemabiblioteca.controller;

import com.marcioh.sistemabiblioteca.model.Titulo;
import com.marcioh.sistemabiblioteca.service.TituloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/titulo")
public class TituloController {
    @Autowired
    private TituloService service;

    @GetMapping
    public ResponseEntity<List<Titulo>> listarTitulos() {
        return ResponseEntity.ok(service.listarTitulos());
    }

    @PostMapping
    public ResponseEntity<Titulo> cadastrarTitulo(@RequestBody Titulo titulo) {
        return ResponseEntity.ok(service.cadastrarTitulo(titulo));
    }
}
