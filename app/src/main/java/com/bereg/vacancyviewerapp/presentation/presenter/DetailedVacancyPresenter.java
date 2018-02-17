package com.bereg.vacancyviewerapp.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bereg.vacancyviewerapp.model.Vacancy;
import com.bereg.vacancyviewerapp.model.interactor.VacancyInteractor;
import com.bereg.vacancyviewerapp.presentation.view.DetailedVacancyView;

import io.reactivex.observers.DisposableSingleObserver;
import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 06.01.2018.
 */

@InjectViewState
public class DetailedVacancyPresenter extends MvpPresenter<DetailedVacancyView> {

    private static final String TAG = DetailedVacancyPresenter.class.getSimpleName();

    private VacancyInteractor mVacancyInteractor;
    private Router mRouter;

    public DetailedVacancyPresenter(VacancyInteractor vacancyInteractor, Router router) {

        mVacancyInteractor = vacancyInteractor;
        mRouter = router;
        Log.e(TAG, "DetailedVacancyPresenter:   " + vacancyInteractor + router);

    }

    public void getVacancyById(Long id) {

        Log.e(TAG, "getVacancyById:   " + id);

        mVacancyInteractor.getVacancyById(id)
                .subscribe(new DisposableSingleObserver<Vacancy>() {
                    @Override
                    public void onSuccess(Vacancy vacancy) {

                        Log.e(TAG, "getVacancyByIdOnSuccess:   " + vacancy);
                        getViewState().showDetails(vacancy);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError:   " + e);

                    }
                });
    }
}
