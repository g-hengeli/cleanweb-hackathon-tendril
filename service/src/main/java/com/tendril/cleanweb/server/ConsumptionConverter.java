package com.tendril.cleanweb.server;

import java.util.ArrayList;
import java.util.List;

import com.tendril.cleanweb.domain.genability.TariffInput;
import com.tendril.cleanweb.domain.tendril.ConsumptionComponent;
import com.tendril.cleanweb.domain.tendril.CostAndConsumption;

public class ConsumptionConverter {

	private ConsumptionConverter() {
		throw new AssertionError("this class cannot be instantiated");
	}

	public static List<TariffInput> convert(
			CostAndConsumption costAndConsumption) {
		List<TariffInput> tariffInputs = new ArrayList<TariffInput>();

		for (ConsumptionComponent component : costAndConsumption.getComponent()) {
			TariffInput tariffInput = new TariffInput();

			tariffInput.setKey("consumption");
			tariffInput.setUnit("kwh");
			tariffInput.setFromDateTime(component.getFromDate());
			tariffInput.setToDateTime(component.getToDate());
			tariffInput.setValue(component.getConsumption().toString());

			tariffInputs.add(tariffInput);
		}

		return tariffInputs;
	}
}
