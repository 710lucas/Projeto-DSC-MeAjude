package com.si.meAjude.service.impl;

import com.si.meAjude.models.Doacao;
import com.si.meAjude.repositories.CampanhaRepository;
import com.si.meAjude.repositories.DoacaoRepository;
import com.si.meAjude.repositories.UsuarioRepository;
import com.si.meAjude.service.DoacaoService;
import com.si.meAjude.service.dtos.doacao.DoacaoDTO;
import com.si.meAjude.service.dtos.doacao.DoacaoSaveDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class DoacaoServiceImpl implements DoacaoService {
    @Autowired
    private DoacaoRepository doacaoRepository;

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public DoacaoDTO save(DoacaoSaveDTO doacaoDTO) {
        Doacao doacao = new Doacao();
        doacao.setCampanha(campanhaRepository.getById(doacaoDTO.campanhaId()));
        doacao.setUsuario(usuarioRepository.getById(doacaoDTO.usuarioId()));
        doacao.setData(doacaoDTO.data());
        doacao.setValorDoado(doacaoDTO.valorDoado());
        return new DoacaoDTO(doacaoRepository.save(doacao));
    }

    @Override
    public DoacaoDTO getById(Long id) {
        return new DoacaoDTO(doacaoRepository.getById(id));
    }

    @Override
    public Page<DoacaoDTO> getAll(Pageable page) {
        return doacaoRepository.findAll(page).map(DoacaoDTO::new);
    }

    @Override
    public DoacaoDTO logicDelete(Long id) {
        Doacao doacao = doacaoRepository.getById(id);
        doacao.delete();
        return new DoacaoDTO(doacaoRepository.save(doacao));
    }
}
