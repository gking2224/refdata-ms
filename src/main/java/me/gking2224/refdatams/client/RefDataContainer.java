package me.gking2224.refdatams.client;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import me.gking2224.common.web.View;

public class RefDataContainer {

    private List<ResourceBean> resources;
    private List<LocationBean> locations;
    private List<ContractTypeBean> contractTypes;

    @JsonView(View.Summary.class)
    public List<ResourceBean> getResources() {
        return resources;
    }

    public void setResources(List<ResourceBean> resources) {
        this.resources = resources;
    }

    @JsonView(View.Summary.class)
    public List<LocationBean> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationBean> locations) {
        this.locations = locations;
    }

    public void setContractTypes(List<ContractTypeBean> contractTypes) {
        this.contractTypes = contractTypes;
    }

    @JsonView(View.Summary.class)
    public List<ContractTypeBean> getContractTypes() {
        return contractTypes;
    }

    @Override
    public String toString() {
        return String.format("RefDataContainer [resources=%s, locations=%s, contractTypes=%s]", resources, locations,
                contractTypes);
    }

}
