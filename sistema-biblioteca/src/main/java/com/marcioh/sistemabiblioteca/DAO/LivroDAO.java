package com.marcioh.sistemabiblioteca.DAO;

import com.marcioh.sistemabiblioteca.model.Livro;

import java.util.List;

public interface LivroDAO {
    Livro findById(String id);
    List<Livro> findAll();
    Livro save(Livro livro);
}
