package com.marcioh.sistemabiblioteca.repository;

import com.marcioh.sistemabiblioteca.DAO.ItemEmprestimoDAO;
import com.marcioh.sistemabiblioteca.model.Emprestimo;
import com.marcioh.sistemabiblioteca.model.ItemEmprestimo;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemEmprestimoRepository implements ItemEmprestimoDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public ItemEmprestimo findById(Long id) {
        return null;
    }

    @Override
    public ItemEmprestimo findByEmprestimo(Long emprestimoId) {
        return null;
    }

    @Override
    public List<ItemEmprestimo> findAll() {
        return entityManager.createQuery("SELECT a FROM itens_emprestimo a", ItemEmprestimo.class).getResultList();
    }

    @Transactional
    @Override
    public ItemEmprestimo save(ItemEmprestimo itemEmprestimo) {
        entityManager.persist(itemEmprestimo);
        return itemEmprestimo;
    }
}
