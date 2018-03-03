package com.bereg.vacancyviewerapp.presentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bereg.vacancyviewerapp.model.Vacancy;
import com.bereg.vacancyviewerapp.model.interactor.VacancyInteractor;
import com.bereg.vacancyviewerapp.presentation.view.DetailedVacancyView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
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
        Log.e(TAG, "constructor:   " + vacancyInteractor + router);

    }

    public void getVacancyById(final Long id) {

        Log.e(TAG, "getVacancyById:   " + id);

        mVacancyInteractor.getRequestResultBuffer()
                .flatMap(new Function<List<Vacancy>, ObservableSource<Vacancy>>() {
                    @Override
                    public ObservableSource<Vacancy> apply(List<Vacancy> vacancies) throws Exception {
                        return Observable.fromIterable(vacancies);
                    }
                })
                .filter(new Predicate<Vacancy>() {
                    @Override
                    public boolean test(Vacancy vacancy) throws Exception {
                        return Long.valueOf(vacancy.getId()).equals(id);
                    }
                })
                .subscribe(new DisposableObserver<Vacancy>() {

                    @Override
                    public void onNext(Vacancy vacancy) {
                        Log.e(TAG, "mVacancyInteractor.getRequestResultBuffer:   " + vacancy);
                        getViewState().showDetails(vacancy);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "mVacancyInteractor.getRequestResultBufferOnError:   " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "mVacancyInteractor.getRequestResultBufferOnComplete:");
                    }
                });
    }
}
