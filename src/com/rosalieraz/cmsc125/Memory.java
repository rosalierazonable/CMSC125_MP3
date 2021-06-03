package com.rosalieraz.cmsc125;

/*
 * MEMORY
 */

import java.util.*;

public class Memory {
    ArrayList<Block> block_list = new ArrayList<>();
    ArrayList<Block> processedB_list = new ArrayList<>();
    int processed_jobs_count;
    int process_inQueue_count;
    boolean is_full;

    Memory() {
        ArrayList<Block> block_list = new ArrayList<>();

        block_list.add(new Block(1, 9500));
        block_list.add(new Block(2, 7000));
        block_list.add(new Block(3, 4500));
        block_list.add(new Block(4, 8500));
        block_list.add(new Block(5, 3000));
        block_list.add(new Block(6, 9000));
        block_list.add(new Block(7, 1000));
        block_list.add(new Block(8, 5500));
        block_list.add(new Block(9, 1500));
        block_list.add(new Block(10, 500));

        this.block_list = block_list;
        this.processed_jobs_count = 0;
        this.process_inQueue_count = 0;
        this.is_full = false;
    }

    void sort_ASC() {
        this.block_list.sort(new SizeAscComparator());
    }

    void sort_DESC() {
        this.block_list.sort(new SizeDescComparator());
    }

    void display_blocks() {
        for(Block b: this.block_list) {
            System.out.println(b.id + ": " + b.size + " ");
        }
    }

    void is_full() {
        for (Block b: this.block_list) {
            if (b.status.equals("free")) {
                this.is_full = false;
                return;
            }
        }
        this.is_full = true;
    }

    void allocate_blocks(ArrayList<Job> jobs) {
        for(Job j: jobs) {
            for (Block block : this.block_list) {
                if (block.job_occupant == null && block.status.equals("free")) {
                    if(j.size <= block.size) {
                        block.assign_to_job(j);
                        j.is_assignable = false;
                        j.status = "in progress";
                    }
                }
            }
        }
    }
}
