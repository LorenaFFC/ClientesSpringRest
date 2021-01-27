package com.ordemservico.osworks.domain.service;

import com.ordemservico.osworks.domain.exception.NegocioException;
import com.ordemservico.osworks.domain.model.Cliente;
import com.ordemservico.osworks.domain.model.OrdemServico;
import com.ordemservico.osworks.domain.model.StatusOrdemServico;
import com.ordemservico.osworks.domain.repositoy.ClienteRepository;
import com.ordemservico.osworks.domain.repositoy.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class GestaoOrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    public OrdemServico criar(OrdemServico ordemServico){
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new NegocioException("Cliente não encontrado."));
        // definindo o status quando cria uma ordem de servico
        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());
        return ordemServicoRepository.save(ordemServico);
    }
}
