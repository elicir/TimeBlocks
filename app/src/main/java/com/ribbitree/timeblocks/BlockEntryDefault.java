package com.ribbitree.timeblocks;

import java.util.ArrayList;

public class BlockEntryDefault {

    public static ArrayList<BlockEntry> populateBlockEntries() {
        ArrayList<BlockEntry> blockEntries = new ArrayList<BlockEntry>();
        for (int i = 0; i < 48; i += 2) {
            int hour = i / 2;
            String temp = Integer.toString(hour);
            String hourString = (temp.length() == 2) ? temp : "0" + temp;
            blockEntries.add(new BlockEntry(hourString, "00", false, true, false));
            blockEntries.add(new BlockEntry(hourString, "30", false, true, false));
        }
        blockEntries.add(new BlockEntry("24", "00", false, true, false));
        return blockEntries;
    }
}
