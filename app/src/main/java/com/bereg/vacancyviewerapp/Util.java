package com.bereg.vacancyviewerapp;

import com.bereg.vacancyviewerapp.model.Vacancy;

import java.util.List;

/**
 * Created by 1 on 04.02.2018.
 */

public class Util {

    public static List<Vacancy> searchOccurence(List<Vacancy> list, String keywords) {

        for (Vacancy v : list) {
            if (!v.getHeader().contains(keywords) && !v.getDescription().contains(keywords)) {
                list.remove(v);
            }
        }
        return list;
    }
}
