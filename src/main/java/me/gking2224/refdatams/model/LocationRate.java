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

@Entity
@Table
public class LocationRate implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2707606975268392692L;
    
    private Long id;
    
    private Location location;

    private ContractType contractType;

    private BigDecimal rate;
    
    public LocationRate() {
        super();
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "location_rate_id")
    @JsonProperty("_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="location_id")
    public Location getLocation() {
        return location;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="contract_type_id")
    public ContractType getContractType() {
        return contractType;
    }
    
    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    @Column
    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
    
    @Transient
    @JsonProperty("contractType")
    @JsonView(View.Summary.class)
    public String getContractTypeCode() {
        return getContractType().getCode();
    }
    
    @Transient
    @JsonProperty("locationId")
    @JsonView(View.Summary.class)
    public Long getLocationId() {
        return getLocation().getId();
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
        LocationRate other = (LocationRate) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
