package com.si.meAjude.models.comparators;

import com.si.meAjude.service.dtos.campanha.CampaignGetDTO;

import java.util.Comparator;

public class DataComparator implements Comparator<CampaignGetDTO> {
    @Override
    public int compare(CampaignGetDTO o1, CampaignGetDTO o2) {
        return o1.startingDate().compareTo(o2.startingDate());
    }
}
