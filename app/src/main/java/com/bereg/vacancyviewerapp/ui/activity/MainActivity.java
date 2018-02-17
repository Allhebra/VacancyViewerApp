package com.bereg.vacancyviewerapp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bereg.vacancyviewerapp.App;
import com.bereg.vacancyviewerapp.R;

import com.bereg.vacancyviewerapp.Screens;
import com.bereg.vacancyviewerapp.presentation.presenter.MainPresenter;
import com.bereg.vacancyviewerapp.presentation.view.MainView;
import com.bereg.vacancyviewerapp.ui.fragment.DetailedVacancyFragment;
import com.bereg.vacancyviewerapp.ui.fragment.SearchFragment;
import com.bereg.vacancyviewerapp.ui.fragment.VacancyListFragment;

import butterknife.ButterKnife;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    private static final String TAG = MainActivity.class.getSimpleName();

    @InjectPresenter
    MainPresenter mMainPresenter;

    @ProvidePresenter
    MainPresenter provideMainPresenter() {

        Router router = App.getInstance().getRouter();
        return new MainPresenter(router);
    }

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.fragment_container) {

        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            switch (screenKey) {
                case Screens.SEARCH_SCREEN:
                    Log.e(TAG, "SEARCH_SCREEN");
                    return SearchFragment.getInstance();
                case Screens.VACANCY_LIST_SCREEN:
                    Log.e(TAG, "VACANCY_LIST_SCREEN");
                    return VacancyListFragment.getInstance();
                case Screens.DETAILED_VACANCY_SCREEN:
                    Log.e(TAG, "DETAILED_VACANCY_SCREEN");
                    return DetailedVacancyFragment.getInstance((Long) data);
            }
            Log.e(TAG, "NULL");
            return null;
        }

        @Override
        protected void showSystemMessage(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void exit() {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.getInstance().getNavigatorHolder().setNavigator(navigator);
        mMainPresenter.showSearchForm();
        Log.e(TAG, "onResume");
        Toast.makeText(MainActivity.this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {

        App.getInstance().getNavigatorHolder().removeNavigator();
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    public void showSearchForm() {

        Toast.makeText(MainActivity.this, "showSearchForm", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "showSearchForm");
    }

    /*@Override
    public void someEvent(String s) {

        showVacancyList();
    }*/
}
