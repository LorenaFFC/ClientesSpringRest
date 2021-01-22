package com.ordemservico.osworks.api.controller;

import com.ordemservico.osworks.domain.model.Cliente;
import com.ordemservico.osworks.domain.repositoy.ClienteRepository;
import com.ordemservico.osworks.domain.service.CadastroClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

//   <! @PersistenceContext
//private EntityManager manager;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CadastroClienteService cadastroClienteService;


    @GetMapping
    public List<Cliente> listar(){
        return clienteRepository.findAll();
       // return manager.createQuery("select p from Cliente p",Cliente.class).getResultList();
    }
    @GetMapping("/{clienteID}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteID){
        Optional<Cliente>  cliente = clienteRepository.findById(clienteID);

        if (cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente){
        return cadastroClienteService.salvar(cliente);
    }

    @PutMapping("/{clienteID}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteID,
                                             @RequestBody Cliente cliente){
        if (!clienteRepository.existsById(clienteID)){
            return ResponseEntity.notFound().build();
        }

        // PARA NAO CRIAR UM LINHA NOVA ATRIBUI O MESMO ID
        cliente.setId(clienteID);
        cliente = cadastroClienteService.salvar(cliente);
                //clienteRepository.save(cliente);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{clienteID}")
    public ResponseEntity<Void> delete(@PathVariable Long clienteID){
        if(!clienteRepository.existsById(clienteID)){
            return ResponseEntity.notFound().build();
        }

        //clienteRepository.deleteById(clienteID);
        cadastroClienteService.excluir(clienteID);
        return ResponseEntity.noContent().build();
    }
}
