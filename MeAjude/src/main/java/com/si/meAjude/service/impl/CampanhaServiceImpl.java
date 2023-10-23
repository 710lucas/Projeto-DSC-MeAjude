package com.si.meAjude.service.impl;


import com.si.meAjude.exceptions.*;
import com.si.meAjude.models.Campanha;
import com.si.meAjude.models.User;
import com.si.meAjude.models.enums.CriterioEnum;
import com.si.meAjude.repositories.CampanhaRepository;
import com.si.meAjude.repositories.DonationRepository;
import com.si.meAjude.repositories.UserRepository;
import com.si.meAjude.service.CampanhaService;
import com.si.meAjude.service.dtos.campanha.CampanhaDTO;
import com.si.meAjude.service.dtos.campanha.CampanhaUpdateDTO;
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
public class CampanhaServiceImpl implements CampanhaService{

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DonationRepository doacaoRepository;

    public CampanhaDTO save(CampanhaDTO campanhaDTO){
        Campanha campanha = new Campanha();
        campanha.setCriador(userRepository.getById(campanhaDTO.criadorId()));
        campanha.setAtiva(campanhaDTO.ativa());
        campanha.setTitulo(campanhaDTO.titulo());
        campanha.setDescricao(campanhaDTO.descricao());
        campanha.setDeletado(campanhaDTO.deletado());
        campanha.setMeta(campanhaDTO.meta());
        campanha.setDataFinal(campanhaDTO.dataFinal());
        campanha.setDataInicio((campanhaDTO.dataInicio()));
        campanha.setDoacoes(campanhaDTO.doacoes());
        campanha.setValorArrecadado(campanhaDTO.valorArrecadado());
        campanha.setDeletado(campanhaDTO.deletado());
        return new CampanhaDTO(campanhaRepository.save(campanha));
    }

    @Override
    public CampanhaDTO update(CampanhaUpdateDTO campanhaDTO) throws DataInvalida, MetaInvalidaException, DescricaoInvalidaException, TituloInvalidoException {

        if(campanhaDTO.id() == null)
            throw new RuntimeException("Id informado ao mudar classe é inválido");

        Campanha c = campanhaRepository.getById(campanhaDTO.id());

        if(campanhaDTO.ativa() != null && campanhaDTO.ativa() != c.isAtiva()) mudarEstado(campanhaDTO.ativa(), campanhaDTO.id());
        if(campanhaDTO.dataFinal() != null && !campanhaDTO.dataFinal().equals(c.getDataFinal())) mudarDataFinal(campanhaDTO.dataFinal(), campanhaDTO.id());
        if(campanhaDTO.meta() != null && !campanhaDTO.meta().equals(c.getMeta())) mudarMeta(campanhaDTO.meta(), campanhaDTO.id());
        if(campanhaDTO.descricao() != null && !campanhaDTO.descricao().equals(c.getDescricao())) mudarDescricao(campanhaDTO.descricao(), campanhaDTO.id());
        if(campanhaDTO.titulo() != null && !campanhaDTO.titulo().equals(c.getTitulo())) mudarTitulo(campanhaDTO.titulo(), campanhaDTO.id());

        return new CampanhaDTO(c);
    }

    @Override
    public CampanhaDTO removerCampanha(long id) {
        Campanha c = campanhaRepository.getById(id);
        if(c == null)
            throw new RuntimeException("Campanha com id: "+id+" não existe");
        c.setDeletado(true);
        campanhaRepository.save(c);
        return new CampanhaDTO(c);
    }

    @Override
    public CampanhaDTO getCampanha(Long id) {
        if(!campanhaRepository.existsById(id))
            throw new RuntimeException("Campanha de id: "+id+" não existe");
        return new CampanhaDTO(campanhaRepository.getById(id));
    }

