package com.marcioh.sistemabiblioteca.controller;

import com.marcioh.sistemabiblioteca.dto.emprestimo.EmprestimoAlunoResponseDTO;
import com.marcioh.sistemabiblioteca.dto.emprestimo.EmprestimoRequestDTO;
import com.marcioh.sistemabiblioteca.dto.emprestimo.EmprestimoResponseDTO;
import com.marcioh.sistemabiblioteca.model.Aluno;
import com.marcioh.sistemabiblioteca.model.Emprestimo;
import com.marcioh.sistemabiblioteca.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/emprestimo")
public class EmprestimoController {
    @Autowired
    private EmprestimoService service;

    @GetMapping
    public ResponseEntity<List<Emprestimo>> listarEmprestimos() {
        return ResponseEntity.ok(service.listarEmprestimos());
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<EmprestimoAlunoResponseDTO> listarPorMatricula(@PathVariable(value = "matricula") String matricula) {
        return ResponseEntity.ok(service.listarLivrosEmprestimoAluno(matricula));
    }

    @PostMapping
    public ResponseEntity<EmprestimoResponseDTO> cadastrarEmprestimo(@RequestBody EmprestimoRequestDTO emprestimoRequest) {
        return ResponseEntity.ok(service.cadastrarEmprestimo(emprestimoRequest));
    }
}
