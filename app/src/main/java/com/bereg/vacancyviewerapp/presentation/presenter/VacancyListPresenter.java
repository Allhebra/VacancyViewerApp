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

import java.util.List;

import io.reactivex.functions.Consumer;
import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 06.01.2018.
 */

@InjectViewState
public class VacancyListPresenter extends MvpPresenter<VacancyListView> {

    private static final String TAG = VacancyListPresenter.class.getSimpleName();
    private VacancyInteractor mVacancyInteractor;
    private Router mRouter;


    public VacancyListPresenter(VacancyInteractor vacancyInteractor, Router router) {

        mVacancyInteractor = vacancyInteractor;
        mRouter = router;
        getVacancyList();
        Log.e(TAG, "showVacancyListInPresenter");
        RecyclerAdapter.getViewClickedObservable()
                .subscribe(new Consumer<View>() {
                    @Override
                    public void accept(View view) throws Exception {
                        mRouter.navigateTo(Screens.DETAILED_VACANCY_SCREEN);
                    }
                });
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
