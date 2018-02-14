package com.bereg.vacancyviewerapp.presentation.presenter;

import com.arellomobile.mvp.MvpPresenter;
import com.bereg.vacancyviewerapp.model.interactor.VacancyInteractor;
import com.bereg.vacancyviewerapp.presentation.view.DetailedVacancyView;

import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 06.01.2018.
 */

public class DetailedVacancyPresenter extends MvpPresenter<DetailedVacancyView> {

    VacancyInteractor mVacancyInteractor;
    Router mRouter;

    public DetailedVacancyPresenter(VacancyInteractor vacancyInteractor, Router router) {

        mVacancyInteractor = vacancyInteractor;
        mRouter = router;
    }
}
