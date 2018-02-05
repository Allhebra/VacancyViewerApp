package com.bereg.vacancyviewerapp.di;

import com.bereg.vacancyviewerapp.ui.fragment.VacancyListFragment;

import dagger.Subcomponent;

/**
 * Created by 1 on 13.01.2018.
 */

@Subcomponent(modules = VacancyListFragmentModule.class)
public interface VacancyListFragmentComponent {

    void injectVacancyListFragment(VacancyListFragment fragment);

}
