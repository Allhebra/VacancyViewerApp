package com.bereg.vacancyviewerapp.db.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.joda.time.DateTime;

/**
 * Created by 1 on 28.01.2018.
 */

@Entity
public class Vacancy {

    @PrimaryKey
    public long id;

    //public DateTime dateTime;

    public String header;

    public String description;

    public int salary_min;

    public int salary_max;

    public String currency;

    public String education;

    //experience_lenght,contact,addres_description,logo,requirements,salary
}
