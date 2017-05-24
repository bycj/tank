package mytank;
import java.util.Vector;
import java.io.*;
/**
 * Created by cj on 2017/4/24.
 */
// 坦克类
class Tank{
    int x=0;
    int y=0;
    int speed =3;
    int color=0;
    int direct =0;
    boolean islive=true;
    //创建子弹容器
    Vector<Shot>ss=new Vector<Shot>();
    Shot s=null;
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }


    public Tank(int x, int y){
        this.x=x;
        this.y=y;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


}
//我的坦克，继承坦克类
class  Hero extends Tank{


    public Hero(int x,int y){
        super(x,y);}
//射击敌人
public void shotEmy(){

        switch (direct){
            case 0:
            s=new Shot(x+10,y,0);
            //把子弹加入容器
            ss.add(s);
            break;
            case 1:
                s=new Shot(x+10,y+30,1);
                ss.add(s);
                break;
            case 2:
                s=new Shot(x,y+10,2);
                ss.add(s);
                break;
            case 3:
                s=new Shot(x+30,y+10,3);
                ss.add(s);
                break;
        }
         //创建线程并启动

        Thread t=new Thread(s);
        t.start();

}

    public void moveup(){
        y-=speed;
    }
    public void movedown(){
     y+=speed;
    }
    public void moveleft(){
        x-=speed;
    }

