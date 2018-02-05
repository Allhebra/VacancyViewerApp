package com.bereg.vacancyviewerapp.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bereg.vacancyviewerapp.model.Vacancy;
import com.bereg.vacancyviewerapp.model.VacancyInteractor;
import com.bereg.vacancyviewerapp.model.VacancyList;
import com.bereg.vacancyviewerapp.presentation.view.VacancyListView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by 1 on 06.01.2018.
 */

@InjectViewState
public class VacancyListPresenter extends MvpPresenter<VacancyListView> {

    private static final String TAG = VacancyListPresenter.class.getSimpleName();
    private VacancyInteractor mVacancyInteractor;

    public VacancyListPresenter(VacancyInteractor vacancyInteractor) {

        mVacancyInteractor = vacancyInteractor;
        getVacancyList();
        Log.e(TAG, "showVacancyListInPresenter");
    }

    private void getVacancyList() {

        Log.e(TAG, "getVacancyListInPresenter");
        mVacancyInteractor.showVacancies()
        .subscribe(new Consumer<List<Vacancy>>() {
            @Override
            public void accept(List<Vacancy> vacancies) throws Exception {
                Log.e(TAG, "inAccept");
                getViewState().showVacancyList(vacancies);
            }
        });
    }
}
