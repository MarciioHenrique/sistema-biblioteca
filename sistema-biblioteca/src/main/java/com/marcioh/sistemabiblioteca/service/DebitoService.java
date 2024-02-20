package com.marcioh.sistemabiblioteca.service;

import com.marcioh.sistemabiblioteca.model.Aluno;
import com.marcioh.sistemabiblioteca.model.Debito;
import com.marcioh.sistemabiblioteca.repository.DebitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

@Service
public class DebitoService {
    @Autowired
    private DebitoRepository debitoRepository;

    public List<Debito> listarDebitosAluno(String matricula) {
        return debitoRepository.listarDebitosAluno(matricula);
    }

    public Debito adicionarDebito(Aluno aluno, float valorTotal) {
        Debito debito = new Debito();
        debito.setAluno(aluno);
        debito.setValor(valorTotal);
        debito.setData(new Date());

        return debitoRepository.adicionarDebito(debito);
    }
}
