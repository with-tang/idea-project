package com.tang.IODemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

/**
 *
 * 使用StreamTokenizer来统计文件中的字符数
 * StreamTokenizer 类获取输入流并将其分析为“标记”，允许一次读取一个标记。
 * 分析过程由一个表和许多可以设置为各种状态的标志控制。
 * 该流的标记生成器可以识别标识符、数字、引用的字符串和各种注释样式。
 *
 *  默认情况下，StreamTokenizer认为下列内容是Token: 字母、数字、除C和C++注释符号以外的其他符号。
 *  如符号"/"不是Token，注释后的内容也不是，而"\"是Token。单引号和双引号以及其中的内容，只能算是一个Token。
 *  统计文章字符数的程序，不是简单的统计Token数就万事大吉，因为字符数不等于Token。按照Token的规定，
 *  引号中的内容就算是10页也算一个Token。如果希望引号和引号中的内容都算作Token，应该调用下面的代码：
 *    st.ordinaryChar('\'');
 * st.ordinaryChar('\"');
 * Created by tang on 2017/4/5.
 */
public class StreamTokenizerExample {
 public static void main(String[] args){
  //String fileName="E:\\IDEA workspace\\April\\testsrc\\token.txt";
  String fileName="testsrc/token.txt";
  StreamTokenizerExample.statis(fileName);
 }
 public static long statis(String fileName)
 {
  FileReader fileReader=null;
  try{
   fileReader=new FileReader(fileName);
   /* 创建给顶字符流的标记生成器 */
   StreamTokenizer stk=new StreamTokenizer(new BufferedReader(fileReader)) ;
/* ordinaryChar方法指定字符参数在此标记生成器中是“普通”字符。
   下面指定单引号、双引号和注释符号是普通字符
   */
   stk.ordinaryChar('\"');
           stk.ordinaryChar('/');
   stk.ordinaryChar('\'');
   String s;
   int numberSum=0;
   int wordSum=0;
   int symbolSum=0;
   int total=0;
   //nextToken方法读取下一个Token.
   //TT_EOF指示已读到流末尾的常量。

   while(stk.nextToken()!=StreamTokenizer.TT_EOF)
   {
    switch (stk.ttype)
    {
     case StreamTokenizer.TT_EOF:break;
     case StreamTokenizer.TT_NUMBER:
       s=String.valueOf(stk.nval);
      System.out.println("数字有："+s);
      numberSum++;break;
      case StreamTokenizer.TT_WORD:
      s=stk.sval;
       System.out.println("单词有： "+s);
       wordSum ++;
       break;
     default:
      s=String.valueOf(stk.ttype);
      System.out.println("标点有："+s);
      symbolSum++;
    }
      }
   System.out.println("数字有 " + numberSum+"个");
   System.out.println("单词有 " + wordSum+"个");
   System.out.println("标点符号有： " + symbolSum+"个");
   total = symbolSum + numberSum +wordSum;
   System.out.println("Total = " + total);
   return total;
  } catch(IOException ffe)
  {
   ffe.printStackTrace();
   return -1;
  }
  finally {
   if(fileReader!=null)
   {
    try{
     fileReader.close();
    }catch (IOException e)
    {
     e.printStackTrace();
    }
   }
  }
 }
}
