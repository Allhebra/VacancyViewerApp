package com.bereg.vacancyviewerapp.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.bereg.vacancyviewerapp.model.Vacancy;

import java.util.List;

/**
 * Created by 1 on 06.01.2018.
 */

public interface SearchView extends MvpView {

    void showShortSearchResult(int numberOfVacancies);

    //void showSearchForm();

    /*@StateStrategyType(SkipStrategy.class)
    void showVacancyList();*/
}
