package com.bereg.vacancyviewerapp.model.data.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by 1 on 28.01.2018.
 */

@Entity
public class Vacancy {

    public Vacancy() {

    }

    @PrimaryKey
    private long id;

    private String addDate;

    private String header;

    private String description;

    private String minSalary;

    private String maxSalary;

    private String salary;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String salary_min) {
        this.minSalary = salary_min;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String salary_max) {
        this.maxSalary = salary_max;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
