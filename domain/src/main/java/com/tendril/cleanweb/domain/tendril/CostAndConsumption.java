package com.tendril.cleanweb.domain.tendril;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@XmlRootElement
public class CostAndConsumption {

	private Double cost;
	private Double consumption;
	private List<ConsumptionComponent> component;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getConsumption() {
		return consumption;
	}

	public void setConsumption(Double consumption) {
		this.consumption = consumption;
	}

	public List<ConsumptionComponent> getComponent() {
		return component;
	}

	public void setComponent(List<ConsumptionComponent> component) {
		this.component = component;
	}

}
