package com.si.meAjude.service.dtos.campanha;

import jakarta.annotation.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CampanhaUpdateDTO (
    @Nullable
    Long id,
    @Nullable
    String titulo,
    BigDecimal meta,
    @Nullable
    Boolean ativa,
    @Nullable
    String descricao,
    @Nullable
    LocalDateTime dataInicio,
    @Nullable
    LocalDateTime dataFinal,
    @Nullable
    Long criadorId
){}
