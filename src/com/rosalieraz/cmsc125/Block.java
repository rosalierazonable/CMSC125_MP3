package com;

/*
 * BLOCK
 */

import java.util.*;

public class Block {
    final int id;
    int size;
    Job job_occupant;
    String status;
    int job_count;
    ArrayList<Integer> free_space = new ArrayList<>();
    ArrayList<Integer> exhausted_space = new ArrayList<>();

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
        this.exhausted_space.add(j.burst_time);
    }

    void kick_job_occupant() {
        this.free_space.add((this.size - this.job_occupant.size));
        this.job_occupant = null;
        this.status = "free";
    }
}