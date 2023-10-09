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
    Page<Doacao> findAllByUsuario_Id(Pageable page, Long userId);
    Page<Doacao> findAllByCampanhaId(Pageable page, Long id);
    Page<Doacao> findAllByUsuarioIdAndCampanhaId(Pageable page, Long userId, Long campanhaId);
    Page<Doacao> findAllByDataAndUsuario_Id(Pageable pageable, LocalDate data, Long userId);
    Page<Doacao> findAllByDataAndCampanhaId(Pageable page, LocalDate date, Long id);
    Page<Doacao> findAllByDataAndUsuario_IdAndCampanha_Id(Pageable page, LocalDate date, Long userId, Long campanhaId);
}
