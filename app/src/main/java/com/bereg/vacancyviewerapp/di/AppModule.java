package com.bereg.vacancyviewerapp.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.bereg.vacancyviewerapp.model.RequestParameterModel;
import com.bereg.vacancyviewerapp.model.data.api.NgsApi;
import com.bereg.vacancyviewerapp.model.data.api.RetrofitService;
import com.bereg.vacancyviewerapp.model.data.room.AppDatabase;
import com.bereg.vacancyviewerapp.model.interactor.VacancyInteractor;
import com.bereg.vacancyviewerapp.model.repository.RoomRepository;
import com.bereg.vacancyviewerapp.model.repository.ServerRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 1 on 13.01.2018.
 */

@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase() {
        return Room.databaseBuilder(mContext,
                AppDatabase.class, "VacancyViewerDatabase")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    VacancyInteractor provideVacancyInteractor(NgsApi ngsApi, RequestParameterModel requestParameterModel, RoomRepository roomRepository, ServerRepository serverRepository) {
        return new VacancyInteractor(/*ngsApi, */requestParameterModel, roomRepository, serverRepository);
    }

    @Provides
    @Singleton
    NgsApi provideNGSApi() {
        return RetrofitService.getApi();
    }

    @Provides
    @Singleton
    RequestParameterModel provideRequestParameterModel() {
        return new RequestParameterModel();
    }

    @Provides
    @Singleton
    RoomRepository provideRoomRepository(AppDatabase appDatabase) {
        return new RoomRepository(appDatabase);
    }

    @Provides
    @Singleton
    ServerRepository provideServerRepository(NgsApi ngsApi, RequestParameterModel requestParameterModel) {
        return new ServerRepository(ngsApi, requestParameterModel);
    }
}
