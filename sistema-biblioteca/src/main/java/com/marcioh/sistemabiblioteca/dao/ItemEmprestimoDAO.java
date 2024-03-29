package com.marcioh.sistemabiblioteca.dao;

import com.marcioh.sistemabiblioteca.model.ItemEmprestimo;

import java.util.List;

public interface ItemEmprestimoDAO {
    ItemEmprestimo findById(Long id);
    List<ItemEmprestimo> findByEmprestimo(Long emprestimoId);
    boolean findByLivro(Long livroId);
    List<ItemEmprestimo> findAll();
    ItemEmprestimo save(ItemEmprestimo itemEmprestimo);
}
