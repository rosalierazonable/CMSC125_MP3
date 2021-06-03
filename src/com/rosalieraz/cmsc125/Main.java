package com.rosalieraz.cmsc125;

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
            memory.allocate_blocks(job_list.get_job_list());
        }
        scanner.close();
    }
}
