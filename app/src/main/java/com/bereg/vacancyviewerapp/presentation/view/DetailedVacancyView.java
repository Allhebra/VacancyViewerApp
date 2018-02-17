package com.bereg.vacancyviewerapp.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.bereg.vacancyviewerapp.model.Vacancy;

/**
 * Created by 1 on 10.02.2018.
 */

public interface DetailedVacancyView extends MvpView {

    void showDetails(Vacancy vacancy);
}
