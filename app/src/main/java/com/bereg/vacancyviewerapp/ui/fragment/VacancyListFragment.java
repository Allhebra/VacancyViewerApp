package com.bereg.vacancyviewerapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bereg.vacancyviewerapp.App;
import com.bereg.vacancyviewerapp.R;
import com.bereg.vacancyviewerapp.Screens;
import com.bereg.vacancyviewerapp.model.Vacancy;
import com.bereg.vacancyviewerapp.model.interactor.VacancyInteractor;
import com.bereg.vacancyviewerapp.presentation.presenter.VacancyListPresenter;
import com.bereg.vacancyviewerapp.presentation.view.VacancyListView;
import com.bereg.vacancyviewerapp.ui.adapters.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 07.01.2018.
 */

public class VacancyListFragment extends MvpAppCompatFragment implements VacancyListView {

    private static final String TAG = VacancyListFragment.class.getSimpleName();

    @InjectPresenter
    VacancyListPresenter mVacancyListPresenter;

    @ProvidePresenter
    VacancyListPresenter provideSearchPresenter() {
        VacancyInteractor mVacancyInteractor = App.getAppComponent().getVacancyInteractor();
        Router router = App.getInstance().getRouter();
        return new VacancyListPresenter(mVacancyInteractor, router);
    }

    private List<Vacancy> vacancies = new ArrayList<>();
    private RecyclerAdapter mRecyclerAdapter;

    public VacancyListFragment() {
    }

    public static VacancyListFragment getInstance() {
        Bundle args = new Bundle();
        VacancyListFragment fragment = new VacancyListFragment();
        fragment.setArguments(args);
        //Log.e(TAG, "getInstance");
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Log.e(TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_vacancy_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView mRecyclerView = view.findViewById(R.id.rv_frag_vacancy_list);
        //mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerAdapter = new RecyclerAdapter(vacancies);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerAdapter.notifyDataSetChanged();

        RecyclerAdapter.getViewClickedObservable()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "RecyclerAdapter.getViewClickedObservable:   " + integer);
                        mVacancyListPresenter.showDetail(integer);
                        //mRouter.navigateTo(Screens.DETAILED_VACANCY_SCREEN);
                    }
                });
        //Log.e(TAG, "onViewCreated");
        //ButterKnife.bind(this, view);
    }

    @Override
    public void showVacancyList(List<Vacancy> list) {

        vacancies.clear();
        vacancies.addAll(list);
        Log.e(TAG, "showVacancyList" + vacancies);
    }
}
