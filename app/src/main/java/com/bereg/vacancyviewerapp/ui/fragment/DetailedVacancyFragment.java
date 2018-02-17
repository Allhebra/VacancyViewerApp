package com.bereg.vacancyviewerapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bereg.vacancyviewerapp.App;
import com.bereg.vacancyviewerapp.R;
import com.bereg.vacancyviewerapp.di.AppComponent;
import com.bereg.vacancyviewerapp.model.Vacancy;
import com.bereg.vacancyviewerapp.model.interactor.VacancyInteractor;
import com.bereg.vacancyviewerapp.presentation.presenter.DetailedVacancyPresenter;
import com.bereg.vacancyviewerapp.presentation.view.DetailedVacancyView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.Router;

/**
 * Created by 1 on 10.02.2018.
 */

public class DetailedVacancyFragment extends MvpAppCompatFragment implements DetailedVacancyView {

    private static final String TAG = DetailedVacancyFragment.class.getSimpleName();

    @InjectPresenter
    DetailedVacancyPresenter mDetailedVacancyPresenter;

    @ProvidePresenter
    DetailedVacancyPresenter provideDetailedVacancyPresenter() {
        AppComponent appComponent = App.getAppComponent();
        VacancyInteractor vacancyInteractor = appComponent.getVacancyInteractor();
        Router router = App.getInstance().getRouter();

        return new DetailedVacancyPresenter(vacancyInteractor, router);
    }

    @BindView(R.id.tv_header)
    TextView header;
    @BindView(R.id.tv_description)
    TextView description;
    @BindView(R.id.tv_add_date)
    TextView addDate;

    public DetailedVacancyFragment() {
    }

    public static DetailedVacancyFragment getInstance(Long id) {
        Bundle args = new Bundle();
        args.putLong("ID", id);
        DetailedVacancyFragment fragment = new DetailedVacancyFragment();
        fragment.setArguments(args);
        Log.e(TAG, "getInstance:   " + args + id);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Toast.makeText(getContext(), "onAttach", Toast.LENGTH_SHORT).show();

        /*try {
            someEventListener = (onSomeEventListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }*/
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getContext(), "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail_vacancy, container, false);
        ButterKnife.bind(this, view);
        Log.e(TAG, "onCreateView");
        Toast.makeText(getContext(), "onCreateView", Toast.LENGTH_SHORT).show();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDetailedVacancyPresenter.getVacancyById(getArguments().getLong("ID"));
        Log.e(TAG, "onViewCreated");
    }

    @Override
    public void showDetails(Vacancy vacancy) {

        Log.e(TAG, "showDetails:   " + vacancy);
        addDate.setText(vacancy.getAddDate());
        header.setText(vacancy.getHeader());
        description.setText(vacancy.getDescription());
    }
}
