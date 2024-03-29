package com.marcioh.sistemabiblioteca.service;

import com.marcioh.sistemabiblioteca.exception.BadRequestException;
import com.marcioh.sistemabiblioteca.repository.AlunoRepository;
import com.marcioh.sistemabiblioteca.model.Aluno;
import jakarta.persistence.NoResultException;
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

    public Aluno listarPorMatricula(String matricula) {
        return alunoDAO.findByMatricula(matricula);
    }

    public Aluno cadastrarAluno(Aluno aluno) {
        if (aluno.getMatricula().isEmpty() || aluno.getNome().isEmpty() || aluno.getCpf().isEmpty() || aluno.getEndereco().isEmpty()) {
            throw new BadRequestException("Dados inválidos");
        }
        return alunoDAO.save(aluno);
    }
}
