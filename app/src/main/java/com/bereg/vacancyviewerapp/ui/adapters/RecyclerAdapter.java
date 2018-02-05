package com.bereg.vacancyviewerapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bereg.vacancyviewerapp.R;
import com.bereg.vacancyviewerapp.model.Vacancy;

import java.util.List;

/**
 * Created by 1 on 07.01.2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final String TAG = RecyclerAdapter.class.getSimpleName();
    private List<Vacancy> items;

    public RecyclerAdapter(List<Vacancy> list) {
        this.items = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vacancy_list_item, parent, false);
        Log.e(TAG, "onCreateViewHolder");
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //holder.numberTextView.setText(items.get(position).getNumber());
        holder.headerTextView.setText(items.get(position).getHeader());
        holder.addDateTextView.setText(items.get(position).getAddDate());
        holder.minSalaryTextView.setText(items.get(position).getMinSalary());
        holder.maxSalaryTextView.setText(items.get(position).getMaxSalary());
        holder.contactTextView.setText(items.get(position).getContact().getCity().getTitle());
        Log.e(TAG, "onBindViewHolder" + position);
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "item count:   " + items.size());
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        //TextView numberTextView;
        TextView headerTextView;
        TextView addDateTextView;
        TextView minSalaryTextView;
        TextView maxSalaryTextView;
        TextView contactTextView;

        ViewHolder(View v) {
            super(v);
            //numberTextView = (TextView) v.findViewById(R.id.numberTextView);
            headerTextView = v.findViewById(R.id.headerTextView);
            addDateTextView = v.findViewById(R.id.addDateTextView);
            minSalaryTextView = v.findViewById(R.id.min_salary_TextView);
            maxSalaryTextView = v.findViewById(R.id.max_salary_TextView);
            contactTextView = v.findViewById(R.id.contact_TextView);
        }
    }
}
