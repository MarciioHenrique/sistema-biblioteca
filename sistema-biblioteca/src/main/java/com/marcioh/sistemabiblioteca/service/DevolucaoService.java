package com.marcioh.sistemabiblioteca.service;

import com.marcioh.sistemabiblioteca.dto.devolucao.DevolucaoRequestDTO;
import com.marcioh.sistemabiblioteca.dto.devolucao.DevolucaoResponseDTO;
import com.marcioh.sistemabiblioteca.dto.itemDevolucao.ItemDevolucaoResponseDTO;
import com.marcioh.sistemabiblioteca.model.Aluno;
import com.marcioh.sistemabiblioteca.model.Devolucao;
import com.marcioh.sistemabiblioteca.model.Emprestimo;
import com.marcioh.sistemabiblioteca.model.ItemDevolucao;
import com.marcioh.sistemabiblioteca.repository.DevolucaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DevolucaoService {
    @Autowired
    private DevolucaoRepository devolucaoRepository;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private EmprestimoService emprestimoService;

    @Autowired
    private ItemDevolucaoService itemDevolucaoService;

    public List<Devolucao> findAll() {
        return devolucaoRepository.findAll();
    }

    public DevolucaoResponseDTO save(DevolucaoRequestDTO devolucaoRequest) {
        //faz verificação do aluno
        Aluno aluno = alunoService.listarPorMatricula(devolucaoRequest.alunoMatricula());

        Emprestimo emprestimo = emprestimoService.listarEmprestimoAluno(aluno.getMatricula());

        List<ItemDevolucao> itensDevolucao = itemDevolucaoService.verificarLivros(devolucaoRequest.itensDevolucao(), devolucaoRequest.dataDevolucao(), emprestimo.getDataPrevista());

        Devolucao devolucao = new Devolucao();
        devolucao.setDataDevoculao(devolucaoRequest.dataDevolucao());
        devolucao.setAtraso(calculaAtraso(devolucaoRequest.dataDevolucao(), emprestimo.getDataPrevista()));
        devolucao.setEmprestimo(emprestimo);
        devolucao.setValorTotal(calculaValorTotal(itensDevolucao));

        devolucaoRepository.save(devolucao);

        List<ItemDevolucaoResponseDTO> itensDevolucaoResponse = itemDevolucaoService.cadastrarItensDevolucao(itensDevolucao, devolucao);
        //falta atualizar o emprestimo para relacionar com a Devolucao

        return new DevolucaoResponseDTO(
                true,
                10f,
                itensDevolucaoResponse
        );
    }

    public boolean calculaAtraso(Date dataDevolucao, Date dataPrevisa) {
        // Converte as datas para Calendar
        Calendar dataDevolucaoCalendar = Calendar.getInstance();
        dataDevolucaoCalendar.setTime(dataDevolucao);
        Calendar dataEmprestimoCalendar = Calendar.getInstance();
        dataEmprestimoCalendar.setTime(dataPrevisa);

        // Compara as datas
        int comparacao = dataDevolucaoCalendar.compareTo(dataEmprestimoCalendar);

        // Retorna true se a data de devolução for posterior à data prevista
        return comparacao > 0;
    }

    public float calculaValorTotal(List<ItemDevolucao> itensDevolucao) {
        float valorTotal = 0f;
        for (ItemDevolucao item : itensDevolucao) {
            valorTotal += item.getValor();
        }
        return valorTotal;
    }
}
