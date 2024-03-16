package com.ontariotechu.sofe3980U.core;

import org.junit.Test;
import java.time.LocalDateTime;


public class PathFinderTest {
    @Test(expected = IllegalArgumentException.class)
    public static void matchingArriveDepart() {
        Airport airporta = new Airport("YYZ", "America/Toronto", 0);
        DowDate date = new DowDate(0, LocalDateTime.now());
        PathFinder.pathFind(MemoryStore.getInstance(), airporta, airporta, date, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public static void matchingArriveDepart2() {
        Airport airporta = new Airport("YYZ", "America/Toronto", 0);
        DowDate date = new DowDate(0, LocalDateTime.now());
        PathFinder.pathFind(MemoryStore.getInstance(), airporta, airporta, date, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public static void matchingArriveDepart3() {
        Airport airporta = new Airport("YYZ", "America/Toronto", 0);
        Airport airportb = new Airport("YYZ", "America/Toronto", 0);
        DowDate date = new DowDate(0, LocalDateTime.now());
        DowDate date2 = new DowDate(7, LocalDateTime.now());
        PathFinder.pathFind(MemoryStore.getInstance(), airporta, airportb, date2, date);
    }

    @Test(expected = NullPointerException.class)
    public static void nullInputs(){
        PathFinder.pathFind(MemoryStore.getInstance(), null, null, null, null);
    }

    @Test(expected = NullPointerException.class)
    public static void nullInputs2(){
        Airport airporta = new Airport("YYZ", "America/Toronto", 0);
        DowDate date = new DowDate(0, LocalDateTime.now());
        PathFinder.pathFind(null, airporta, null, date, date);
    }

    @Test(expected = NullPointerException.class)
    public static void nullInputs3(){
        Airport airporta = new Airport("YYZ", "America/Toronto", 0);
        DowDate date = new DowDate(0, LocalDateTime.now());
        DowDate date2 = new DowDate(1, LocalDateTime.now());
        PathFinder.pathFind(null, null, airporta, date, date2);
    }

    @Test(expected = NullPointerException.class)
    public static void nullInputs4(){
        Airport airporta = new Airport("YYZ", "America/Toronto", 0);
        Airport airportb = new Airport("NYC", "America/NewYork", 2);
        DowDate date2 = new DowDate(1, LocalDateTime.now());
        PathFinder.pathFind(MemoryStore.getInstance(), airportb, airporta, null, date2);
    }

    @Test(expected = NullPointerException.class)
    public static void nullInputs5(){
        Airport airporta = new Airport("YYZ", "America/Toronto", 0);
        DowDate date = new DowDate(0, LocalDateTime.now());
        DowDate date2 = new DowDate(1, LocalDateTime.now());
        PathFinder.pathFind(null, airporta, null, date, date2);
    }

    @Test(expected = NullPointerException.class)
    public static void nullInputs6(){
        Airport airporta = new Airport("YYZ", "America/Toronto", 0);
        DowDate date2 = new DowDate(1, LocalDateTime.now());
        PathFinder.pathFind(null, airporta, airporta, null, date2);
    }

    @Test(expected = NullPointerException.class)
    public static void nullInputs7(){
        Airport airporta = new Airport("YYZ", "America/Toronto", 0);
        Airport airportb = new Airport("NYC", "America/NewYork", 2);
        DowDate date2 = new DowDate(1, LocalDateTime.now());
        PathFinder.pathFind(null, airportb, airporta, date2, null);
    }

    @Test(expected = NullPointerException.class)
    public static void nullInputs8(){
        Airport airporta = new Airport("YYZ", "America/Toronto", 0);
        DowDate date2 = new DowDate(1, LocalDateTime.now());
        PathFinder.pathFind(null, null, airporta, date2, date2);
    }
}
