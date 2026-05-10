package com.felipebrito.surebet_api.service;

import com.felipebrito.surebet_api.client.OddsApiClient;
import com.felipebrito.surebet_api.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SurebetService {
    private final OddsApiClient oddsApiClient;

    public SurebetService(OddsApiClient oddsApiClient) {
        this.oddsApiClient = oddsApiClient;
    }

    public FixtureOdds getOdds(String fixtureId) {
        return oddsApiClient.getOdds(fixtureId);
    }

    public List<MarketOdds> marketOddsList(FixtureOdds fixtureOdds) {
        List<MarketOdds> result = new ArrayList<>();
        for (Map.Entry<String, BookmakerOdds> entry : fixtureOdds.getBookmakerOdds().entrySet()) {
            String bookmakerName = entry.getKey();
            BookmakerOdds bookmakerOdds = entry.getValue();
            Market market101 = bookmakerOdds.getMarkets().get("101");
            if (market101 != null && market101.isMarketActive()) {
                Outcome home = market101.getOutcomes().get("101");
                Outcome draw = market101.getOutcomes().get("102");
                Outcome away = market101.getOutcomes().get("103");

                if (home == null || draw == null || away == null) continue;

                double homeOdds = home.getPlayers().get("0").getPrice();
                double drawOdds = draw.getPlayers().get("0").getPrice();
                double awayOdds = away.getPlayers().get("0").getPrice();
                MarketOdds marketOdds = new MarketOdds();
                marketOdds.setBookmakerName(bookmakerName);
                marketOdds.setHomeOdds(homeOdds);
                marketOdds.setDrawOdds(drawOdds);
                marketOdds.setAwayOdds(awayOdds);
                result.add(marketOdds);
                marketOdds.setAwayOdds(awayOdds);
            }



        }
        return result;
    }



}


