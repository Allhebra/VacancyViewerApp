package com.bereg.vacancyviewerapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bereg.vacancyviewerapp.R;
import com.bereg.vacancyviewerapp.model.Vacancy;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.AdapterViewItemClickEvent;
import com.jakewharton.rxbinding2.widget.RxTextView;

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
    private static PublishSubject<Integer> mViewClickSubject = PublishSubject.create();
    private List<Vacancy> items;

    public RecyclerAdapter(List<Vacancy> list) {
        this.items = list;
    }

    public static Observable<Integer> getViewClickedObservable() {
        return mViewClickSubject.hide();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vacancy_list_item, parent, false);
        Log.e(TAG, "onCreateViewHolder");

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.headerTextView.setText(items.get(position).getHeader());
        holder.addDateTextView.setText(items.get(position).getAddDate());
        holder.minSalaryTextView.setText(items.get(position).getMinSalary());
        holder.maxSalaryTextView.setText(items.get(position).getMaxSalary());
        //holder.contactTextView.setText(items.get(position).getContact().getCity().getTitle());
        Log.e(TAG, "onBindViewHolder" + position);

        RxView.clicks(holder.view)
                //.takeUntil(RxView.detaches(parent))
                .map(new Function<Object, Integer>() {
                    @Override
                    public Integer apply(Object o) throws Exception {
                        return position/*holder.getView()*/;
                    }
                })
                .subscribe(mViewClickSubject);
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "item count:   " + items.size());
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        View view;

        TextView headerTextView;
        TextView addDateTextView;
        TextView minSalaryTextView;
        TextView maxSalaryTextView;
        TextView contactTextView;

        ViewHolder(View v) {
            super(v);
            this.view = v;
            headerTextView = v.findViewById(R.id.tv_header);
            addDateTextView = v.findViewById(R.id.tv_add_date);
            minSalaryTextView = v.findViewById(R.id.tv_min_salary);
            maxSalaryTextView = v.findViewById(R.id.tv_max_salary);
            contactTextView = v.findViewById(R.id.tv_contact);
        }

        View getView() {
            return view;
        }
    }
}
