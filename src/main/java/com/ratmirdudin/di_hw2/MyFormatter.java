package com.ratmirdudin.di_hw2;

import org.jetbrains.annotations.NotNull;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;


public class MyFormatter extends Formatter {
    private static int logCounter = 0;
    private final String tag;

    public MyFormatter() {
        this("");
    }

    public MyFormatter(String tag) {
        this.tag = tag;
    }


    @Override
    public String format(@NotNull LogRecord record) {
        String str = (logCounter++) + ". " + record.getInstant() + ". Logger from " + record.getLoggerName() + ". " + record.getLevel() + ": " + record.getMessage();
        str = tag.isEmpty() ? str : "<" + tag + "> " + str + " </" + tag + ">";
        return str + "\n";
    }
}
