package com.si.meAjude.service;

import com.si.meAjude.exceptions.*;
import com.si.meAjude.models.Campanha;
import com.si.meAjude.models.Doacao;
import com.si.meAjude.models.Usuario;
import com.si.meAjude.service.dtos.CampanhaDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface CampanhaService{

    public CampanhaDTO removerCampanha(long id);

    public CampanhaDTO mudar(String tipo, CampanhaDTO dto, long id) throws Exception;

    public CampanhaDTO getCampanha(Long id);

    public CampanhaDTO mudarEstado(boolean estado, long id);

    public CampanhaDTO mudarTitulo(String titulo, long id) throws TituloInvalidoException;

    public CampanhaDTO mudarDescricao(String descricao, long id) throws DescricaoInvalidaException;

    public CampanhaDTO mudarMeta(double meta, long id) throws MetaInvalidaException;

    public CampanhaDTO mudarDataFinal(LocalDateTime dataFinal, long id) throws DataInvalida;
    public CampanhaDTO mudarCriador(Usuario criador, long id) throws CriadorInvalidoException;

    public Campanha adicionarDoacao(Doacao doacao, long id) throws DoacaoInvalidaException;

    public CampanhaDTO adicionarCampanha(CampanhaDTO dto) throws DataInvalida, TituloInvalidoException, CriadorInvalidoException, DescricaoInvalidaException, MetaInvalidaException;

}
