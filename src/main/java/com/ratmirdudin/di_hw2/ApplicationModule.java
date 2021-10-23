package com.ratmirdudin.di_hw2;

import com.google.inject.AbstractModule;

public final class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Application.class).to(ApplicationImpl.class);
        bind(MyFormatter.class).toInstance(new MyFormatter());
    }
}
