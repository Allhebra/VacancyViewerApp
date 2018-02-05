package com.bereg.vacancyviewerapp;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.util.Log;

import com.bereg.vacancyviewerapp.db.room.AppDatabase;
import com.bereg.vacancyviewerapp.di.AppComponent;
import com.bereg.vacancyviewerapp.di.DaggerAppComponent;

import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 11.01.2018.
 */

public class App extends Application {

    private static final String TAG = App.class.getSimpleName();
    private static App instance;
    private Cicerone<Router> cicerone;
    public Router mRouter;
    public NavigatorHolder mNavigatorHolder;

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.create();
        cicerone = Cicerone.create();
        AppDatabase database =  Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "VacancyViewerDatabase").build();
        Log.e(TAG, String.valueOf(appComponent) + String.valueOf(cicerone));
    }

    public NavigatorHolder getNavigatorHolder() {
        Log.e(TAG, "getNavigatorHolder");
        if (mNavigatorHolder == null) mNavigatorHolder = cicerone.getNavigatorHolder();
        return mNavigatorHolder;
    }

    public Router getRouter() {
        Log.e(TAG, "getRouter");
        if (mRouter == null) mRouter = cicerone.getRouter();
        return mRouter;
    }

    public static App getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
