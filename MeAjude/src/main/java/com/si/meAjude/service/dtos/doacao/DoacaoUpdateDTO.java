package com.si.meAjude.service.dtos.doacao;

import com.si.meAjude.models.Campanha;
import com.si.meAjude.models.Doacao;
import com.si.meAjude.models.Usuario;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DoacaoUpdateDTO(
        Long id,
        Usuario usuario,
        Campanha campanha,
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate data,
        BigDecimal valorDoado) {

        public Doacao updateDoacao(Doacao doacao){
                return updateUsuario(this, doacao);
        }

        private Doacao updateUsuario(DoacaoUpdateDTO doacaoUpdateDTO, Doacao doacao){
                if(doacaoUpdateDTO.campanha != null) doacao.setCampanha(doacaoUpdateDTO.campanha);
                if(doacaoUpdateDTO.data != null) doacao.setData(doacaoUpdateDTO.data);
                if(doacaoUpdateDTO.valorDoado != null) doacao.setValorDoado(doacaoUpdateDTO.valorDoado);
                return doacao;
        }
}
