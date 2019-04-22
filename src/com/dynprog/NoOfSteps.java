package com.dynprog;


/**
 * Given a number of steps, "n" find the number of possible ways to climb up those steps either 1, 2 or 3 steps at a time
 */
public class NoOfSteps {
    private static final int NO_OF_STEPS = 50;

    private static long noOfStepsMemo(int steps, long[] waysArr) {
        if(steps <= 0) {
            return 0;
        }
        if(steps == 1){
            return 1;
        }

        if(waysArr[steps -1] == 0) {
            waysArr[steps -1] = noOfStepsMemo(steps -1, waysArr) + noOfStepsMemo(steps -2, waysArr) + noOfStepsMemo(steps -3, waysArr);
        }

        return waysArr[steps -1];
    }

    private static long noOfStepsDP(int steps) {

        long[] waysArr = new long[steps];
        waysArr[0] = 1;
        waysArr[1] = 1;
        waysArr[2] = 2;

        for(int i=3; i< steps; i++) {
            waysArr[i] = waysArr[i-1] + waysArr[i-2] + waysArr[i-3];
        }
        return waysArr[steps -1];
    }


    private static long noOfSteps(int steps) {
        long[] waysArr = new long[steps];
        return noOfStepsMemo(steps, waysArr);
    }

    public static void main(String[] args) {
        long before = System.currentTimeMillis();
        System.out.println("There are " + noOfSteps(NO_OF_STEPS) + " ways to climb up " + NO_OF_STEPS + " steps");
        System.out.println(" This took " + (System.currentTimeMillis() - before) + " ms");


        before = System.currentTimeMillis();
        System.out.println("There are " + noOfStepsDP(NO_OF_STEPS) + " ways to climb up " + NO_OF_STEPS + " steps");
        System.out.println(" This took " + (System.currentTimeMillis() - before) + " ms");
    }
}
