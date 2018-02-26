package com.bereg.vacancyviewerapp.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.bereg.vacancyviewerapp.model.Vacancy;

/**
 * Created by 1 on 10.02.2018.
 */

public interface DetailedVacancyView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showDetails(Vacancy vacancy);
}
