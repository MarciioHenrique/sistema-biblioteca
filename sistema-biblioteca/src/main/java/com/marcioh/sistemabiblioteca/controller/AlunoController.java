package com.marcioh.sistemabiblioteca.controller;

import com.marcioh.sistemabiblioteca.model.Aluno;
import com.marcioh.sistemabiblioteca.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/aluno")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunos() {
        return ResponseEntity.ok(service.listarAlunos());
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Aluno> listarPorMatricula(@PathVariable(value = "matricula") String matricula) {
        return ResponseEntity.ok(service.listarPorMatricula(matricula));
    }

    @PostMapping
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno) {
        return ResponseEntity.ok(service.cadastrarAluno(aluno));
    }
}
