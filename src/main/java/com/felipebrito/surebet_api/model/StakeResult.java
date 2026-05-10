package com.felipebrito.surebet_api.model;


import lombok.Data;

@Data
public class StakeResult {
    private double stakeHome;
    private double stakeDraw;
    private double stakeAway;
    private double profit;
    private double totalInvested;
    private double returnAmount;
}
