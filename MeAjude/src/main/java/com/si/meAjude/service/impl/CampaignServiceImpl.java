package com.si.meAjude.service.impl;


import com.si.meAjude.exceptions.*;
import com.si.meAjude.models.Campaign;
import com.si.meAjude.models.User;
import com.si.meAjude.models.enums.CriterioEnum;
import com.si.meAjude.repositories.CampaignRepository;
import com.si.meAjude.repositories.DonationRepository;
import com.si.meAjude.repositories.UserRepository;
import com.si.meAjude.service.CampaignService;
import com.si.meAjude.service.dtos.campanha.CampanhaDTO;
import com.si.meAjude.service.dtos.campanha.ListaCampanhasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DonationRepository donationRepository;

    public CampanhaDTO save(CampanhaDTO campanhaDTO){
        Campaign campaign = new Campaign();
        campaign.setCriador(userRepository.getById(campanhaDTO.criadorId()));
        campaign.setAtiva(campanhaDTO.ativa());
        campaign.setTitulo(campanhaDTO.titulo());
        campaign.setDescricao(campanhaDTO.descricao());
        campaign.setDeletado(campanhaDTO.deletado());
        campaign.setMeta(campanhaDTO.meta());
        campaign.setDataFinal(campanhaDTO.dataFinal());
        campaign.setDataInicio((campanhaDTO.dataInicio()));
        campaign.setDoacoes(campanhaDTO.doacoes());
        campaign.setValorArrecadado(campanhaDTO.valorArrecadado());
        campaign.setDeletado(campanhaDTO.deletado());
        return new CampanhaDTO(campaignRepository.save(campaign));
    }

    @Override
    public CampanhaDTO removerCampanha(long id) {
        Campaign c = campaignRepository.getById(id);
        if(c == null)
            throw new RuntimeException("Campanha com id: "+id+" não existe");
        c.setDeletado(true);
        campaignRepository.save(c);
        return new CampanhaDTO(c);
    }

    @Override
    public CampanhaDTO getCampanha(Long id) {
        if(!campaignRepository.existsById(id))
            throw new RuntimeException("Campanha de id: "+id+" não existe");
        return new CampanhaDTO(campaignRepository.getById(id));
    }

    @Override
    public CampanhaDTO mudarEstado(boolean estado, long id) {
        if(!campaignRepository.existsById(id))
            throw new RuntimeException("Campanha de id: "+id+" não existe");
        Campaign c = campaignRepository.getById(id);
        c.setAtiva(estado);
        campaignRepository.save(c);
        return new CampanhaDTO(c);
    }


    @Override
    public CampanhaDTO mudarTitulo(String titulo, long id) throws TituloInvalidoException {
        if(titulo.length() > 100 || titulo.trim().isEmpty())
            throw new TituloInvalidoException("Titulo informado é inválido");
        else if(!campaignRepository.existsById(id))
            throw new TituloInvalidoException("Campanha de id: "+id+" não existe");
        Campaign c = campaignRepository.getById(id);
        c.setTitulo(titulo);
        campaignRepository.save(c);
        return new CampanhaDTO(c);
    }

    @Override
    public CampanhaDTO mudarDescricao(String descricao, long id) throws DescricaoInvalidaException {
        if(descricao.length() > 1000)
            throw new DescricaoInvalidaException("A descrição informada é inválida");
        else if(!campaignRepository.existsById(id))
            throw new RuntimeException("Campanha de id: "+id+" não existe");
        Campaign c = campaignRepository.getById(id);
        c.setDescricao(descricao);
        campaignRepository.save(c);
        return new CampanhaDTO(c);
    }

    @Override
    public CampanhaDTO mudarMeta(BigDecimal meta, long id) throws MetaInvalidaException {
        if(meta.doubleValue() <= 0)
            throw new MetaInvalidaException("O valod não pode ser menor ou igual a zero");
        else if(!campaignRepository.existsById(id))
            throw new RuntimeException("Campanha de id: "+id+" não existe");
        Campaign c = campaignRepository.getById(id);
        c.setMeta(meta);
        campaignRepository.save(c);
        return new CampanhaDTO(c);
    }

    @Override
    public CampanhaDTO mudarDataFinal(LocalDateTime dataFinal, long id) throws DataInvalida {
        if(!campaignRepository.existsById(id))
            throw new RuntimeException("Campanha de id: "+id+" não existe");
        Campaign c = campaignRepository.getById(id);
        if(dataFinal.isBefore(c.getDataInicio()) || dataFinal.isEqual(c.getDataInicio()))
            throw new DataInvalida("A date informada deve ser depois da date de inicio da campanha");
        c.setDataFinal(dataFinal);
        campaignRepository.save(c);
        return new CampanhaDTO(c);
    }

    @Override
    public CampanhaDTO mudarCriador(long criadorId, long id) throws CriadorInvalidoException {
        if(!campaignRepository.existsById(id))
            throw new RuntimeException("Campanha de id: "+id+" não existe");
        Campaign c = campaignRepository.getById(id);
        User criador = userRepository.getById(criadorId);
        if(criador == null || criador.isDeleted())
            throw new CriadorInvalidoException("O criador informado é inválido");
        c.setCriador(criador);
        campaignRepository.save(c);
        return new CampanhaDTO(c);
    }

    @Override
    public CampanhaDTO adicionarCampanha(CampanhaDTO dto)  {
        if(dto == null)
            throw new RuntimeException("Informações da campanha informadas são inválidas");
        return save(dto);
    }

    @Override
    public ListaCampanhasDTO listarCampanhas(Optional<Long> quantidade, Optional<String> criterioString) throws CriterioInvalidoException {

        CriterioEnum criterio = criterioString.map(s -> CriterioEnum.valueOf(s.toUpperCase())).orElse(CriterioEnum.ATIVAS_DATA);

        long quantidadeElementos = quantidade.orElse(campaignRepository.count());
        if(quantidadeElementos == -1) quantidadeElementos = campaignRepository.count();
        int quantidadePaginas = 1;
        if(quantidadeElementos > Integer.MAX_VALUE) quantidadePaginas = (int)(quantidadeElementos/Integer.MAX_VALUE);

        List<Campaign> campaigns = new ArrayList<>();

        for(int i = 0; i<quantidadePaginas; i++){
            PageRequest paginaAtual = PageRequest.of(i, (int)(quantidadeElementos/quantidadePaginas));
            Page<Campaign> campanhasPagina = campaignRepository.findAll(paginaAtual);
            campaigns.addAll(campanhasPagina.getContent());
        }

        return new ListaCampanhasDTO(campaigns, criterio);
    }


}
