package com.bereg.vacancyviewerapp.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bereg.vacancyviewerapp.Screens;
import com.bereg.vacancyviewerapp.presentation.view.MainView;

import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 27.01.2018.
 */

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private Router mRouter;

    public MainPresenter(Router router) {
        mRouter = router;
    }

    protected void /*showSearchForm*/onFirstViewAttach() {
        super.onFirstViewAttach();
        //getViewState().showSearchForm();
        mRouter.navigateTo(Screens.SEARCH_SCREEN);
        Log.e(TAG, "showSearchForm");
    }

    public void onMenuFavoriteClicked() {


    }
}
