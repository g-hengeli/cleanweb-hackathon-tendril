package com.tendril.cleanweb.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Fight {

	private int localScore;
	private int comparisonScore;

	public int getLocalScore() {
		return localScore;
	}

	public void setLocalScore(int localScore) {
		this.localScore = localScore;
	}

	public int getComparisonScore() {
		return comparisonScore;
	}

	public void setComparisonScore(int comparisonScore) {
		this.comparisonScore = comparisonScore;
	}

}
