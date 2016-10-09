package me.gking2224.refdatams.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import me.gking2224.common.web.View;

@Entity
@Table
public class Resource {

    private Long id;
    
    private Person person;
    
    private BigDecimal billRate;
    
    private Location location;
    
    private ContractType contractType;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "resource_id")
    @JsonProperty("_id")
    @JsonView(View.Summary.class)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch=FetchType.EAGER, optional=true)
    @JoinColumn(name="person_id")
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Column
    @JsonView(View.Summary.class)
    public BigDecimal getBillRate() {
        return billRate;
    }

    public void setBillRate(BigDecimal billRate) {
        this.billRate = billRate;
    }

    @ManyToOne(fetch=FetchType.EAGER, optional=true)
    @JoinColumn(name="location_id")
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @ManyToOne(fetch=FetchType.EAGER, optional=true)
    @JoinColumn(name="contract_type_id")
    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }
    
    @Transient
    @JsonView(View.Summary.class)
    @JsonProperty("contractType")
    public String getContractTypeCode() {
        return getContractType() == null ? null : getContractType().getCode();
    }
    
    @Transient
    @JsonView(View.Summary.class)
    public String getFirstName() {
        return getPerson() == null ? null : getPerson().getFirstName();
    }

    @JsonView(View.Summary.class)
    @Transient
    public String getSurname() {
        return getPerson() == null ? null : getPerson().getSurname();
    }

//    @JsonView(View.Summary.class)
//    @Transient
//    public Long getLocationId() {
//        return getLocation() == null ? null : getLocation().getId();
//    }

    @JsonView(View.Summary.class)
    @Transient
    public String getLocationName() {
        return getLocation() == null ? null : getLocation().getName();
    }

    @JsonView(View.Summary.class)
    @Transient
    public Long getLocationId() {
        return getLocation() == null ? null : getLocation().getId();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((billRate == null) ? 0 : billRate.hashCode());
        result = prime * result + ((contractType == null) ? 0 : contractType.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((person == null) ? 0 : person.hashCode());
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
        Resource other = (Resource) obj;
        if (billRate == null) {
            if (other.billRate != null)
                return false;
        } else if (!billRate.equals(other.billRate))
            return false;
        if (contractType == null) {
            if (other.contractType != null)
                return false;
        } else if (!contractType.equals(other.contractType))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (person == null) {
            if (other.person != null)
                return false;
        } else if (!person.equals(other.person))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("Resource [id=%s, person=%s, billRate=%s, location=%s, contractType=%s]", id, person,
                billRate, location, contractType);
    }
}
