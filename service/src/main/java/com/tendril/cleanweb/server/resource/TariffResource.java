package com.tendril.cleanweb.server.resource;

import com.tendril.cleanweb.client.GenabilityClient;
import com.tendril.cleanweb.domain.genability.Tariff;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 */
@Path("/tariff")
public class TariffResource {

	@Inject
    GenabilityClient genabilityClient;

	@GET
	public List<Tariff> getTariffs(@QueryParam("zipcode") String zipCode) {
		return genabilityClient.getTariffs(zipCode);
	}

}
