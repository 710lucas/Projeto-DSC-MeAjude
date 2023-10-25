package com.si.meAjude.models.comparators;
import com.si.meAjude.service.dtos.campaign.CampaignGetDTO;

import java.util.Comparator;

public class TituloComparator implements Comparator<CampaignGetDTO> {
    @Override
    public int compare(CampaignGetDTO o1, CampaignGetDTO o2) {
        return o1.title().toLowerCase().compareTo(o2.title().toLowerCase());
    }
}
