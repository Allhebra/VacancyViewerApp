package com.bereg.vacancyviewerapp.di;

import com.bereg.vacancyviewerapp.model.data.room.AppDatabase;
import com.bereg.vacancyviewerapp.model.interactor.VacancyInteractor;
import com.bereg.vacancyviewerapp.model.repository.RoomRepository;
import com.bereg.vacancyviewerapp.model.repository.ServerRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 1 on 13.01.2018.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void injectRoomRepository(RoomRepository roomRepository);
    void injectServerRepository(ServerRepository serverRepository);

    VacancyInteractor getVacancyInteractor();
    SearchFragmentComponent createSearchFragmentComponent();
    AppDatabase getAppDatabase();
}
