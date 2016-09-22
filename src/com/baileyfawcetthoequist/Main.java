package com.baileyfawcetthoequist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import static java.lang.Math.toIntExact;

public class Main {
    //hello
    public static void main(String[] args) {
        List<Long> num1List = new ArrayList<>();
        List<Long> num2List = new ArrayList<>();
        List<Long> gcdV1List = new ArrayList<>();
        List<Long> gcdV2List = new ArrayList<>();
        List<Long> v1Runtimes = new ArrayList<>();
        List<Long> v2Runtimes = new ArrayList<>();

        double sum; //for calculating averages
        double v1Avg;
        double v2Avg;
        Long v1Min;
        Long v2Min;
        Long v1Max;
        Long v2Max;
        Long v1Median;
        Long v2Median;

        // write your code here
        System.out.println("~EUCLIDS COMPARATOR~\n");

        int numberOfTests = 100;
        EuclidResult[] euclidResults = new EuclidResult[numberOfTests];

        //GENERATE 100 pairs of random integers
        for(int i = 0; i < numberOfTests; i++){
            euclidResults[i] = new EuclidResult(); //GCD is computed and timed in constructor
            num1List.add(euclidResults[i].numberOne);
            num2List.add(euclidResults[i].numberTwo);
            gcdV1List.add(euclidResults[i].gcdV1);
            gcdV2List.add(euclidResults[i].gcdV2);
            v1Runtimes.add(euclidResults[i].v1ns);
            v2Runtimes.add(euclidResults[i].v2ns);
        }


        /*
        System.out.format("\n/ORIGINAL STATS/\n%-15s\t%-15s\n","Statistics","Microseconds");
        printTable(v1Runtimes);
        System.out.format("\n/MODIFIED STATS/\n%-15s\t%-15s\n","Statistics","Microseconds");
        printTable(v2Runtimes);


        System.out.format("\n/ORIGINAL/\n%-10s\t%-10s\t%-10s\t%-10s\n","Number One","Number Two", "GCD", "Runtime (ns)");
        for(int i = 0; i < numberOfTests; i++){
            System.out.format("%-10d\t%-10d\t%-10d\t%-10f\n",num1List.get(i),num2List.get(i), gcdV1List.get(i), ((float)v1Runtimes.get(i)));
        }
        System.out.format("\n/MODIFIED/\n%-10s\t%-10s\t%-10s\t%-10s\n","Number One","Number Two", "GCD", "Runtime (ns)");
        for(int i = 0; i < numberOfTests; i++){
            System.out.format("%-10d\t%-10d\t%-10d\t%-10f\n",num1List.get(i),num2List.get(i), gcdV2List.get(i), ((float)v2Runtimes.get(i)));
        }

        //Conclusions section.  So far tells how many times the modified version takes less time than the original version
        //Compares the runtimes using .get() and if the original(a) is larger than the modified(b) then the modified(b) outperformed the original(a)
        //Prints the conclusion to the screen
        System.out.format("\n/CONCLUSIONS/\n");
        int v2Count = 0;
        for(int i = 0; i < numberOfTests; i++) {
            float a = ((float)v1Runtimes.get(i));
            float b = ((float)v2Runtimes.get(i));
            if(a > b) {
                v2Count += 1;
            }
        }
        System.out.format("Out of %d pairs of integers, the modified Euclid's algorithm outperformed the original in %d pairs.", numberOfTests, v2Count);

*/








        /***
         *  VERSION 1 STATS
         ***/
        //SORT the array based on V1 algorithm running time
        Arrays.sort(euclidResults, new Comparator<EuclidResult>() {
            public int compare(EuclidResult er1, EuclidResult er2) {
                return toIntExact(er1.v1RunTime - er2.v1RunTime);
            }
        });
        //Output list and Generate stats for V1
        sum = 0.0;
        System.out.format("\n/ORIGINAL/\n%-10s\t%-10s\n","Number One","Number Two", "GCD", "Runtime (ms)");
        for(int i = 0; i < numberOfTests; i++){
            EuclidResult e = euclidResults[i];
            sum += e.v1RunTime;
            System.out.format("%-10d\t%-10d\t%-10d\t%-10f\n",e.numberOne,e.numberTwo, e.gcdV1, e.v1RunTimeMS);
        }

        //get stats for v1 (original algorithm)
        v1Min = euclidResults[0].v1RunTime;
        v1Max = euclidResults[numberOfTests-1].v1RunTime;
        v1Avg = sum / numberOfTests;
        v1Median = (euclidResults[49].v1RunTime + euclidResults[50].v1RunTime) / 2;

        //display stats
        System.out.format("\n/ORIGINAL STATS/ (ms)\n%-10s\t%-10s\t%-10s\t%-10s\n","Min","Max", "Average", "Median");
        System.out.format("%-10f\t%-10f\t%-10f\t%-10f\n",v1Min / 1000000.0,v1Max / 1000000.0, v1Avg / 1000000.0, v1Median / 1000000.0);

        /***
         * VERSION 2 STATS - still has a bug
         */
        //RE-SORT the array based on V2 algorithm running time
        Arrays.sort(euclidResults, new Comparator<EuclidResult>() {
            public int compare(EuclidResult er1, EuclidResult er2) {
                return toIntExact(er1.v2RunTime - er2.v2RunTime);
            }
        });
        //Output list and Generate stats for V2
        sum = 0.0;
        System.out.format("\n\nIMPROVED\n%-10s\t%-10s\n","Number One","Number Two", "GCD", "Runtime (ms)");
        for(int i = 0; i < numberOfTests; i++){
            EuclidResult e = euclidResults[i];
            sum += e.v2RunTime;
            System.out.format("%-10d\t%-10d\t%-10d\t%-10f\n",e.numberOne,e.numberTwo, e.gcdV2, e.v2RunTimeMS);
        }

        //get stats for v1 (original algorithm)
        v2Min = euclidResults[0].v2RunTime;
        v2Max = euclidResults[numberOfTests-1].v2RunTime;
        v2Avg = sum / numberOfTests;
        v2Median = (euclidResults[49].v2RunTime + euclidResults[50].v2RunTime) / 2;

        //display stats
        System.out.format("IMPROVED STATS (ms)\n%-10s\t%-10s\t%-10s\t%-10s\n","Min","Max", "Average", "Median");
        System.out.format("%-10f\t%-10f\t%-10f\t%-10f\n",v2Min / 1000000.0,v2Max / 1000000.0, v2Avg / 1000000.0, v2Median / 1000000.0);

        //UPDATED FOR VERSION 2
        //Conclusions section.  So far tells how many times the modified version takes less time than the original version
        //Compares the runtimes using .get() and if the original(a) is larger than the modified(b) then the modified(b) outperformed the original(a)
        //Prints the conclusion to the screen
        System.out.format("\n/CONCLUSIONS/\n");
        int updatev2Count = 0;
        for(int i = 0; i < numberOfTests; i++) {
            EuclidResult e = euclidResults[i];
            if(e.v1ms > e.v2ms) {
                updatev2Count += 1;
            }
        }
        System.out.format("Out of %d pairs of integers, the modified Euclid's algorithm outperformed the original in %d pairs.", numberOfTests, updatev2Count);






        //Generate stats for comparison

        //Write out files (generate as CSV and manually save as excel for submission):
        //      Original_Euclid_Results.csv
        //      Original_Euclid_Statistics.csv
        //      Improved_Euclid_Statistics.csv
        //
    }

    private static void printTable(List<Long> runtimes){
        Collections.sort(runtimes);         //Easy way to find min and max
        long total = 0;
        double median;
        int index = runtimes.size()/2;    //Will be used in determining median

        for(Long time : runtimes){
            total += time;
        }
        double avg = total/(runtimes.size()*1.0);
        if(runtimes.size()%2==1){
            median = runtimes.get(index)*1.0;
        }
        else{
            median = (runtimes.get(index) + runtimes.get(index-1))/2.0;
        }
        System.out.format("%-15s\t%-15f\n","Maximum Time",runtimes.get(runtimes.size()-1)/1.0);
        System.out.format("%-15s\t%-15f\n","Minimum Time",runtimes.get(0)/1.0);
        System.out.format("%-15s\t%-15f\n","Average Time",avg);
        System.out.format("%-15s\t%-15f\n","Median Time",median);
    }

}

