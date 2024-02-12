package com.marcioh.sistemabiblioteca.DAO;

import com.marcioh.sistemabiblioteca.model.Aluno;

import java.util.List;

public interface AlunoDAO {

    Aluno findByMatricula(String matricula);
    Aluno findByCpf(String cpf);
    Aluno findByNome(String nome);
    List<Aluno> findAll();
    Aluno save(Aluno aluno);
}
