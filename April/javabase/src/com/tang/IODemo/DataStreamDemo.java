package com.tang.IODemo;

import java.io.*;

class Member
{
    private String name;
    private int age;
    public Member()
    {  }
    public Member(String name,int age)
    {
        this.name=name;
        this.age=age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
/**
 * Created by tang on 2017/4/5.
 */
public class DataStreamDemo {
    public static void main(String[] args)
    {
        Member members[]={
                new Member("tang",11),
                new Member("liang",11),
                new Member("wang",11)
        };
        try{
            DataOutputStream outputStream=new DataOutputStream(new FileOutputStream("DataStream"));
            for(Member mem:members)
            {
                outputStream.writeUTF(mem.getName());
                outputStream.writeInt(mem.getAge());
            }
            outputStream.flush();
            outputStream.close();

            DataInputStream inputStream =new DataInputStream(new FileInputStream("DataStream"));
            for(int i=0;i<members.length;i++)
            {
                String name=inputStream.readUTF();
                int score=inputStream.readInt();
                members[i]=new Member(name,score);
            }
            inputStream.close();
        }catch (IOException ex)
        {
            ex.printStackTrace();
        }
        for(int i=0;i<members.length;i++)
        {
            System.out.println(members[i].getName()+" : "+members[i].getAge());
        }
    }
}
