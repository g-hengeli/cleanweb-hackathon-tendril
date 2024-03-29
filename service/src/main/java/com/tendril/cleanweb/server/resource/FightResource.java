package com.tendril.cleanweb.server.resource;

import com.tendril.cleanweb.domain.Fight;
import com.tendril.cleanweb.service.FightService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;

@Path("/fight")
public class FightResource {

	@Inject
	FightService fightService;

	@GET
	public Fight fight(@HeaderParam("custom-auth") String auth,
			@MatrixParam("myTariffId") Long myTariffId,
			@MatrixParam("comparisonTariffId") Long comparisonTariffId) {
		String[] credentials = auth.split(":");

		return fightService.createFight(credentials[0], credentials[1],
				myTariffId, comparisonTariffId);
	}

}
