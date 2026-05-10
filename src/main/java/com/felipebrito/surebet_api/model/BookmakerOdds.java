package com.felipebrito.surebet_api.model;

import lombok.Data;
import java.util.Map;

@Data
public class BookmakerOdds {
    private boolean bookmakerIsActive;
    private boolean suspended;
    private Map<String, Market> markets;
}
