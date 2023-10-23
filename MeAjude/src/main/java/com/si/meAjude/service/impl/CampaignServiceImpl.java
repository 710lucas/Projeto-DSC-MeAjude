package com.si.meAjude.service.impl;


import com.si.meAjude.exceptions.*;
import com.si.meAjude.models.Campaign;
import com.si.meAjude.models.User;
import com.si.meAjude.models.enums.CriterioEnum;
import com.si.meAjude.repositories.CampaignRepository;
import com.si.meAjude.repositories.DonationRepository;
import com.si.meAjude.repositories.UserRepository;
import com.si.meAjude.service.CampaignService;
import com.si.meAjude.service.dtos.campanha.CampaignDTO;
import com.si.meAjude.service.dtos.campanha.CampaignUpdateDTO;
import com.si.meAjude.service.dtos.campanha.CampaignListDTO;
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

    public CampaignDTO save(CampaignDTO campaignDTO){
        Campaign campanha = new Campaign();
        campanha.setCreator(userRepository.getById(campaignDTO.creatorId()));
        campanha.setActive(campaignDTO.active());
        campanha.setTitle(campaignDTO.title());
        campanha.setDescription(campaignDTO.description());
        campanha.setDeleted(campaignDTO.deleted());
        campanha.setGoal(campaignDTO.goal());
        campanha.setFinalDate(campaignDTO.finalDate());
        campanha.setStartingDate((campaignDTO.startingDate()));
        campanha.setDonations(campaignDTO.donations());
        campanha.setRaisedMoney(campaignDTO.raisedMoney());
        campanha.setDeleted(campaignDTO.deleted());
        return new CampaignDTO(campaignRepository.save(campanha));
    }

    @Override
    public CampaignDTO update(CampaignUpdateDTO campaign) throws InvalidDateException, InvalidGoalException, InvalidDescriptionException, InvalidTitleException, InvalidCreatorException {

        if(campaign.id() == null)
            throw new RuntimeException("Id informado ao mudar classe é inválido");

        Campaign c = campaignRepository.getById(campaign.id());

        if(campaign.active() != null && campaign.active() != c.isActive()) changeState(campaign.active(), campaign.id());
        if(campaign.finalDate() != null && !campaign.finalDate().equals(c.getFinalDate())) changeFinalDate(campaign.finalDate(), campaign.id());
        if(campaign.goal() != null && !campaign.goal().equals(c.getGoal())) changeGoal(campaign.goal(), campaign.id());
        if(campaign.description() != null && !campaign.description().equals(c.getDescription())) changeDescription(campaign.description(), campaign.id());
        if(campaign.title() != null && !campaign.title().equals(c.getTitle())) changeTitle(campaign.title(), campaign.id());
        if(campaign.creatorId() != null && !campaign.creatorId().equals(c.getCreator().getId())) changeCreator(campaign.creatorId(), campaign.id());

        return new CampaignDTO(c);
    }

    @Override
    public CampaignDTO removeCampaign(long id) {
        Campaign c = campaignRepository.getById(id);
        if(c == null)
            throw new RuntimeException("Campanha com id: "+id+" não existe");
        c.setDeleted(true);
        campaignRepository.save(c);
        return new CampaignDTO(c);
    }

    @Override
    public CampaignDTO getCampaign(Long id) {
        if(!campaignRepository.existsById(id))
            throw new RuntimeException("Campanha de id: "+id+" não existe");
        return new CampaignDTO(campaignRepository.getById(id));
    }

    @Override
    public CampaignDTO changeState(boolean state, long id) {
        System.out.printf("Setando estado de id: "+id+" para "+ state);
        if(!campaignRepository.existsById(id))
            throw new RuntimeException("Campanha de id: "+id+" não existe");
        Campaign c = campaignRepository.getById(id);
        c.setActive(state);
        campaignRepository.save(c);
        return new CampaignDTO(c);
    }


    @Override
    public CampaignDTO changeTitle(String title, long id) throws InvalidTitleException {
        if(title.length() > 100 || title.trim().isEmpty())
            throw new InvalidTitleException("Titulo informado é inválido");
        else if(!campaignRepository.existsById(id))
            throw new InvalidTitleException("Campanha de id: "+id+" não existe");
        Campaign c = campaignRepository.getById(id);
        c.setTitle(title);
        campaignRepository.save(c);
        return new CampaignDTO(c);
    }

    @Override
    public CampaignDTO changeDescription(String description, long id) throws InvalidDescriptionException {
        if(description.length() > 1000)
            throw new InvalidDescriptionException("A descrição informada é inválida");
        else if(!campaignRepository.existsById(id))
            throw new RuntimeException("Campanha de id: "+id+" não existe");
        Campaign c = campaignRepository.getById(id);
        c.setDescription(description);
        campaignRepository.save(c);
        return new CampaignDTO(c);
    }

    @Override
    public CampaignDTO changeGoal(BigDecimal goal, long id) throws InvalidGoalException {
        if(goal.doubleValue() <= 0)
            throw new InvalidGoalException("O valod não pode ser menor ou igual a zero");
        else if(!campaignRepository.existsById(id))
            throw new RuntimeException("Campanha de id: "+id+" não existe");
        Campaign c = campaignRepository.getById(id);
        c.setGoal(goal);
        campaignRepository.save(c);
        return new CampaignDTO(c);
    }

    @Override
    public CampaignDTO changeFinalDate(LocalDateTime finalDate, long id) throws InvalidDateException {
        if(!campaignRepository.existsById(id))
            throw new RuntimeException("Campanha de id: "+id+" não existe");
        Campaign c = campaignRepository.getById(id);
        if(finalDate.isBefore(c.getStartingDate()) || finalDate.isEqual(c.getStartingDate()))
            throw new InvalidDateException("A data informada deve ser depois da data de inicio da campanha");
        c.setFinalDate(finalDate);
        campaignRepository.save(c);
        return new CampaignDTO(c);
    }

    @Override
    public CampaignDTO changeCreator(long creator_id, long id) throws InvalidCreatorException {
        if(!campaignRepository.existsById(id))
            throw new RuntimeException("Campanha de id: "+id+" não existe");
        Campaign c = campaignRepository.getById(id);
        User criador = userRepository.getById(creator_id);
        if(criador == null || criador.isDeleted())
            throw new InvalidCreatorException("O criador informado é inválido");
        c.setCreator(criador);
        campaignRepository.save(c);
        return new CampaignDTO(c);
    }

    @Override
    public CampaignDTO addCampaign(CampaignDTO dto)  {
        if(dto == null)
            throw new RuntimeException("Informações da campanha informadas são inválidas");
        return save(dto);
    }

    @Override
    public CampaignListDTO listCampaign(Optional<Long> amount, Optional<String> filterString) throws CriterioInvalidoException {

        CriterioEnum criterio = filterString.map(s -> CriterioEnum.valueOf(s.toUpperCase())).orElse(CriterioEnum.ATIVAS_DATA);

        long quantidadeElementos = amount.orElse(campaignRepository.count());
        if(quantidadeElementos == -1) quantidadeElementos = campaignRepository.count();
        int quantidadePaginas = 1;
        if(quantidadeElementos > Integer.MAX_VALUE) quantidadePaginas = (int)(quantidadeElementos/Integer.MAX_VALUE);

        List<Campaign> campanhas = new ArrayList<>();

        for(int i = 0; i<quantidadePaginas; i++){
            PageRequest paginaAtual = PageRequest.of(i, (int)(quantidadeElementos/quantidadePaginas));
            Page<Campaign> campanhasPagina = campaignRepository.findAll(paginaAtual);
            campanhas.addAll(campanhasPagina.getContent());
        }

        return new CampaignListDTO(campanhas, criterio);
    }


}
