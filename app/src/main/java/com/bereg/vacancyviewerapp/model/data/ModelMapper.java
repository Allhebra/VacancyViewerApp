package com.bereg.vacancyviewerapp.model.data;

import android.util.Log;

import com.bereg.vacancyviewerapp.model.Vacancy;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

/**
 * Created by 1 on 11.02.2018.
 */

public class ModelMapper {

    private static final String TAG = ModelMapper.class.getSimpleName();

    public static List<Vacancy> mapDatabaseToServerModel(List<com.bereg.vacancyviewerapp.model.data.room.entity.Vacancy> vacancyList) {

        List<Vacancy> list = new ArrayList<>();

        if (vacancyList.isEmpty()) {
            return list;
        }else {
            for (int i = 0; i < vacancyList.size(); i++) {

                list.get(i).setId(valueOf(vacancyList.get(i).getId()));
                list.get(i).setAddDate(vacancyList.get(i).getAddDate());
                list.get(i).setHeader(vacancyList.get(i).getHeader());
                list.get(i).setDescription(vacancyList.get(i).getDescription());
                list.get(i).setMinSalary(valueOf(vacancyList.get(i).getMinSalary()));
                list.get(i).setMaxSalary(valueOf(vacancyList.get(i).getMaxSalary()));
                list.get(i).setSalary(valueOf(vacancyList.get(i).getSalary()));
            }
            return list;
        }
    }

    public static List<com.bereg.vacancyviewerapp.model.data.room.entity.Vacancy> mapServerToDatabaseModel(List<Vacancy> vacancyList) {

        List<com.bereg.vacancyviewerapp.model.data.room.entity.Vacancy> list = new ArrayList<>();

        if (vacancyList.isEmpty()) {
            Log.e(TAG,"if");
            return list;
        } else {
            Log.e(TAG,"else");
            for (int i = 0; i < vacancyList.size(); i++) {

                list.add(new com.bereg.vacancyviewerapp.model.data.room.entity.Vacancy());

                Log.e(TAG,"for" + i);
                Log.e(TAG,"for" + list.get(i));
                Log.e(TAG,"for" + vacancyList.get(i));

                list.get(i).setId(Long.valueOf(vacancyList.get(i).getId()));
                list.get(i).setAddDate(vacancyList.get(i).getAddDate());
                list.get(i).setHeader(vacancyList.get(i).getHeader());
                list.get(i).setDescription(vacancyList.get(i).getDescription());
                list.get(i).setMinSalary(vacancyList.get(i).getMinSalary());
                list.get(i).setMaxSalary(vacancyList.get(i).getMaxSalary());
                list.get(i).setSalary(vacancyList.get(i).getSalary());
                Log.e(TAG,"end" + i);

            }
            Log.e(TAG,"return");
            return list;
        }
    }
}
