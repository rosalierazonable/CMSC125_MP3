package com;

import java.util.Comparator;

public class SizeAscComparator implements Comparator<Block> {

    @Override
    public int compare(Block b1, Block b2)
    {
        return Integer.compare(b1.size, b2.size);
    }
}
