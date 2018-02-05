package com.bereg.vacancyviewerapp.db.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.bereg.vacancyviewerapp.db.room.entity.Vacancy;

import java.util.List;

/**
 * Created by 1 on 28.01.2018.
 */

@Dao
public interface VacancyDao {

    @Query("SELECT * FROM vacancy")
    List<Vacancy> getAll();

    @Query("SELECT * FROM vacancy WHERE id = :id")
    Vacancy getById(long id);

    @Insert
    void insert(Vacancy vacancy);

    @Update
    void update(Vacancy vacancy);

    @Delete
    void delete(Vacancy vacancy);
}
