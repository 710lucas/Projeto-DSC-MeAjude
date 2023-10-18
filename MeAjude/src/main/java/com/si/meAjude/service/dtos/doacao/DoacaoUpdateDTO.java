package com.si.meAjude.service.dtos.doacao;

import com.si.meAjude.models.Campanha;
import com.si.meAjude.models.Doacao;
import com.si.meAjude.models.Usuario;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DoacaoUpdateDTO(
        @NotNull
        Long id,
        Long usuarioId,
        Long campanhaId) {
}
