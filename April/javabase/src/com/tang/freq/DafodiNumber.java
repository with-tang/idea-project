package com.tang.freq;

/**
 * Created by tang on 2017/4/1.
、输出所有的水仙花数，把谓水仙花数是指一个数3位数，其各各位数字立方和等于其本身，
        例如： 153 = 1*1*1 + 3*3*3 + 5*5*5
 */
class DafodilNumber{

    public static void main(String[] args){

        System.out.println("以下是所有的水仙花数");

        int number = 100;     // 由于水仙花数是三位数，故由100开始算起

        int i, j, k;     // i  j  k  分别为number 的百位、十位、个位

        for (int sum; number<1000; number++){

            i=number/100;  j=(number-i*100)/10;  k=number-i*100-j*10;

            sum=i*i*i+j*j*j+k*k*k;

            if (sum==number) System.out.println(number+" is a dafodil number! ");

        }

    }

}