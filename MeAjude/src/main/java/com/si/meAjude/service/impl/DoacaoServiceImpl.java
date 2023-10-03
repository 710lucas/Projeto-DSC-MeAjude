package com.si.meAjude.service.impl;

import com.si.meAjude.models.Doacao;
import com.si.meAjude.repositories.DoacaoRepository;
import com.si.meAjude.service.DoacaoService;
import com.si.meAjude.service.dtos.doacao.DoacaoDTO;
import com.si.meAjude.service.dtos.doacao.DoacaoUpdateDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public DoacaoDTO update(DoacaoUpdateDTO doacaoUpdate) {
        Doacao doacaoLocalizada = doacaoRepository.getById(doacaoUpdate.id());
        Doacao doacaoAtualizada = doacaoUpdate.updateDoacao(doacaoLocalizada);
        return new DoacaoDTO(doacaoRepository.save(doacaoAtualizada));
    }

    @Override
    public DoacaoDTO logicDelete(Long id) {
        Doacao doacao = doacaoRepository.getById(id);
        doacao.delete();
        return new DoacaoDTO(doacaoRepository.save(doacao));
    }

    @Override
    public DoacaoDTO delete(Long id) {
        Doacao doacao = doacaoRepository.getById(id);
        doacaoRepository.delete(doacao);
        return new DoacaoDTO(doacao);
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
    public Page<DoacaoDTO> getAllByDeletadoFalse(Pageable page) {
        return doacaoRepository.findAllByDeletadoFalse(page).map(DoacaoDTO::new);
    }

    @Override
    public Page<DoacaoDTO> getByUserId(Pageable page, Long id) {
        return doacaoRepository.findAllByUserId(page, id).map(DoacaoDTO::new);
    }

    @Override
    public Page<DoacaoDTO> getByData(Pageable page, LocalDate data) {
        return doacaoRepository.findAllByData(page, data).map(DoacaoDTO::new);
    }

    @Override
    public Page<DoacaoDTO> getByCampanhaId(Pageable page, Long id) {
        return doacaoRepository.findAllByCampanhaId(page, id).map(DoacaoDTO::new);
    }

    @Override
    public Page<DoacaoDTO> getByDataAndUserId(Pageable page, LocalDate data, Long id) {
        return doacaoRepository.findAllByDataAndUserId(page, data, id).map(DoacaoDTO::new);
    }

    @Override
    public Page<DoacaoDTO> getByDataAndCampanhaId(Pageable page, LocalDate data, Long id) {
        return doacaoRepository.findAllByDataAndCampanhaId(page, data, id).map(DoacaoDTO::new);
    }

    @Override
    public Page<DoacaoDTO> getByDataAndUserIdAndCampanhaId(Pageable page, LocalDate data, Long usuarioId, Long campanhaId) {
        return doacaoRepository. findAllByDataAndUserIdAndCampanhaId(page, data, usuarioId, campanhaId).map(DoacaoDTO::new);
    }

    @Override
    public Page<DoacaoDTO> getByUsuarioIdAndCampanhaId(Pageable page, Long userId, Long campanhaId) {
        return doacaoRepository.findAllByUsuarioIdAndCampanhaId(page, userId, campanhaId).map(DoacaoDTO::new);
    }
}
