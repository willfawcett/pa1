package com.baileyfawcetthoequist;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.Comparator;
/**
 * Created by Bailey-Fawcett-Hoequist on 9/20/16.
 *
 * Container for a set of integers, their GDC, and the time in MS to calculate GDC using two different Euclid algorithms
 * Includes methods to computer the GDC using both algorithms
 *
 */
public class EuclidResult {
    public long numberOne;   //randomly generated number, greater than 0 - using long to sidestep overflow problems
    public long numberTwo;   //randomly generated number, greater than 0 - using long to sidestep overflow problems
    public long gdcV1;       //GDC as calculated by V1
    public long gdcV2;       //GDC as calculated by V2 (should be the same in all cases, but space is cheap so why not...)
    public long v1ns;       //time (nanoseconds) to calculate GDC using Euler's algorithm 1
    public long v2ns;       //time (nanoseconds) to calculate GDC using Euler's algorithm 2 (improved)

    public double v1ms;     //time in milliseconds for original algorithm
    public double v2ms;     //time for improved algorithm in milliseconds

    public EuclidResult(){
        long startTime;     //used for timing the algorithms
        long runningTime;   //used for timing the algorithms

        //generate and set the two random number
        numberOne = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
        numberTwo = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);

        //calculate the GDC using Euclid's algorithm 1, and get the running time while we're at it
        startTime = System.nanoTime();
        gdcV1 = euclidV1(numberOne, numberTwo);
        v1ns = System.nanoTime() - startTime;
        v1ms = v1ns / 1000000.0;
        //System.out.println("v1: " + runningTime + "ns");
        //v1ms = TimeUnit.MICROSECONDS.convert(runningTime, TimeUnit.NANOSECONDS);

        //calculate the GDC using Euclid's algorithm 2
        startTime = System.nanoTime();
        gdcV2 = euclidV2(numberOne, numberTwo);
        v2ns = System.nanoTime() - startTime;
        v2ms = v2ns / 1000000.0;
        //System.out.println("v2: " + runningTime + "ns");
        //v2ms = TimeUnit.MICROSECONDS.convert(runningTime, TimeUnit.NANOSECONDS);

        //for debugging
        //System.out.println(numberOne + " " + numberTwo + " " + gdcV1 + " " + gdcV2 + " " + "v1ms:" +v1ms + "v2ms:" + v2ms);
    }

    /**
     * Euclid's Algorithm v1
     * returns the GDC of two input integers
     * returns 0 on invalid input
     */
    static long euclidV1(long a, long b){
        long quotient;
        long remainder;

        //make sure both a and b are greater than 0
        if(a < 1 || b < 1)
            return 0;

        remainder = -1; //ensure we start with a non-zero remainder
        while(remainder != 0){
            quotient = a / b;
            remainder = a - quotient * b;  //possibility for overflow error here...
            a = b;
            b = remainder;
        }
        return a; //this is the GDC
    }

    /**
     * Euclid's Algorithm v2 (improved)
     * returns the GDC of two input integers
     * returns 0 on invalid input
     */
    static long euclidV2(long a, long b){
        long remainder;

        //make sure both inputs are greater than 0
        if(a < 1 || b < 1)
            return 0;

        //make sure a > b
        if(a < b){
            long z = a;
            a = b;
            b = z;
        }

        remainder = -1; //start remainder at non-zero
        while(remainder != 0){
            remainder = a - b;
            if(remainder >= b){
                remainder = remainder - b;
                if(remainder >= b){
                    remainder = remainder - b;
                    if(remainder > b){
                        remainder = a - b * (a / b);
                    }
                }
            }
            a = b;
            b = remainder;
        }
        return a;  //this is the GDC
    }


}