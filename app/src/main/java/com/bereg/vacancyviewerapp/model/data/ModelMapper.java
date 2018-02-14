package com.bereg.vacancyviewerapp.model.data;

import com.bereg.vacancyviewerapp.model.Vacancy;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

/**
 * Created by 1 on 11.02.2018.
 */

public class ModelMapper {

    public static List<Vacancy> mapModels(List<com.bereg.vacancyviewerapp.model.data.room.entity.Vacancy> vacancyList) {

        List<Vacancy> list = new ArrayList<>();

        if (vacancyList.isEmpty()) {
            return list;
        }else {
            for (int i = 0; i <= vacancyList.size(); i++) {

                list.get(i).setId(valueOf(vacancyList.get(i).id));
                list.get(i).setAddDate(vacancyList.get(i).addDate);
                list.get(i).setHeader(vacancyList.get(i).header);
                list.get(i).setDescription(vacancyList.get(i).description);
                list.get(i).setMinSalary(valueOf(vacancyList.get(i).salary_min));
                list.get(i).setMaxSalary(valueOf(vacancyList.get(i).salary_max));
                list.get(i).setSalary(valueOf(vacancyList.get(i).salary));
            }
            return list;
        }
    }
}
