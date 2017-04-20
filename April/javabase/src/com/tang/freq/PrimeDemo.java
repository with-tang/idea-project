package com.tang.freq;

/**
 * Created by tang on 2017/4/5.
 */
public class PrimeDemo {
    public static void main(String[] args) {
        for (int i = 2; i < 100; i++)
            if (isPrime(i)) {
                System.out.println(i + " is a prime");
            } else System.out.println(i + " is not a prime");
    }

    public static boolean isPrime(int n)
    {
        boolean flag=false;
        if(n==2||n==3)return true;
        else {
            for (int i = 2; i < n; i++) {
                if (n % i == 0) {
                    flag = false;
                    break;
                } else flag = true;
            }
            return flag;
        }
    }
}
