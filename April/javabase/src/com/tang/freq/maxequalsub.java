package com.tang.freq;

/**
 * 求两个字符串的最大相同子串
 * 思路：用较小的串求字串，看较大的串中是否包含它，如果不包含则较小串继续求子串
 * Created by tang on 2017/4/3.
 */
public class maxequalsub {


    public  String getMaxSubString(String max,String min) {
        String temp = "";
        for (int i = 0; i < min.length(); i++) {
            for (int j = 0; j <= i; j++) {
                String subString = min.substring(j, min.length() - i + j);
                if (max.contains(subString)) {
                    if (temp.length() < subString.length()) {
                        temp = subString;
                    }
                }
            }
        }
        return temp;
    }
    public static  void main(String[] args)
    {
        String a="abcdefgabcdaaabbbsss";
        String b="abcdefa";

        String max=a.length()>b.length()?a:b;
        String min=a.length()>b.length()?b:a;
        String sub=new maxequalsub().getMaxSubString(max,min);
        System.out.println(sub);
    }
}

