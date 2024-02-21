package com.marcioh.sistemabiblioteca.service;

import com.marcioh.sistemabiblioteca.model.Aluno;
import com.marcioh.sistemabiblioteca.model.Livro;
import com.marcioh.sistemabiblioteca.repository.AlunoRepository;
import com.marcioh.sistemabiblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroDAO;

    public List<Livro> listarLivros() {
        return livroDAO.findAll();
    }

    public Livro cadastrarLivro(Livro livro) {
        return livroDAO.save(livro);
    }

    public Livro atualizarDisponibilidade(Livro livro) {
        return livroDAO.livroIndisponivel(livro);
    }
}
