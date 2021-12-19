package com.rpdescriptions.plugin.cooldown;

public class Cooldown {

    public long timestamp;
    public TimeSpan timeSpan;

    public Cooldown(long timestamp, TimeSpan timeSpan) {
        this.timeSpan = timeSpan;
        this.timestamp = timestamp;
    }
}
