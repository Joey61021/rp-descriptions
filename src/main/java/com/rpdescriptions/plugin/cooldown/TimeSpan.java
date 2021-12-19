package com.rpdescriptions.plugin.cooldown;

import java.util.concurrent.TimeUnit;

public class TimeSpan {

    public long amount;
    public TimeUnit timeUnit;

    public TimeSpan(long amount, TimeUnit timeUnit) {
        this.amount = amount;
        this.timeUnit = timeUnit;
    }
}
