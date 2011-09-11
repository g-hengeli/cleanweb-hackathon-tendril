package com.tendril.cleanweb.service;

import com.tendril.cleanweb.client.GenabilityClient;
import com.tendril.cleanweb.client.TendrilClient;
import com.tendril.cleanweb.domain.Fight;
import com.tendril.cleanweb.domain.tendril.CostAndConsumption;

import javax.inject.Inject;

/**
 */
public class FightService {

    private TendrilClient tendrilClient;
    private GenabilityClient genabilityClient;

    @Inject
    public FightService(TendrilClient tendrilClient, GenabilityClient genabilityClient) {
        this.tendrilClient = tendrilClient;
        this.genabilityClient = genabilityClient;
    }

    public Fight createFight() {
        CostAndConsumption cc = tendrilClient.getCostAndConsumption();
        Double cost = genabilityClient.getCost();

        return null;
    }
}
