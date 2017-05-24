package mytank;
//Created by cj on 2017/4/13.
//1.0画出Hero坦克
//2.0画出敌人坦克，Hero坦克可以移动
//3.0画出敌人坦克，画出Hero子弹。
//3.1子弹击中后敌人坦克消失
//3.2子弹击中坦克(Enemy,Hero 都有)产生爆炸效果(第一次击中无爆炸，画图函数应该换一种哦...)
//47敌人坦克不重叠
    //放入Enemytank中
//48显示成绩
    //做一个开始panel，它是空的
    //让提示闪烁
    //显示出成绩（击毁坦克数，自己生命值）
//49记录成绩文件流
    //记录总成绩
    //存盘退出

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class MyTankGame extends JFrame implements ActionListener{

    MyPanel mp=null;
    StartPanel smp=null;
    //设计菜单
    JMenuBar jmb=null;
    //开始游戏
    JMenu jm1=null;
    JMenuItem jmi1=null;
    //退出系统
    JMenuItem jmi2=null;
    //存盘退出
    JMenuItem jmi3=null;
    //继续上局游戏
    JMenuItem jmi4=null;
    public static void main(String[] args) {

        MyTankGame mtg=new MyTankGame();
    }
    //构造函数
    public MyTankGame()
    {
//        mp=new MyPanel();
//        //启动线程
//        Thread t=new Thread(mp);
//        t.start();
//        this.add(mp);
//        //注册事件
//        this.addKeyListener(mp);
//创建菜单以及菜单选项
        jmb=new JMenuBar ();
        jm1=new JMenu("游戏(G))");
        //设置快捷方式
        jm1.setMnemonic('G');
        jmi1=new JMenuItem("开始新游戏(N)");
        jmi2=new JMenuItem("退出游戏(E)");
        jmi3 =new JMenuItem("存盘退出(S)");
        jmi4 =new JMenuItem("继续上局游戏(C)");
        //对jmi4进行相应 存盘退出游戏
        jmi4.addActionListener(this);
        jmi4.setActionCommand("congame");
        //对jmi3进行相应 存盘退出游戏
        jmi3.addActionListener(this);
        jmi3.setActionCommand("saveexit");
        //对jmi2进行相应 退出游戏
        jmi2.addActionListener(this);
        jmi2.setActionCommand("exit");

        //对jmi1进行相应 开始游戏
        jmi1.addActionListener(this);
        jmi1.setActionCommand("newgame");

        jm1.add(jmi1);
        jm1.add(jmi2);
        jm1.add(jmi3);
        jm1.add(jmi4);
        jmb.add(jm1);
        smp=new StartPanel();
        Thread t=new Thread(smp);
        t.start();
        this.setJMenuBar(jmb);
        this.add(smp);
        this.setSize(600,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("newgame"))
        {
            //创建战场的面板
            mp=new MyPanel("new");
            //启东mp线程
            Thread t=new Thread(mp);
            t.start();
            //删掉旧的面板msp
            this.remove(smp);
            this.add(mp);
            //注册监听
            this.addKeyListener(mp);
            //显示刷新JFrame
            this.setVisible(true);
        }
        else if(e.getActionCommand().equals("exit")){
            //保存击毁敌人数量
            Recorder.keepRecording();
            System.exit(0);
        }
        else if(e.getActionCommand().equals("saveexit")){

            Recorder rd=new Recorder();
            rd.setEts(mp.ets);
            //保存击毁敌人数量和敌人坐标
            rd.keepEnemyRecording();
            System.exit(0);
        }
        else if(e.getActionCommand().equals("congame")){
            //去上局
            //创建战场的面板
            mp=new MyPanel("con");

            //启东mp线程
            Thread t=new Thread(mp);
            t.start();
            //删掉旧的面板smp
            this.remove(smp);
            this.add(mp);
            //注册监听
            this.addKeyListener(mp);
            //显示刷新JFrame
            this.setVisible(true);
        }



    }
}
//我的面板，做成线程
class MyPanel extends JPanel implements KeyListener,Runnable {
    //定义一个我的坦克
    Hero hero = null;
    //定义敌人坦克
    Vector<EnemyTank> ets = new Vector<EnemyTank>();
    //恢复容器
    Vector<Node> nodes=new Vector<Node>();
    int ensize = 7;
    //定义炸弹
    Vector<Bomb> bombs=new  Vector<Bomb>();
    //定义三图片
    Image image1 =null;
    Image image2 =null;
    Image image3 =null;
    //构造函数,进行初始化
    public MyPanel(String flag) {
        //回复记录
        Recorder.getRecording();
        //初始化我的坦克
        hero = new Hero(100, 100);
        //初始化敌人坦克
        if (flag.equals("new")){
            for (int i = 0; i < ensize; i++) {
                EnemyTank et = new EnemyTank((i + 1) * 50, 0);
                et.setDirect(1);
                et.setColor(0);
                //将Mypanel的敌人坦克交给该敌人坦克
                et.setEts(ets);
                //启动线程
                Thread t1 = new Thread(et);
                t1.start();
                //添加子弹
                Shot s = new Shot(et.x + 10, et.y, 1);
                et.ss.add(s);
                //启动线程
                Thread t2 = new Thread(s);
                t2.start();
                ets.add(et);
            }
    }
    else if(flag.equals("con")){
            nodes=new Recorder().getNodes();
            for (int i = 0; i < nodes.size(); i++) {
                Node node=nodes.get(i);
                EnemyTank et = new EnemyTank(node.x, node.y);
                et.setDirect(node.direct);
                et.setColor(0);
                //将Mypanel的敌人坦克交给该敌人坦克
                et.setEts(ets);
                //启动线程
                Thread t1 = new Thread(et);
                t1.start();
                //添加子弹
                Shot s = new Shot(et.x + 10, et.y, 1);
                et.ss.add(s);
                //启动线程
                Thread t2 = new Thread(s);
                t2.start();
                ets.add(et);
            }
        }
        //初始化三图片
        image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.jpg"));
        image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.jpg"));
        image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.jpg"));
    }
    //绘制提示信息
    public void showInfo(Graphics g)
    {
        //画出提示信息敌人的坦克
        this.drawTank(80,330, g, 0, 0);
        //设置字体的颜色
        g.setColor(Color.red);
        g.drawString(Recorder.getEnNum()+"",110, 350);

        //画出提示信息我的坦克记录信息
        this.drawTank(130,330, g, 0, 1);
        g.setColor(Color.black);
        g.drawString(Recorder.getMyLife()+"",160, 350);

        //画出玩家的总成绩提示信息
        g.setColor(Color.black);
        //设置字体的大小
        Font f=new Font("宋体",Font.BOLD,20);
        g.setFont(f);
        g.drawString("您的总成绩",420 , 30);
        //画出玩家的总共摧毁敌人多少坦克的记录
        this.drawTank(420,60, g, 0, 0);
        g.setColor(Color.black);
        g.drawString(Recorder.getAllEnEum()+"",460,80);
    }
    //重写paint
    public void paint(Graphics g) {

        super.paint(g);

        //设置图像的背景色
        g.fillRect(0, 0, 400, 300);
        //画出提示信息
        this.showInfo(g);
        //我的坦克
        if (hero.islive) {
            this.drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
        }
        //画子弹
        //Shot myShot=hero.ss.get(i);
        for (int i = 0; i < hero.ss.size(); i++) {
            Shot s = hero.ss.get(i);
            if (s != null && s.islive) {
                g.draw3DRect(s.x, s.y, 1, 1, false);
            }
            //我认为子弹false下不会重画，所以不用移除
            //经测试，必须移除，因为线程只有5个子弹，不移除无法添加新子弹
            if (s.islive == false) {
                hero.ss.remove(s);
            }
        }
        //绘制敌人坦克
        for (int i = 0; i < ets.size(); i++) {
            EnemyTank et = ets.get(i);
            if (et.islive) {
                this.drawTank(et.getX(), et.getY(), g, et.getDirect(), 0);
                //画敌人子弹
                for (int j = 0; j < et.ss.size(); j++) {
                    Shot emys = et.ss.get(j);
                    if ( emys.islive) {
                        g.draw3DRect(emys.x, emys.y, 1, 1, false);
                    }
                    //我认为子弹false下不会重画，所以不用移除
                    //经测试，必须移除，因为线程只有5个子弹，不移除无法添加新子弹
                    if (emys.islive == false) {
                        et.ss.remove(emys);
                    }
                }

            }
        }
        //画炸弹
        for (int i=0; i<bombs.size(); i++)
        {
            System.out.println("bombs.size()="+bombs.size());
            //取出炸弹
            Bomb b=bombs.get(i);

            if(b.life>6)
            {
                g.drawImage(image1, b.x, b.y, 30, 30, this);  // this表示就在当前面板上绘制
            }
            else if(b.life>3)
            {
                g.drawImage(image2, b.x, b.y, 30, 30, this);  // this表示就在当前面板上绘制
            }
            else
            {
                g.drawImage(image3, b.x, b.y, 30, 30, this);  // this表示就在当前面板上绘制
            }
            //减小b的生命值
            b.lifeDown();
            //如果炸弹的生命值为0，就把炸弹bombs去掉
            if(b.life==0)
            {
                bombs.remove(b);
            }
        }
    }
    //写一个函数，判断敌人坦克是否被击中,
    public void hitEnemy(){
        for(int i=0;i<hero.ss.size();i++){
            Shot myshot=hero.ss.get(i);
            if(myshot.islive){
                //对每个有效子弹，判断是否击中坦克
                for (int j=0;j<ets.size();j++){
                    EnemyTank et=ets.get(j);
                    if(et.islive){
                        if(this.hitTank(myshot,et)){
                            Recorder.reduceEnNum();
                            Recorder.addEnNumRec();
                        }
                    }
                }
            }
        }
    }
    //写一个函数，判断我的坦克是否被击中,
    public void hitHero(){
       for (int i=0;i<ets.size();i++){
           EnemyTank et=ets.get(i);
           if(et.islive){
               for (int j=0;j<et.ss.size();j++){
                   Shot emyshot =et.ss.get(j);
                   if(this.hitTank(emyshot,hero)){
                       //判断我的坦克生命是否大于零大于等于2 ，  我的坦克是三次生命 分别是  myLife 的值是 true 3，  ture2,  true 1 ,false，
                       //所以够我坦克的生命就延续了三次
                       if(Recorder.getMyLife()>=2)
                       {
                           //生命重新设为真
                           hero.islive=true;
                       }
                       else
                       {
                           hero.islive=false;
                       }
                       //如果打中我的坦克，我的坦克生命就减小一个
                       Recorder.reduceMyNum();
                   }
               }
           }
       }
    }
    //如果子弹击中坦克，则子弹和坦克都变成false。 击中坦克爆炸效果
    public boolean hitTank(Shot s,Tank tk){
        boolean isShotTank=false;
        switch (tk.direct){
            //向上,下
            case 0:
            case 1:
                if(s.x>tk.x&&s.x<tk.x+20&&s.y>tk.y&&s.y<tk.y+30){
                    s.islive=false;
                    tk.islive=false;
                    //创建炸弹
                    Bomb b=new Bomb(tk.x,tk.y);
                    bombs.add(b);
                    isShotTank=true;
                }
                //向左右
            case 2:
            case 3:
                if(s.x>tk.x&&s.x<tk.x+30&&s.y>tk.y&&s.y<tk.y+20){
                    s.islive=false;
                    tk.islive=false;
                    //创建炸弹
                    Bomb b=new Bomb(tk.x,tk.y);
                    bombs.add(b);
                    isShotTank=true;
                }
        }
        return isShotTank;
    }
    //绘制坦克的函数
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //必须得把判断颜色放上面
        switch (type) {
            case 0:
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }
        switch (direct) {//向上
            case 0:
                //左边矩形
                g.fill3DRect(x, y, 5, 30, false);
                //右边矩形
                g.fill3DRect(x + 15, y, 5, 30, false);
                //中间矩形
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                //中间圆形
                g.fillOval(x + 5, y + 10, 10, 10);
                //炮筒
                g.drawLine(x + 10, y + 15, x + 10, y);
                break;
            //向下
            case 1:
                //左边矩形
                g.fill3DRect(x, y, 5, 30, false);
                //右边矩形
                g.fill3DRect(x + 15, y, 5, 30, false);
                //中间矩形
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                //中间圆形
                g.fillOval(x + 5, y + 10, 10, 10);
                //炮筒
                g.drawLine(x + 10, y + 15, x + 10, y + 30);
                break;
            //向左
            case 2:
                //上边矩形
                g.fill3DRect(x, y, 30, 5, false);
                //右边矩形
                g.fill3DRect(x, y + 15, 30, 5, false);
                //中间矩形
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                //中间圆形
                g.fillOval(x + 10, y + 5, 10, 10);
                //炮筒
                g.drawLine(x + 15, y + 10, x, y + 10);
                break;
            //向右
            case 3:
                //上边矩形
                g.fill3DRect(x, y, 30, 5, false);
                //右边矩形
                g.fill3DRect(x, y + 15, 30, 5, false);
                //中间矩形
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                //中间圆形
                g.fillOval(x + 10, y + 5, 10, 10);
                //炮筒
                g.drawLine(x + 15, y + 10, x + 30, y + 10);
                break;
        }


    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            this.hero.setDirect(0);
            this.hero.moveup();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            this.hero.setDirect(1);
            this.hero.movedown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            this.hero.setDirect(2);
            this.hero.moveleft();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            this.hero.setDirect(3);
            this.hero.moveright();
        }
        //射击
        else if (e.getKeyCode() == KeyEvent.VK_J) {
            //最多5个子弹
            if(hero.ss.size()<5) {
                this.hero.shotEmy();//产生子弹并创建线程
            }
        }
        this.repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //敌人坦克被击中
            this.hitEnemy();
            //我的坦克被击中
            this.hitHero();
            this.repaint();
        }
    }
}
//定义一个新面板，永远游戏开始
class StartPanel extends JPanel implements Runnable{
    int times=0;
public void paint(Graphics g){
    super.paint(g);

    g.fillRect(0, 0, 400, 300);
    //提示信息
    if(times%2==0)
    {
        // 设置字体颜色
        g.setColor(Color.yellow);
        //设置字体
        Font myFont=new Font("华文新魏",Font.BOLD,30);
        g.setFont(myFont);
        g.drawString("stage: 1", 150, 150);
    }
}


    @Override
    public void run() {
    while (true){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        times++;
        this.repaint();

    }

    }
}

