package com.si.meAjude.service;



import com.si.meAjude.models.Doacao;
import com.si.meAjude.service.dtos.doacao.DoacaoDTO;
import com.si.meAjude.service.dtos.doacao.DoacaoUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;


public interface DoacaoService {
     DoacaoDTO save (Doacao doacao);
     DoacaoDTO getById(Long id);
     Page<DoacaoDTO> getAll(Pageable page);
     DoacaoDTO update(DoacaoUpdateDTO doacaoUpdate);
     DoacaoDTO logicDelete(Long id);
     DoacaoDTO delete(Long id);
     Page<DoacaoDTO> getByDataAndUserIdAndCampanhaId(Pageable page, LocalDate data, Long usuarioId, Long campanhaId);
     Page<DoacaoDTO> getByDataAndUserId(Pageable page, LocalDate data, Long id);
     Page<DoacaoDTO> getByDataAndCampanhaId(Pageable page, LocalDate data, Long id);
     Page<DoacaoDTO> getByData(Pageable page, LocalDate data);
     Page<DoacaoDTO> getByUserId(Pageable page, Long id);
     Page<DoacaoDTO> getByCampanhaId(Pageable page, Long id);
     Page<DoacaoDTO> getByUsuarioIdAndCampanhaId(Pageable page, Long usuarioId, Long campanhaId);
     Page<DoacaoDTO> getAllByDeletadoFalse(Pageable page);
}
