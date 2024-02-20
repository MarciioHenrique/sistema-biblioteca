package com.marcioh.sistemabiblioteca.dao;

import com.marcioh.sistemabiblioteca.model.Devolucao;

import java.util.List;

public interface DevolucaoDAO {
    List<Devolucao> findAll();
    Devolucao save(Devolucao devolucao);
}
