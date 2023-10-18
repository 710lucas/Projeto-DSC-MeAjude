package com.si.meAjude.controllers;


import com.si.meAjude.exceptions.*;
import com.si.meAjude.service.CampanhaService;
import com.si.meAjude.service.dtos.campanha.AddDoacaoDTO;
import com.si.meAjude.service.dtos.campanha.CampanhaDTO;
import com.si.meAjude.service.dtos.campanha.CampanhaUpdateDTO;
import com.si.meAjude.service.dtos.campanha.ListaCampanhasDTO;
import com.si.meAjude.service.dtos.doacao.DoacaoDTO;
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

    @PatchMapping("/ativa")
    public ResponseEntity<CampanhaDTO> setAtiva(@RequestBody CampanhaUpdateDTO campanha){
        return ResponseEntity.ok(campanhaService.mudarEstado(campanha.ativa(), campanha.id()));
    }

    @PatchMapping("/titulo")
    public ResponseEntity<CampanhaDTO> modificarTitulo(@RequestBody CampanhaUpdateDTO campanha) throws TituloInvalidoException {
        return ResponseEntity.ok(campanhaService.mudarTitulo(campanha.titulo(), campanha.id()));
    }

    @PatchMapping("/descricao")
    public ResponseEntity<CampanhaDTO> setDescricao(@RequestBody CampanhaUpdateDTO campanha) throws DescricaoInvalidaException {
        return ResponseEntity.ok(campanhaService.mudarDescricao(campanha.descricao(), campanha.id()));
    }

    @PatchMapping("/meta")
    public ResponseEntity<CampanhaDTO> setMeta(@RequestBody CampanhaUpdateDTO campanha) throws MetaInvalidaException {
        return ResponseEntity.ok(campanhaService.mudarMeta(campanha.meta(), campanha.id()));
    }

    @PatchMapping("/data-final")
    public ResponseEntity<CampanhaDTO> setDataFinal(@RequestBody CampanhaUpdateDTO campanha) throws DataInvalida {
        return ResponseEntity.ok(campanhaService.mudarDataFinal(campanha.dataFinal(), campanha.id()));
    }
    @PatchMapping("/criador")
    public ResponseEntity<CampanhaDTO> setCriador(@RequestBody CampanhaUpdateDTO campanha) throws CriadorInvalidoException {
        return ResponseEntity.ok(campanhaService.mudarCriador(campanha.criadorId(), campanha.id()));
    }

    @GetMapping()
    public ResponseEntity<ListaCampanhasDTO> listar(@RequestParam(name = "quantidade", required = false, defaultValue = "-1") String quantidade, @RequestParam(name = "criterio", required = false) String criterio) throws DoacaoInvalidaException, CriterioInvalidoException {
        return ResponseEntity.ok(campanhaService.listarCampanhas(Optional.of(Long.parseLong(quantidade)), Optional.ofNullable(criterio)));
    }



//    @Autowired
//    CampanhaService campanhaService;
//
//    @PostMapping
//    public ResponseEntity<CampanhaDTO> addCampanha(@RequestBody CampanhaDTO dto) throws DataInvalida, TituloInvalidoException, CriadorInvalidoException, DescricaoInvalidaException, MetaInvalidaException {
//        return ResponseEntity.ok(campanhaService.adicionarCampanha(dto));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CampanhaDTO> getCampanha(@PathVariable long id){
//        return ResponseEntity.ok(campanhaService.getCampanha(id));
//    }
//
//    @PutMapping("/mudar/{tipo}/{id}")
//    public ResponseEntity<CampanhaDTO> mudarCampanha(@PathVariable("tipo") String tipo, @PathVariable("id") long id, @RequestBody CampanhaDTO dto) throws Exception {
//        return ResponseEntity.ok(campanhaService.mudar(tipo, dto, id));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<CampanhaDTO> deletarCampanha(@PathVariable long id){
//        return ResponseEntity.ok(campanhaService.removerCampanha(id));
//    }
//
//    @GetMapping("/listar/{criterio}")
//    public ResponseEntity<ListaCampanhasDTO> getCampanhasAtivas(@PathVariable(value = "criterio") String criterioString) throws CriterioInvalidoException {
//        return ResponseEntity.ok(campanhaService.listarCampanhas(Optional.empty(), criterioString));
//    }
//    @GetMapping("/listar/{criterio}/{tamanho}")
//    public ResponseEntity<ListaCampanhasDTO> getCampanhasAtivasTamanho(@PathVariable("tamanho") long tamanho, @PathVariable(value = "criterio") String criterioString) throws CriterioInvalidoException {
//        return ResponseEntity.ok(campanhaService.listarCampanhas(Optional.of(tamanho), criterioString));
//    }

}
