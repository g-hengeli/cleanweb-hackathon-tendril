package com.tendril.cleanweb.domain.tendril;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConsumptionComponent {

	private String fromDate;
	private String toDate;
	private Double cost;
	private Double consumption;

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
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

}
