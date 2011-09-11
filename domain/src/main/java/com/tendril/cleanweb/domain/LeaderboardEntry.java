package com.tendril.cleanweb.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlRootElement;

/**
 */
@XmlRootElement
public class LeaderboardEntry {
    private String userId;
    private String zipcode;
    private int score;
    private String tariffName;

    public LeaderboardEntry() {
    }

    public LeaderboardEntry(String userId, String zipcode, int score, String tariffName) {
        this.userId = userId;
        this.zipcode = zipcode;
        this.score = score;
        this.tariffName = tariffName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
