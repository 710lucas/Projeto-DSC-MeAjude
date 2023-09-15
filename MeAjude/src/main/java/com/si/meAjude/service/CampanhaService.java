package com.si.meAjude.service;


import com.si.meAjude.exceptions.*;
import com.si.meAjude.models.Campanha;
import com.si.meAjude.models.DataInvalida;
import com.si.meAjude.models.Doacao;
import com.si.meAjude.models.Usuario;
import com.si.meAjude.repositories.CampanhaRepository;
import com.si.meAjude.service.dtos.CampanhaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CampanhaService {

    private CampanhaRepository campanhas;

    @Autowired
    public CampanhaService(CampanhaRepository campanhas){
        this.campanhas = campanhas;
    }

    public CampanhaDTO adicionarCampanha(CampanhaDTO dto) throws DataInvalida, TituloInvalidoException, CriadorInvalidoException, DescricaoInvalidaException, MetaInvalidaException {
        Campanha c = new Campanha(dto.getCriador(), dto.getTitulo(), dto.getDescricao(), dto.getMeta(), dto.getDataFinal());
        campanhas.save(c);
        return dto;
    }

    public CampanhaDTO removerCampanha(long id){
        Campanha c = campanhas.findById(id).get();
        CampanhaDTO dto = new CampanhaDTO(c);
        campanhas.delete(c);
        return dto;
    }

    public CampanhaDTO mudar(String tipo, CampanhaDTO dto, long id) throws Exception {
        if(campanhas.findById(id).get().getDataFinal().isAfter(LocalDateTime.now())){
            switch(tipo){
                case "estado":
                    return mudarEstado(dto.isAtiva(), id);
                case "titulo":
                    return mudarTitulo(dto.getTitulo(), id);
                case "descricao":
                    return mudarDescricao(dto.getDescricao(), id);
                case "meta":
                    return mudarMeta(dto.getMeta(), id);
                case "data-final":
                    return mudarDataFinal(dto.getDataFinal(), id);
                case "criador":
                    return mudarCriador(dto.getCriador(), id);
                default:
                    break;
            }
        }
        throw new Exception("Houve um erro ao mudar a campanha");
    }

    public CampanhaDTO getCampanha(Long id){
        return new CampanhaDTO(campanhas.findById(id).get());
    }

    public CampanhaDTO mudarEstado(boolean estado, long id){
        Campanha c = campanhas.findById(id).get();
        c.setAtiva(estado);
        campanhas.save(c);
        return new CampanhaDTO(c);
    }

    public CampanhaDTO mudarTitulo(String titulo, long id) throws TituloInvalidoException {
        Campanha c = campanhas.findById(id).get();
        c.setTitulo(titulo);
        campanhas.save(c);
        return new CampanhaDTO(c);
    }

    public CampanhaDTO mudarDescricao(String descricao, long id) throws DescricaoInvalidaException {
        Campanha c = campanhas.findById(id).get();
        c.setDescricao(descricao);
        campanhas.save(c);
        return new CampanhaDTO(c);
    }

    public CampanhaDTO mudarMeta(double meta, long id) throws MetaInvalidaException {
        Campanha c = campanhas.findById(id).get();
        c.setMeta(meta);
        campanhas.save(c);
        return new CampanhaDTO(c);
    }

    public CampanhaDTO mudarDataFinal(LocalDateTime dataFinal, long id) throws DataInvalida {
        Campanha c = campanhas.findById(id).get();
        c.setDataFinal(dataFinal);
        campanhas.save(c);
        return new CampanhaDTO(c);
    }

    public CampanhaDTO mudarCriador(Usuario criador, long id) throws CriadorInvalidoException{
        Campanha c = campanhas.findById(id).get();
        c.setCriador(criador);
        campanhas.save(c);
        return new CampanhaDTO(c);
    }

    public CampanhaDTO adicionarDoacao(Doacao doacao, long id) throws DoacaoInvalidaException {
        Campanha c = campanhas.findById(id).get();
        c.adicionarDoacao(doacao);
        campanhas.save(c);
        return new CampanhaDTO(c);
    }

}
