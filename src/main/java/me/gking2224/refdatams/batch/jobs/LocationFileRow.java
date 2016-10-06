package me.gking2224.refdatams.batch.jobs;

import me.gking2224.common.batch.generic.FileRowEntry;

public class LocationFileRow extends FileRowEntry<LocationFileRow> {

    private String name;
    
    private String building;
    
    private String city;
    
    private String country;

    public LocationFileRow() {
        super();
    }

    public LocationFileRow(String[] tokens) {
        setName(tokens[0]);
        setBuilding(tokens[1]);
        setCity(tokens[2]);
        setCountry(tokens[3]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return String.format("LocationFileRow [name=%s, building=%s, city=%s, country=%s]", name, building, city,
                country);
    }

    @Override
    public String[] getTokens() {
        return new String[] {
                this.name, this.building, this.city, this.country
        };
    }
}
