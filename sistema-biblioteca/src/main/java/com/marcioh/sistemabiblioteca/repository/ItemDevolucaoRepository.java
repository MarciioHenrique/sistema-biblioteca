package com.marcioh.sistemabiblioteca.repository;

import com.marcioh.sistemabiblioteca.dao.ItemDevolucaoDAO;
import com.marcioh.sistemabiblioteca.model.ItemDevolucao;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDevolucaoRepository implements ItemDevolucaoDAO {
    @Autowired
    private EntityManager entityManager;
    @Override
    public List<ItemDevolucao> findAll() {
        return null;
    }

    @Transactional
    @Override
    public ItemDevolucao save(ItemDevolucao itemDevolucao) {
        entityManager.persist(itemDevolucao);
        return itemDevolucao;
    }
}
