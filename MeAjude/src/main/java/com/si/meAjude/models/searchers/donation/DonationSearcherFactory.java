package com.si.meAjude.models.searchers.donation;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component
public class DonationSearcherFactory {

    private HashMap<DonationSearchCriterion, DonationSearcher> searchers = new HashMap<>();

    public DonationSearcherFactory(Set<DonationSearcher> searchers){
        searchers.forEach(searcher -> this.searchers.put(searcher.getCriterion(), searcher));
    }

    public DonationSearcher getSearcher(DonationSearchCriterion criterion){
        return searchers.get(criterion);
    }

}
