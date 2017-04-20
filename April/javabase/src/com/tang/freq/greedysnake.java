package com.tang.freq;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by tang on 2017/4/1.
 */
public class greedysnake extends JFrame implements ActionListener, KeyListener,Runnable {

    private static final long serialVersionUID = 1L;
    private JMenuBar 蛇体;
    private JMenu 游戏,难度,最高分,关于;
    private JMenuItem 开始游戏,退出游戏,作者,分数;
    private JCheckBoxMenuItem 简单,普通,困难;
    private int 长度 = 6;
    private Toolkit 工具包;
    private int i,x,y,z,X坐标,Y坐标,object=0,增长=0,速度;//bojectX,Y

    private int m[]=new int[50];
    private int n[]=new int[50];
    private Thread 蛇 = null;
    private int 方向=0;
    private int 食物 = 0;
    private int 成绩=0;
    public void run(){
        速度=500;
        for(i=0;i<=长度-1;i++)
        {
            m[i]=90-i*10;n[i]=60;
        }

        x=m[0];
        y=n[0];
        z=4;


        while(蛇!=null)
        {

            检查();
            try
            {
                Thread.sleep(速度);
            }
            catch(Exception ee)
            {
                System.out.println(z+"");
            }
        }
    }

    public greedysnake() {

        setVisible(true);
        蛇体 = new JMenuBar();
        工具包=getToolkit();

        游戏 = new JMenu("游戏");
        开始游戏 = new JMenuItem("开始游戏");
        退出游戏 = new JMenuItem("退出游戏");

        难度 = new JMenu("困难程度");
        简单 = new JCheckBoxMenuItem("简单");
        普通 = new JCheckBoxMenuItem("普通");
        困难 = new JCheckBoxMenuItem("困难");

        最高分 = new JMenu("积分排行");
        分数 = new JMenuItem("最高记录");

        关于 = new JMenu("关于");
        作者 = new JMenuItem("关于作者");

        关于.add(作者);

        难度.add(简单);
        难度.add(普通);
        难度.add(困难);

        最高分.add(分数);
        游戏.add(开始游戏);
        游戏.add(退出游戏);
        蛇体.add(游戏);
        蛇体.add(难度);
        蛇体.add(最高分);
        蛇体.add(关于);
        作者.addActionListener(this);
        开始游戏.addActionListener(this);
        退出游戏.addActionListener(this);
        addKeyListener(this);
        分数.addActionListener(this);
        KeyStroke 开始 = KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK); //快捷键设置，Ctrl+O开始游戏
        开始游戏.setAccelerator(开始);
        KeyStroke 退出 = KeyStroke.getKeyStroke('X',InputEvent.CTRL_DOWN_MASK); //快捷键设置，Ctrl+X退出游戏
        退出游戏.setAccelerator(退出);
        setJMenuBar(蛇体);
        setTitle("贪吃蛇");
        setResizable(false);
        setBounds(300,200,400,400);
        validate();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public static void main(String args[]) {
        new greedysnake();
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==开始游戏)
        {
            长度 = 6;
            方向 = 0;
            食物 = 0;
            if(蛇==null)
            {
                蛇=new Thread(this);
                蛇.start();
            }
            else if(蛇!=null)
            {
                蛇=null;
                蛇= new Thread(this);
                蛇.start();
            }
        }
        if(e.getSource()==退出游戏)
        {
            System.exit(0);
        }
        if(e.getSource()==作者)
        {
            JOptionPane.showMessageDialog(this, "时代创想技术团队练少制作"+"\n\n"+"         "+"QQ号:1003823662"+"\n");
        }
        if(e.getSource()==分数)
        {
            JOptionPane.showMessageDialog(this,"最高记录为"+成绩+"");
        }

    }

    public void 检查(){
        是否死亡();
        if(蛇!=null)
        {
            if(增长==0)
            {
                得到食物(); //得到食物
            }
            else
            {
                生成食物(); //生成食物
            }
            if(x==X坐标&&y==Y坐标)
            {
                object=0;
                增长=1;
                工具包.beep();
            }
            if(object==0)
            {
                object=1;
                X坐标=(int)Math.floor(Math.random()*39)*10;
                Y坐标=(int)Math.floor(Math.random()*29)*10+50;
            }
            this.repaint(); //重绘
        }
    }
    void 是否死亡()
    {
        //判断游戏是否结束的方法
        if(z==4)
        {
            x=x+10;
        }
        else if(z==3)
        {
            x=x-10;
        }
        else if(z==2)
        {
            y=y+10;
        }
        else if(z==1)
        {
            y=y-10;
        }
        if(x<0||x>390||y<50||y>390)
        {
            蛇=null;
        }
        for(i=1;i<长度;i++)
        {
            if(m[i]==x&&n[i]==y)
            {
                蛇=null;
            }
        }

    }
    public void 生成食物()
    {
        //当蛇吃到东西时的方法
        if(长度<50)
        {
            长度++;
        }
        增长--;
        速度=速度-10;
        得到食物();
        方向+=100;
        if(成绩<方向)
        {
            成绩 = 方向;
        }
        食物++;
    }
    public void 得到食物()
    {
        for(i=长度-1;i>0;i--)
        {

            m[i]=m[i-1];
            n[i]=n[i-1];
        }
        if(z==4)
        {
            m[0]=m[0]+10;
        }
        if(z==3)
        {
            m[0]=m[0]-10;
        }
        if(z==2)
        {
            n[0]=n[0]+10;
        }
        if(z==1)
        {
            n[0]=n[0]-10;
        }
    }

    public void keyPressed(KeyEvent e)
    {
        if(蛇!=null)
        {
            if(e.getKeyCode()==KeyEvent.VK_UP)
            {
                if(z!=2)
                {
                    z=1;
                    检查();
                }
            }
            else if(e.getKeyCode()==KeyEvent.VK_DOWN)
            {
                if(z!=1)
                {
                    z=2;
                    检查();
                }
            }
            else if(e.getKeyCode()==KeyEvent.VK_LEFT)
            {
                if(z!=4)
                {
                    z=3;
                    检查();
                }
            }
            else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
            {
                if(z!=3)
                {
                    z=4;
                    检查();
                }
            }

        }

    }
    public void keyReleased(KeyEvent e)
    {

    }
    public void keyTyped(KeyEvent e)
    {

    }
    public void paint(Graphics g)  {
        g.setColor(Color.DARK_GRAY); //设置背景
        g.fillRect(0,50,400,400);
        g.setColor(Color.pink);
        for(i=0;i<=长度-1;i++)
        {
            g.fillRect(m[i],n[i],10,10);
        }
        g.setColor(Color.green); //蛇的食物
        g.fillRect(X坐标,Y坐标,10,10);
        g.setColor(Color.white);
        g.drawString("当前      分数"+this.方向,6,60);
        g.drawString("当前已吃食物数"+this.食物,6,72);
    }
}
