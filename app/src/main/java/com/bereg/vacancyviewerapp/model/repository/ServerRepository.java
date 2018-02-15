package com.bereg.vacancyviewerapp.model.repository;

import android.util.Log;

import com.bereg.vacancyviewerapp.Util;
import com.bereg.vacancyviewerapp.model.RequestParameterModel;
import com.bereg.vacancyviewerapp.model.Vacancy;
import com.bereg.vacancyviewerapp.model.VacancyList;
import com.bereg.vacancyviewerapp.model.data.api.NgsApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.bereg.vacancyviewerapp.Constants.HEADER;
import static com.bereg.vacancyviewerapp.Constants.ID;
import static com.bereg.vacancyviewerapp.Constants.MAX_SALARY;
import static com.bereg.vacancyviewerapp.Constants.MIN_SALARY;

/**
 * Created by 1 on 11.02.2018.
 */

public class ServerRepository {

    private static final String TAG = ServerRepository.class.getSimpleName();

    @Inject
    public NgsApi mNgsApi;
    @Inject
    public RequestParameterModel mRequestParameterModel;

    public  ServerRepository(NgsApi ngsApi, RequestParameterModel requestParameterModel) {
        mNgsApi = ngsApi;
        mRequestParameterModel = requestParameterModel;
    }

    public Single<List<Vacancy>> getFromServer(/*final DisposableSingleObserver<List<Vacancy>> disposableSingleObserver*/) {

        return mNgsApi.getShortObservableData(ID, HEADER, mRequestParameterModel.getString(), MIN_SALARY, MAX_SALARY, mRequestParameterModel.getMinSalary())
                .subscribeOn(Schedulers.io())
                .map(new Function<VacancyList, List<Vacancy>>() {
                    @Override
                    public List<Vacancy> apply(VacancyList vacancyList) throws Exception {
                        Log.e(TAG, "" + vacancyList.getVacancies().size());
                        return Util.searchOccurence(vacancyList.getVacancies(), mRequestParameterModel.getKeywords());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
