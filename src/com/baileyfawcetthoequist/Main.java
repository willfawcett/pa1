package com.baileyfawcetthoequist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Long> num1List = new ArrayList<>();
        List<Long> num2List = new ArrayList<>();
        List<Long> gcdV1List = new ArrayList<>();
        List<Long> gcdV2List = new ArrayList<>();
        List<Long> v1Runtimes = new ArrayList<>();
        List<Long> v2Runtimes = new ArrayList<>();

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
            v1Runtimes.add(euclidResults[i].v1ms);
            v2Runtimes.add(euclidResults[i].v2ms);
        }

        System.out.format("ORIGINAL STATS\n%-15s\t%-15s\n","Statistics","Milliseconds");
        printTable(v1Runtimes);
        System.out.format("MODIFIED STATS\n%-15s\t%-15s\n","Statistics","Milliseconds");
        printTable(v2Runtimes);


        System.out.format("ORIGINAL\n%-10s\t%-10s\t%-10s\t%-10s\n","Number One","Number Two", "GCD", "Runtime (ms)");
        for(int i = 0; i < 100; i++){
            System.out.format("%-10d\t%-10d\t%-10d\t%-10f\n",num1List.get(i),num2List.get(i), gcdV1List.get(i), ((float)v1Runtimes.get(i))/1000);
        }
        System.out.format("MODIFIED\n%-10s\t%-10s\t%-10s\t%-10s\n","Number One","Number Two", "GCD", "Runtime (ms)");
        for(int i = 0; i < 100; i++){
            System.out.format("%-10d\t%-10d\t%-10d\t%-10f\n",num1List.get(i),num2List.get(i), gcdV2List.get(i), ((float)v2Runtimes.get(i))/1000);
        }

        //SORT the array based on V1 algorithm running time

        //Generate stats for V1


        //SORT the array based on V2 algorithm running time

        //Generate stats for V2

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


