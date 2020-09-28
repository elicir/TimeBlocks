package com.ribbitree.timeblocks;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class BlockEntry {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String hour = "";
    private String minute = "";
    @ColumnInfo
    private String entry = "";

    private Boolean hasEntry;
    private Boolean editable;
    private Boolean hasText;

    public BlockEntry() {}

    public BlockEntry(String hour, String minute, Boolean hasEntry, Boolean editable, Boolean hasText) {
        this.hour = hour;
        this.minute = minute;
        this.hasEntry = hasEntry;
        this.editable = editable;
        this.hasText = hasText;
    }

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