package com.si.meAjude.service.dtos.doacao;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DonationSaveDTO(
        @NotNull
        Long usuarioId,
        @NotNull
        Long campanhaId,
        @NotNull
        LocalDate data,
        @NotNull
        @Min(value = 1)
        BigDecimal valorDoado) {
}
