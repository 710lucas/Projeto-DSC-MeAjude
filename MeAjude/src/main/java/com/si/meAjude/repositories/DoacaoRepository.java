package com.si.meAjude.repositories;

import com.si.meAjude.models.Doacao;
import com.si.meAjude.service.dtos.doacao.DoacaoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
    Page<Doacao> findAllByDeletadoFalse(Pageable page);
    Page<Doacao> findAll(Pageable page);
    Page<Doacao> findAllByData(Pageable page, LocalDate date);
    Page<DoacaoDTO> findAllByUserId(Pageable page, Long id);
    Page<DoacaoDTO> findAllByCampanhaId(Pageable page, Long id);
}
