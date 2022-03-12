package model;

import java.text.DecimalFormat;

public class HeartModel{
    int rate;
    int PRinterval;
    int QTinterval;
    public int getPP(int rate){
//s
        DecimalFormat df = new DecimalFormat("0.000");
        String pp=df.format((float)60/rate);
        Double pp1=Double.valueOf(pp);
        DecimalFormat df1 = new DecimalFormat("0");
        String pp2=df1.format(pp1*1000);
        int pp3=Integer.valueOf(pp2);

        return pp3;


    }

}
