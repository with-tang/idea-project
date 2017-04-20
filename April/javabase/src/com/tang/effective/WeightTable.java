package com.tang.effective;

/**
 * Created by tang on 2017/4/9.
 */
public class WeightTable {
public static void main(String [] args)
{
    double earthWeight=100d;
    double mass=earthWeight/Planet.EARTH.surfaceGravity();
    for(Planet p:Planet.values())
    {
        System.out.printf("Weight on %s is %f%n",p,p.surfaceWeight(mass));
    }

}
}
