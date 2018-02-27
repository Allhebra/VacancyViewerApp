package com.bereg.vacancyviewerapp.model.interactor;

import android.util.Log;

import com.bereg.vacancyviewerapp.model.RequestParameterModel;
import com.bereg.vacancyviewerapp.model.Vacancy;
import com.bereg.vacancyviewerapp.model.repository.RoomRepository;
import com.bereg.vacancyviewerapp.model.repository.ServerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.subjects.BehaviorSubject;

import static com.bereg.vacancyviewerapp.Constants.*;

/**
 * Created by 1 on 08.01.2018.
 */

public class VacancyInteractor {

    private static final String TAG = VacancyInteractor.class.getSimpleName();
    private List<Vacancy> mVacancies = new ArrayList<>();
    private BehaviorSubject<List<Vacancy>> requestResultBuffer = BehaviorSubject.create();

    @Inject
    public RequestParameterModel mRequestParameterModel;
    @Inject
    public RoomRepository mRoomRepository;
    @Inject
    public ServerRepository mServerRepository;

    public VacancyInteractor(RequestParameterModel requestParameterModel, RoomRepository roomRepository, ServerRepository serverRepository) {
        mRequestParameterModel = requestParameterModel;
        mRoomRepository = roomRepository;
        mServerRepository = serverRepository;
        Log.e(TAG, "constructorInteractor");
    }

    public void requestDataHandle(Observable<CharSequence> keywordsObservable,
                                  Observable<Boolean> booleanObservable,
                                  Observable<CharSequence> minSalaryObservable,
                                  Observable<CharSequence> cityObservable) {

        Log.e(TAG, mRequestParameterModel.toString());

        keywordsObservable.debounce(300, TimeUnit.MILLISECONDS)
                .subscribe(new DisposableObserver<CharSequence>() {
                    @Override
                    public void onNext(CharSequence charSequence) {
                        mRequestParameterModel.setKeywords(charSequence.toString());
                        Log.e(TAG, "keywordsObservable" + mRequestParameterModel.getKeywords());
                        getShortObservableData();
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
                        getShortObservableData();
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
                        getShortObservableData();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getShortObservableData() {

        Log.e(TAG, "getShortObservableData");

        mServerRepository.getFromServer()
                .subscribe(new DisposableSingleObserver<List<Vacancy>>() {
                    @Override
                    public void onSuccess(List<Vacancy> vacancies) {
                        Log.e(TAG, "getShortObservableDataOnSuccess" + vacancies.size());
                        mVacancies.clear();
                        mVacancies.addAll(0, vacancies);
                        requestResultBuffer.onNext(vacancies);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "getShortObservableDataOnError" + e);
                        requestResultBuffer.onError(e);
                    }
                });
    }

    public Single<Vacancy> getVacancyById(Long id) {

        Log.e(TAG, "getVacancyById:   " + id);
        return mRoomRepository.getFromDatabaseById(id);
    }

    public Single<List<Long>> saveVacancies(List<Vacancy> vacancyList) {

        return mRoomRepository.saveToDatabase(vacancyList);
    }

    public Completable changeVacancy(Vacancy vacancy) {

        if (vacancy.isFavorite()) {
            return mRoomRepository.saveToDatabase(vacancy);
        }else {
            return mRoomRepository.deleteFromDatabase(vacancy);
        }
    }

    public BehaviorSubject<List<Vacancy>> getRequestResultBuffer() {

        return requestResultBuffer;
    }
}
