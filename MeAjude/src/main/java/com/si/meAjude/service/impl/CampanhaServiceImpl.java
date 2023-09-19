package com.si.meAjude.service.impl;


import com.si.meAjude.exceptions.*;
import com.si.meAjude.models.Campanha;
import com.si.meAjude.models.Doacao;
import com.si.meAjude.models.Usuario;
import com.si.meAjude.repositories.CampanhaRepository;
import com.si.meAjude.service.CampanhaService;
import com.si.meAjude.service.dtos.CampanhaDTO;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CampanhaServiceImpl implements CampanhaService {

    @Autowired
    private CampanhaRepository campanhaRepository;

    public CampanhaDTO adicionarCampanha(CampanhaDTO dto) throws DataInvalida, TituloInvalidoException, CriadorInvalidoException, DescricaoInvalidaException, MetaInvalidaException {
        Campanha campanhaNova = new Campanha(dto.getCriador(), dto.getTitulo(), dto.getDescricao(), dto.getMeta(), dto.getDataFinal());
        campanhaRepository.save(campanhaNova);
        return dto;
    }

    public CampanhaDTO removerCampanha(long id){
        Campanha campanhaLocalizada = campanhaRepository.findById(id).get();
        CampanhaDTO campanhaDTO = new CampanhaDTO(campanhaLocalizada);
        campanhaRepository.delete(campanhaLocalizada);
        return campanhaDTO;
    }

    public CampanhaDTO mudar(String tipo, CampanhaDTO dto, long id) throws Exception {
        if(campanhaRepository.findById(id).get().getDataFinal().isAfter(LocalDateTime.now())){
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
        return new CampanhaDTO(campanhaRepository.findById(id).get());
    }

    public CampanhaDTO mudarEstado(boolean estado, long id){
        Campanha campanhaLocalizada = campanhaRepository.findById(id).get();
        if(estado)campanhaLocalizada.ativar();
        else campanhaLocalizada.desAtivar();
        campanhaRepository.save(campanhaLocalizada);
        return new CampanhaDTO(campanhaLocalizada);
    }

    public CampanhaDTO mudarTitulo(String titulo, long id) throws TituloInvalidoException {
        Campanha c = campanhaRepository.findById(id).get();
        c.setTitulo(titulo);
        campanhaRepository.save(c);
        return new CampanhaDTO(c);
    }

    public CampanhaDTO mudarDescricao(String descricao, long id) throws DescricaoInvalidaException {
        Campanha c = campanhaRepository.findById(id).get();
        c.setDescricao(descricao);
        campanhaRepository.save(c);
        return new CampanhaDTO(c);
    }

    public CampanhaDTO mudarMeta(double meta, long id) throws MetaInvalidaException {
        Campanha c = campanhaRepository.findById(id).get();
        c.setMeta(meta);
        campanhaRepository.save(c);
        return new CampanhaDTO(c);
    }

    public CampanhaDTO mudarDataFinal(LocalDateTime dataFinal, long id) throws DataInvalida {
        Campanha c = campanhaRepository.findById(id).get();
        c.setDataFinal(dataFinal);
        campanhaRepository.save(c);
        return new CampanhaDTO(c);
    }

    public CampanhaDTO mudarCriador(Usuario criador, long id) throws CriadorInvalidoException{
        Campanha c = campanhaRepository.findById(id).get();
        c.setCriador(criador);
        campanhaRepository.save(c);
        return new CampanhaDTO(c);
    }

    @Override
    @Transactional
    public Campanha adicionarDoacao(Doacao doacao, long campanhaId) throws DoacaoInvalidaException {
        Campanha campanha = campanhaRepository.findById(campanhaId)
                .orElseThrow(() -> new DoacaoInvalidaException("Campanha não encontrada"));
        Hibernate.initialize(campanha.getDoacoes());
        campanha.adicionarDoacao(doacao);
        campanhaRepository.save(campanha);
        return campanha;
    }

}
