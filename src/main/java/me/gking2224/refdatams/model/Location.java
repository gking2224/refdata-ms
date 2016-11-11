package me.gking2224.refdatams.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
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

import me.gking2224.common.model.AbstractEntity;
import me.gking2224.common.web.View;
import me.gking2224.refdatams.client.LocationBean;

@Entity
@Table
public class Location extends AbstractEntity<Long, LocationBean> implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2707606975268392692L;

    private Long id;

    private String name;

    private Building building;

    private City city;

    private Country country;
    
    private Set<LocationRate> locationRates = new HashSet<LocationRate>();
    
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
    public String getCityName() {
        if (getCity() != null) return getCity().getName();
        else return null;
    }
    
    @Transient
    public Optional<LocationRate> getRate(final ContractType contractType) {
        return this.locationRates.stream()
                .filter(r -> r.getContractTypeCode().equals(contractType.getCode()))
                .findFirst();
    }
    
    public void setLocationRate(final ContractType contractType, final BigDecimal rate) {
        
        Optional<LocationRate> locationRate = getRate(contractType);
        
        if (!locationRate.isPresent()) {
            this.locationRates.add(newLocationRate(contractType, rate));
        }
        else {
            locationRate.get().setRate(rate);
        }
    }

    private LocationRate newLocationRate(ContractType contractType, BigDecimal rate) {
        LocationRate rv = new LocationRate();
        rv.setContractType(contractType);
        rv.setRate(rate);
        rv.setLocation(this);
        return rv;
    }

    @OneToMany(mappedBy="location", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    public Set<LocationRate> getLocationRates() {
        return locationRates;
    }

    public void addLocationRate(LocationRate newRate) {
        locationRates.add(newRate);
    }

    public void setLocationRates(Set<LocationRate> locationRatesSet) {
        this.locationRates = locationRatesSet;
    }

    @JsonProperty("rates")
    @JsonView(View.Summary.class)
    @Transient
    public Map<String, BigDecimal> getLocationRatesMap() {
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

    @Override
    @Transient
    public LocationBean getBean() {
        LocationBean rv = new LocationBean();
        rv.setId(this.getId());
        rv.setBuilding(this.getBuildingName());
        rv.setCity(this.getCityName());
        rv.setCountry(this.getCountryCode());
        rv.setRates(this.getLocationRatesMap());
        return rv;
    }
}
