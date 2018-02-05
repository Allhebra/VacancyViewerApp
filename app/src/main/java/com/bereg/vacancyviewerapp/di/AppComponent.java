package com.bereg.vacancyviewerapp.di;

import com.bereg.vacancyviewerapp.model.VacancyInteractor;
import com.bereg.vacancyviewerapp.presentation.presenter.SearchPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 1 on 13.01.2018.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    VacancyInteractor getVacancyInteractor();
    //ActivityComponent createActivityComponent();
    SearchFragmentComponent createSearchFragmentComponent();

    //void inject(SearchPresenter presenter);
}
