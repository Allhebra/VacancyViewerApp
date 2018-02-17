package com.bereg.vacancyviewerapp.model.interactor;

import android.util.Log;

import com.bereg.vacancyviewerapp.model.RequestParameterModel;
import com.bereg.vacancyviewerapp.model.Vacancy;
import com.bereg.vacancyviewerapp.model.data.api.NgsApi;
import com.bereg.vacancyviewerapp.model.repository.RoomRepository;
import com.bereg.vacancyviewerapp.model.repository.ServerRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;

import static com.bereg.vacancyviewerapp.Constants.*;

/**
 * Created by 1 on 08.01.2018.
 */

public class VacancyInteractor {

    private static final String TAG = VacancyInteractor.class.getSimpleName();
    //private NgsApi ngsApi;
    private List<Vacancy> mVacancies = new ArrayList<>();
    /*private String minSalary;
    private String keywords;
    private String string;
    private Boolean saveResults;*/

    @Inject
    public RequestParameterModel mRequestParameterModel;
    @Inject
    public RoomRepository mRoomRepository;
    @Inject
    public ServerRepository mServerRepository;

    public VacancyInteractor(/*NgsApi ngsApi, */RequestParameterModel requestParameterModel, RoomRepository roomRepository, ServerRepository serverRepository) {
        //this.ngsApi = ngsApi;
        mRequestParameterModel = requestParameterModel;
        mRoomRepository = roomRepository;
        mServerRepository = serverRepository;
        Log.e(TAG, "constructorInteractor");
    }

    public void requestDataHandle(Observable<CharSequence> keywordsObservable,
                                  Observable<Boolean> booleanObservable,
                                  Observable<CharSequence> minSalaryObservable,
                                  Observable<CharSequence> cityObservable,
                                  Observable<Boolean> saveResultsObservable,
                                  final DisposableSingleObserver<List<Vacancy>> disposableSingleObserver) {

        Log.e(TAG, mRequestParameterModel.toString());

        keywordsObservable.debounce(300, TimeUnit.MILLISECONDS)
                .subscribe(new DisposableObserver<CharSequence>() {
                    @Override
                    public void onNext(CharSequence charSequence) {
                        mRequestParameterModel.setKeywords(charSequence.toString());
                        Log.e(TAG, "keywordsObservable" + mRequestParameterModel.getKeywords());
                        getShortObservableData(disposableSingleObserver);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        booleanObservable.debounce(300, TimeUnit.MILLISECONDS)
                .subscribe(new DisposableObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            mRequestParameterModel.setString(DESCRIPTION);
                        } else mRequestParameterModel.setString("");
                        Log.e(TAG, "booleanObservable" + mRequestParameterModel.getString());
                        getShortObservableData(disposableSingleObserver);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        minSalaryObservable.skipWhile(new Predicate<CharSequence>() {
            @Override
            public boolean test(CharSequence charSequence) throws Exception {
                Log.e(TAG, charSequence.toString());
                return charSequence.length()<3;
            }
        })
                .subscribe(new DisposableObserver<CharSequence>() {
                    @Override
                    public void onNext(CharSequence charSequence) {
                        mRequestParameterModel.setMinSalary(charSequence.toString());
                        Log.e(TAG, "minSalaryObservable" + mRequestParameterModel.getString());
                        getShortObservableData(disposableSingleObserver);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        saveResultsObservable
                .subscribe(new DisposableObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        mRequestParameterModel.setSaveResults(aBoolean);
                        Log.e(TAG, "saveResultsObservable" + mRequestParameterModel.getString());
                        getShortObservableData(disposableSingleObserver);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getShortObservableData(final DisposableSingleObserver<List<Vacancy>> disposableSingleObserver) {

        Log.e(TAG, "getShortObservableData");
        mServerRepository.getFromServer()
                .subscribe(new DisposableSingleObserver<List<Vacancy>>() {
                    @Override
                    public void onSuccess(List<Vacancy> vacancies) {
                        Log.e(TAG, "getShortObservableDataOnSuccess" + vacancies.size());
                        mVacancies.clear();
                        mVacancies.addAll(0, vacancies);
                        //mRoomRepository.saveToDatabase(mVacancies);
                        disposableSingleObserver.onSuccess(vacancies);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.toString());
                    }
                });
    }

    public Single<Vacancy> getVacancyById(Long id) {

        Log.e(TAG, "getVacancyById:   " + id);
        return mRoomRepository.getFromDatabaseById(id);
    }

    public Single<List<Vacancy>> showVacancies() {
        Log.e(TAG, "showVacancies");
        return Single.just(mVacancies);
    }
}
