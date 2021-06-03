package com.rosalieraz.cmsc125;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        JobList job_list = new JobList();
        Memory memory = new Memory();

        System.out.println("Choose Algorithm: [1] Worst-Fit [2] Best Fit [3] First-Fit");
        Scanner scanner= new Scanner(System.in);

        int algo = scanner.nextInt();

        if(algo == 1){
            memory.sort_DESC();
        } else if(algo == 2)
            memory.sort_ASC();
        else if(algo > 3) {
            System.out.println("Invalid Choice of Algorithm");
            return;
        }

        int time = 1;
        while(!job_list.is_exhausted()) {
            if(!memory.is_full()) {
                if(memory.is_empty() && time > 1)
                    break;
                memory.allocate_blocks(job_list);
            }

            execute(memory, time);
            job_list.increment_WT();

            time++;
        }

        display_performance(memory, time, algo);

        scanner.close();
    }

    static void execute(Memory m, int time) {
        System.out.println("------------------------------ AT TIME t = " + time + " ------------------------------");
        System.out.println();
        for (Block b: m.block_list) {
            if(b.job_occupant!=null) {
                System.out.println("Job " + b.job_occupant.id + " has been allocated in memory block " + b.id + " and will reside for " + b.job_occupant.burst_time + " ms");
                b.job_occupant.burst_time--;
                m.processed_jobs_count++;

                if(b.job_occupant.burst_time == 0) {
                    b.job_occupant.status = "done";
                    m.completed_jobs.add(b.job_occupant);
                    b.deallocate_block();
                }
            } else {
                b.calc_exhausted_space();
                b.setMax_free_space();
            }
        }
        System.out.println();
    }

    static void display_performance(Memory m, int time, int algo) {
        int total_unused, total_exhausted, sum_WT;

        total_exhausted = total_unused = sum_WT = 0;

        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(2);

        for (Job j: m.completed_jobs) {
            sum_WT += j.waiting_time;
        }

        for(Block b: m.block_list) {
            if(b.exhausted_space.size() > 0)
                total_exhausted += b.exhausted_space.get(0);

            b.setMax_free_space();

            if(b.free_space.size() > 0) {
                total_unused += b.free_space.get(0);
            }

        }

        System.out.println();
        if(algo == 1)
            System.out.println("=============================== WORST-FIT ===============================");
        else if(algo == 2)
            System.out.println("=============================== BEST-FIT ===============================");
        else
            System.out.println("=============================== FIRST-FIT ===============================");

        System.out.println();
        System.out.println("AVERAGE THROUGHPUT: " + format.format(m.processed_jobs_count / (float)(time-1)) + " jobs per unit of time");
        System.out.println("AVERAGE WAITING QUEUE LENGTH: " + format.format(m.process_inQueue_count / (float) (time-1)) + " jobs per unit of time");
        System.out.println("AVERAGE WAITING TIME: " + format.format((float) sum_WT/ (float)(m.completed_jobs.size())) + " unit of time");

        System.out.println();
        System.out.println("TOTAL UNUSED PARTITION: " + format.format(((float) total_unused / 50000) * 100) + "% out of 50 000 memory capacity");
        System.out.println("TOTAL HEAVILY USED PARTITION: " + format.format(((float) total_exhausted / 50000) * 100) + "% out of 50 000 memory capacity");

        System.out.println();
        System.out.println("------------------------ INTERNAL FRAGMENTATION ------------------------");
        System.out.println("Note: I.F. refers to free spaces in each allocation, where current job's size < block's size.");
        System.out.println();

        for (Block b: m.block_list) {
            if(b.get_total_internal_fragmentation() > 0) {
                System.out.println("Block " + b.id + "'s total internal fragmentation: " + b.get_total_internal_fragmentation() + " units of memory");
                System.out.println("Block " + b.id + "'s average internal fragmentation: " + format.format((float) b.get_total_internal_fragmentation() / (time-1)) + " units of memory");
                System.out.println();
            } else
                System.out.println("Block " + b.id + " was not allocated to any job.");
        }
    }
}
