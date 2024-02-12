package com.marcioh.sistemabiblioteca.service;

import com.marcioh.sistemabiblioteca.model.Titulo;
import com.marcioh.sistemabiblioteca.repository.TituloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TituloService {
    @Autowired
    private TituloRepository tituloDAO;

    public List<Titulo> listarTitulos() {
        return tituloDAO.findAll();
    }

    public Titulo cadastrarTitulo(Titulo titulo) {
        return tituloDAO.save(titulo);
    }
}
