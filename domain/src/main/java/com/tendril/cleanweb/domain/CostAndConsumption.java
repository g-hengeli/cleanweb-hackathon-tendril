package com.tendril.cleanweb.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CostAndConsumption {

	private Double cost;
	private Double consumption;
	private List<ConsumptionComponent> component;

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
