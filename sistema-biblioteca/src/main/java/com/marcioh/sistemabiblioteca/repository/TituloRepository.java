package com.marcioh.sistemabiblioteca.repository;

import com.marcioh.sistemabiblioteca.dao.TituloDAO;
import com.marcioh.sistemabiblioteca.model.Titulo;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TituloRepository implements TituloDAO {
    @Autowired
    private EntityManager entityManager;
    @Override
    public Titulo findById(Long id) {
        return null;
    }

    @Override
    public Titulo findByIsbn(String isbn) {
        return null;
    }

    @Override
    public List<Titulo> findAll() {
        return entityManager.createQuery("SELECT a FROM titulos a", Titulo.class).getResultList();
    }

    @Transactional
    @Override
    public Titulo save(Titulo titulo) {
        entityManager.persist(titulo);
        return titulo;
    }
}
