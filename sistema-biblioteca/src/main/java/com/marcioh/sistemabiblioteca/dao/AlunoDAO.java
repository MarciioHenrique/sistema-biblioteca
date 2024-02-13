package com.marcioh.sistemabiblioteca.dao;

import com.marcioh.sistemabiblioteca.model.Aluno;

import java.util.List;

public interface AlunoDAO {

    Aluno findByMatricula(String matricula);
    List<Aluno> findAll();
    Aluno save(Aluno aluno);
}
