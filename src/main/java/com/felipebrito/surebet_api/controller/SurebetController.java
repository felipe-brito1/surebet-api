package com.felipebrito.surebet_api.controller;

import com.felipebrito.surebet_api.service.SurebetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SurebetController {

    private final SurebetService surebetService;

    public SurebetController(SurebetService surebetService) {
        this.surebetService = surebetService;
    }

    @GetMapping("/odds/{fixtureId}")
    public String fixtureId(@PathVariable String fixtureId) {
        return surebetService.getOdds(fixtureId);

    }
}
