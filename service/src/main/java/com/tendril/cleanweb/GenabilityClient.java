package com.tendril.cleanweb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.tendril.cleanweb.domain.CalculateInput;
import com.tendril.cleanweb.domain.CalculatedCost;
import com.tendril.cleanweb.domain.Tariff;
import com.tendril.cleanweb.domain.TariffInput;
import com.tendril.cleanweb.domain.TariffList;

public class GenabilityClient {

	private static final String APP_ID = "b9c201aa";
	private static final String APP_KEY = "3ffccf70d6caa98d680c673fff878af1";

	public List<Tariff> getTariffs(String zipCode) {
		List<Tariff> results = new ArrayList<Tariff>();

		WebResource webResource = Client.create().resource(
				"http://api.genability.com/rest/public/tariffs");

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

	public double getCost() {
		double result = 0.0d;

		int masterTariffId = 26531;

		WebResource webResource = Client.create().resource(
				"http://api.genability.com/rest/alpha/calculate/"
						+ masterTariffId);

		CalculateInput calculateInput = new CalculateInput();
		calculateInput.setAppId(APP_ID);
		calculateInput.setAppKey(APP_KEY);
		calculateInput.setFromDateTime("2011-09-01T00:00:00.0-0700");
		calculateInput.setToDateTime("2011-11-01T00:00:00.0-0700");
		calculateInput.setTerritoryId(1009L);
		calculateInput.setDetailLevel("TOTAL");

		TariffInput tariffInput = new TariffInput();
		tariffInput.setKey("consumption");
		tariffInput.setUnit("kwh");
		tariffInput.setFromDateTime("2011-09-01T00:00:00.0-0700");
		tariffInput.setToDateTime("2011-10-01T00:00:00.0-0700");
		tariffInput.setValue("100");

		TariffInput tariffInput2 = new TariffInput();
		tariffInput2.setKey("consumption");
		tariffInput2.setUnit("kwh");
		tariffInput2.setFromDateTime("2011-10-01T00:00:00.0-0700");
		tariffInput2.setToDateTime("2011-11-01T00:00:00.0-0700");
		tariffInput2.setValue("100");

		calculateInput.setTariffInputs(Arrays.asList(new TariffInput[] {
				tariffInput, tariffInput2 }));

		CalculatedCost calculatedCost = webResource.type(
				MediaType.APPLICATION_JSON_TYPE).post(CalculatedCost.class,
				calculateInput);

		if (calculatedCost.getResults() != null
				&& calculatedCost.getResults().size() > 0) {
			result = calculatedCost.getResults().get(0).getTotalCost();
		}

		return result;
	}
}
