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
                        Log.e(TAG, "tag" + mRequestParameterModel.getKeywords());
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
                        Log.e(TAG, "tag" + mRequestParameterModel.getString());
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
        Log.e(TAG, "111");
        mVacancies.clear();

        Single
                .concat(mRoomRepository.getFromDatabase(), mServerRepository.getFromServer())
                .filter(new Predicate<List<Vacancy>>() {
                    @Override
                    public boolean test(List<Vacancy> vacancies) throws Exception {
                        Log.e(TAG, "" + vacancies.size());
                        return !vacancies.isEmpty();
                    }
                })
                .first(Collections.<Vacancy>emptyList())
                .subscribe(new DisposableSingleObserver<List<Vacancy>>() {
                    @Override
                    public void onSuccess(List<Vacancy> vacancies) {
                        Log.e(TAG, "" + vacancies.size());
                        mVacancies.addAll(0, vacancies);
                        disposableSingleObserver.onSuccess(vacancies);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.toString());
                    }
                });

        /*ngsApi.getShortObservableData(ID, HEADER, string, MIN_SALARY, MAX_SALARY, minSalary)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<VacancyList>() {
                    @Override
                    public void onSuccess(VacancyList vacancyList) {
                        Log.e(TAG, "222");
                        vacancies = Util.searchOccurence(vacancyList.getVacancies(), keywords);
                        disposableSingleObserver.onSuccess(vacancies);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.toString());
                    }
                });*/
    }

    /*public void getVacancies(final DisposableSingleObserver<Boolean> disposableSingleObserver) {

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
    }*/

    public Single<List<Vacancy>> showVacancies() {
        Log.e(TAG, "showVacancies");
        return Single.just(mVacancies);
    }
}
