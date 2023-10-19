package com.si.meAjude.service.dtos.doacao;

import jakarta.validation.constraints.NotNull;

public record DonationUpdateDTO(
        @NotNull
        Long id,
        Long usuarioId,
        Long campanhaId) {
}
