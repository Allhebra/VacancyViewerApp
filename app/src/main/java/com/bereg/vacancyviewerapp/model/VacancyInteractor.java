package com.bereg.vacancyviewerapp.model;

import android.util.Log;

import com.bereg.vacancyviewerapp.Util;
import com.bereg.vacancyviewerapp.api.NgsApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.bereg.vacancyviewerapp.Constants.*;

/**
 * Created by 1 on 08.01.2018.
 */

public class VacancyInteractor {

    private static final String TAG = VacancyInteractor.class.getSimpleName();
    private NgsApi ngsApi;
    private List<Vacancy> vacancies = new ArrayList<>();
    private String minSalary;
    private String keywords;

    public VacancyInteractor(NgsApi ngsApi) {
        this.ngsApi = ngsApi;
        Log.e(TAG, "constrInteractor");
    }

    public void requestDataHandle(Observable<CharSequence> keywordsObservable,
                                  Observable<Boolean> booleanObservable,
                                  Observable<CharSequence> minSalaryObservable,
                                  Observable<CharSequence> cityObservable,
                                  final DisposableSingleObserver<List<Vacancy>> disposableSingleObserver) {

        keywordsObservable.subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence charSequence) throws Exception {
                keywords = charSequence.toString();
            }
        });

        booleanObservable.map(new Function<Boolean, String>() {
            @Override
            public String apply(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    return DESCRIPTION;
                }else return "";
            }
        })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        ngsApi.getShortObservableData(HEADER, s)
                                .subscribe(new Consumer<VacancyList>() {
                                    @Override
                                    public void accept(VacancyList vacancyList) throws Exception {
                                        vacancies = Util.searchOccurence(vacancyList.getVacancies(), keywords);
                                    }
                                });
                    }
                });
    }



    public void getInfo(Observable<CharSequence> keywordsObservable,
                        Observable<CharSequence> minSalaryObservable,
                        Observable<CharSequence> cityObservable,
                        final DisposableSingleObserver<List<Vacancy>> disposableSingleObserver) {

        minSalaryObservable.skipWhile(new Predicate<CharSequence>() {
            @Override
            public boolean test(CharSequence charSequence) throws Exception {
                Log.e(TAG, charSequence.toString());
                return charSequence.length()<3;
            }
        })
                .subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence charSequence) throws Exception {
                minSalary = charSequence.toString();
                vacancies.clear();
                getVacancies(new DisposableSingleObserver<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        if (aBoolean) disposableSingleObserver.onSuccess(vacancies);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
            }
        });
    }

    public void getVacancies(final DisposableSingleObserver<Boolean> disposableSingleObserver) {

        ngsApi.getObservableData(ID, ADD_DATE, HEADER, MIN_SALARY, MAX_SALARY, CONTACT, minSalary)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<VacancyList>() {

                    @Override
                    public void onSuccess(@NonNull VacancyList vacancyList) {
                        vacancies.addAll(0, vacancyList.getVacancies());
                        Log.e(TAG, "onSuccess: " + vacancies.size());
                        disposableSingleObserver.onSuccess(true);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, String.valueOf(e));
                    }
                });

        Log.e(TAG, "empty " + vacancies.isEmpty());
        Log.e(TAG, String.valueOf(ngsApi));
    }

    public Single<List<Vacancy>> showVacancies() {
        Log.e(TAG, "showVacancies");
        return Single.just(vacancies);
    }
}
