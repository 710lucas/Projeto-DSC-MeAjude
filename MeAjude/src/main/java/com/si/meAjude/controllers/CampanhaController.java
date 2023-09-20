package com.si.meAjude.controllers;


import com.si.meAjude.exceptions.*;
import com.si.meAjude.service.CampanhaService;
import com.si.meAjude.service.dtos.CampanhaDTO;
import com.si.meAjude.service.dtos.ListaCampanhasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/campanhas")
public class CampanhaController {

    @Autowired
    CampanhaService campanhaService;

    @PostMapping
    public ResponseEntity<CampanhaDTO> addCampanha(@RequestBody CampanhaDTO dto) throws DataInvalida, TituloInvalidoException, CriadorInvalidoException, DescricaoInvalidaException, MetaInvalidaException {
        return ResponseEntity.ok(campanhaService.adicionarCampanha(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampanhaDTO> getCampanha(@PathVariable long id){
        return ResponseEntity.ok(campanhaService.getCampanha(id));
    }

    @PutMapping("/mudar/{tipo}/{id}")
    public ResponseEntity<CampanhaDTO> mudarCampanha(@PathVariable("tipo") String tipo, @PathVariable("id") long id, @RequestBody CampanhaDTO dto) throws Exception {
        return ResponseEntity.ok(campanhaService.mudar(tipo, dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CampanhaDTO> deletarCampanha(@PathVariable long id){
        return ResponseEntity.ok(campanhaService.removerCampanha(id));
    }

    @GetMapping("/listar/{criterio}")
    public ResponseEntity<ListaCampanhasDTO> getCampanhasAtivas(@PathVariable(value = "criterio") String criterioString) throws CriterioInvalidoException {
        return ResponseEntity.ok(campanhaService.listarCampanhas(Optional.empty(), criterioString));
    }
    @GetMapping("/listar/{criterio}/{tamanho}")
    public ResponseEntity<ListaCampanhasDTO> getCampanhasAtivasTamanho(@PathVariable("tamanho") long tamanho, @PathVariable(value = "criterio") String criterioString) throws CriterioInvalidoException {
        return ResponseEntity.ok(campanhaService.listarCampanhas(Optional.of(tamanho), criterioString));
    }

}
