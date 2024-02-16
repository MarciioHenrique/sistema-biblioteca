package com.marcioh.sistemabiblioteca.dao;

import com.marcioh.sistemabiblioteca.model.Debito;

import java.util.List;

public interface DebitoDAO {
    List<Debito> listarDebitosAluno(String matricula);
    Debito adicionarDebito(Debito debito);
}
