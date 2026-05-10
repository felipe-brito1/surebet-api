package com.felipebrito.surebet_api.service;

import com.felipebrito.surebet_api.model.MarketOdds;
import com.felipebrito.surebet_api.model.SurebetOportunity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SurebetCalculator {

    public List<SurebetOportunity> surebetCalculator(List<MarketOdds> marketOdds){
        List<SurebetOportunity> result = new ArrayList<>();

        for (MarketOdds home : marketOdds) {
            for (MarketOdds draw : marketOdds) {
                for (MarketOdds away : marketOdds) {
                    double margin = (1.0 / home.getHomeOdds()) + (1.0 / draw.getDrawOdds()) + (1.0 / away.getAwayOdds());
                    if(margin < 1) {
                        SurebetOportunity opportunity = new SurebetOportunity();
                        opportunity.setBookmakerHome(home.getBookmakerName());
                        opportunity.setBookmakerDraw(draw.getBookmakerName());
                        opportunity.setBookmakerAway(away.getBookmakerName());
                        opportunity.setHomeOdds(home.getHomeOdds());
                        opportunity.setDrawOdds(draw.getDrawOdds());
                        opportunity.setAwayOdds(away.getAwayOdds());
                        opportunity.setMargin(margin);
                        result.add(opportunity);
                    }
                }
            }
        }

        return result;
    }
}
