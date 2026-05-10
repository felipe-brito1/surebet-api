package com.felipebrito.surebet_api.service;

import com.felipebrito.surebet_api.client.OddsApiClient;
import org.springframework.stereotype.Service;

@Service
public class SurebetService {
    private final OddsApiClient oddsApiClient;

    public SurebetService(OddsApiClient oddsApiClient) {
        this.oddsApiClient = oddsApiClient;
    }

    public String getOdds(String fixtureId){
        return oddsApiClient.getOdds(fixtureId);
    }
}
