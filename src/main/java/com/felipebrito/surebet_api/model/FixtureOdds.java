package com.felipebrito.surebet_api.model;

import lombok.Data;
import java.util.Map;

@Data
public class FixtureOdds {
    private String fixtureId;
    private String participant1Name;
    private String participant2Name;
    private Map<String, BookmakerOdds> bookmakerOdds;
}
