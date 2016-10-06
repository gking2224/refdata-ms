package me.gking2224.refdatams.batch.jobs;

import me.gking2224.common.batch.generic.FileRowEntry;

public class LocationRatesFileRow extends FileRowEntry<LocationRatesFileRow> {

    private String location;
    
    private String p;
    
    private String m;
    
    private String c;

    public LocationRatesFileRow() {
        super();
    }

    public LocationRatesFileRow(String[] tokens) {
        setLocation(tokens[0]);
        setP(tokens[1]);
        setM(tokens[2]);
        setC(tokens[3]);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return String.format("LocationRatesFileRow [location=%s, p=%s, m=%s, c=%s]", location, p, m, c);
    }

    @Override
    public String[] getTokens() {
        return new String[] { location, p, m, c };
    }
}
