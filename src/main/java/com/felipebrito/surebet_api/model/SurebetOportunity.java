package com.felipebrito.surebet_api.model;

import lombok.Data;

@Data
public class SurebetOportunity {
    private String bookmakerHome;
    private String bookmakerDraw;
    private String bookmakerAway;
    private double homeOdds;
    private double drawOdds;
    private double awayOdds;
    private double margin ;
}
