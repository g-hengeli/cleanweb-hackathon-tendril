package com.tendril.cleanweb.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@XmlRootElement
public class CalculateInput {

	private String appId;
	private String appKey;
	private String fromDateTime;
	private String toDateTime;
	private Long territoryId;
	private String detailLevel;
	private List<TariffInput> tariffInputs;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getFromDateTime() {
		return fromDateTime;
	}

	public void setFromDateTime(String fromDateTime) {
		this.fromDateTime = fromDateTime;
	}

	public String getToDateTime() {
		return toDateTime;
	}

	public void setToDateTime(String toDateTime) {
		this.toDateTime = toDateTime;
	}

	public Long getTerritoryId() {
		return territoryId;
	}

	public void setTerritoryId(Long territoryId) {
		this.territoryId = territoryId;
	}

	public String getDetailLevel() {
		return detailLevel;
	}

	public void setDetailLevel(String detailLevel) {
		this.detailLevel = detailLevel;
	}

	public List<TariffInput> getTariffInputs() {
		return tariffInputs;
	}

	public void setTariffInputs(List<TariffInput> tariffInputs) {
		this.tariffInputs = tariffInputs;
	}

}
