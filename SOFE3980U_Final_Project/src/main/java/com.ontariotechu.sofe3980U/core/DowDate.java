package com.ontariotechu.sofe3980U.core;

import java.time.LocalDateTime;

public class DowDate {
    int DoW;
    LocalDateTime DayTime;

    //Constructor
    public DowDate(int DoW, LocalDateTime time) {
        this.DoW = DoW;
        this.DayTime = time;
    }
}