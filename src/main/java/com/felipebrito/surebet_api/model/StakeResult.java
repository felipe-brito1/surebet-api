package com.felipebrito.surebet_api.model;


import lombok.Data;

import java.util.Map;

@Data
public class StakeResult {
    private Map<String, Double> stakes;
    private Map<String, String> bookmakers;
    private double profit;
    private double totalInvested;
    private double returnAmount;
    private String marketName;
    private String homeTeam;
    private String awayTeam;
}