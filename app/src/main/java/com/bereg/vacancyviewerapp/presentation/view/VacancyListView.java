package com.bereg.vacancyviewerapp.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.bereg.vacancyviewerapp.model.Vacancy;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by 1 on 07.01.2018.
 */

public interface VacancyListView extends MvpView {

    //@StateStrategyType(SkipStrategy.class)
    void showVacancyList(List<Vacancy> vacancies);
}
