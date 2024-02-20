package com.marcioh.sistemabiblioteca.service;

import com.marcioh.sistemabiblioteca.dto.emprestimo.EmprestimoRequestDTO;
import com.marcioh.sistemabiblioteca.dto.emprestimo.EmprestimoResponseDTO;
import com.marcioh.sistemabiblioteca.dto.itemEmprestimo.ItemEmprestimoResponseDTO;
import com.marcioh.sistemabiblioteca.exception.BadRequestException;
import com.marcioh.sistemabiblioteca.model.*;
import com.marcioh.sistemabiblioteca.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
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

        List<ItemEmprestimo> itensEmprestimo = itemEmprestimoService.verificarLivros(emprestimoRequest.itensEmprestimo());

        //cria emprestimo
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataEmprestimo(emprestimoRequest.dataEmprestimo());
        emprestimo.setDataPrevista(CalculaDataPrevistaEmprestimo(itensEmprestimo));
        emprestimo.setDevolucao(null);
        emprestimo.setAluno(aluno);

        emprestimoDAO.save(emprestimo);

        List<ItemEmprestimoResponseDTO> itensEmprestimoResponse = itemEmprestimoService.cadastrarListaItensEmprestimo(itensEmprestimo, emprestimo);

        return new EmprestimoResponseDTO(
                emprestimo.getId(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataPrevista(),
                emprestimo.getAluno(),
                emprestimo.getDevolucao(),
                itensEmprestimoResponse
        );
    }

    private Date CalculaDataPrevistaEmprestimo(List<ItemEmprestimo> itens)
    {
        Date dataAux;
        Date dataPrevista = new Date();

        for (ItemEmprestimo item : itens) {
            dataAux = item.getDataPrevista();
            if (dataPrevista.compareTo(dataAux) < 0)
                dataPrevista = dataAux;
        }
        if (itens.size() > 2) {
            int tam = itens.size()-2;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dataPrevista);
            calendar.add(Calendar.DATE, (tam*2));
            dataPrevista = calendar.getTime();
        }
        for (ItemEmprestimo item : itens) {
            item.setDataPrevista(dataPrevista);
        }

        return dataPrevista;

    }

    public Emprestimo listarEmprestimoAluno(String alunoMatricula) {
        return emprestimoDAO.findByAlunoMatricula(alunoMatricula);
    }

    public void atualizarEmprestimo(Emprestimo emprestimo, Devolucao devolucao) {
        emprestimoDAO.atualizarEmprestimoDevolucao(emprestimo, devolucao);
    }
}
