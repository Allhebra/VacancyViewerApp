package com.bereg.vacancyviewerapp.model.repository;

import android.util.Log;

import com.bereg.vacancyviewerapp.Util;
import com.bereg.vacancyviewerapp.model.RequestParameterModel;
import com.bereg.vacancyviewerapp.model.Vacancy;
import com.bereg.vacancyviewerapp.model.VacancyList;
import com.bereg.vacancyviewerapp.model.data.ModelMapper;
import com.bereg.vacancyviewerapp.model.data.api.NgsApi;
import com.bereg.vacancyviewerapp.model.data.room.AppDatabase;
import com.bereg.vacancyviewerapp.model.data.room.dao.VacancyDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.inject.Inject;

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

    /*public void getVacancy() {

        getFromDatabase()
                .subscribe(new DisposableSingleObserver<List<com.bereg.vacancyviewerapp.model.data.room.entity.Vacancy>>() {
                    @Override
                    public void onSuccess(List<com.bereg.vacancyviewerapp.model.data.room.entity.Vacancy> vacancies) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }*/

    public Single<List<Vacancy>> getFromDatabase(/*DisposableSingleObserver<List<com.bereg.vacancyviewerapp.model.data.room.entity.Vacancy>> disposableSingleObserver*/) {

        return vacancyDao
                .getAll()
                .subscribeOn(Schedulers.io())
                .map(new Function<List<com.bereg.vacancyviewerapp.model.data.room.entity.Vacancy>, List<Vacancy>>() {
                    @Override
                    public List<Vacancy> apply(List<com.bereg.vacancyviewerapp.model.data.room.entity.Vacancy> vacancies) throws Exception {
                        Log.e(TAG, vacancies.toString() + "size" + vacancies.size());
                        return ModelMapper.mapModels(vacancies);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
                //.subscribe(disposableSingleObserver);
    }
}
