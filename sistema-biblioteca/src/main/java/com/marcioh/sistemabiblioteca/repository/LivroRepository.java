package com.marcioh.sistemabiblioteca.repository;

import com.marcioh.sistemabiblioteca.dao.LivroDAO;
import com.marcioh.sistemabiblioteca.model.Livro;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LivroRepository implements LivroDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Livro findById(String id) {
        return null;
    }

    @Override
    public List<Livro> findAll() {
        return entityManager.createQuery("SELECT a FROM livros a", Livro.class).getResultList();
    }

    @Transactional
    @Override
    public Livro save(Livro livro) {
        entityManager.persist(livro);
        return livro;
    }

    @Transactional
    @Override
    public Livro livroIndisponivel(Livro livro) {
        entityManager.merge(livro);
        return livro;
    }
}
