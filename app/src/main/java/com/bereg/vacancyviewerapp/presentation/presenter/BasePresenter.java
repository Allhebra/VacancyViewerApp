package com.bereg.vacancyviewerapp.presentation.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

//import io.reactivex.Subscription;
//import io.reactivex.subscriptions.CompositeSubscription;

/**
 * Created by 1 on 06.01.2018.
 */

public class BasePresenter<View extends MvpView> extends MvpPresenter<MvpView> {

    /*private CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected void unsubscribeOnDestroy(@NonNull Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override public void onDestroy() {
        super.onDestroy();
        compositeSubscription.clear();
        }
    */
}
