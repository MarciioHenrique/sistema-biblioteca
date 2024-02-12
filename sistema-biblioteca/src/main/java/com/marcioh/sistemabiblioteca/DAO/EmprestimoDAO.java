package com.marcioh.sistemabiblioteca.DAO;

import com.marcioh.sistemabiblioteca.model.Emprestimo;

import java.util.List;

public interface EmprestimoDAO {
    Emprestimo findById(Long id);
    Emprestimo findByAlunoMatricula(String matricula);
    List<Emprestimo> findAll();
    Emprestimo save(Emprestimo emprestimo);

}
