package com.marcioh.sistemabiblioteca.DAO;

import com.marcioh.sistemabiblioteca.model.Titulo;

import java.util.List;

public interface TituloDAO {
    Titulo findById(Long id);
    Titulo findByIsbn(String isbn);
    List<Titulo> findAll();
    Titulo save(Titulo titulo);

}
