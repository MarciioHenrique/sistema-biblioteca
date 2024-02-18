package com.marcioh.sistemabiblioteca.repository;

import com.marcioh.sistemabiblioteca.dao.AlunoDAO;
import com.marcioh.sistemabiblioteca.exception.ResourceNotFoundException;
import com.marcioh.sistemabiblioteca.model.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlunoRepository implements AlunoDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Aluno findByMatricula(String matricula) {
        try {
            return entityManager.createQuery("SELECT a FROM alunos a WHERE a.matricula = :matricula", Aluno.class)
                    .setParameter("matricula", matricula)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new ResourceNotFoundException("Aluno não está cadastrado no sistema");
        }

    }

    @Override
    public List<Aluno> findAll() {
        return entityManager.createQuery("SELECT a FROM alunos a", Aluno.class).getResultList();
    }

    @Transactional
    @Override
    public Aluno save(Aluno aluno) {
        entityManager.persist(aluno);
        return aluno;
    }
}
