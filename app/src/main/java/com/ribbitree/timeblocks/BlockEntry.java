package com.ribbitree.timeblocks;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"hour", "minute"})
public class BlockEntry {

    private String hour;
    private String minute;
    @ColumnInfo(name = "entry_content")
    private String entry = "";

    private Boolean hasEntry = false;
    private Boolean editable = true;
    private Boolean hasText = false;

    public BlockEntry(int hour, Boolean half) {
        String temp = Integer.toString(hour);
        this.hour = (temp.length() == 2) ? temp : "0" + temp;
        this.minute = (half) ? "30" : "00";
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
    public String getEntry() {
        return this.entry;
    }
    public void setEntry(String entry) {
        this.entry = entry;
        this.hasText = true;
    }
    public Boolean isEditable() {
        return this.editable;
    }
    public void setEditable() {
        this.editable = true;
    }
    public Boolean hasText() {
        return hasText;
    }
}