package me.gking2224.refdatams.batch.jobs;

public class ResourceFileRow {

    private String firstName;
    
    private String surname;
    
    private String contractType;
    
    private String rate;
    
    private String location;

    public ResourceFileRow() {
        super();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return String.format("LocationFileRow [firstName=%s, surname=%s, contractType=%s, rate=%s, location=%s]",
                firstName, surname, contractType, rate, location);
    }
}
