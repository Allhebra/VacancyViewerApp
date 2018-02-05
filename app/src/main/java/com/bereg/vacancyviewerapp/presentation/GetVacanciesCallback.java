package com.bereg.vacancyviewerapp.presentation;

import com.bereg.vacancyviewerapp.model.Vacancy;

import java.util.List;

/**
 * Created by 1 on 07.01.2018.
 */

public interface GetVacanciesCallback {

    void onResult(List<Vacancy> vacancies);
}
