package com.bereg.vacancyviewerapp.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bereg.vacancyviewerapp.Screens;
import com.bereg.vacancyviewerapp.model.Vacancy;
import com.bereg.vacancyviewerapp.model.VacancyInteractor;
import com.bereg.vacancyviewerapp.model.VacancyList;
import com.bereg.vacancyviewerapp.presentation.view.SearchView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
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
    public Consumer<CharSequence> mConsumer;

    public SearchPresenter(VacancyInteractor vacancyInteractor, Router router, Consumer<CharSequence> consumer) {

        mVacancyInteractor = vacancyInteractor;
        mRouter = router;
        mConsumer = consumer;

        //mRouter.navigateTo(Screens.SEARCH_SCREEN);
        Log.e(TAG, "SearchPresenterCreated");
        Log.e(TAG, "interactor: " + String.valueOf(mVacancyInteractor));
    }

    public void onViewCreated(Observable<CharSequence> keywordsObservable,
                              Observable<Boolean> booleanObservable,
                              Observable<CharSequence> minSalaryObservable,
                              Observable<CharSequence> cityObservable) {

        mVacancyInteractor.requestDataHandle(keywordsObservable, booleanObservable, minSalaryObservable, cityObservable, new DisposableSingleObserver<List<Vacancy>>() {
            @Override
            public void onSuccess(List<Vacancy> vacancies) {
                getViewState().showShortSearchResult(vacancies.size());
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void onSearchButtonPressed() {

        Log.e(TAG, "requestForData");
        mVacancyInteractor.getVacancies(new DisposableSingleObserver<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                if (aBoolean) mRouter.navigateTo(Screens.VACANCY_LIST_SCREEN);
                    //getViewState().showVacancyList();
                Log.e(TAG, "onSuccess:  getViewState: showVacancies");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, String.valueOf(e));
            }
        });
    }
}