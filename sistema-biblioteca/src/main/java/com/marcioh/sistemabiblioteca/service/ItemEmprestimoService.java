package com.marcioh.sistemabiblioteca.service;

import com.marcioh.sistemabiblioteca.dto.itemEmprestimo.ItemEmprestimoRequestDTO;
import com.marcioh.sistemabiblioteca.dto.itemEmprestimo.ItemEmprestimoResponseDTO;
import com.marcioh.sistemabiblioteca.exception.BadRequestException;
import com.marcioh.sistemabiblioteca.model.Emprestimo;
import com.marcioh.sistemabiblioteca.model.ItemEmprestimo;
import com.marcioh.sistemabiblioteca.repository.ItemEmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ItemEmprestimoService {
    @Autowired
    private ItemEmprestimoRepository itemEmprestimoDAO;

    public List<ItemEmprestimo> listarItensEmprestimo() {
        return itemEmprestimoDAO.findAll();
    }

    public ItemEmprestimo cadastrarItemEmprestimo(ItemEmprestimo itemEmprestimo) {
        return itemEmprestimoDAO.save(itemEmprestimo);
    }

    public void verificarLivros(List<ItemEmprestimoRequestDTO> itemEmprestimoRequest) {
        for (ItemEmprestimoRequestDTO itemEmprestimo : itemEmprestimoRequest) {
            if (itemEmprestimoDAO.findByLivro(itemEmprestimo.livro().getId())) {
                throw new BadRequestException("Livro já reservado");
            }
            if (itemEmprestimo.livro().isExemplarBiblioteca()) {
                throw new BadRequestException("Livro é exemplar da biblioteca e não pode ser emprestado");
            }
        }
    }


    public List<ItemEmprestimoResponseDTO> cadastrarListaItensEmprestimo(List<ItemEmprestimoRequestDTO> itemEmprestimoRequest, Emprestimo emprestimo) {
        List<ItemEmprestimoResponseDTO> response = new ArrayList<>();
        for (ItemEmprestimoRequestDTO itemEmprestimoRequestDTO : itemEmprestimoRequest) {
            ItemEmprestimo itemEmprestimo = new ItemEmprestimo();
            itemEmprestimo.setDataPrevista(new Date(2024, Calendar.JANUARY, 2));
            itemEmprestimo.setLivro(itemEmprestimoRequestDTO.livro());
            itemEmprestimo.setEmprestimo(emprestimo);
            cadastrarItemEmprestimo(itemEmprestimo);
            response.add(new ItemEmprestimoResponseDTO(
                    itemEmprestimo.getId(),
                    itemEmprestimo.getDataPrevista(),
                    itemEmprestimo.getLivro()
            ));
        }
        return response;
    }
}
