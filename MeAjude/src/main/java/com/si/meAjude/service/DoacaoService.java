package com.si.meAjude.service;



import com.si.meAjude.models.Doacao;
import com.si.meAjude.service.dtos.doacao.DoacaoDTO;
import com.si.meAjude.service.dtos.doacao.DoacaoUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface DoacaoService {
     DoacaoDTO save (Doacao doacao);
     DoacaoDTO getById(Long id);
     Page<DoacaoDTO> getAll(Pageable page);
     DoacaoDTO update(DoacaoUpdateDTO doacaoUpdate);
     DoacaoDTO logicDelete(Long id);
     DoacaoDTO delete(Long id);
     Page<DoacaoDTO> getByData(Pageable page, String data);
     Page<DoacaoDTO> getByUserId(Pageable page, Long id);
     Page<DoacaoDTO> getByCampanhaId(Pageable page, Long id);
}
