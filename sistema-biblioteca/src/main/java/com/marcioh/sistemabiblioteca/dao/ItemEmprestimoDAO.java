package com.marcioh.sistemabiblioteca.dao;

import com.marcioh.sistemabiblioteca.model.ItemEmprestimo;

import java.util.List;

public interface ItemEmprestimoDAO {
    ItemEmprestimo findById(Long id);
    ItemEmprestimo findByEmprestimo(Long emprestimoId);
    List<ItemEmprestimo> findAll();
    ItemEmprestimo save(ItemEmprestimo itemEmprestimo);
}
