package com.si.meAjude.service;



import com.si.meAjude.service.dtos.doacao.DoacaoDTO;
import com.si.meAjude.service.dtos.doacao.DoacaoSaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DoacaoService {
     DoacaoDTO save (DoacaoSaveDTO doacao);
     DoacaoDTO getById(Long id);
     Page<DoacaoDTO> getAll(Pageable page);
}
