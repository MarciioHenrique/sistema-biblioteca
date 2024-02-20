package com.marcioh.sistemabiblioteca.repository;

import com.marcioh.sistemabiblioteca.dao.EmprestimoDAO;
import com.marcioh.sistemabiblioteca.model.Aluno;
import com.marcioh.sistemabiblioteca.model.Devolucao;
import com.marcioh.sistemabiblioteca.model.Emprestimo;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmprestimoRepository implements EmprestimoDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public Emprestimo findByAlunoMatricula(String matricula) {
        return entityManager.createQuery("SELECT a FROM emprestimos a WHERE a.aluno.matricula = :matricula", Emprestimo.class)
                .setParameter("matricula", matricula)
                .getSingleResult();
    }

    @Override
    public List<Emprestimo> findAll() {
        return entityManager.createQuery("SELECT a FROM emprestimos a", Emprestimo.class).getResultList();
    }

    @Transactional
    @Override
    public Emprestimo save(Emprestimo emprestimo) {
        entityManager.persist(emprestimo);
        return emprestimo;
    }

    @Transactional
    @Override
    public void atualizarEmprestimoDevolucao(Emprestimo emprestimo, Devolucao devolucao) {
        int linhasAfetadas = entityManager.createQuery("UPDATE emprestimos a SET a.devolucao.id = :devolucao WHERE a.id = :id", Emprestimo.class)
                .setParameter("devolucao", devolucao.getId())
                .setParameter("id", emprestimo.getId())
                .executeUpdate();

        if (linhasAfetadas == 0) {
            throw new RuntimeException("Empréstimo não encontrado");
        }
    }
}
