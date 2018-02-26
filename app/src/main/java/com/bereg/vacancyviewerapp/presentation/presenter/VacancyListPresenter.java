package com.bereg.vacancyviewerapp.presentation.presenter;

import android.util.Log;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bereg.vacancyviewerapp.Screens;
import com.bereg.vacancyviewerapp.model.Vacancy;
import com.bereg.vacancyviewerapp.model.interactor.VacancyInteractor;
import com.bereg.vacancyviewerapp.presentation.view.VacancyListView;
import com.bereg.vacancyviewerapp.ui.adapters.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.observers.DisposableCompletableObserver;
import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 06.01.2018.
 */

@InjectViewState
public class VacancyListPresenter extends MvpPresenter<VacancyListView> {

    private static final String TAG = VacancyListPresenter.class.getSimpleName();
    private VacancyInteractor mVacancyInteractor;
    private Router mRouter;
    private List<Vacancy> mVacancies = new ArrayList<>();

    public VacancyListPresenter(VacancyInteractor vacancyInteractor, Router router) {

        mVacancyInteractor = vacancyInteractor;
        mRouter = router;
        getVacancyList();
        Log.e(TAG, "showVacancyListInPresenter");
    }

    private void getVacancyList() {

        Log.e(TAG, "getVacancyListInPresenter");

        mVacancyInteractor.getRequestResultBuffer()
                .subscribe(new DefaultObserver<List<Vacancy>>() {
                    @Override
                    public void onNext(List<Vacancy> vacancies) {
                        Log.e(TAG, "mVacancyInteractor.getRequestResultBufferOnNext" + vacancies.size());
                        mVacancies.clear();
                        mVacancies.addAll(0, vacancies);
                        getViewState().showVacancyList(vacancies);
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

    public void showDetail(Vacancy integer) {

        Log.e(TAG, "showDetail:   " + integer);
        mRouter.navigateTo(Screens.DETAILED_VACANCY_SCREEN, Long.valueOf(integer.getId()));
    }

    public void onVacancyChanged(final Vacancy vacancy) {

        Log.e(TAG, "onVacancyChangedFavorite:   " + vacancy);
        final boolean isFavorite = vacancy.isFavorite();
        final int index = mVacancies.indexOf(vacancy);
        mVacancies.get(index).setFavorite(isFavorite);
        //vacancy.setFavorite(!isFavorite);
        mVacancyInteractor.changeVacancy(vacancy)
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        mRouter.showSystemMessage("запись изменена!");
                        Log.e(TAG, "onVacancyChangedFavoriteOnComplete:   " + isFavorite + index);
                        getViewState().showVacancyList(mVacancies);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onVacancyChangedFavorite:   " + e);
                        mRouter.showSystemMessage(e.getMessage());
                    }
                });
    }
}
