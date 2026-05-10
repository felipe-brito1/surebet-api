package com.felipebrito.surebet_api.model;


import lombok.Data;
import java.util.Map;

@Data
public class Outcome {
    private Map<String, Player> players;
}
