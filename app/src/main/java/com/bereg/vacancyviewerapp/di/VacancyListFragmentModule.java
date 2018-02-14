package com.bereg.vacancyviewerapp.di;

import com.bereg.vacancyviewerapp.model.interactor.VacancyInteractor;
import com.bereg.vacancyviewerapp.presentation.presenter.VacancyListPresenter;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 13.01.2018.
 */

@Module
public class VacancyListFragmentModule {

    @Provides
    VacancyListPresenter provideVacancyListPresenter(VacancyInteractor vacancyInteractor, Router router) {
        return new VacancyListPresenter(vacancyInteractor, router);
    }
}
