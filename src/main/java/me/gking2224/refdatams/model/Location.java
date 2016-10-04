package me.gking2224.refdatams.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table
public class Location implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2707606975268392692L;

    private Long id;

    private String name;

    private Building building;

    private City city;

    private Country country;
    
//    private Map<Location, BigDecimal> locationRates;
    
    private Set<LocationRate> locationRates;
    
    public Location() {
        super();
    }

    public Location(long id, Building name) {
        this.id = id;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "location_id")
    @JsonProperty("_id")
    @JsonView(View.Summary.class)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonView(View.Summary.class)
    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch=FetchType.EAGER, optional=true)
    @JoinColumn(name="building_id")
    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @ManyToOne(fetch=FetchType.EAGER, optional=true)
    @JoinColumn(name="city_id")
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @ManyToOne(fetch=FetchType.EAGER, optional=true)
    @JoinColumn(name="country_id")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
    
    @Transient
    @JsonProperty("country")
    @JsonView(View.Summary.class)
    public String getCountryCode() {
        if (getCountry() != null) return getCountry().getCode();
        else return null;
    }
    
    @Transient
    @JsonProperty("building")
    @JsonView(View.Summary.class)
    public String getBuildingName() {
        if (getBuilding() != null) return getBuilding().getName();
        else return null;
    }
    
    @Transient
    @JsonProperty("city")
    @JsonView(View.Summary.class)
    public String getCityCode() {
        if (getCity() != null) return getCity().getName();
        else return null;
    }

//    @OneToMany(mappedBy="location", targetEntity=LocationRate.class, fetch=FetchType.LAZY)
//    @MapKeyClass(value=Location.class)
//    @ElementCollection(targetClass=LocationRate.class)
//    @MapKeyJoinColumn(table="location")
//    public Map<Location,BigDecimal> getLocationRates() {
//        return locationRates;
//    }

//    public void setLocationRates(Map<Location,BigDecimal> locationRates) {
//        this.locationRates = locationRates;
//    }

    @OneToMany(mappedBy="location", fetch=FetchType.LAZY)
    public Set<LocationRate> getLocationRates() {
        return locationRates;
    }

    public void setLocationRates(Set<LocationRate> locationRatesSet) {
        this.locationRates = locationRatesSet;
    }

    @JsonProperty("rates")
    @JsonView(View.Summary.class)
    @Transient
    public Map<String,BigDecimal> getLocationRatesMap() {
        Map<String,BigDecimal> rv = new HashMap<String,BigDecimal>();
        getLocationRates().stream().forEach(e -> rv.put(e.getContractTypeCode(), e.getRate()));
        return rv;
    }

    @Override
    public String toString() {
        return String.format("Location [id=%s, name=%s, building=%s, city=%s, country=%s, locationRates=%s]", id, name,
                building, city, country, locationRates);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Location other = (Location) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
