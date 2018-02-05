package com.bereg.vacancyviewerapp.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 1 on 07.01.2018.
 */

public class VacancyList {

    @SerializedName("vacancies")
    @Expose
    private List<Vacancy> vacancies = null;

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }
}
