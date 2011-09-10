package com.tendril.cleanweb;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.tendril.cleanweb.domain.Tariff;
import com.tendril.cleanweb.domain.TariffList;

public class GenabilityClient {

	private static final String APP_KEY = "3ffccf70d6caa98d680c673fff878af1";
	private static final String APP_ID = "b9c201aa";

	public List<Tariff> getTariffs(String zipCode) {
		List<Tariff> results = new ArrayList<Tariff>();

		Client client = Client.create();
		WebResource webResource = client
				.resource("http://api.genability.com/rest/public/tariffs");

		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		params.add("appId", APP_ID);
		params.add("appKey", APP_KEY);
		params.add("zipCode", zipCode);
		params.add("customerClasses", "RESIDENTIAL");
		params.add("tariffTypes", "DEFAULT");

		TariffList tariffList = webResource.queryParams(params).get(
				TariffList.class);

		if (tariffList.getCount() > 0) {
			for (Tariff tariff : tariffList.getResults()) {
				if (tariff.getIsActive())
					results.add(tariff);
			}
		}

		return results;
	}

}
