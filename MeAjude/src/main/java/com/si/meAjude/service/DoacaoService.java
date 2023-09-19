package com.si.meAjude.service;



import com.si.meAjude.exceptions.DoacaoInvalidaException;
import com.si.meAjude.exceptions.MetaInvalidaException;
import com.si.meAjude.models.Doacao;
import com.si.meAjude.service.dtos.DoacaoDTO;
import org.springframework.web.server.ResponseStatusException;


public interface DoacaoService {
    public Doacao saveDoacao (DoacaoDTO doacaoDTO) throws DoacaoInvalidaException, MetaInvalidaException;
    public Doacao buscarDoacao(Long id) throws ResponseStatusException;
    public  Doacao buscarDoacaoPelaData(String data) throws ResponseStatusException;
    public  Doacao buscarDoacaoPelaCampanha(String data) throws ResponseStatusException;
    public  Doacao buscarDoacaoPelaUsuario(String data) throws ResponseStatusException;

}
