package com.si.meAjude.service.dtos.doacao;

import com.si.meAjude.models.Doacao;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DoacaoSaveDTO(
        Long usuarioId,
        Long campanhaId,
        LocalDate data,
        @Min(value = 1, message = "Valor da doação deve ser maior que 0")
        BigDecimal valorDoado) {
}
