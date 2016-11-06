package me.gking2224.refdatams.client;

import java.math.BigDecimal;

import me.gking2224.common.client.AbstractEntityBean;

public class ResourceBean extends AbstractEntityBean {

    Long id;
    Long location;
    String contractType;
    BigDecimal billRate;
    PersonBean person;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getLocation() {
        return location;
    }
    public void setLocation(Long location) {
        this.location = location;
    }
    public String getContractType() {
        return contractType;
    }
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }
    public BigDecimal getBillRate() {
        return billRate;
    }
    public void setBillRate(BigDecimal billRate) {
        this.billRate = billRate;
    }
    public PersonBean getPerson() {
        return person;
    }
    public void setPerson(PersonBean person) {
        this.person = person;
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
        ResourceBean other = (ResourceBean) obj;
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
        return String.format("ResourceBean [id=%s, location=%s, contractType=%s, billRate=%s, person=%s]", id, location,
                contractType, billRate, person);
    }
}
