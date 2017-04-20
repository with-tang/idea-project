package com.tang.IODemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * Created by tang on 2017/4/4.
 */
public class FileCopy {
    public static void main(String[] args)
    {
        boolean success=fileToCopy("w.jpg","ss.jpg");
        if(success){
            System.out.println("复制成功");
        }
        else System.out.println("复制文件失败");
    }

    /**
     *
     * @param source
     * @param desc
     * @return 创建成功
     */
    public static boolean fileToCopy(String source,String desc)
    {
        byte[] buffer=new byte[512];
        int numberRead=0;
        FileInputStream fis=null;
        FileOutputStream fos=null;
        try{
            fis=new FileInputStream(source);
            fos=new FileOutputStream(desc);
            while((numberRead=fis.read(buffer))!=-1)
            {
                fos.write(buffer,0,numberRead);
            }
        }catch(final IOException e)
        {
            e.printStackTrace();
            return false;
        }finally {
            try{
                fis.close();
                fos.close();
                return true;
            }catch (IOException e)
            {
                e.printStackTrace();
                return false;
            }
        }


    }
}
