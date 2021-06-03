package com.rosalieraz.cmsc125;

/*
 * JOB
 */

import java.util.*;

public class Job {
    final int id; // number of processes
    int burst_time;
    int size;
    int waiting_time;
    boolean is_assignable;
    String status;

    Job(int id, int burst_time, int size) {
        this.waiting_time = 0;
        this.id = id;
        this.burst_time = burst_time;
        this.size = size;
        this.is_assignable = (size < 9500);
        this.status = "free";
    }
}
