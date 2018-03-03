package com.bereg.vacancyviewerapp;

import android.util.Log;

import com.bereg.vacancyviewerapp.model.Vacancy;

import java.util.Iterator;
import java.util.List;

/**
 * Created by 1 on 04.02.2018.
 */

public class Util {

    private static final String TAG = Util.class.getSimpleName();

    public static List<Vacancy> searchOccurence(List<Vacancy> list, String keywords) {

        //Log.e(TAG, "Util");
        if (keywords.equals("")) return list;
        Iterator<Vacancy> iterator = list.iterator();

        while (iterator.hasNext()) {
            Vacancy vacancy = iterator.next();
            if (!vacancy.getHeader().contains(keywords) && !vacancy.getDescription().contains(keywords)) iterator.remove();
        }
        //Log.e(TAG, "" + list.size());
        return list;
    }
}
