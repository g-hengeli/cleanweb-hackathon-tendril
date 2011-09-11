package com.tendril.cleanweb.service;

import java.util.List;

import javax.inject.Inject;

import com.tendril.cleanweb.client.GenabilityClient;
import com.tendril.cleanweb.client.TendrilClient;
import com.tendril.cleanweb.domain.Fight;
import com.tendril.cleanweb.domain.genability.TariffInput;
import com.tendril.cleanweb.domain.tendril.CostAndConsumption;
import com.tendril.cleanweb.server.ConsumptionConverter;

/**
 */
public class FightService {

	private TendrilClient tendrilClient;
	private GenabilityClient genabilityClient;

	@Inject
	public FightService(TendrilClient tendrilClient,
			GenabilityClient genabilityClient) {
		this.tendrilClient = tendrilClient;
		this.genabilityClient = genabilityClient;
	}

	public Fight createFight(String username, String password, Long tariffId1,
			Long tariffId2) {
		CostAndConsumption costAndConsumption = tendrilClient.getConsumption(
				username, password);

		List<TariffInput> tariffInputs = ConsumptionConverter
				.convert(costAndConsumption);

		double localScore = costAndConsumption.getConsumption()
				/ genabilityClient.getCost(tariffId1, tariffInputs);
		double comparisonScore = costAndConsumption.getConsumption()
				/ genabilityClient.getCost(tariffId2, tariffInputs);

		Fight fight = new Fight();
		fight.setLocalScore((int) localScore);
		fight.setComparisonScore((int) comparisonScore);
		return fight;
	}
}
