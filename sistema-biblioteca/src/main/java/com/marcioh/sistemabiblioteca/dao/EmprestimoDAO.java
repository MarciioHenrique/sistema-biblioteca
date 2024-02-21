package com.marcioh.sistemabiblioteca.dao;

import com.marcioh.sistemabiblioteca.model.Devolucao;
import com.marcioh.sistemabiblioteca.model.Emprestimo;

import java.util.List;

public interface EmprestimoDAO {
    Emprestimo findByAlunoMatricula(String matricula);
    List<Emprestimo> findAll();
    Emprestimo save(Emprestimo emprestimo);
    void atualizarEmprestimoDevolucao(Emprestimo emprestimo);

}
