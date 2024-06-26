package com.ontariotechu.sofe3980U.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;

public class DowDate implements Comparable<DowDate>{
    private int DoW;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime DayTime;

    // Default constructor is needed for JSON deserialization
    public DowDate() {
    }

    // Constructor
    public DowDate(int DoW, LocalTime time) {
        this.DoW = DoW;
        this.DayTime = time;
    }

    // Getters (and optionally setters)
    public int getDoW() {
        return DoW;
    }

    public void setDoW(int DoW) {
        this.DoW = DoW;
    }

    public LocalTime getDayTime() {
        return DayTime;
    }

    public void setDayTime(LocalTime DayTime) {
        this.DayTime = DayTime;
    }

    /*
    public boolean isAfter(DowDate comparer) {

        if (this.DoW > comparer.DoW) {
            return true;
        }
        return this.DayTime.isAfter(comparer.DayTime);
    }
    */

    @Override
    public int compareTo(DowDate comparer) {
        if (this.DoW > comparer.DoW) {
            return 1;
        }
        else if (this.DoW < comparer.DoW) {
            return -1;
        }
        else { //same DoW
            return this.DayTime.compareTo(comparer.DayTime); //compare time of day
        }
    }
}
