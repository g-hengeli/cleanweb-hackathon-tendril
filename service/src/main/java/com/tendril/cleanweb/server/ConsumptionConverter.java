package com.tendril.cleanweb.server;

import com.tendril.cleanweb.domain.genability.TariffInput;
import com.tendril.cleanweb.domain.tendril.ConsumptionComponent;
import com.tendril.cleanweb.domain.tendril.CostAndConsumption;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

public class ConsumptionConverter {

	private ConsumptionConverter() {
		throw new AssertionError("this class cannot be instantiated");
	}

	public static List<TariffInput> convert(
			CostAndConsumption costAndConsumption) {
		List<TariffInput> tariffInputs = new ArrayList<TariffInput>();

		for (ConsumptionComponent component : costAndConsumption
				.getComponentList().getComponent()) {
			TariffInput tariffInput = new TariffInput();

			tariffInput.setKey("consumption");
			tariffInput.setUnit("kwh");
			tariffInput.setFromDateTime(ISODateTimeFormat.dateTime().print(
					new DateTime(component.getFromDate())));
			tariffInput.setToDateTime(ISODateTimeFormat.dateTime().print(
					new DateTime(component.getToDate())));
			tariffInput.setValue(component.getConsumption().toString());

			tariffInputs.add(tariffInput);
		}

		return tariffInputs;
	}
}
