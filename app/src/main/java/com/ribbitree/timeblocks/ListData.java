package com.ribbitree.timeblocks;

public class ListData{
    private String hour;
    private String minute;
    private Boolean hasEntry;
    public ListData(int hour, Boolean half) {
        String temp = Integer.toString(hour);
        this.hour = (temp.length() == 2) ? temp : "0" + temp;
        this.minute = (half) ? "30" : "00";
        this.hasEntry = false;
    }
    public String getTime() {
        return this.hour + ":" + this.minute;
    }
    public Boolean hasEntry() {
        return hasEntry;
    }
    public void setHasEntry(Boolean hasEntry) {
        this.hasEntry = hasEntry;
    }
}