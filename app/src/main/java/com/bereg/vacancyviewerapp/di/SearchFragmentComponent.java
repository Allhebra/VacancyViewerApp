package com.bereg.vacancyviewerapp.di;

import dagger.Subcomponent;
import io.reactivex.functions.Consumer;

/**
 * Created by 1 on 01.02.2018.
 */

@SearchFragmentScope
@Subcomponent(modules = {SearchModule.class})
public interface SearchFragmentComponent {

    Consumer<CharSequence> getConsumer();
}
