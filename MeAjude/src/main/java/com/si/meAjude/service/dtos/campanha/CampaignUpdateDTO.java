package com.si.meAjude.service.dtos.campanha;

import jakarta.annotation.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    LocalDateTime startingDate,
    @Nullable
    LocalDateTime finalDate,
    @Nullable
    Long creatorId
){}
