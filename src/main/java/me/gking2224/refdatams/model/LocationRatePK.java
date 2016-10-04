package me.gking2224.refdatams.model;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class LocationRatePK implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2707606975268392692L;

    private Location location;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="contract_type_id")
    private ContractType contractType;
    
    public LocationRatePK() {
        super();
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }
    
    public ContractType getContractType() {
        return contractType;
    }

    public Location getLocation() {
        return location;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contractType == null) ? 0 : contractType.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
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
        LocationRatePK other = (LocationRatePK) obj;
        if (contractType == null) {
            if (other.contractType != null)
                return false;
        } else if (!contractType.equals(other.contractType))
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        return true;
    }

}
