package com.ordemservico.osworks.domain.service;

import com.ordemservico.osworks.domain.exception.NegocioException;
import com.ordemservico.osworks.domain.model.Cliente;
import com.ordemservico.osworks.domain.repositoy.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente){

        Cliente clienteExistente = clienteRepository.findAllByEmail(cliente.getEmail());

        if(clienteExistente !=null && !clienteExistente.equals(cliente)){
            throw new NegocioException("Já existe um cliente cadastrado com esse e-mail");
        }

        return clienteRepository.save(cliente);


    }
    public void excluir(Long clienteId){
        clienteRepository.deleteById(clienteId);
    }
}
