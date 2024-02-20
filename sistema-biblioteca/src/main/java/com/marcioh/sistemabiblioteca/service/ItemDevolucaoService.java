package com.marcioh.sistemabiblioteca.service;

import com.marcioh.sistemabiblioteca.dto.itemDevolucao.ItemDevolucaoResponseDTO;
import com.marcioh.sistemabiblioteca.exception.BadRequestException;
import com.marcioh.sistemabiblioteca.model.Devolucao;
import com.marcioh.sistemabiblioteca.model.ItemDevolucao;
import com.marcioh.sistemabiblioteca.model.Livro;
import com.marcioh.sistemabiblioteca.repository.ItemDevolucaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ItemDevolucaoService {
    @Autowired
    private ItemDevolucaoRepository repository;

    public ItemDevolucao cadastrarItemDevolucao(ItemDevolucao itemDevolucao) {
        return repository.save(itemDevolucao);
    }

    public List<ItemDevolucaoResponseDTO> cadastrarItensDevolucao(List<ItemDevolucao> itensDevolucao, Devolucao devolucao) {
        List<ItemDevolucaoResponseDTO> itensDevolucaoResponse = new ArrayList<>();
        for (ItemDevolucao itemDevolucao : itensDevolucao) {
            itemDevolucao.setDevolucao(devolucao);
            cadastrarItemDevolucao(itemDevolucao);
            itensDevolucaoResponse.add(new ItemDevolucaoResponseDTO(
                    itemDevolucao.getDataDevolucao(),
                    itemDevolucao.getDiasAtraso(),
                    itemDevolucao.getValor(),
                    itemDevolucao.getLivro()
            ));
        }
        return itensDevolucaoResponse;
    }

    public List<ItemDevolucao> verificarLivros(List<Livro> livros, Date dataDevolucao, Date dataPrevista) {
        List<ItemDevolucao> itensDevolucao = new ArrayList<>();
        for (Livro livro : livros) {
            ItemDevolucao itemDevolucao = new ItemDevolucao();
            itemDevolucao.setDataDevolucao(dataDevolucao);
            itemDevolucao.setLivro(livro);
            itemDevolucao.setMulta(3.00f);
            itemDevolucao.setDiasAtraso(calculaDiasAtraso(dataDevolucao, dataPrevista));
            itemDevolucao.setValor(calculaValor(itemDevolucao.getMulta(), itemDevolucao.getDiasAtraso()));
            itemDevolucao.setDevolucao(null);
            itensDevolucao.add(itemDevolucao);
        }
        return itensDevolucao;
    }

    public int calculaDiasAtraso(Date dataDevolucao, Date dataPrevista) {
        Calendar dataDevolucaoCalendar = Calendar.getInstance();
        dataDevolucaoCalendar.setTime(dataDevolucao);
        Calendar dataPrevistaCalendar = Calendar.getInstance();
        dataPrevistaCalendar.setTime(dataPrevista);

        // Compara as datas
        int comparacao = dataDevolucaoCalendar.compareTo(dataPrevistaCalendar);

        // Se a data de devolução for posterior à data prevista, calcula a diferença em dias
        if (comparacao > 0) {
            long diferencaEmMilissegundos = dataDevolucaoCalendar.getTimeInMillis() - dataPrevistaCalendar.getTimeInMillis();
            return (int) (diferencaEmMilissegundos / (1000 * 60 * 60 * 24));
        }

        // Se o livro não estiver atrasado, retorna 0
        return 0;
    }

    public float calculaValor(float multa, int diasAtraso) {
        return multa * diasAtraso;
    }
}
