package com.ordemservico.osworks.domain.service;

import com.ordemservico.osworks.domain.model.OrdemServico;
import com.ordemservico.osworks.domain.model.StatusOrdemServico;
import com.ordemservico.osworks.domain.repositoy.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GestaoOrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    public OrdemServico criar(OrdemServico ordemServico){
        // definindo o status quando cria uma ordem de servico
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());
        return ordemServicoRepository.save(ordemServico);
    }
}
