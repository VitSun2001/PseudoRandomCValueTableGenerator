package ru.somniumcraft;

import java.util.Map;

public class Main {

    private static final int TEST_COUNT = 100;
    private static final int MAX_TRIES = 1000000000;
    private static Map<Double, Double> cValueMap;

    public static void main(String[] args) {
        CValueTableGenerator cValueTableGenerator = new CValueTableGenerator();
        cValueMap = cValueTableGenerator.getCValueMap();

        System.out.println("Number of tests per probability: " + TEST_COUNT);
        System.out.println("---");
        for (var entry : cValueMap.entrySet()) {
            double sum = 0;
            for(int i = 1; i <= TEST_COUNT; i++){
                double cVal = entry.getValue();
                for (int n = 1; n < MAX_TRIES; n++) {
                    double probability = cVal * n;
                    if (Math.random() < probability) {
                        sum += n;
                        break;
                    }
                }
            }
            System.out.println("Average tries before proc for probability " + entry.getKey() + " :\t" + sum / TEST_COUNT);
        }
        System.out.println("---");
    }
}
