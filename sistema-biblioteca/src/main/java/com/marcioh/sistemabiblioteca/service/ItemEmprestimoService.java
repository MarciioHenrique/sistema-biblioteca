package com.marcioh.sistemabiblioteca.service;

import com.marcioh.sistemabiblioteca.DTO.itemEmprestimo.ItemEmprestimoRequestDTO;
import com.marcioh.sistemabiblioteca.DTO.itemEmprestimo.ItemEmprestimoResponseDTO;
import com.marcioh.sistemabiblioteca.model.Emprestimo;
import com.marcioh.sistemabiblioteca.model.ItemEmprestimo;
import com.marcioh.sistemabiblioteca.repository.EmprestimoRepository;
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
    private ItemEmprestimoRepository itemEMprestimoDAO;

    public List<ItemEmprestimo> listarItensEmprestimo() {
        return itemEMprestimoDAO.findAll();
    }

    public ItemEmprestimo cadastrarItemEmprestimo(ItemEmprestimo itemEmprestimo) {
        return itemEMprestimoDAO.save(itemEmprestimo);
    }

    public List<ItemEmprestimoResponseDTO> cadastrarListaItensEmprestimo(List<ItemEmprestimoRequestDTO> itemEmprestimoRequest, Emprestimo emprestimo) {
        List<ItemEmprestimoResponseDTO> response = new ArrayList<>();
        System.out.println(itemEmprestimoRequest.get(0).livro().getId());
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
