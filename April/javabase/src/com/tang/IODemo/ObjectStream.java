package com.tang.IODemo;

import java.io.*;

/**
 * Created by tang on 2017/4/5.
 * 写对象：ObjectInputStream 和ObjectOutputStream ，该流允许读取或写入用户自定义的类，但是要实现这种功能，
 * 被读取和写入的类必须实现Serializable接口，其实该接口并没有什么方法，可能相当于一个标记而已，
 * 但是确实不合缺少的。实例代码如下：
 */
public class ObjectStream {
    public static void main(String[] args)
    {
        ObjectInputStream ois=null;
        ObjectOutputStream oos=null;
        try{
            //将Student对象存储到Student.txt本地文件中
            oos=new ObjectOutputStream(new FileOutputStream("Student"));
            oos.writeObject(new Student("tang",20));
            oos.writeObject(new Student("wang",21));
            oos.writeObject(new Student("zhang",22));
            //读取本地文件的对象，
            ois=new ObjectInputStream(new FileInputStream("Student"));
            for(int i=0;i<3;i++)
            {
                System.out.println(ois.readObject());
            }
        }
        catch(IOException|ClassNotFoundException ex){
            ex.printStackTrace();
        }
        finally {
            try{
                ois.close();
                oos.close();
            }catch(IOException e)
            {
                e.printStackTrace();
            }
        }

    }
}
class Student implements Serializable
{
    private String name;
    private int age;
    Student(String name,int age)
    {
        super();
        this.name=name;
        this.age=age;
    }
    public String toString()
    {
        return "name="+name+"  age="+age;
    }
}
