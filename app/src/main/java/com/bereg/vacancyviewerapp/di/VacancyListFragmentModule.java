package com.bereg.vacancyviewerapp.di;

import com.bereg.vacancyviewerapp.model.VacancyInteractor;
import com.bereg.vacancyviewerapp.presentation.presenter.VacancyListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 1 on 13.01.2018.
 */

@Module
public class VacancyListFragmentModule {

    @Provides
    VacancyListPresenter provideVacancyListPresenter(VacancyInteractor vacancyInteractor) {
        return new VacancyListPresenter(vacancyInteractor);
    }
}
