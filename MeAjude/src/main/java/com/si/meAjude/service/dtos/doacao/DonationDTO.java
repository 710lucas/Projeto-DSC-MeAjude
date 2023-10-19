package com.si.meAjude.service.dtos.doacao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.si.meAjude.models.Donation;
import com.si.meAjude.service.dtos.campanha.CampanhaDTO;
import com.si.meAjude.service.dtos.usuario.UserDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record DonationDTO(UserDTO userDTO, @JsonBackReference CampanhaDTO campanhaDTO, LocalDate data, BigDecimal valorDoado) {
    public DonationDTO(Donation donation){
        this(new UserDTO(donation.getUser()), new CampanhaDTO(donation.getCampaign()), donation.getDate(), donation.getDonationValue());
    }

    public static List<DonationDTO> doacaoToDTO(List<Donation> donationList){
        return donationList.stream().map(DonationDTO::new).collect(Collectors.toList());
    }
}
