package com.felipebrito.surebet_api.service;

import com.felipebrito.surebet_api.model.MarketOdds;
import com.felipebrito.surebet_api.model.SurebetOportunity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SurebetCalculator {

    public List<SurebetOportunity> surebetCalculator(List<MarketOdds> marketOdds) {
        List<SurebetOportunity> result = new ArrayList<>();
        if (marketOdds.isEmpty()) return result;

        List<String> outcomeKeys = new ArrayList<>(marketOdds.get(0).getOutcomes().keySet());

        findCombinations(marketOdds, outcomeKeys, 0, new HashMap<>(), new HashMap<>(), result);
        return result;
    }

    private void findCombinations(List<MarketOdds> marketOdds, List<String> outcomeKeys, int index,
                                  Map<String, String> bookmakers, Map<String, Double> odds,
                                  List<SurebetOportunity> result) {
        if (index == outcomeKeys.size()) {
            double margin = odds.values().stream().mapToDouble(odd -> 1.0 / odd).sum();
            if (margin < 1) {
                SurebetOportunity opportunity = new SurebetOportunity();
                opportunity.setBookmakers(new HashMap<>(bookmakers));
                opportunity.setOutcomes(new HashMap<>(odds));
                opportunity.setMargin(margin);
                result.add(opportunity);
            }
            return;
        }

        String outcomeKey = outcomeKeys.get(index);
        for (MarketOdds market : marketOdds) {
            bookmakers.put(outcomeKey, market.getBookmakerName());
            odds.put(outcomeKey, market.getOutcomes().get(outcomeKey));
            findCombinations(marketOdds, outcomeKeys, index + 1, bookmakers, odds, result);
        }
        bookmakers.remove(outcomeKey);
        odds.remove(outcomeKey);
    }
}