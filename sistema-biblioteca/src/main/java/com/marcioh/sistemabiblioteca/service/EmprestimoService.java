package com.marcioh.sistemabiblioteca.service;

import com.marcioh.sistemabiblioteca.DTO.emprestimo.EmprestimoRequestDTO;
import com.marcioh.sistemabiblioteca.DTO.emprestimo.EmprestimoResponseDTO;
import com.marcioh.sistemabiblioteca.DTO.itemEmprestimo.ItemEmprestimoResponseDTO;
import com.marcioh.sistemabiblioteca.model.Emprestimo;
import com.marcioh.sistemabiblioteca.model.ItemEmprestimo;
import com.marcioh.sistemabiblioteca.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository emprestimoDAO;

    @Autowired
    private ItemEmprestimoService itemEmprestimoService;


    public List<Emprestimo> listarEmprestimos() {
        return emprestimoDAO.findAll();
    }

    public EmprestimoResponseDTO  cadastrarEmprestimo(EmprestimoRequestDTO emprestimoRequest) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataEmprestimo(emprestimoRequest.dataEmprestimo());
        emprestimo.setDataPrevista(new Date(2024,10,1));
        emprestimo.setDevolucao(null);
        emprestimo.setAluno(emprestimoRequest.aluno());

        emprestimoDAO.save(emprestimo);

        List<ItemEmprestimoResponseDTO> itensEmprestimo = itemEmprestimoService.cadastrarListaItensEmprestimo(emprestimoRequest.itensEmprestimo(), emprestimo);

        return new EmprestimoResponseDTO(
                emprestimo.getId(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataPrevista(),
                emprestimo.getAluno(),
                emprestimo.getDevolucao(),
                itensEmprestimo
        );
    }
}
