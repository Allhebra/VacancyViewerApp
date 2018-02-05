package com.bereg.vacancyviewerapp.api;

import com.bereg.vacancyviewerapp.model.VacancyList;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 1 on 07.01.2018.
 */

public interface NgsApi {

    @GET("vacancies/?fields=")
    Single<VacancyList> getObservableData(@Query("id") String id,
                                          @Query("add_date") String addDate,
                                          @Query("header") String header,
                                          @Query("salary_min") String minSalary,
                                          @Query("salary_max") String maxSalary,
                                          @Query("contact") String contact,
                                          @Query("salary") String salary);

    @GET("vacancies/?fields=")
    Single<VacancyList> getShortObservableData(@Query("header") String header,
                                               @Query("description") String description);
}
