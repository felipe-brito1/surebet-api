package com.felipebrito.surebet_api.controller;

import com.felipebrito.surebet_api.model.FixtureOdds;
import com.felipebrito.surebet_api.model.MarketOdds;
import com.felipebrito.surebet_api.model.StakeResult;
import com.felipebrito.surebet_api.model.SurebetOportunity;
import com.felipebrito.surebet_api.service.SurebetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
        Map<String, String> outcomeNames = Map.of("101", "home", "102", "draw", "103", "away");
        return surebetService.marketOddsList(getOdds, "101", outcomeNames);

    }
    @GetMapping("/odds/{fixtureId}/surebet")
    public List<SurebetOportunity> findSurebets(@PathVariable String fixtureId) {
        return surebetService.findSurebets(fixtureId);
    }

    @GetMapping("/odds/{fixtureId}/stake")
    public List<StakeResult> calculator(@PathVariable String fixtureId, @RequestParam double total){
        return surebetService.calculateStake(fixtureId, total);
    }
    @GetMapping("/odds/{fixtureId}/btts")
    public List<StakeResult> findBtts(@PathVariable String fixtureId, @RequestParam double total){
        Map<String, String> outcomeNames = Map.of("104", "yes", "105", "no");
        return surebetService.findSurebetsByMarket(fixtureId, "104", outcomeNames, total);
    }
}
