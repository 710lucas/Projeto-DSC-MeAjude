package com.si.meAjude.controllers;


import com.si.meAjude.exceptions.*;
import com.si.meAjude.service.CampanhaService;
import com.si.meAjude.service.dtos.campanha.CampanhaDTO;
import com.si.meAjude.service.dtos.campanha.CampanhaUpdateDTO;
import com.si.meAjude.service.dtos.campanha.ListaCampanhasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/campanhas")
public class CampanhaController {


    @Autowired CampanhaService campanhaService;


    @PostMapping
    public ResponseEntity<CampanhaDTO> adicionar(@RequestBody CampanhaDTO campanha) throws DataInvalida, TituloInvalidoException, CriadorInvalidoException, DescricaoInvalidaException, MetaInvalidaException {
        return ResponseEntity.ok(campanhaService.adicionarCampanha(campanha));
    }

    @DeleteMapping
    public ResponseEntity<CampanhaDTO> remover(@RequestBody CampanhaUpdateDTO campanha){
        return ResponseEntity.ok(campanhaService.removerCampanha(campanha.id()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampanhaDTO> getById(@PathVariable String id){
        return ResponseEntity.ok(campanhaService.getCampanha(Long.parseLong(id)));
    }

    @PutMapping()
    public ResponseEntity<CampanhaDTO> modificar(@RequestBody CampanhaUpdateDTO campanha) throws DataInvalida, TituloInvalidoException, DescricaoInvalidaException, MetaInvalidaException {
        return ResponseEntity.ok(campanhaService.update(campanha));
    }


    @GetMapping()
    public ResponseEntity<ListaCampanhasDTO> listar(@RequestParam(name = "quantidade", required = false, defaultValue = "-1") String quantidade, @RequestParam(name = "criterio", required = false) String criterio) throws DoacaoInvalidaException, CriterioInvalidoException {
        return ResponseEntity.ok(campanhaService.listarCampanhas(Optional.of(Long.parseLong(quantidade)), Optional.ofNullable(criterio)));
    }




}
