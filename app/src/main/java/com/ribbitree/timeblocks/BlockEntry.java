package com.ribbitree.timeblocks;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"hour", "minute"})
public class BlockEntry {

    @NonNull
    private String hour = "";
    @NonNull
    private String minute = "";
    @ColumnInfo(name = "entry_content")
    private String entry = "";

    private Boolean hasEntry = false;
    private Boolean editable = true;
    private Boolean hasText = false;

    @Ignore
    public BlockEntry(int hour, Boolean half) {
        String temp = Integer.toString(hour);
        this.hour = (temp.length() == 2) ? temp : "0" + temp;
        this.minute = (half) ? "30" : "00";
    }

    public BlockEntry(){}

    public String getTime() {
        return this.hour + ":" + this.minute;
    }
    public String getHour() {
        return this.hour;
    }
    public void setHour(String hour) {
        this.hour = hour;
    }
    public String getMinute() {
        return this.minute;
    }
    public void setMinute(String minute) {
        this.minute = minute;
    }
    public Boolean getHasEntry() {
        return hasEntry;
    }
    public void setHasEntry(Boolean hasEntry) {
        this.hasEntry = hasEntry;
    }
    public String getEntry() {
        return this.entry;
    }
    public void setEntry(String entry) {
        this.entry = entry;
        setHasText(true);
    }
    public Boolean getEditable() {
        return this.editable;
    }
    public void setEditable(Boolean editable) {
        this.editable = editable;
    }
    public Boolean getHasText() {
        return hasText;
    }
    public void setHasText(Boolean hasText) {
        this.hasText = hasText;
    }
}