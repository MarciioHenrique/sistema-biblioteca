package com.marcioh.sistemabiblioteca.service;

import com.marcioh.sistemabiblioteca.repository.AlunoRepository;
import com.marcioh.sistemabiblioteca.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoDAO;

    public List<Aluno> listarAlunos() {
        return alunoDAO.findAll();
    }

    public Aluno cadastrarAluno(Aluno aluno) {
        return alunoDAO.save(aluno);
    }
}
