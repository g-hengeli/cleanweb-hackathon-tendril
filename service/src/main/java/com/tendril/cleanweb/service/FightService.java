package com.tendril.cleanweb.service;

import com.tendril.cleanweb.client.GenabilityClient;
import com.tendril.cleanweb.client.TendrilClient;
import com.tendril.cleanweb.domain.Fight;
import com.tendril.cleanweb.domain.genability.TariffInput;
import com.tendril.cleanweb.domain.tendril.ConsumptionComponent;
import com.tendril.cleanweb.domain.tendril.CostAndConsumption;
import com.tendril.cleanweb.server.ConsumptionConverter;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

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

    public Fight createFight(Long tariffId1, Long tariffId2) {

		ConsumptionComponent consumptionComponent1 = new ConsumptionComponent();
		consumptionComponent1.setFromDate("2011-09-01T00:00:00.0-0700");
		consumptionComponent1.setToDate("2011-10-01T00:00:00.0-0700");
		consumptionComponent1.setConsumption(100.0d);

		ConsumptionComponent consumptionComponent2 = new ConsumptionComponent();
		consumptionComponent2.setFromDate("2011-10-01T00:00:00.0-0700");
		consumptionComponent2.setToDate("2011-11-01T00:00:00.0-0700");
		consumptionComponent2.setConsumption(120.0d);

		CostAndConsumption costAndConsumption = new CostAndConsumption();
		costAndConsumption.setComponent(Arrays
				.asList(new ConsumptionComponent[]{consumptionComponent1,
                        consumptionComponent2}));

		List<TariffInput> tariffInputs = ConsumptionConverter
				.convert(costAndConsumption);

		Fight fight = new Fight();
		double localScore = costAndConsumption.getConsumption()
				/ genabilityClient.getCost(tariffId1, tariffInputs);

		fight.setLocalScore((int) localScore);
		double comparisonScore = genabilityClient.getCost(tariffId2, tariffInputs);
		fight.setComparisonScore((int) comparisonScore);

        return fight;
    }
}
