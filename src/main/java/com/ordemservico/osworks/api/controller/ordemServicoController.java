package com.ordemservico.osworks.api.controller;

import com.ordemservico.osworks.api.model.OrdemSericoModel;
import com.ordemservico.osworks.domain.model.Cliente;
import com.ordemservico.osworks.domain.model.OrdemServico;
import com.ordemservico.osworks.domain.repositoy.OrdemServicoRepository;
import com.ordemservico.osworks.domain.service.GestaoOrdemServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/ordens-servicos")
public class ordemServicoController {
    @Autowired
    private GestaoOrdemServicoService gestaoOrdemServicoService;
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@Valid @RequestBody OrdemServico ordemServico){

        return gestaoOrdemServicoService.criar(ordemServico);

    }

    @GetMapping
    public List<OrdemServico> listar(){
        return ordemServicoRepository.findAll();
    }

    @GetMapping("/{ordemServicoID}")
    public ResponseEntity<OrdemSericoModel> buscar(@PathVariable Long ordemServicoID){
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoID);

        if (ordemServico.isPresent()){
            OrdemSericoModel ordemServicoModel = modelMapper.map(toModel(ordemServico.get()), OrdemSericoModel.class);
            return ResponseEntity.ok(ordemServicoModel);
        }
        return ResponseEntity.notFound().build();

    }

    private OrdemSericoModel toModel(OrdemServico ordemServico){
        return modelMapper.map(ordemServico, OrdemSericoModel.class);
    }
}
