package me.gking2224.refdatams.batch.jobs;

import me.gking2224.common.batch.generic.FileRowEntry;

public class CountryInFile extends FileRowEntry<CountryInFile>{

    private String code;
    
    private String description;
    
    private String fileField;

    public CountryInFile() {
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileField() {
        return fileField;
    }

    public void setFileField(String fileField) {
        this.fileField = fileField;
    }

    @Override
    public String[] getTokens() {
        return new String[] { code, description, fileField };
    }
}