    public void moveright(){
        x+=speed;
    }

}
//敌人坦克，继承坦克类，并做成线程
class  EnemyTank extends Tank implements Runnable{
    public  EnemyTank(int x,int y){
        super(x,y);
    }
    int times=0;
   Vector<EnemyTank>ets=new Vector<EnemyTank>();
   //得到Mypanel上的坦克
    public void setEts(Vector<EnemyTank>ets){
        this.ets=ets;
    }
   //判断坦克重叠
    public boolean isTouch(){
        boolean b=false;
        switch (this.direct){
            case 0://up
                //去除所有坦克
                for (int i=0;i<ets.size();i++){
                EnemyTank et=ets.get(i);
                //判断不是自己
                if (this!=et){
                    //敌人坦克上或者下
                    if (et.direct==0||et.direct==1){
                        if (this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30){
                            b= true;
                        }
                        if (this.x+20>=et.x&&this.x+20<=et.x+20&&this.y>=et.y&&this.y<=et.y+30){
                            b= true;
                        }
                    }
                    //敌人坦克向左或者右
                    if (et.direct==2||et.direct==3){
                        if (this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20){
                            b= true;
                        }
                        if (this.x+20>=et.x&&this.x+20<=et.x+30&&this.y>=et.y&&this.y<=et.y+20){
                            b= true;
                        }
                    }
                }
                }
                break;
            case 1://down
                //去除所有坦克
                for (int i=0;i<ets.size();i++){
                    EnemyTank et=ets.get(i);
                    //判断不是自己
                    if (this!=et){
                        //敌人坦克上或者下
                        if (et.direct==0||et.direct==1){
                            if (this.x>=et.x&&this.x<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30){
                                b= true;
                            }
                            if (this.x+20>=et.x&&this.x+20<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30){
                                b= true;
                            }
                        }
                        //敌人坦克向左或者右
                        if (et.direct==2||et.direct==3){
                            if (this.x>et.x&&this.x<et.x+30&&this.y+30>et.y&&this.y+30<et.y+20){
                                b= true;
                            }
                            if (this.x+20>et.x&&this.x+20<et.x+20&&this.y+30>et.y&&this.y+30<et.y+20){
                                b= true;
                            }
                        }
                    }
                }
                break;
            case 2://left
                //去除所有坦克
                for (int i=0;i<ets.size();i++){
                    EnemyTank et=ets.get(i);
                    //判断不是自己
                    if (this!=et){
                        //敌人坦克上或者下
                        if (et.direct==0||et.direct==1){
                            if (this.x>et.x&&this.x<et.x+20&&this.y>et.y&&this.y<et.y+30){
                                b= true;
                            }
                            if (this.x>et.x&&this.x<et.x+20&&this.y+20>et.y&&this.y+20<et.y+30){
                                b= true;
                            }
                        }
                        //敌人坦克向左或者右
                        if (et.direct==2||et.direct==3){
                            if (this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20){
                                b= true;
                            }
                            if (this.x>=et.x&&this.x<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20){
                                b= true;
                            }
                        }
                    }
                }
                break;
            case 3://right
                //去除所有坦克
                for (int i=0;i<ets.size();i++){
                    EnemyTank et=ets.get(i);
                    //判断不是自己
                    if (this!=et){
                        //敌人坦克上或者下
                        if (et.direct==0||et.direct==1){
                            if (this.x+30>et.x&&this.x+30<et.x+20&&this.y>et.y&&this.y<et.y+30){
                                b= true;
                            }
                            if (this.x+30>et.x&&this.x+30<et.x+20&&this.y+20>et.y&&this.y+20<et.y+30){
                                b= true;
                            }
                        }
                        //敌人坦克向左或者右
                        if (et.direct==2||et.direct==3){
                            if (this.x+30>=et.x&&this.x+30<=et.x+30&&this.y>=et.y&&this.y<=et.y+20){
                                b= true;
                            }
                            if (this.x+30>=et.x&&this.x+30<=et.x+30&&this.y>=et.y&&this.y+20<=et.y+20){
                                b= true;
                            }
                        }
                    }
                }
                break;
        }
        return b;
    }
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (this.direct){
                case 0://坦克向上移动

                    for(int i=0;i<30;i++) {
                        if (y > 0&&!this.isTouch()) {
                            y -= speed;
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
                case 1://坦克向下移动

                    for(int i=0;i<30;i++) {
                        if (y < 300&&!this.isTouch()) {
                            y += speed;
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
                case 2://坦克向左移动

                    for(int i=0;i<30;i++) {
                        if (x > 0&&!this.isTouch()) {
                            x -= speed;
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
                case 3://坦克向右移动

                    for(int i=0;i<30;i++) {
                        if (x < 400&&!this.isTouch()) {
                            x += speed;
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
                    }
               this.times++;
            //添加子弹
            if (times%2==0) {
                if (islive) {
                    if (ss.size() < 5) {
                       // public void shotHero()//写一个函数，射击Hero坦克
                       //若是也封装成函数，怎么实现
                        switch(this.direct)
                        {  case 0:
                                s=new Shot(x+10,y ,0);
                                ss.add(s);
                                 break;
                            case 1:
                                    s=new Shot(x+30,y+10, 1);
                                    ss.add(s);
                                    break;
                                case 2:
                                    s=new Shot(x+10,y+30, 2);
                                    ss.add(s);
                                    break;
                                case 3:
                                    s=new Shot(x,y+10, 3);
                                    ss.add(s);
                                    break;
                            }
                            //创建线程并启动
                            Thread t=new Thread(s);
                            t.start();

                    }
                }
            }
            //让坦克随机产生一个新的方向
            this.direct=(int)(Math.random()*4);
              //判断坦克是否死亡
            if(this.islive==false){
               //坦克死亡退出线程
                break;
            }
            }
        }
    }
//子弹类,做成线程
class  Shot implements Runnable{
int x;
int y;
int speed=1;
int direct;
//子弹是否活着
boolean islive=true;
public Shot(int x,int y,int direct){
    this.x=x;
    this.y=y;
    this.direct=direct;
}
//线程
    @Override
    public void run() {
        while (true){
            //处理异常
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct){
                case 0:
                    y=y-speed;
                    break;
                case 1:
                    y=y+speed;
                    break;
                case 2:
                    x=x-speed;
                    break;
                case 3:
                    x=x+speed;
                    break;
        }
        //坐标
            //System.out.println("子弹的坐标x="+x+"子弹的y坐标y="+y);
        if(x<0||x>400||y<0||y>300){
                islive=false;
                break;
            }
        }
    }
}
//炸弹类
class Bomb{
    int x;
    int y;
    int life=9;
    boolean islive=true;
    public Bomb(int x,int y){this.x=x;this.y=y;}
    //减少炸弹生命
    public void lifeDown(){
        if(life>0)
        {this.life--;}
        else
        {islive=false;}
    }
}
//记录类，同事可以保存玩家的设置
class Recorder
{
    //记录每关有多少敌人
    private static int enNum=7;
    //设置我有多少可以用人
    private static int myLife=3;
    //记录总共消灭了多少敌人的坦克
    private static int allEnEum=0;
    //从文件中回复记录点
    static Vector<Node>nodes=new Vector<Node>();
    private static FileWriter fw=null;
    private static FileReader fr=null;
    private static BufferedWriter bw =null;
    private static BufferedReader br=null;
    private static Vector<EnemyTank>ets=new Vector<EnemyTank>();
    //完成读取任务
    public Vector<Node> getNodes(){
        try {
            fr=new FileReader("f:/myRecording.txt");
            br=new BufferedReader(fr);
            String n="";
            //读取第一行
            n=br.readLine();
            allEnEum=Integer.parseInt(n);
            while ((n=br.readLine())!=null)
            {
                String []xyz=n.split(" ");   //以空格分隔字符串，得到字符串数组
                Node node=new Node(Integer.parseInt(xyz[0]),Integer.parseInt(xyz[1]),Integer.parseInt(xyz[2]));
                nodes.add(node);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return nodes;
    }
    //从文件中读取记录
    public static void getRecording(){
        try {
            fr=new FileReader("f:/myRecording.txt");
            br=new BufferedReader(fr);
            String n=br.readLine();
            allEnEum=Integer.parseInt(n);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //保存击毁敌人数量和敌人坦克坐标，方向
    public   void keepEnemyRecording(){
        try {
            /* 创建 */
            fw = new FileWriter("f:/myRecording.txt");
            bw = new BufferedWriter(fw);
            bw.write(allEnEum+"\r\n");
            //保存当前活的敌人坦克的坐标和方向
            for (int i=0;i<ets.size();i++){
                //取第一个坦克
                EnemyTank et=ets.get(i);
                if(et.islive){
                    //活的保存
                    String recode=et.x+" "+et.y+" "+et.direct;
                    //写入
                    bw.write(recode+"\r\n");


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭流
            try {
                bw.close();
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }
    }
    //把玩家击毁敌人坦克数量保存到文件中
    public static void keepRecording(){
        try {
            /* 创建 */
            fw = new FileWriter("f:/myRecording.txt");
            bw = new BufferedWriter(fw);
            bw.write(allEnEum+"\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭流
            try {
                    bw.close();
                    fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }

    }
    public  Vector<EnemyTank> getEts() {
        return ets;
    }
    public  void setEts(Vector<EnemyTank> ets) {
        this.ets = ets;
    }
    public static int getEnNum() {
        return enNum;
    }
    public static void setEnNum(int enNum) {
        Recorder.enNum = enNum;
    }
    public static int getMyLife() {
        return myLife;
    }
    public static void setMyLife(int myLife) {
        Recorder.myLife = myLife;
    }
    //减少敌人生命的的数量
    public static void reduceEnNum()
    {
        enNum--;
    }
    //减少我的生命数量
    public static void reduceMyNum()
    {
        myLife--;
    }
    //当消灭敌人的时候
    public static void addEnNumRec()
    {
        allEnEum++;
    }
    public static int getAllEnEum() {
        return allEnEum;
    }
    public static void setAllEnEum(int allEnEum) {
        Recorder.allEnEum = allEnEum;
    }
}
class  Node{
    int x;
    int y;
    int direct;
    public Node(int x, int y, int direct)
    {
        this.x=x;
        this.y=y;
        this.direct=direct;
    }
}