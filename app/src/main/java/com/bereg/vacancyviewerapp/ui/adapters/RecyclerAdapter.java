package com.bereg.vacancyviewerapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bereg.vacancyviewerapp.R;
import com.bereg.vacancyviewerapp.model.Vacancy;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by 1 on 07.01.2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final String TAG = RecyclerAdapter.class.getSimpleName();
    private static PublishSubject<Vacancy> mViewClickSubject = PublishSubject.create();
    private static PublishSubject<Vacancy> mViewCheckedChangesSubject = PublishSubject.create();
    private List<Vacancy> items;

    public RecyclerAdapter(List<Vacancy> list) {
        this.items = list;
    }

    public static Observable<Vacancy> getViewClickedObservable() {
        return mViewClickSubject.hide();
    }

    public static Observable<Vacancy> getViewCheckedChangesObservable() {
        return mViewCheckedChangesSubject.hide();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vacancy_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        Log.e(TAG, "onCreateViewHolder:  " + viewHolder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Log.e(TAG, "onBindViewHolder:  " + holder + "to" + position + "with" + items.get(position) + items.get(position).isFavorite());
        holder.bind(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "item count:   " + items.size());
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        Vacancy mVacancy;
        Observable<Object> clicksObservable;
        Observable<Boolean> favoriteChangeObservable;

        private TextView headerTextView;
        private TextView addDateTextView;
        private TextView minSalaryTextView;
        private TextView maxSalaryTextView;
        private CheckBox favoriteCheckBox;

        ViewHolder(View v) {
            super(v);
            this.view = v;
            headerTextView = v.findViewById(R.id.tv_header);
            addDateTextView = v.findViewById(R.id.tv_add_date);
            minSalaryTextView = v.findViewById(R.id.tv_min_salary);
            maxSalaryTextView = v.findViewById(R.id.tv_max_salary);
            favoriteCheckBox = v.findViewById(R.id.chk_favorite);

            clicksObservable = RxView.clicks(view);
            favoriteChangeObservable = RxCompoundButton
                    .checkedChanges(favoriteCheckBox)
                    .skipInitialValue();
        }

        public void bind(final Vacancy vacancy, final int position) {

            mVacancy = vacancy;
            Log.e(TAG, "bind   :   " + vacancy + vacancy.isFavorite() + "onPos   " + position);
            headerTextView.setText(vacancy.getHeader());
            addDateTextView.setText(vacancy.getAddDate());
            minSalaryTextView.setText(vacancy.getMinSalary());
            maxSalaryTextView.setText(vacancy.getMaxSalary());
            //contactTextView.setText(vacancy.getContact().getCity().getTitle());
            Log.e(TAG, "bind111   :   " + favoriteCheckBox.isChecked());
            favoriteCheckBox.setChecked((vacancy.isFavorite()));
            Log.e(TAG, "bind222   :   " + favoriteCheckBox.isChecked());

            clicksObservable
                    .map(new Function<Object, Vacancy>() {
                @Override
                public Vacancy apply(Object o) throws Exception {
                    return vacancy;
                }
            })
                    .subscribe(mViewClickSubject);

            favoriteChangeObservable
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) throws Exception {
                            setFavorite(aBoolean);
                        }
                    });
        }

        void setFavorite(Boolean aBoolean) {
            mVacancy.setFavorite(aBoolean);
            Log.e(TAG, "aaa:   " + mVacancy + mVacancy.isFavorite());
            mViewCheckedChangesSubject.onNext(mVacancy);
        }

        View getView() {
            return view;
        }
    }
}
