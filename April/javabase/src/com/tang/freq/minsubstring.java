package com.tang.freq;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 获取一个子串在字符串中出现的次数。"nbawernbatyunbaidfnbaghjnba" nba出现了几次+1
 * Created by tang on 2017/4/3.
 */
public class minsubstring {
    public static void main(String[] args)
    {
        String originStr="nbawernbatyunbaidfnbaghjnba";
        char[] strChar=originStr.toCharArray();
        char[] sub="nba".toCharArray();
        int count=0;
        LinkedList ll=new LinkedList();
        for(int i=0;i<=strChar.length-sub.length;i++)
        {
            if((strChar[i]==sub[0])&&(strChar[i+1]==sub[1])&&(strChar[i+2]==sub[2])) {
                    count++;
                    ll.add(i);
            }
            else continue;
        }
        System.out.println("子串个数："+count);
        System.out.println("出现的位置：");
        Iterator it=ll.iterator();
        while(it.hasNext())
        {
            System.out.print(it.next()+", ");
        }
        }

}
