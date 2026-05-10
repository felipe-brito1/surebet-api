package com.felipebrito.surebet_api.model;


import lombok.Data;

import java.util.Map;

@Data
public class MarketOdds {
    private String bookmakerName;
    private Map<String, Double> outcomes;
}
