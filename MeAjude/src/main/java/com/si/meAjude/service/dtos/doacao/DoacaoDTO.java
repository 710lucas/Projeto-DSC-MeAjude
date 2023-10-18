package com.si.meAjude.service.dtos.doacao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.si.meAjude.models.Doacao;
import com.si.meAjude.service.dtos.campanha.CampanhaDTO;
import com.si.meAjude.service.dtos.usuario.UsuarioDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record DoacaoDTO(UsuarioDTO userDTO, @JsonBackReference CampanhaDTO campanhaDTO, LocalDate data, BigDecimal valorDoado) {
    public DoacaoDTO(Doacao doacao){
        this(new UsuarioDTO(doacao.getUsuario()), new CampanhaDTO(doacao.getCampanha()), doacao.getData(), doacao.getValorDoado());
    }

    public static List<DoacaoDTO> doacaoToDTO(List<Doacao> doacaoList){
        return doacaoList.stream().map(DoacaoDTO::new).collect(Collectors.toList());
    }
}
