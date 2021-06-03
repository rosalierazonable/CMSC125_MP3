package com.rosalieraz.cmsc125;

import java.util.Comparator;

public class SizeDescComparator implements Comparator<Block> {

    @Override
    public int compare(Block b1, Block b2)
    {
        if (b1.size == b2.size) {
            return 0;
        }
        else if (b1.size < b2.size) {
            return 1;
        }
        else {
            return -1;
        }
    }
}
