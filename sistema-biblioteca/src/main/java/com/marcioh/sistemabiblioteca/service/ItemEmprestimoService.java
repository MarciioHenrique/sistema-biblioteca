package com.marcioh.sistemabiblioteca.service;

import com.marcioh.sistemabiblioteca.dto.itemEmprestimo.ItemEmprestimoRequestDTO;
import com.marcioh.sistemabiblioteca.dto.itemEmprestimo.ItemEmprestimoResponseDTO;
import com.marcioh.sistemabiblioteca.exception.BadRequestException;
import com.marcioh.sistemabiblioteca.model.Emprestimo;
import com.marcioh.sistemabiblioteca.model.ItemEmprestimo;
import com.marcioh.sistemabiblioteca.model.Livro;
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

    public List<ItemEmprestimo> verificarLivros(List<Livro> itemEmprestimoRequest) {
        List<ItemEmprestimo> itensEmprestimo = new ArrayList<>();
        for (Livro itemEmprestimo : itemEmprestimoRequest) {
            if (itemEmprestimoDAO.findByLivro(itemEmprestimo.getId())) {
                throw new BadRequestException("Livro já reservado");
            }
            if (itemEmprestimo.isExemplarBiblioteca()) {
                throw new BadRequestException("Livro é exemplar da biblioteca e não pode ser emprestado");
            }
            ItemEmprestimo item = new ItemEmprestimo();
            item.setDataPrevista(calculaDataDevolucaoItem(new Date(), itemEmprestimo.getTitulo().getPrazo()));
            item.setLivro(itemEmprestimo);
            item.setEmprestimo(null);
            itensEmprestimo.add(item);

        }
        return itensEmprestimo;
    }


    public List<ItemEmprestimoResponseDTO> cadastrarListaItensEmprestimo(List<ItemEmprestimo> itensEmprestimo, Emprestimo emprestimo) {
        List<ItemEmprestimoResponseDTO> response = new ArrayList<>();
        for (ItemEmprestimo item : itensEmprestimo) {
            cadastrarItemEmprestimo(item);
            response.add(new ItemEmprestimoResponseDTO(
                    item.getId(),
                    item.getDataPrevista(),
                    item.getLivro()
            ));
        }
        return response;
    }

    public Date calculaDataDevolucaoItem(Date data, int prazo)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.DATE, prazo);
        return calendar.getTime();
    }
}
