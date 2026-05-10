package com.felipebrito.surebet_api.model;


import lombok.Data;

@Data
public class MarketOdds {
    private String bookmakerName;
    private double homeOdds;
    public double drawOdds;
    public double awayOdds;
}
