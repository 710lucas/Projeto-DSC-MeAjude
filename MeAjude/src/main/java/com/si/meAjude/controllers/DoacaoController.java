package com.si.meAjude.controllers;

import com.si.meAjude.exceptions.DoacaoInvalidaException;
import com.si.meAjude.exceptions.MetaInvalidaException;
import com.si.meAjude.models.Doacao;
import com.si.meAjude.service.DoacaoService;
import com.si.meAjude.service.dtos.DoacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doacao")
public class DoacaoController {
    @Autowired
    DoacaoService doacaoService;

    public DoacaoService getDoacaoService() {
        return doacaoService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Doacao saveDoacao(DoacaoDTO doacaoDTO) throws DoacaoInvalidaException, MetaInvalidaException {
        return doacaoService.saveDoacao(doacaoDTO);
    }
}
