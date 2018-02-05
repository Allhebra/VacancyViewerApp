package com.bereg.vacancyviewerapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 1 on 06.01.2018.
 */

public class Vacancy {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("add_date")
    @Expose
    private String addDate;
    @SerializedName("header")
    @Expose
    private String header;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("salary_min")
    @Expose
    private String minSalary;
    @SerializedName("salary_max")
    @Expose
    private String maxSalary;
    @SerializedName("salary")
    @Expose
    private String salary;
    @SerializedName("contact")
    @Expose
    private Contact contact;

    public String getId() {
        return id;
    }
    public String getAddDate() {
        return addDate;
    }
    public String getHeader() {
        return header;
    }
    public String getDescription() {
        return description;
    }
    public String getMinSalary() {
        return minSalary;
    }
    public String getMaxSalary() {
        return maxSalary;
    }
    /*public String getSalary() {
        return salary;
    }*/
    public Contact getContact() {
        return contact;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }
    public void setHeader(String header) {
        this.header = header;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }
    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }
    public void setSalary(String salary) {
        this.salary = salary;
    }
}
