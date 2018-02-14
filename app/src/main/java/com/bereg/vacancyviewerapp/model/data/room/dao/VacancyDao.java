package com.bereg.vacancyviewerapp.model.data.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.bereg.vacancyviewerapp.model.data.room.entity.Vacancy;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by 1 on 28.01.2018.
 */

@Dao
public interface VacancyDao {

    @Query("SELECT * FROM vacancy")
    Single<List<Vacancy>> getAll();

    @Query("SELECT * FROM vacancy WHERE id = :id")
    Single<Vacancy> getById(long id);

    @Insert
    void insert(Vacancy vacancy);

    @Insert
    void insert(List<Vacancy> list);

    @Update
    void update(Vacancy vacancy);

    @Delete
    void delete(Vacancy vacancy);
}
