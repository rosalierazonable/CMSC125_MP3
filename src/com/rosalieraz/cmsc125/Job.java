package com.rosalieraz.cmsc125;

/*
 * JOB
 */

public class Job {
    final int id; // number of processes
    int size; // job size
    int burst_time; // time the job will use the memory block
    int waiting_time; // how long has it been waiting before it was assigned to the memory block
    boolean is_assignable; // can the job be assigned to a block
    String status; // free, assigned, done

    Job(int id, int burst_time, int size) {
        this.id = id;
        this.size = size;
        this.waiting_time = 0;
        this.burst_time = burst_time;
        this.is_assignable = (size < 9500); // the job should not exceed the largest memory block
        this.status = "free";
    }
}
