package com.si.meAjude.controllers;


import com.si.meAjude.exceptions.*;
import com.si.meAjude.service.CampaignService;
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


    @Autowired
    CampaignService campaignService;


    @PostMapping
    public ResponseEntity<CampanhaDTO> adicionar(@RequestBody CampanhaDTO campanha) throws DataInvalida, TituloInvalidoException, CriadorInvalidoException, DescricaoInvalidaException, MetaInvalidaException {
        return ResponseEntity.ok(campaignService.adicionarCampanha(campanha));
    }

    @DeleteMapping
    public ResponseEntity<CampanhaDTO> remover(@RequestBody CampanhaUpdateDTO campanha){
        return ResponseEntity.ok(campaignService.removerCampanha(campanha.id()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampanhaDTO> getById(@PathVariable String id){
        return ResponseEntity.ok(campaignService.getCampanha(Long.parseLong(id)));
    }

    @PatchMapping("/ativa")
    public ResponseEntity<CampanhaDTO> setAtiva(@RequestBody CampanhaUpdateDTO campanha){
        return ResponseEntity.ok(campaignService.mudarEstado(campanha.ativa(), campanha.id()));
    }

    @PatchMapping("/titulo")
    public ResponseEntity<CampanhaDTO> modificarTitulo(@RequestBody CampanhaUpdateDTO campanha) throws TituloInvalidoException {
        return ResponseEntity.ok(campaignService.mudarTitulo(campanha.titulo(), campanha.id()));
    }

    @PatchMapping("/descricao")
    public ResponseEntity<CampanhaDTO> setDescricao(@RequestBody CampanhaUpdateDTO campanha) throws DescricaoInvalidaException {
        return ResponseEntity.ok(campaignService.mudarDescricao(campanha.descricao(), campanha.id()));
    }

    @PatchMapping("/meta")
    public ResponseEntity<CampanhaDTO> setMeta(@RequestBody CampanhaUpdateDTO campanha) throws MetaInvalidaException {
        return ResponseEntity.ok(campaignService.mudarMeta(campanha.meta(), campanha.id()));
    }

    @PatchMapping("/date-final")
    public ResponseEntity<CampanhaDTO> setDataFinal(@RequestBody CampanhaUpdateDTO campanha) throws DataInvalida {
        return ResponseEntity.ok(campaignService.mudarDataFinal(campanha.dataFinal(), campanha.id()));
    }
    @PatchMapping("/criador")
    public ResponseEntity<CampanhaDTO> setCriador(@RequestBody CampanhaUpdateDTO campanha) throws CriadorInvalidoException {
        return ResponseEntity.ok(campaignService.mudarCriador(campanha.criadorId(), campanha.id()));
    }

    @GetMapping()
    public ResponseEntity<ListaCampanhasDTO> listar(@RequestParam(name = "quantidade", required = false, defaultValue = "-1") String quantidade, @RequestParam(name = "criterio", required = false) String criterio) throws DoacaoInvalidaException, CriterioInvalidoException {
        return ResponseEntity.ok(campaignService.listarCampanhas(Optional.of(Long.parseLong(quantidade)), Optional.ofNullable(criterio)));
    }




}
