package com.rosalieraz.cmsc125;

/*
 * MEMORY
 */

import java.util.*;

public class Memory {
    boolean is_full;
    int processed_jobs_count; // number of jobs that have been assigned to a block
    int process_inQueue_count; // number of jobs waiting for every iteration
    int total_waiting_time;

    ArrayList<Block> block_list = new ArrayList<>(); // list of memory blocks
    ArrayList<Job> completed_jobs = new ArrayList<>(); // list of jobs that have been completed

    Queue<Job> job_in_waiting = new LinkedList<>(); // jobs that are waiting

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
        this.total_waiting_time = 0;
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
            System.out.println(b.id + ": " + b.size);
        }
    }

    void display_block_occupied() {
        for(Block b: this.block_list) {
            if(b.job_occupant != null)
                System.out.println(b.id + ": " + b.size + " | " + b.job_occupant.id + ": " + b.job_occupant.size);
            else
                System.out.println(b.id + ": " + b.size + " | " + "No job occupant");
        }
    }

    boolean is_full() {
        for (Block b: this.block_list) {
            if (b.status.equals("free"))
                return false;
        }
        return true;
    }

    boolean is_empty() {
        for (Block b: this.block_list) {
            if (!b.status.equals("free"))
                return false;
        }
        return true;
    }

    void allocate_blocks(JobList jobList) {
        for(Job j: jobList.job_list) {
           if(j.status.equals("free")) {
               if(!this.is_full()) {
                   for (Block block : this.block_list) {
                       if (block.status.equals("free")) {
                           if(j.size <= block.size) {
                               block.assign_to_job(j);

                               j.is_assignable = false;
                               j.status = "assigned";
                               break;
                           }
                       }
                   }
                   if(j.is_assignable) {
                       this.total_waiting_time++;
                       this.job_in_waiting.add(j);
                       this.process_inQueue_count++;
                   }
               }
           }
        }
        this.is_full = true;
    }
}
