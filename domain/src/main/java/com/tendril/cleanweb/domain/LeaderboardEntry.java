package com.tendril.cleanweb.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 */
@XmlRootElement
public class LeaderboardEntry {
    private String zipcode;
    private int score;
    private String tariffName;

    public LeaderboardEntry() {
    }

    public LeaderboardEntry(String zipcode, int score, String tariffName) {
        this.zipcode = zipcode;
        this.score = score;
        this.tariffName = tariffName;
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
}
