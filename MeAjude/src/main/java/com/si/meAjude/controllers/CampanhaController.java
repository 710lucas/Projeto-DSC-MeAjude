package com.si.meAjude.controllers;


import com.si.meAjude.exceptions.CriadorInvalidoException;
import com.si.meAjude.exceptions.DescricaoInvalidaException;
import com.si.meAjude.exceptions.MetaInvalidaException;
import com.si.meAjude.exceptions.TituloInvalidoException;
import com.si.meAjude.models.DataInvalida;
import com.si.meAjude.service.CampanhaService;
import com.si.meAjude.service.dtos.CampanhaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/campanhas/")
public class CampanhaController {

    CampanhaService campanhas;

    @Autowired
    public CampanhaController(CampanhaService campanhas){
        this.campanhas = campanhas;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampanhaDTO> getCampanha(@PathVariable long id){
        return ResponseEntity.ok(campanhas.getCampanha(id));
    }

    @PostMapping("/")
    public ResponseEntity<CampanhaDTO> addCampanha(@RequestBody CampanhaDTO dto) throws DataInvalida, TituloInvalidoException, CriadorInvalidoException, DescricaoInvalidaException, MetaInvalidaException {
        return ResponseEntity.ok(campanhas.adicionarCampanha(dto));
    }

    @PutMapping("/mudar/{tipo}/{id}")
    public ResponseEntity<CampanhaDTO> mudarCampanha(@PathVariable("tipo") String tipo, @PathVariable("id") long id, @RequestBody CampanhaDTO dto) throws Exception {
        return ResponseEntity.ok(campanhas.mudar(tipo, dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CampanhaDTO> deletarCampanha(@PathVariable long id){
        return ResponseEntity.ok(campanhas.removerCampanha(id));
    }


}
