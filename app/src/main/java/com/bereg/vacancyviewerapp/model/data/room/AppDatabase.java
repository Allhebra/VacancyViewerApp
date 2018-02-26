package com.bereg.vacancyviewerapp.model.data.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.bereg.vacancyviewerapp.model.data.room.dao.VacancyDao;
import com.bereg.vacancyviewerapp.model.data.room.entity.Vacancy;

/**
 * Created by 1 on 28.01.2018.
 */

@Database(entities = {Vacancy.class}, version = 5, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract VacancyDao vacancyDao();
}
