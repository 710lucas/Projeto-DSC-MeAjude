package com.si.meAjude.service.dtos.campaign;

import jakarta.annotation.Nullable;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CampaignUpdateDTO(
    @Nullable
    Long id,
    @Nullable
    String title,
    BigDecimal goal,
    @Nullable
    Boolean active,
    @Nullable
    String description,
    @Nullable
    LocalDate startingDate,
    @Nullable
    LocalDate finalDate,
    @Nullable
    Long creatorId
){}
