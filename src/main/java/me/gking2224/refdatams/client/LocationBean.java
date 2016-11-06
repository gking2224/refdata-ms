package me.gking2224.refdatams.client;

import java.math.BigDecimal;
import java.util.Map;

import me.gking2224.common.client.AbstractEntityBean;

public class LocationBean extends AbstractEntityBean {

    Long id;
    String building;
    String city;
    String country;
    Map<String, BigDecimal> rates;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBuilding() {
        return building;
    }
    public void setBuilding(String building) {
        this.building = building;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public Map<String, BigDecimal> getRates() {
        return rates;
    }
    public void setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((building == null) ? 0 : building.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((rates == null) ? 0 : rates.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LocationBean other = (LocationBean) obj;
        if (building == null) {
            if (other.building != null)
                return false;
        } else if (!building.equals(other.building))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (rates == null) {
            if (other.rates != null)
                return false;
        } else if (!rates.equals(other.rates))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return String.format("LocationBean [id=%s, building=%s, city=%s, country=%s, rates=%s]", id, building, city,
                country, rates);
    }
}
