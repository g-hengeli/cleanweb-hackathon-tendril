package com.tendril.cleanweb.domain.tendril;

import javax.xml.bind.annotation.XmlRootElement;

/**
 */
@XmlRootElement
public class TendrilLocation {
    private String id;
    private String city;
    private String countryCode;
    private String externalResidenceId;
    private String postalCode;
    private String state;
    private String streetAddress;
    private String timeZone;

    public TendrilLocation() {
    }

    public TendrilLocation(String id, String city, String countryCode, String externalResidenceId, String postalCode, String state, String streetAddress, String timeZone) {
        this.id = id;
        this.city = city;
        this.countryCode = countryCode;
        this.externalResidenceId = externalResidenceId;
        this.postalCode = postalCode;
        this.state = state;
        this.streetAddress = streetAddress;
        this.timeZone = timeZone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getExternalResidenceId() {
        return externalResidenceId;
    }

    public void setExternalResidenceId(String externalResidenceId) {
        this.externalResidenceId = externalResidenceId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
