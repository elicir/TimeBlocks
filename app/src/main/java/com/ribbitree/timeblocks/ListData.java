package com.ribbitree.timeblocks;

public class ListData{
    private String hour;
    private String minute;
    public ListData(int hour, Boolean half) {
        String temp = Integer.toString(hour);
        this.hour = (temp.length() == 2) ? temp : "0" + temp;
        this.minute = (half) ? "30" : "00";
    }
    public String getTime() {
        return this.hour + ":" + this.minute;
    }
}