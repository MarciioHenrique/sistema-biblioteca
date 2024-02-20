package com.marcioh.sistemabiblioteca.dao;

import com.marcioh.sistemabiblioteca.model.ItemDevolucao;

import java.util.List;

public interface ItemDevolucaoDAO {
    List<ItemDevolucao> findAll();
    ItemDevolucao save(ItemDevolucao itemDevolucao);
}
