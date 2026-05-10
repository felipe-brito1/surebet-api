package com.felipebrito.surebet_api.service;


import com.felipebrito.surebet_api.model.StakeResult;
import com.felipebrito.surebet_api.model.SurebetOportunity;
import org.springframework.stereotype.Component;

@Component
public class StakeCalculator {

    public StakeResult calculate(SurebetOportunity opportunity, double total) {
        double margin = opportunity.getMargin();

        double stakeHome = Math.round((total / opportunity.getHomeOdds()) / margin * 100.0) / 100.0;
        double stakeDraw = Math.round((total / opportunity.getDrawOdds()) / margin * 100.0) / 100.0;
        double stakeAway = Math.round((total / opportunity.getAwayOdds()) / margin * 100.0) / 100.0;
        double profit = Math.round((1 / margin - 1) * 10000.0) / 100.0;


        StakeResult result = new StakeResult();
        result.setStakeHome(stakeHome);
        result.setStakeDraw(stakeDraw);
        result.setStakeAway(stakeAway);
        result.setProfit(profit);
        result.setTotalInvested(total);
        result.setReturnAmount(Math.round((total + (total * profit / 100)) * 100.0) / 100.0);

        return result;
    }
}
