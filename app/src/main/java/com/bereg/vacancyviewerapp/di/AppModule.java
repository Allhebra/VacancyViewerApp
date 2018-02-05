package com.bereg.vacancyviewerapp.di;

import com.bereg.vacancyviewerapp.api.NgsApi;
import com.bereg.vacancyviewerapp.api.RetrofitService;
import com.bereg.vacancyviewerapp.model.VacancyInteractor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 1 on 13.01.2018.
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    VacancyInteractor provideVacancyInteractor(NgsApi ngsApi) {
        return new VacancyInteractor(ngsApi);
    }

    @Provides
    @Singleton
    NgsApi provideNGSApi() {
        return RetrofitService.getApi();
    }
}
