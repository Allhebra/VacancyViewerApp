package com.bereg.vacancyviewerapp.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.bereg.vacancyviewerapp.model.Vacancy;

import java.util.List;

/**
 * Created by 1 on 07.01.2018.
 */

public interface VacancyListView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showVacancyList(List<Vacancy> vacancies);
}
