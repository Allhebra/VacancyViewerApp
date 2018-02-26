package com.bereg.vacancyviewerapp.model.repository;

import android.util.Log;

import com.bereg.vacancyviewerapp.Util;
import com.bereg.vacancyviewerapp.model.RequestParameterModel;
import com.bereg.vacancyviewerapp.model.Vacancy;
import com.bereg.vacancyviewerapp.model.VacancyList;
import com.bereg.vacancyviewerapp.model.data.ModelMapper;
import com.bereg.vacancyviewerapp.model.data.room.AppDatabase;
import com.bereg.vacancyviewerapp.model.data.room.dao.VacancyDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.bereg.vacancyviewerapp.Constants.HEADER;
import static com.bereg.vacancyviewerapp.Constants.ID;
import static com.bereg.vacancyviewerapp.Constants.MAX_SALARY;
import static com.bereg.vacancyviewerapp.Constants.MIN_SALARY;

/**
 * Created by 1 on 11.02.2018.
 */

public class RoomRepository {

    private static final String TAG = RoomRepository.class.getSimpleName();

    @Inject
    public AppDatabase mAppDatabase;
    @Inject
    public RequestParameterModel mRequestParameterModel;
    public VacancyDao vacancyDao;
    private List<Vacancy> vacancies = new ArrayList<>();

    public RoomRepository(AppDatabase appDatabase) {
        mAppDatabase = appDatabase;
        vacancyDao = mAppDatabase.vacancyDao();
    }

    public Single<List<Vacancy>> getFromDatabase() {

        return vacancyDao
                .getAll()
                .subscribeOn(Schedulers.io())
                .map(new Function<List<com.bereg.vacancyviewerapp.model.data.room.entity.Vacancy>, List<Vacancy>>() {
                    @Override
                    public List<Vacancy> apply(List<com.bereg.vacancyviewerapp.model.data.room.entity.Vacancy> vacancies) throws Exception {
                        Log.e(TAG, vacancies.toString() + "getFromDatabase" + vacancies.size());
                        return ModelMapper.mapAllDatabaseToServerModel(vacancies);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Vacancy> getFromDatabaseById(Long id) {

        Log.e(TAG,"getFromDatabaseById:   " + id);
        return vacancyDao
                .getById(id)
                .subscribeOn(Schedulers.io())
                .map(new Function<com.bereg.vacancyviewerapp.model.data.room.entity.Vacancy, Vacancy>() {
                    @Override
                    public Vacancy apply(com.bereg.vacancyviewerapp.model.data.room.entity.Vacancy vacancy) throws Exception {
                        Log.e(TAG, vacancies.toString() + "getFromDatabase" + vacancy.getId());
                        return ModelMapper.mapDatabaseToServerModel(vacancy);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());

    }

    public Completable saveToDatabase(final List<Vacancy> vacancyList) {

        Log.e(TAG,"saveToDatabase" + vacancyList.size());
        return Completable.fromCallable(new Callable<List>() {
            @Override
            public List<Long> call() throws Exception {
                return vacancyDao.insert(ModelMapper.mapAllServerToDatabaseModel(vacancyList));
            }
        });
    }

    public Completable saveToDatabase(final Vacancy vacancy){

        Log.e(TAG,"saveToDatabase:   " + vacancy);
        return Completable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return vacancyDao.insert(ModelMapper.mapServerToDatabaseModel(vacancy));
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    
    public Completable deleteFromDatabase(final Vacancy vacancy) {

        Log.e(TAG,"deleteFromDatabase:   " + vacancy);
        return Completable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return vacancyDao.delete(ModelMapper.mapServerToDatabaseModel(vacancy));
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
