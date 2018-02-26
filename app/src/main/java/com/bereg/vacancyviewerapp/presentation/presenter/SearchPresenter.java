package com.bereg.vacancyviewerapp.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bereg.vacancyviewerapp.Screens;
import com.bereg.vacancyviewerapp.model.Vacancy;
import com.bereg.vacancyviewerapp.model.interactor.VacancyInteractor;
import com.bereg.vacancyviewerapp.presentation.view.SearchView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 06.01.2018.
 */

@InjectViewState
public class SearchPresenter extends MvpPresenter<SearchView> {

    private static final String TAG = SearchPresenter.class.getSimpleName();

    public VacancyInteractor mVacancyInteractor;
    private Router mRouter;

    public SearchPresenter(VacancyInteractor vacancyInteractor, Router router) {

        mVacancyInteractor = vacancyInteractor;
        mRouter = router;

        Log.e(TAG, "SearchPresenterCreated");
        Log.e(TAG, "interactor: " + String.valueOf(mVacancyInteractor));
    }

    public void onViewCreated(Observable<CharSequence> keywordsObservable,
                              Observable<Boolean> booleanObservable,
                              Observable<CharSequence> minSalaryObservable,
                              Observable<CharSequence> cityObservable) {

        mVacancyInteractor.requestDataHandle(
                keywordsObservable,
                booleanObservable,
                minSalaryObservable,
                cityObservable);/*,
                new DisposableSingleObserver<List<Vacancy>>() {
            @Override
            public void onSuccess(List<Vacancy> vacancies) {
                Log.e(TAG, "mVacancyInteractor.getRequestResultBufferOnNext" + vacancies.size());
                getViewState().showShortSearchResult(vacancies.size());            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "mVacancyInteractor.getRequestResultBufferOnError" + e);
            }
        });*/

        mVacancyInteractor.getRequestResultBuffer()
                .subscribe(new DefaultObserver<List<Vacancy>>() {
                    @Override
                    public void onNext(List<Vacancy> vacancies) {
                        Log.e(TAG, "mVacancyInteractor.getRequestResultBufferOnNext" + vacancies.size());
                        getViewState().showShortSearchResult(vacancies.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "mVacancyInteractor.getRequestResultBufferOnError" + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "mVacancyInteractor.getRequestResultBufferOnComplete");
                    }
                });
    }

    public void onShowButtonPressed() {

        Log.e(TAG, "onShowButtonPressed");
        mRouter.navigateTo(Screens.VACANCY_LIST_SCREEN);
    }
}
