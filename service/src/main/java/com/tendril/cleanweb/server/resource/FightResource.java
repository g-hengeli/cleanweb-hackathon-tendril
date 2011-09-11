package com.tendril.cleanweb.server.resource;

import com.tendril.cleanweb.domain.Fight;
import com.tendril.cleanweb.service.FightService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/fight")
public class FightResource {

    @Inject
    FightService fightService;

	@GET
	public Fight fight(@HeaderParam("custom-auth") String auth,
			@QueryParam("myTariffId") Long myTariffId,
			@QueryParam("comparisonTariffId") Long comparisonTariffId) {

        return fightService.createFight(myTariffId, comparisonTariffId);
	}

}
