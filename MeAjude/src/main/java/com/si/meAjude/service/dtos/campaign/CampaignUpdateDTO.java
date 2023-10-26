package com.si.meAjude.service.dtos.campaign;

import jakarta.annotation.Nullable;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CampaignUpdateDTO(
    @Nullable
    Boolean active,

    @Nullable
    String title,

    @Nullable
    String description,

    @Nullable
    BigDecimal goal,

    @Nullable
    LocalDate finalDate,

    @Nullable
    LocalDate startingDate,

    @Nullable
    Long creatorId,

    @Nullable
    BigDecimal raisedMoney,

    @Nullable
    Boolean deleted
){}
