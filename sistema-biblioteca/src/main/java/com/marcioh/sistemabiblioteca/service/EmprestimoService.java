package com.marcioh.sistemabiblioteca.service;

import com.marcioh.sistemabiblioteca.dto.emprestimo.EmprestimoRequestDTO;
import com.marcioh.sistemabiblioteca.dto.emprestimo.EmprestimoResponseDTO;
import com.marcioh.sistemabiblioteca.dto.itemEmprestimo.ItemEmprestimoResponseDTO;
import com.marcioh.sistemabiblioteca.exception.BadRequestException;
import com.marcioh.sistemabiblioteca.model.Aluno;
import com.marcioh.sistemabiblioteca.model.Debito;
import com.marcioh.sistemabiblioteca.model.Emprestimo;
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

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private DebitoService debitoService;


    public List<Emprestimo> listarEmprestimos() {
        return emprestimoDAO.findAll();
    }

    public EmprestimoResponseDTO  cadastrarEmprestimo(EmprestimoRequestDTO emprestimoRequest) {
        //verifica se o aluno está cadastrado
        Aluno aluno = alunoService.listarPorMatricula(emprestimoRequest.alunoMatricula());

        //verifica se o aluno possui pendências
        List<Debito> debitos = debitoService.listarDebitosAluno(emprestimoRequest.alunoMatricula());
        if (!debitos.isEmpty()) {
            throw new BadRequestException("O aluno possui débitos pendentes");
        }

        itemEmprestimoService.verificarLivros(emprestimoRequest.itensEmprestimo());

        //cria emprestimo
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataEmprestimo(emprestimoRequest.dataEmprestimo());
        emprestimo.setDataPrevista(new Date(2024,10,1));
        emprestimo.setDevolucao(null);
        emprestimo.setAluno(aluno);

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
