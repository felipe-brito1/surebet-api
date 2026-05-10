package com.felipebrito.surebet_api.service;


import com.felipebrito.surebet_api.model.StakeResult;
import com.felipebrito.surebet_api.model.SurebetOportunity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StakeCalculator {

    public StakeResult calculate(SurebetOportunity opportunity, double total) {
        double margin = opportunity.getMargin();
        double profit = Math.round((1 / margin - 1) * 10000.0) / 100.0;

        Map<String, Double> stakes = new HashMap<>();
        for (Map.Entry<String, Double> entry : opportunity.getOutcomes().entrySet()) {
            double stake = Math.round((total / entry.getValue()) / margin * 100.0) / 100.0;
            stakes.put(entry.getKey(), stake);
        }

        StakeResult result = new StakeResult();
        result.setStakes(stakes);
        result.setBookmakers(opportunity.getBookmakers());
        result.setProfit(profit);
        result.setTotalInvested(total);
        result.setReturnAmount(Math.round((total + (total * profit / 100)) * 100.0) / 100.0);

        return result;
    }
}