package com.tendril.cleanweb.server.resource;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.tendril.cleanweb.client.GenabilityClient;
import com.tendril.cleanweb.client.TendrilClient;
import com.tendril.cleanweb.domain.Fight;
import com.tendril.cleanweb.domain.genability.TariffInput;
import com.tendril.cleanweb.domain.tendril.ConsumptionComponent;
import com.tendril.cleanweb.domain.tendril.CostAndConsumption;
import com.tendril.cleanweb.server.ConsumptionConverter;

@Path("/fight")
public class FightResource {

	@Inject
	GenabilityClient genabilityClient;

	@Inject
	TendrilClient tendrilClient;

	@GET
	public Fight fight(@HeaderParam("custom-auth") String auth,
			@QueryParam("myTariffId") Long myTariffId,
			@QueryParam("comparisonTariffId") Long comparisonTariffId) {
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
				.asList(new ConsumptionComponent[] { consumptionComponent1,
						consumptionComponent2 }));

		// CostAndConsumption costAndConsumption =
		// tendrilClient.getConsumption(auth);

		List<TariffInput> tariffInputs = ConsumptionConverter
				.convert(costAndConsumption);

		Fight fight = new Fight();
		double localScore = costAndConsumption.getConsumption()
				/ genabilityClient.getCost(myTariffId, tariffInputs);
		fight.setLocalScore((int) localScore);
		double comparisonScore = genabilityClient.getCost(comparisonTariffId,
				tariffInputs);
		fight.setComparisonScore((int) comparisonScore);

		return fight;
	}

}
