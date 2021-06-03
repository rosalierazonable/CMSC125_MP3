package com.rosalieraz.cmsc125;

/*
 * BLOCK
 */

import java.util.*;

public class Block {
    final int id;
    int size;  // memory size
    int job_count; // count of jobs that the block has accommodated
    int max_free_space; //max unused memory space

    String status; // either free or occupied
    ArrayList<Integer> free_space = new ArrayList<>();
    ArrayList<Integer> exhausted_space = new ArrayList<>(); // list of exhausted space, its minimum corresponds to the heavily used/exhausted space
    Job job_occupant;

    Block(int id, int size) {
        this.id = id;
        this.size = size;
        this.job_occupant = null;
        this.status = "free";
        this.job_count = 0;
    }

    void assign_to_job(Job j) {
        this.job_occupant = j;
        this.job_count++;
        this.status = "occupied";
        this.exhausted_space.add(j.size);
        this.free_space.add(this.size - j.size);
    }

    void deallocate_block() {
        this.job_occupant = null;
        this.status = "free";
    }

    int calc_exhausted_space() {
        Collections.sort(this.exhausted_space);
        if(!this.exhausted_space.isEmpty())
            return this.exhausted_space.get(0);
        else
            return -1;
    }

    void setMax_free_space() {
        int temp = this.calc_exhausted_space();
        if(temp > 0)
            Collections.sort(this.free_space);
    }

    int get_total_internal_fragmentation() {
        int sum = 0;
        for (int internal_fragmentation: this.free_space) {
            sum += internal_fragmentation;
        }
        return sum;
    }
}
