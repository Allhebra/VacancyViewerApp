package com.bereg.vacancyviewerapp.model.data.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by 1 on 28.01.2018.
 */

@Entity
public class Vacancy {

    @PrimaryKey
    public long id;

    public String addDate;

    public String header;

    public String description;

    public int salary_min;

    public int salary_max;

    public int salary;
}
