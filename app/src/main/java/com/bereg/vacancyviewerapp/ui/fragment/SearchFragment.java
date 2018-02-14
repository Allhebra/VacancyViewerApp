package com.bereg.vacancyviewerapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import com.bereg.vacancyviewerapp.App;
import com.bereg.vacancyviewerapp.R;
import com.bereg.vacancyviewerapp.di.AppComponent;
import com.bereg.vacancyviewerapp.model.interactor.VacancyInteractor;
import com.bereg.vacancyviewerapp.presentation.presenter.SearchPresenter;
import com.bereg.vacancyviewerapp.presentation.view.SearchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import io.reactivex.functions.Consumer;
import ru.terrakok.cicerone.Router;

import static com.jakewharton.rxbinding2.widget.RxCheckedTextView.check;
import static com.jakewharton.rxbinding2.widget.RxCompoundButton.checked;
import static com.jakewharton.rxbinding2.widget.RxTextView.textChanges;
import static com.jakewharton.rxbinding2.widget.RxCompoundButton.checkedChanges;

/**
 * A simple {@link Fragment} subclass.
 */

public class SearchFragment extends MvpAppCompatFragment implements SearchView {

    /*public interface onSomeEventListener {
        void someEvent(String s);
    }

    onSomeEventListener someEventListener;*/

    private static final String TAG = SearchFragment.class.getSimpleName();

    @InjectPresenter
    SearchPresenter mSearchPresenter;

    @ProvidePresenter
    SearchPresenter provideSearchPresenter() {
        AppComponent appComponent = App.getAppComponent();
        VacancyInteractor vacancyInteractor = appComponent.getVacancyInteractor();
        Router router = App.getInstance().getRouter();

        return new SearchPresenter(vacancyInteractor, router);
    }

    @BindView(R.id.keywords)
    EditText keywords;
    @BindView(R.id.radioButton)
    RadioButton radioButton;
    @BindView(R.id.min_salary)
    EditText minSalary;
    @BindView(R.id.city)
    EditText city;
    @BindView(R.id.period_spinner)
    Spinner periodSpinner;
    @BindView(R.id.short_search_result_textView)
    TextView shortSearchResultTextView;
    @BindView(R.id.checkBox_search_save_results)
    CheckBox saveResultsCheckBox;
    @BindView(R.id.activity_main_button_search)
    Button searchButton;

    public SearchFragment() {
    }

    public static SearchFragment getInstance() {
        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        Log.e(TAG, "getInstance");
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

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        Log.e(TAG, "onCreateView");
        Toast.makeText(getContext(), "onCreateView", Toast.LENGTH_SHORT).show();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.period_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        periodSpinner.setAdapter(adapter);

        mSearchPresenter.onViewCreated(
                textChanges(keywords),
                checkedChanges(radioButton),
                textChanges(minSalary),
                textChanges(city),
                checkedChanges(saveResultsCheckBox));

        Log.e(TAG, "onViewCreated");
    }

    @OnClick(R.id.activity_main_button_search)
    public void onSearchButtonClick() {
        mSearchPresenter.onSearchButtonPressed();
    }

    @Override
    public void showShortSearchResult(int numberOfVacancies) {

        shortSearchResultTextView.setText("По вашему запросу найдено " + numberOfVacancies + " вакансий");
    }
}
