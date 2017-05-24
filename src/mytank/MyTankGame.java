package mytank;
//Created by cj on 2017/4/13.
//1.0����Hero̹��
//2.0��������̹�ˣ�Hero̹�˿����ƶ�
//3.0��������̹�ˣ�����Hero�ӵ���
//3.1�ӵ����к����̹����ʧ
//3.2�ӵ�����̹��(Enemy,Hero ����)������ըЧ��(��һ�λ����ޱ�ը����ͼ����Ӧ�û�һ��Ŷ...)
//47����̹�˲��ص�
    //����Enemytank��
//48��ʾ�ɼ�
    //��һ����ʼpanel�����ǿյ�
    //����ʾ��˸
    //��ʾ���ɼ�������̹�������Լ�����ֵ��
//49��¼�ɼ��ļ���
    //��¼�ܳɼ�
    //�����˳�

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class MyTankGame extends JFrame implements ActionListener{

    MyPanel mp=null;
    StartPanel smp=null;
    //��Ʋ˵�
    JMenuBar jmb=null;
    //��ʼ��Ϸ
    JMenu jm1=null;
    JMenuItem jmi1=null;
    //�˳�ϵͳ
    JMenuItem jmi2=null;
    //�����˳�
    JMenuItem jmi3=null;
    //�����Ͼ���Ϸ
    JMenuItem jmi4=null;
    public static void main(String[] args) {

        MyTankGame mtg=new MyTankGame();
    }
    //���캯��
    public MyTankGame()
    {
//        mp=new MyPanel();
//        //�����߳�
//        Thread t=new Thread(mp);
//        t.start();
//        this.add(mp);
//        //ע���¼�
//        this.addKeyListener(mp);
//�����˵��Լ��˵�ѡ��
        jmb=new JMenuBar ();
        jm1=new JMenu("��Ϸ(G))");
        //���ÿ�ݷ�ʽ
        jm1.setMnemonic('G');
        jmi1=new JMenuItem("��ʼ����Ϸ(N)");
        jmi2=new JMenuItem("�˳���Ϸ(E)");
        jmi3 =new JMenuItem("�����˳�(S)");
        jmi4 =new JMenuItem("�����Ͼ���Ϸ(C)");
        //��jmi4������Ӧ �����˳���Ϸ
        jmi4.addActionListener(this);
        jmi4.setActionCommand("congame");
        //��jmi3������Ӧ �����˳���Ϸ
        jmi3.addActionListener(this);
        jmi3.setActionCommand("saveexit");
        //��jmi2������Ӧ �˳���Ϸ
        jmi2.addActionListener(this);
        jmi2.setActionCommand("exit");

        //��jmi1������Ӧ ��ʼ��Ϸ
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
            //����ս�������
            mp=new MyPanel("new");
            //����mp�߳�
            Thread t=new Thread(mp);
            t.start();
            //ɾ���ɵ����msp
            this.remove(smp);
            this.add(mp);
            //ע�����
            this.addKeyListener(mp);
            //��ʾˢ��JFrame
            this.setVisible(true);
        }
        else if(e.getActionCommand().equals("exit")){
            //������ٵ�������
            Recorder.keepRecording();
            System.exit(0);
        }
        else if(e.getActionCommand().equals("saveexit")){

            Recorder rd=new Recorder();
            rd.setEts(mp.ets);
            //������ٵ��������͵�������
            rd.keepEnemyRecording();
            System.exit(0);
        }
        else if(e.getActionCommand().equals("congame")){
            //ȥ�Ͼ�
            //����ս�������
            mp=new MyPanel("con");

            //����mp�߳�
            Thread t=new Thread(mp);
            t.start();
            //ɾ���ɵ����smp
            this.remove(smp);
            this.add(mp);
            //ע�����
            this.addKeyListener(mp);
            //��ʾˢ��JFrame
            this.setVisible(true);
        }



    }
}
//�ҵ���壬�����߳�
class MyPanel extends JPanel implements KeyListener,Runnable {
    //����һ���ҵ�̹��
    Hero hero = null;
    //�������̹��
    Vector<EnemyTank> ets = new Vector<EnemyTank>();
    //�ָ�����
    Vector<Node> nodes=new Vector<Node>();
    int ensize = 7;
    //����ը��
    Vector<Bomb> bombs=new  Vector<Bomb>();
    //������ͼƬ
    Image image1 =null;
    Image image2 =null;
    Image image3 =null;
    //���캯��,���г�ʼ��
    public MyPanel(String flag) {
        //�ظ���¼
        Recorder.getRecording();
        //��ʼ���ҵ�̹��
        hero = new Hero(100, 100);
        //��ʼ������̹��
        if (flag.equals("new")){
            for (int i = 0; i < ensize; i++) {
                EnemyTank et = new EnemyTank((i + 1) * 50, 0);
                et.setDirect(1);
                et.setColor(0);
                //��Mypanel�ĵ���̹�˽����õ���̹��
                et.setEts(ets);
                //�����߳�
                Thread t1 = new Thread(et);
                t1.start();
                //����ӵ�
                Shot s = new Shot(et.x + 10, et.y, 1);
                et.ss.add(s);
                //�����߳�
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
                //��Mypanel�ĵ���̹�˽����õ���̹��
                et.setEts(ets);
                //�����߳�
                Thread t1 = new Thread(et);
                t1.start();
                //����ӵ�
                Shot s = new Shot(et.x + 10, et.y, 1);
                et.ss.add(s);
                //�����߳�
                Thread t2 = new Thread(s);
                t2.start();
                ets.add(et);
            }
        }
        //��ʼ����ͼƬ
        image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.jpg"));
        image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.jpg"));
        image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.jpg"));
    }
    //������ʾ��Ϣ
    public void showInfo(Graphics g)
    {
        //������ʾ��Ϣ���˵�̹��
        this.drawTank(80,330, g, 0, 0);
        //�����������ɫ
        g.setColor(Color.red);
        g.drawString(Recorder.getEnNum()+"",110, 350);

        //������ʾ��Ϣ�ҵ�̹�˼�¼��Ϣ
        this.drawTank(130,330, g, 0, 1);
        g.setColor(Color.black);
        g.drawString(Recorder.getMyLife()+"",160, 350);

        //������ҵ��ܳɼ���ʾ��Ϣ
        g.setColor(Color.black);
        //��������Ĵ�С
        Font f=new Font("����",Font.BOLD,20);
        g.setFont(f);
        g.drawString("�����ܳɼ�",420 , 30);
        //������ҵ��ܹ��ݻٵ��˶���̹�˵ļ�¼
        this.drawTank(420,60, g, 0, 0);
        g.setColor(Color.black);
        g.drawString(Recorder.getAllEnEum()+"",460,80);
    }
    //��дpaint
    public void paint(Graphics g) {

        super.paint(g);

        //����ͼ��ı���ɫ
        g.fillRect(0, 0, 400, 300);
        //������ʾ��Ϣ
        this.showInfo(g);
        //�ҵ�̹��
        if (hero.islive) {
            this.drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
        }
        //���ӵ�
        //Shot myShot=hero.ss.get(i);
        for (int i = 0; i < hero.ss.size(); i++) {
            Shot s = hero.ss.get(i);
            if (s != null && s.islive) {
                g.draw3DRect(s.x, s.y, 1, 1, false);
            }
            //����Ϊ�ӵ�false�²����ػ������Բ����Ƴ�
            //�����ԣ������Ƴ�����Ϊ�߳�ֻ��5���ӵ������Ƴ��޷�������ӵ�
            if (s.islive == false) {
                hero.ss.remove(s);
            }
        }
        //���Ƶ���̹��
        for (int i = 0; i < ets.size(); i++) {
            EnemyTank et = ets.get(i);
            if (et.islive) {
                this.drawTank(et.getX(), et.getY(), g, et.getDirect(), 0);
                //�������ӵ�
                for (int j = 0; j < et.ss.size(); j++) {
                    Shot emys = et.ss.get(j);
                    if ( emys.islive) {
                        g.draw3DRect(emys.x, emys.y, 1, 1, false);
                    }
                    //����Ϊ�ӵ�false�²����ػ������Բ����Ƴ�
                    //�����ԣ������Ƴ�����Ϊ�߳�ֻ��5���ӵ������Ƴ��޷�������ӵ�
                    if (emys.islive == false) {
                        et.ss.remove(emys);
                    }
                }

            }
        }
        //��ը��
        for (int i=0; i<bombs.size(); i++)
        {
            System.out.println("bombs.size()="+bombs.size());
            //ȡ��ը��
            Bomb b=bombs.get(i);

            if(b.life>6)
            {
                g.drawImage(image1, b.x, b.y, 30, 30, this);  // this��ʾ���ڵ�ǰ����ϻ���
            }
            else if(b.life>3)
            {
                g.drawImage(image2, b.x, b.y, 30, 30, this);  // this��ʾ���ڵ�ǰ����ϻ���
            }
            else
            {
                g.drawImage(image3, b.x, b.y, 30, 30, this);  // this��ʾ���ڵ�ǰ����ϻ���
            }
            //��Сb������ֵ
            b.lifeDown();
            //���ը��������ֵΪ0���Ͱ�ը��bombsȥ��
            if(b.life==0)
            {
                bombs.remove(b);
            }
        }
    }
    //дһ���������жϵ���̹���Ƿ񱻻���,
    public void hitEnemy(){
        for(int i=0;i<hero.ss.size();i++){
            Shot myshot=hero.ss.get(i);
            if(myshot.islive){
                //��ÿ����Ч�ӵ����ж��Ƿ����̹��
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
    //дһ���������ж��ҵ�̹���Ƿ񱻻���,
    public void hitHero(){
       for (int i=0;i<ets.size();i++){
           EnemyTank et=ets.get(i);
           if(et.islive){
               for (int j=0;j<et.ss.size();j++){
                   Shot emyshot =et.ss.get(j);
                   if(this.hitTank(emyshot,hero)){
                       //�ж��ҵ�̹�������Ƿ��������ڵ���2 ��  �ҵ�̹������������ �ֱ���  myLife ��ֵ�� true 3��  ture2,  true 1 ,false��
                       //���Թ���̹�˵�����������������
                       if(Recorder.getMyLife()>=2)
                       {
                           //����������Ϊ��
                           hero.islive=true;
                       }
                       else
                       {
                           hero.islive=false;
                       }
                       //��������ҵ�̹�ˣ��ҵ�̹�������ͼ�Сһ��
                       Recorder.reduceMyNum();
                   }
               }
           }
       }
    }
    //����ӵ�����̹�ˣ����ӵ���̹�˶����false�� ����̹�˱�ըЧ��
    public boolean hitTank(Shot s,Tank tk){
        boolean isShotTank=false;
        switch (tk.direct){
            //����,��
            case 0:
            case 1:
                if(s.x>tk.x&&s.x<tk.x+20&&s.y>tk.y&&s.y<tk.y+30){
                    s.islive=false;
                    tk.islive=false;
                    //����ը��
                    Bomb b=new Bomb(tk.x,tk.y);
                    bombs.add(b);
                    isShotTank=true;
                }
                //������
            case 2:
            case 3:
                if(s.x>tk.x&&s.x<tk.x+30&&s.y>tk.y&&s.y<tk.y+20){
                    s.islive=false;
                    tk.islive=false;
                    //����ը��
                    Bomb b=new Bomb(tk.x,tk.y);
                    bombs.add(b);
                    isShotTank=true;
                }
        }
        return isShotTank;
    }
    //����̹�˵ĺ���
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //����ð��ж���ɫ������
        switch (type) {
            case 0:
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }
        switch (direct) {//����
            case 0:
                //��߾���
                g.fill3DRect(x, y, 5, 30, false);
                //�ұ߾���
                g.fill3DRect(x + 15, y, 5, 30, false);
                //�м����
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                //�м�Բ��
                g.fillOval(x + 5, y + 10, 10, 10);
                //��Ͳ
                g.drawLine(x + 10, y + 15, x + 10, y);
                break;
            //����
            case 1:
                //��߾���
                g.fill3DRect(x, y, 5, 30, false);
                //�ұ߾���
                g.fill3DRect(x + 15, y, 5, 30, false);
                //�м����
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                //�м�Բ��
                g.fillOval(x + 5, y + 10, 10, 10);
                //��Ͳ
                g.drawLine(x + 10, y + 15, x + 10, y + 30);
                break;
            //����
            case 2:
                //�ϱ߾���
                g.fill3DRect(x, y, 30, 5, false);
                //�ұ߾���
                g.fill3DRect(x, y + 15, 30, 5, false);
                //�м����
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                //�м�Բ��
                g.fillOval(x + 10, y + 5, 10, 10);
                //��Ͳ
                g.drawLine(x + 15, y + 10, x, y + 10);
                break;
            //����
            case 3:
                //�ϱ߾���
                g.fill3DRect(x, y, 30, 5, false);
                //�ұ߾���
                g.fill3DRect(x, y + 15, 30, 5, false);
                //�м����
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                //�м�Բ��
                g.fillOval(x + 10, y + 5, 10, 10);
                //��Ͳ
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
        //���
        else if (e.getKeyCode() == KeyEvent.VK_J) {
            //���5���ӵ�
            if(hero.ss.size()<5) {
                this.hero.shotEmy();//�����ӵ��������߳�
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
            //����̹�˱�����
            this.hitEnemy();
            //�ҵ�̹�˱�����
            this.hitHero();
            this.repaint();
        }
    }
}
//����һ������壬��Զ��Ϸ��ʼ
class StartPanel extends JPanel implements Runnable{
    int times=0;
public void paint(Graphics g){
    super.paint(g);

    g.fillRect(0, 0, 400, 300);
    //��ʾ��Ϣ
    if(times%2==0)
    {
        // ����������ɫ
        g.setColor(Color.yellow);
        //��������
        Font myFont=new Font("������κ",Font.BOLD,30);
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

