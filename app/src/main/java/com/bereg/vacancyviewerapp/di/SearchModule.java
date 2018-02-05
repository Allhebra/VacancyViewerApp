package com.bereg.vacancyviewerapp.di;

import dagger.Module;
import dagger.Provides;
import io.reactivex.functions.Consumer;

/**
 * Created by 1 on 01.02.2018.
 */

@Module
public class SearchModule {

    @Provides
    @SearchFragmentScope
    Consumer<CharSequence> provideConsumer() {
        return  new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence charSequence) throws Exception {

            }
        };
    }
}
