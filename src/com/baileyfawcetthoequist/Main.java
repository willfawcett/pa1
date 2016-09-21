package com.baileyfawcetthoequist;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Hello World!");

        int pairsCount = 100;
        EuclidResult[] euclidResults = new EuclidResult[pairsCount];

        //GENERATE 100 pairs of random integers
        for(int i = 0; i < pairsCount; i++){
            euclidResults[i] = new EuclidResult(); //GDC is computed and timed in constructor
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
}
