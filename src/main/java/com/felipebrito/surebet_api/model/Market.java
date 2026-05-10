package com.felipebrito.surebet_api.model;

import lombok.Data;
import java.util.Map;



@Data
public class Market {
    private boolean marketActive;
    private Map<String, Outcome> outcomes;
}
