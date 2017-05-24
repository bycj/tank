package mytank;
import java.util.Vector;
import java.io.*;
/**
 * Created by cj on 2017/4/24.
 */
// ̹����
class Tank{
    int x=0;
    int y=0;
    int speed =3;
    int color=0;
    int direct =0;
    boolean islive=true;
    //�����ӵ�����
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
//�ҵ�̹�ˣ��̳�̹����
class  Hero extends Tank{


    public Hero(int x,int y){
        super(x,y);}
//�������
public void shotEmy(){

        switch (direct){
            case 0:
            s=new Shot(x+10,y,0);
            //���ӵ���������
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
         //�����̲߳�����

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
//����̹�ˣ��̳�̹���࣬�������߳�
class  EnemyTank extends Tank implements Runnable{
    public  EnemyTank(int x,int y){
        super(x,y);
    }
    int times=0;
   Vector<EnemyTank>ets=new Vector<EnemyTank>();
   //�õ�Mypanel�ϵ�̹��
    public void setEts(Vector<EnemyTank>ets){
        this.ets=ets;
    }
   //�ж�̹���ص�
    public boolean isTouch(){
        boolean b=false;
        switch (this.direct){
            case 0://up
                //ȥ������̹��
                for (int i=0;i<ets.size();i++){
                EnemyTank et=ets.get(i);
                //�жϲ����Լ�
                if (this!=et){
                    //����̹���ϻ�����
                    if (et.direct==0||et.direct==1){
                        if (this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30){
                            b= true;
                        }
                        if (this.x+20>=et.x&&this.x+20<=et.x+20&&this.y>=et.y&&this.y<=et.y+30){
                            b= true;
                        }
                    }
                    //����̹�����������
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
                //ȥ������̹��
                for (int i=0;i<ets.size();i++){
                    EnemyTank et=ets.get(i);
                    //�жϲ����Լ�
                    if (this!=et){
                        //����̹���ϻ�����
                        if (et.direct==0||et.direct==1){
                            if (this.x>=et.x&&this.x<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30){
                                b= true;
                            }
                            if (this.x+20>=et.x&&this.x+20<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30){
                                b= true;
                            }
                        }
                        //����̹�����������
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
                //ȥ������̹��
                for (int i=0;i<ets.size();i++){
                    EnemyTank et=ets.get(i);
                    //�жϲ����Լ�
                    if (this!=et){
                        //����̹���ϻ�����
                        if (et.direct==0||et.direct==1){
                            if (this.x>et.x&&this.x<et.x+20&&this.y>et.y&&this.y<et.y+30){
                                b= true;
                            }
                            if (this.x>et.x&&this.x<et.x+20&&this.y+20>et.y&&this.y+20<et.y+30){
                                b= true;
                            }
                        }
                        //����̹�����������
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
                //ȥ������̹��
                for (int i=0;i<ets.size();i++){
                    EnemyTank et=ets.get(i);
                    //�жϲ����Լ�
                    if (this!=et){
                        //����̹���ϻ�����
                        if (et.direct==0||et.direct==1){
                            if (this.x+30>et.x&&this.x+30<et.x+20&&this.y>et.y&&this.y<et.y+30){
                                b= true;
                            }
                            if (this.x+30>et.x&&this.x+30<et.x+20&&this.y+20>et.y&&this.y+20<et.y+30){
                                b= true;
                            }
                        }
                        //����̹�����������
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
                case 0://̹�������ƶ�

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
                case 1://̹�������ƶ�

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
                case 2://̹�������ƶ�

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
                case 3://̹�������ƶ�

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
            //����ӵ�
            if (times%2==0) {
                if (islive) {
                    if (ss.size() < 5) {
                       // public void shotHero()//дһ�����������Hero̹��
                       //����Ҳ��װ�ɺ�������ôʵ��
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
                            //�����̲߳�����
                            Thread t=new Thread(s);
                            t.start();

                    }
                }
            }
            //��̹���������һ���µķ���
            this.direct=(int)(Math.random()*4);
              //�ж�̹���Ƿ�����
            if(this.islive==false){
               //̹�������˳��߳�
                break;
            }
            }
        }
    }
//�ӵ���,�����߳�
class  Shot implements Runnable{
int x;
int y;
int speed=1;
int direct;
//�ӵ��Ƿ����
boolean islive=true;
public Shot(int x,int y,int direct){
    this.x=x;
    this.y=y;
    this.direct=direct;
}
//�߳�
    @Override
    public void run() {
        while (true){
            //�����쳣
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
        //����
            //System.out.println("�ӵ�������x="+x+"�ӵ���y����y="+y);
        if(x<0||x>400||y<0||y>300){
                islive=false;
                break;
            }
        }
    }
}
//ը����
class Bomb{
    int x;
    int y;
    int life=9;
    boolean islive=true;
    public Bomb(int x,int y){this.x=x;this.y=y;}
    //����ը������
    public void lifeDown(){
        if(life>0)
        {this.life--;}
        else
        {islive=false;}
    }
}
//��¼�࣬ͬ�¿��Ա�����ҵ�����
class Recorder
{
    //��¼ÿ���ж��ٵ���
    private static int enNum=7;
    //�������ж��ٿ�������
    private static int myLife=3;
    //��¼�ܹ������˶��ٵ��˵�̹��
    private static int allEnEum=0;
    //���ļ��лظ���¼��
    static Vector<Node>nodes=new Vector<Node>();
    private static FileWriter fw=null;
    private static FileReader fr=null;
    private static BufferedWriter bw =null;
    private static BufferedReader br=null;
    private static Vector<EnemyTank>ets=new Vector<EnemyTank>();
    //��ɶ�ȡ����
    public Vector<Node> getNodes(){
        try {
            fr=new FileReader("f:/myRecording.txt");
            br=new BufferedReader(fr);
            String n="";
            //��ȡ��һ��
            n=br.readLine();
            allEnEum=Integer.parseInt(n);
            while ((n=br.readLine())!=null)
            {
                String []xyz=n.split(" ");   //�Կո�ָ��ַ������õ��ַ�������
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
    //���ļ��ж�ȡ��¼
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
    //������ٵ��������͵���̹�����꣬����
    public   void keepEnemyRecording(){
        try {
            /* ���� */
            fw = new FileWriter("f:/myRecording.txt");
            bw = new BufferedWriter(fw);
            bw.write(allEnEum+"\r\n");
            //���浱ǰ��ĵ���̹�˵�����ͷ���
            for (int i=0;i<ets.size();i++){
                //ȡ��һ��̹��
                EnemyTank et=ets.get(i);
                if(et.islive){
                    //��ı���
                    String recode=et.x+" "+et.y+" "+et.direct;
                    //д��
                    bw.write(recode+"\r\n");


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //�ر���
            try {
                bw.close();
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }
    }
    //����һ��ٵ���̹���������浽�ļ���
    public static void keepRecording(){
        try {
            /* ���� */
            fw = new FileWriter("f:/myRecording.txt");
            bw = new BufferedWriter(fw);
            bw.write(allEnEum+"\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //�ر���
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
    //���ٵ��������ĵ�����
    public static void reduceEnNum()
    {
        enNum--;
    }
    //�����ҵ���������
    public static void reduceMyNum()
    {
        myLife--;
    }
    //��������˵�ʱ��
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