    @Override
    public CampanhaDTO mudarEstado(boolean estado, long id) {
        System.out.printf("Setando estado de id: "+id+" para "+estado);
        if(!campanhaRepository.existsById(id))
            throw new RuntimeException("Campanha de id: "+id+" não existe");
        Campanha c = campanhaRepository.getById(id);
        c.setAtiva(estado);
        campanhaRepository.save(c);
        return new CampanhaDTO(c);
    }


    @Override
    public CampanhaDTO mudarTitulo(String titulo, long id) throws TituloInvalidoException {
        if(titulo.length() > 100 || titulo.trim().isEmpty())
            throw new TituloInvalidoException("Titulo informado é inválido");
        else if(!campanhaRepository.existsById(id))
            throw new TituloInvalidoException("Campanha de id: "+id+" não existe");
        Campanha c = campanhaRepository.getById(id);
        c.setTitulo(titulo);
        campanhaRepository.save(c);
        return new CampanhaDTO(c);
    }

    @Override
    public CampanhaDTO mudarDescricao(String descricao, long id) throws DescricaoInvalidaException {
        if(descricao.length() > 1000)
            throw new DescricaoInvalidaException("A descrição informada é inválida");
        else if(!campanhaRepository.existsById(id))
            throw new RuntimeException("Campanha de id: "+id+" não existe");
        Campanha c = campanhaRepository.getById(id);
        c.setDescricao(descricao);
        campanhaRepository.save(c);
        return new CampanhaDTO(c);
    }

    @Override
    public CampanhaDTO mudarMeta(BigDecimal meta, long id) throws MetaInvalidaException {
        if(meta.doubleValue() <= 0)
            throw new MetaInvalidaException("O valod não pode ser menor ou igual a zero");
        else if(!campanhaRepository.existsById(id))
            throw new RuntimeException("Campanha de id: "+id+" não existe");
        Campanha c = campanhaRepository.getById(id);
        c.setMeta(meta);
        campanhaRepository.save(c);
        return new CampanhaDTO(c);
    }

    @Override
    public CampanhaDTO mudarDataFinal(LocalDateTime dataFinal, long id) throws DataInvalida {
        if(!campanhaRepository.existsById(id))
            throw new RuntimeException("Campanha de id: "+id+" não existe");
        Campanha c = campanhaRepository.getById(id);
        if(dataFinal.isBefore(c.getDataInicio()) || dataFinal.isEqual(c.getDataInicio()))
            throw new DataInvalida("A data informada deve ser depois da data de inicio da campanha");
        c.setDataFinal(dataFinal);
        campanhaRepository.save(c);
        return new CampanhaDTO(c);
    }

    @Override
    public CampanhaDTO mudarCriador(long criadorId, long id) throws CriadorInvalidoException {
        if(!campanhaRepository.existsById(id))
            throw new RuntimeException("Campanha de id: "+id+" não existe");
        Campanha c = campanhaRepository.getById(id);
        User criador = userRepository.getById(criadorId);
        if(criador == null || criador.isDeleted())
            throw new CriadorInvalidoException("O criador informado é inválido");
        c.setCriador(criador);
        campanhaRepository.save(c);
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

        long quantidadeElementos = quantidade.orElse(campanhaRepository.count());
        if(quantidadeElementos == -1) quantidadeElementos = campanhaRepository.count();
        int quantidadePaginas = 1;
        if(quantidadeElementos > Integer.MAX_VALUE) quantidadePaginas = (int)(quantidadeElementos/Integer.MAX_VALUE);

        List<Campanha> campanhas = new ArrayList<>();

        for(int i = 0; i<quantidadePaginas; i++){
            PageRequest paginaAtual = PageRequest.of(i, (int)(quantidadeElementos/quantidadePaginas));
            Page<Campanha> campanhasPagina = campanhaRepository.findAll(paginaAtual);
            campanhas.addAll(campanhasPagina.getContent());
        }

        return new ListaCampanhasDTO(campanhas, criterio);
    }


}
