package com.felipebrito.surebet_api.model;

import lombok.Data;

import java.util.Map;

@Data
public class SurebetOportunity {
    private Map<String, String> bookmakers;
    private Map<String, Double> outcomes;
    private double margin;
}
