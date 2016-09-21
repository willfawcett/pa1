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
        System.out.println("Hello World!");

        int pairsCount = 100;
        EuclidResult[] euclidResults = new EuclidResult[pairsCount];

        //GENERATE 100 pairs of random integers
        for(int i = 0; i < pairsCount; i++){
            euclidResults[i] = new EuclidResult(); //GDC is computed and timed in constructor
            num1List.add(euclidResults[i].numberOne);
            num2List.add(euclidResults[i].numberTwo);
            gcdV1List.add(euclidResults[i].gdcV1);
            gcdV2List.add(euclidResults[i].gdcV2);
            v1Runtimes.add(euclidResults[i].v1ns);
            v2Runtimes.add(euclidResults[i].v2ns);
        }

        System.out.format("ORIGINAL STATS\n%-15s\t%-15s\n","Statistics","Microseconds");
        printTable(v1Runtimes);
        System.out.format("MODIFIED STATS\n%-15s\t%-15s\n","Statistics","Microseconds");
        printTable(v2Runtimes);


        System.out.format("ORIGINAL\n%-10s\t%-10s\t%-10s\t%-10s\n","Number One","Number Two", "GCD", "Runtime (ns)");
        for(int i = 0; i < pairsCount; i++){
            System.out.format("%-10d\t%-10d\t%-10d\t%-10f\n",num1List.get(i),num2List.get(i), gcdV1List.get(i), ((float)v1Runtimes.get(i)));
        }
        System.out.format("MODIFIED\n%-10s\t%-10s\t%-10s\t%-10s\n","Number One","Number Two", "GCD", "Runtime (ns)");
        for(int i = 0; i < pairsCount; i++){
            System.out.format("%-10d\t%-10d\t%-10d\t%-10f\n",num1List.get(i),num2List.get(i), gcdV2List.get(i), ((float)v2Runtimes.get(i)));
        }









        /***
         *  VERSION 1 STATS
         */
        //SORT the array based on V1 algorithm running time
        Arrays.sort(euclidResults, new Comparator<EuclidResult>() {
            public int compare(EuclidResult er1, EuclidResult er2) {
                return toIntExact(er1.v1ns - er2.v1ns);
            }
        });
        //Output list and Generate stats for V1
        sum = 0.0;
        System.out.format("ORIGINAL\n%-10s\t%-10s\t%-10s\t%-10s\n","Number One","Number Two", "GCD", "Runtime (ms)");
        for(int i = 0; i < pairsCount; i++){
            EuclidResult e = euclidResults[i];
            sum += e.v1ms;
            System.out.format("%-10d\t%-10d\t%-10d\t%-10f\n",e.numberOne,e.numberTwo, e.gdcV1, e.v1ms);
        }

        //get stats for v1 (original algorithm)
        v1Min = euclidResults[0].v1ns;
        v1Max = euclidResults[pairsCount-1].v1ns;
        v1Avg = sum / pairsCount;
        v1Median = (euclidResults[49].v1ns + euclidResults[50].v1ns) / 2;

        //display stats
        System.out.format("ORIGINAL STATS (ms)\n%-10s\t%-10s\t%-10s\t%-10s\n","Min","Max", "Average", "Median");
        System.out.format("%-10f\t%-10f\t%-10f\t%-10f\n",v1Min / 1000000.0,v1Max / 1000000.0, v1Avg, v1Median / 1000000.0);

        /***
         * VERSION 2 STATS - still has a bug
         */
        //RE-SORT the array based on V2 algorithm running time
        Arrays.sort(euclidResults, new Comparator<EuclidResult>() {
            public int compare(EuclidResult er1, EuclidResult er2) {
                return toIntExact(er1.v2ns - er2.v2ns);
            }
        });
        //Output list and Generate stats for V2
        sum = 0.0;
        System.out.format("IMPROVED\n%-10s\t%-10s\t%-10s\t%-10s\n","Number One","Number Two", "GCD", "Runtime (ms)");
        for(int i = 0; i < pairsCount; i++){
            EuclidResult e = euclidResults[i];
            sum += e.v2ms;
            System.out.format("%-10d\t%-10d\t%-10d\t%-10f\n",e.numberOne,e.numberTwo, e.gdcV2, e.v2ms);
        }

        //get stats for v1 (original algorithm)
        v2Min = euclidResults[0].v2ns;
        v2Max = euclidResults[pairsCount-1].v2ns;
        v2Avg = sum / pairsCount;
        v2Median = (euclidResults[49].v2ns + euclidResults[50].v2ns) / 2;

        //display stats
        System.out.format("ORIGINAL STATS (ms)\n%-10s\t%-10s\t%-10s\t%-10s\n","Min","Max", "Average", "Median");
        System.out.format("%-10f\t%-10f\t%-10f\t%-10f\n",v2Min / 1000000.0,v2Max / 1000000.0, v2Avg, v2Median / 1000000.0);







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


