package com.bereg.vacancyviewerapp.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by 1 on 27.01.2018.
 */

public interface MainView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void showSearchForm();
}
