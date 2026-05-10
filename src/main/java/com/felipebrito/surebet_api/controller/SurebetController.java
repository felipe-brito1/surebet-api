package com.felipebrito.surebet_api.controller;

import com.felipebrito.surebet_api.model.FixtureOdds;
import com.felipebrito.surebet_api.model.MarketOdds;
import com.felipebrito.surebet_api.model.SurebetOportunity;
import com.felipebrito.surebet_api.service.SurebetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SurebetController {

    private final SurebetService surebetService;

    public SurebetController(SurebetService surebetService) {
        this.surebetService = surebetService;
    }

    @GetMapping("/odds/{fixtureId}")
    public FixtureOdds fixtureId(@PathVariable String fixtureId) {
        return surebetService.getOdds(fixtureId);
    }

    @GetMapping("/odds/{fixtureId}/markets")
    public List<MarketOdds> marketOddsList(@PathVariable String fixtureId){
        FixtureOdds getOdds = surebetService.getOdds(fixtureId);
        return surebetService.marketOddsList(getOdds);
    }
    @GetMapping("/odds/{fixtureId}/surebet")
    public List<SurebetOportunity> findSurebets(@PathVariable String fixtureId) {
        return surebetService.findSurebets(fixtureId);
    }
}
