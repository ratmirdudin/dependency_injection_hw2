package com.ratmirdudin.di_hw2;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Main {
    public static void main(@NotNull String[] args) throws IOException {
        Injector injector = Guice.createInjector(new ApplicationModule());
        injector.getInstance(Application.class).waitForInput(args);
    }
}
