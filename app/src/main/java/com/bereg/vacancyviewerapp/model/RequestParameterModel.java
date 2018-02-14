package com.bereg.vacancyviewerapp.model;

/**
 * Created by 1 on 11.02.2018.
 */

public class RequestParameterModel {

    private String minSalary;
    private String keywords;
    private String string;
    private boolean saveResults;

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Boolean getSaveResults() {
        return saveResults;
    }

    public void setSaveResults(Boolean aBoolean) {
        this.saveResults = aBoolean;
    }
}
