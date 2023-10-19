package com.si.meAjude.service;

import com.si.meAjude.exceptions.*;
import com.si.meAjude.service.dtos.campanha.CampanhaDTO;
import com.si.meAjude.service.dtos.campanha.ListaCampanhasDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public interface CampaignService {

    CampanhaDTO removerCampanha(long id);


    CampanhaDTO getCampanha(Long id);

    CampanhaDTO mudarEstado(boolean estado, long id);

    CampanhaDTO mudarTitulo(String titulo, long id) throws TituloInvalidoException;

    CampanhaDTO mudarDescricao(String descricao, long id) throws DescricaoInvalidaException;

    CampanhaDTO mudarMeta(BigDecimal meta, long id) throws MetaInvalidaException;

    CampanhaDTO mudarDataFinal(LocalDateTime dataFinal, long id) throws DataInvalida;
    CampanhaDTO mudarCriador(long criador_id, long id) throws CriadorInvalidoException;

    CampanhaDTO adicionarCampanha(CampanhaDTO dto) throws DataInvalida, TituloInvalidoException, CriadorInvalidoException, DescricaoInvalidaException, MetaInvalidaException;

    ListaCampanhasDTO listarCampanhas(Optional<Long> quantidade, Optional<String> criterioString) throws CriterioInvalidoException;



}
