package com;

import java.util.*;

public class JobList {
    ArrayList<Job> job_list = new ArrayList<>();

    JobList() {
        ArrayList<Job> job_list = new ArrayList<>();

        job_list.add(new Job(1, 5, 5760));
        job_list.add(new Job(2, 4, 4190));
        job_list.add(new Job(3, 8, 3290));
        job_list.add(new Job(4, 2, 2030));
        job_list.add(new Job(5, 2, 2550));

        job_list.add(new Job(6, 6, 6990));
        job_list.add(new Job(7, 8, 8940));
        job_list.add(new Job(8, 10, 740));
        job_list.add(new Job(9, 7, 3930));
        job_list.add(new Job(10, 6, 6890));

        job_list.add(new Job(11, 5, 6580));
        job_list.add(new Job(12, 8, 3820));
        job_list.add(new Job(13, 9, 9140));
        job_list.add(new Job(14, 10, 420));
        job_list.add(new Job(15, 10, 220));

        job_list.add(new Job(16, 7, 7540));
        job_list.add(new Job(17, 3, 3210));
        job_list.add(new Job(18, 1, 1380));
        job_list.add(new Job(19, 9, 9850));
        job_list.add(new Job(20, 3, 3610));

        job_list.add(new Job(21, 7, 7540));
        job_list.add(new Job(22, 2, 2710));
        job_list.add(new Job(23, 8, 8390));
        job_list.add(new Job(24, 5, 5950));
        job_list.add(new Job(25, 10, 760));

        this.job_list = job_list;
    }

    boolean is_exhausted() {
        for(Job j: this.job_list) {
            if(j.status != "done")
                return false;
        }
        return true;
    }

    ArrayList<Job> get_job_list() {
        return this.job_list;
    }

    /*
    void sort_ASC() {
      this.job_list.sort(new SizeAscComparator());
    }

    void sort_DESC() {
      this.job_list.sort(new SizeDescComparator());
    }
    */

    void display_jobs() {
        for(Job j: this.job_list) {
            System.out.println(j.id + ": " + j.size + " ");
        }
    }
}
