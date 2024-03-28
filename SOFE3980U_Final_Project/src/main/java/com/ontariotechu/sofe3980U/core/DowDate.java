package com.ontariotechu.sofe3980U.core;

import java.time.LocalTime;

public class DowDate {
    int DoW;
    LocalTime DayTime;

    //Constructor
    public DowDate(int DoW, LocalTime time) {
        this.DoW = DoW;
        this.DayTime = time;
    }
}