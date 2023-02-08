package ru.somniumcraft;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.Math.abs;

public class CValueTableGenerator {
    private Map<Double,Double> CValueMap;

    public CValueTableGenerator()
    {
        CValueMap = new LinkedHashMap<Double,Double>();
        generate();
    }

    private void generate() {
        for(double i = 1; i < 10000; i++)
        {
            double p = (double)Math.round((i*0.0001) * 10000d) / 10000d;
            CValueMap.put(p, calculateCValueFromProbability(p));
        }
    }

    private double calculateCValueFromProbability(double p)
    {
        double Cupper = p;
        double Clower = 0;
        double Cmid;
        double p1;
        double p2 = 1;
        int runs = 1;
        while (true){
            Cmid = (Cupper + Clower)/2d;
            double pProcOnN = 0;
            double pProcByN = 0;
            double sumNpProcOnN = 0;

            int maxFails = (int)Math.ceil(1 / Cmid);
            for (int N = 1; N <= maxFails; ++N)
            {
                pProcOnN = Math.min(1.0, N * Cmid) * (1 - pProcByN);
                pProcByN += pProcOnN;
                sumNpProcOnN += N * pProcOnN;
            }
            p1 = (1 / sumNpProcOnN);

            if (abs(p1 - p2) <= 0) break;

            if(p1 > p){
                Cupper = Cmid;
            }else
            {
                Clower = Cmid;
            }
            p2 = p1;
            ++runs;
        }

        return Cmid;
    }

    public Map<Double, Double> getCValueMap(){
        return this.CValueMap;
    }

}
