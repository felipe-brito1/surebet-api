package com.felipebrito.surebet_api.service;

import com.felipebrito.surebet_api.client.OddsApiClient;
import com.felipebrito.surebet_api.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SurebetService {
    private final OddsApiClient oddsApiClient;
    private final SurebetCalculator surebetCalculator;
    private final StakeCalculator stakeCalculator;

    public SurebetService(OddsApiClient oddsApiClient, SurebetCalculator surebetCalculator, StakeCalculator stakeCalculator) {
        this.oddsApiClient = oddsApiClient;
        this.surebetCalculator = surebetCalculator;
        this.stakeCalculator = stakeCalculator;
    }

    public FixtureOdds getOdds(String fixtureId) {
        return oddsApiClient.getOdds(fixtureId);
    }

    public List<MarketOdds> marketOddsList(FixtureOdds fixtureOdds, String marketId, Map<String, String> outcomeNames) {
        List<String> allowedBookmakers = List.of("bet365", "betano", "estrelabet", "kto", "superbet", "pinnacle", "blaze", "meridianbet", "betnacional", "betfair");
        List<MarketOdds> result = new ArrayList<>();

        for (Map.Entry<String, BookmakerOdds> entry : fixtureOdds.getBookmakerOdds().entrySet()) {
            String bookmakerName = entry.getKey();
            if (!allowedBookmakers.contains(bookmakerName)) continue;

            BookmakerOdds bookmakerOdds = entry.getValue();
            Market market = bookmakerOdds.getMarkets().get(marketId);
            if (market == null || !market.isMarketActive()) continue;

            Map<String, Double> outcomes = new HashMap<>();
            boolean valid = true;

            for (Map.Entry<String, String> outcomeEntry : outcomeNames.entrySet()) {
                String outcomeId = outcomeEntry.getKey();
                String outcomeName = outcomeEntry.getValue();
                Outcome outcome = market.getOutcomes().get(outcomeId);
                if (outcome == null || outcome.getPlayers().get("0") == null) { valid = false; break; }
                outcomes.put(outcomeName, outcome.getPlayers().get("0").getPrice());
            }

            if (!valid) continue;

            MarketOdds marketOdds = new MarketOdds();
            marketOdds.setBookmakerName(bookmakerName);
            marketOdds.setOutcomes(outcomes);
            result.add(marketOdds);
        }

        return result;
    }

    public List<SurebetOportunity> findSurebets(String fixtureId) {
        FixtureOdds fixtureOdds = getOdds(fixtureId);
        Map<String, String> outcomeNames = Map.of("101", "home", "102", "draw", "103", "away");
        List<MarketOdds> marketOdds = marketOddsList(fixtureOdds, "101", outcomeNames);
        return surebetCalculator.surebetCalculator(marketOdds);
    }

    public List<StakeResult> calculateStake(String fixtureId, double total) {
        List<SurebetOportunity> surebets = findSurebets(fixtureId);
        List<StakeResult> result = new ArrayList<>();
        for (SurebetOportunity opportunity : surebets) {
            result.add(stakeCalculator.calculate(opportunity, total));
        }
        return result;
    }
}