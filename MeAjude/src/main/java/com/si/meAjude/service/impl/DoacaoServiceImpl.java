package com.si.meAjude.service.impl;

import com.si.meAjude.models.Doacao;
import com.si.meAjude.repositories.DoacaoRepository;
import com.si.meAjude.service.DoacaoService;
import com.si.meAjude.service.dtos.doacao.DoacaoDTO;
import com.si.meAjude.service.dtos.doacao.DoacaoUpdateDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class DoacaoServiceImpl implements DoacaoService {
    @Autowired
    private DoacaoRepository doacaoRepository;

    @Override
    public DoacaoDTO save(Doacao doacao) {
        return new DoacaoDTO(doacaoRepository.save(doacao));
    }

    @Override
    public DoacaoDTO getById(Long id) {
        return new DoacaoDTO(doacaoRepository.getById(id));
    }

    @Override
    public Page<DoacaoDTO> getAll(Pageable page) {
        return null;
    }

    @Override
    public DoacaoDTO update(DoacaoUpdateDTO doacaoUpdate) {
        return null;
    }

    @Override
    public DoacaoDTO logicDelete(Long id) {
        return null;
    }

    @Override
    public DoacaoDTO delete(Long id) {
        return null;
    }

    @Override
    public Page<DoacaoDTO> getByData(Pageable page, String data) {
        return null;
    }

    @Override
    public Page<DoacaoDTO> getByUserId(Pageable page, Long id) {
        return null;
    }

    @Override
    public Page<DoacaoDTO> getByCampanhaId(Pageable page, Long id) {
        return null;
    }
}
