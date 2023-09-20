package com.si.meAjude.models.comparators;

import com.si.meAjude.service.dtos.CampanhaDTO;

import java.util.Comparator;

public class DataComparator implements Comparator<CampanhaDTO> {
    @Override
    public int compare(CampanhaDTO o1, CampanhaDTO o2) {
        return o1.getDataInicio().compareTo(o2.getDataInicio());
    }
}
