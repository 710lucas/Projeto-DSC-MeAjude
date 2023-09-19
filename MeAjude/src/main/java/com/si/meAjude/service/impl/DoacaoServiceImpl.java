package com.si.meAjude.service.impl;

import com.si.meAjude.exceptions.DoacaoInvalidaException;
import com.si.meAjude.exceptions.MetaInvalidaException;
import com.si.meAjude.models.Campanha;
import com.si.meAjude.models.Doacao;
import com.si.meAjude.models.Usuario;
import com.si.meAjude.repositories.CampanhaRepository;
import com.si.meAjude.repositories.DoacaoRepository;
import com.si.meAjude.repositories.UsuarioRepository;
import com.si.meAjude.service.DoacaoService;
import com.si.meAjude.service.dtos.DoacaoDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;


@AllArgsConstructor
@Service
public class DoacaoServiceImpl implements DoacaoService {
    @Autowired
    private DoacaoRepository doacaoRepository;
    @Autowired
    private CampanhaRepository campanhaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CampanhaServiceImpl campanhaService;



    @Override
    public Doacao saveDoacao(DoacaoDTO doacaoDTO) throws MetaInvalidaException {
        Usuario usuario = usuarioRepository.findById(doacaoDTO.UserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado para esse ID:" + doacaoDTO.UserId()));
        Campanha campanha = campanhaRepository.findById(doacaoDTO.CampanhaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Campanha não encontrada para esse ID:" + doacaoDTO.CampanhaId()));
        if (verificarMeta(campanha,doacaoDTO.valorDoado())){
            throw new MetaInvalidaException("Meta da campanha já batida, que tal doar para outra Campanha ??");
        } else {
            Doacao doacao = new Doacao(usuario,campanha,LocalDate.now(),new BigDecimal(doacaoDTO.valorDoado()));
            try {
                campanhaService.adicionarDoacao(doacao,doacaoDTO.CampanhaId());
                doacaoRepository.save(doacao);
            }catch (DoacaoInvalidaException doacaoInvalidaException){
                doacaoInvalidaException.getMessage();
            }
            return doacao;
        }

    }



    public boolean verificarMeta(Campanha campanha, Double valorDoado){
        Double meta = campanha.getMeta();
        if(meta >= valorDoado){
            return false;
        } return true;
    }

    @Override
    public Doacao buscarDoacao(Long id) throws ResponseStatusException {
        return null;
    }

    @Override
    public Doacao buscarDoacaoPelaData(String data) throws ResponseStatusException {
        return null;
    }

    @Override
    public Doacao buscarDoacaoPelaCampanha(String data) throws ResponseStatusException {
        return null;
    }

    @Override
    public Doacao buscarDoacaoPelaUsuario(String data) throws ResponseStatusException {
        return null;
    }
}
