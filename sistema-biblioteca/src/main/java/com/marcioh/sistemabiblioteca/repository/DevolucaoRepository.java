package com.marcioh.sistemabiblioteca.repository;

import com.marcioh.sistemabiblioteca.dao.DevolucaoDAO;
import com.marcioh.sistemabiblioteca.model.Devolucao;
import com.marcioh.sistemabiblioteca.model.Emprestimo;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DevolucaoRepository implements DevolucaoDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Devolucao> findAll() {
        return entityManager.createQuery("SELECT a FROM devolucoes a", Devolucao.class).getResultList();
    }

    @Transactional
    @Override
    public Devolucao save(Devolucao devolucao) {
        entityManager.persist(devolucao);
        return devolucao;
    }
}
