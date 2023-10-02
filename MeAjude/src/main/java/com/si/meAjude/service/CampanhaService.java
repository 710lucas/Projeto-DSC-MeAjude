package com.si.meAjude.service;

import com.si.meAjude.exceptions.*;
import com.si.meAjude.models.Doacao;
import com.si.meAjude.models.Usuario;
import com.si.meAjude.service.dtos.campanha.CampanhaDTO;
import com.si.meAjude.service.dtos.campanha.ListaCampanhasDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public interface CampanhaService{

    CampanhaDTO removerCampanha(long id);

    CampanhaDTO mudar(String tipo, CampanhaDTO dto, long id) throws Exception;

    CampanhaDTO getCampanha(Long id);

    CampanhaDTO mudarEstado(boolean estado, long id);

    CampanhaDTO mudarTitulo(String titulo, long id) throws TituloInvalidoException;

    CampanhaDTO mudarDescricao(String descricao, long id) throws DescricaoInvalidaException;

    CampanhaDTO mudarMeta(BigDecimal meta, long id) throws MetaInvalidaException;

    CampanhaDTO mudarDataFinal(LocalDateTime dataFinal, long id) throws DataInvalida;
    CampanhaDTO mudarCriador(Usuario criador, long id) throws CriadorInvalidoException;

    CampanhaDTO adicionarDoacao(Doacao doacao, long id) throws DoacaoInvalidaException;

    CampanhaDTO adicionarCampanha(CampanhaDTO dto) throws DataInvalida, TituloInvalidoException, CriadorInvalidoException, DescricaoInvalidaException, MetaInvalidaException;

    ListaCampanhasDTO listarCampanhas(Optional<Long> quantidade, String criterioString) throws CriterioInvalidoException;



}
