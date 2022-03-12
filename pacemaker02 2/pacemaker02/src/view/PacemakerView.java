package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacemakerView extends JFrame implements ChangeListener, ActionListener{
    Boolean ON = false;

    Controller controller = new Controller();
    JFrame frame = new JFrame();
    //    on off AAI DDD 等按钮
    JButton on, off, AAI, DDD, VVI, modify;

    //    需要更改成心率直方图
    JTextField resultText = new JTextField("心率图区域", 30);
//    JTextArea resultText=new JTextArea("心率图区域",1,30);


    int x = 0;
    JPanel jP4 = new JPanel(){
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Color myGreen = new Color(30, 184, 35);
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, 1000, 300);
            g2.setColor(myGreen);
            g2.setStroke(new BasicStroke(4));
            g2.drawLine(x, 145, x + 100, 145);//开始直线
            g2.drawArc(x + 95, 106, 50, 200, 38, 103);//圆弧
            g2.drawLine(x + 140, 145, x + 160, 145);//连接直线
            g2.drawLine(x + 160, 145, x + 170, 10);//z开头
            g2.drawLine(x + 170, 10, x + 180, 200);//z下落
            g2.drawLine(x + 180, 200, x + 185, 145);//z回升
            g2.drawLine(x + 185, 145, x + 250, 145);//结束直线
        }
//            public void run() {
//                while(true){
//                    x++;
//                }
//            }
    };




//        jFrame.add(jPanel);


    //    同上
    JTextField pacemakerText = new JTextField("pacemaker off", 30);

    //    JTextArea pacemakerText=new JTextArea("pacemaker off",1,30);
    //bpm: beat per minute   normal: 60-100
    JTextField rateInit = new JTextField("Heart Rate:85 bpm");
    JSlider heartRate = new JSlider(0, 180, 85);

    //    PR 间隔
    JTextField pRrateInit = new JTextField("PR interval:130");

    JSlider pRRate = new JSlider(120, 200, 130);
    JCheckBox checkBox1;
    JCheckBox checkBox2;
    JCheckBox checkBox3;

    //视图展示
    public PacemakerView() {
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    void init() {

//    建立一个画本放文本框（暂时）  画本放了一个纵向的BOX，box里纵向添加了两个文本框，最后放心电图
        JPanel textP = new JPanel();
        textP.setPreferredSize(new Dimension(800, 800));
        Box box = Box.createVerticalBox();//创建纵向Box容器
        textP.add(box);

//        textP.setLocation(100, 20);
        jP4.setPreferredSize(new Dimension(800, 300));

        box.add(jP4);

        box.add(pacemakerText);

//        建立一个画本放按钮
        /*JPanel buttonP = new JPanel();
        buttonP.setLayout(new GridLayout(5, 1));*/

//        5个按钮
        on = new JButton("on");
        off = new JButton("off");
        AAI = new JButton("AAI");
        VVI = new JButton("VVI");
        DDD = new JButton("DDD");
        modify = new JButton("modify");
        off.setEnabled(false);
        AAI.setEnabled(false);
        VVI.setEnabled(false);
        DDD.setEnabled(false);
        modify.setEnabled(false);


//        一个画布，里面两个纵向的BOX横放
        JPanel jp = new JPanel();
        jp.setPreferredSize(new Dimension(800, 100));
        Box box1 = Box.createVerticalBox();//创建纵向Box容器
        Box box2 = Box.createVerticalBox();//创建纵向Box容器
//        一个box放按钮
        jp.add(box1);
        box1.add(on);
        box1.add(off);
        box1.add(AAI);
        box1.add(VVI);
        box1.add(DDD);
        box1.add(modify);
//一个box放两个文本框和两个滑块
        jp.add(box2);
        box2.add(rateInit);
        box2.add(heartRate);
        box2.add(pRrateInit);
        box2.add(pRRate);
//
        JPanel jp3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        Box box3 = Box.createVerticalBox();//创建纵向Box容器
        checkBox3 = new JCheckBox("battery failure");
        checkBox1 = new JCheckBox("Leeds SA failure");
        checkBox2 = new JCheckBox("Leeds AV failure");

        jp3.add(box3);
        box3.add(checkBox3);
        box3.add(checkBox1);
        box3.add(checkBox2);


//整体frame  两个画本置于南北
        /*frame.getContentPane().add("North", textP);
        frame.getContentPane().add("center", jp);
        frame.getContentPane().add("South", jp2);*/

        frame.add(textP, BorderLayout.NORTH);
        frame.add(jp, BorderLayout.CENTER);
        frame.add(jp3, BorderLayout.SOUTH);

        frame.setTitle("pacemaker simulator");

        frame.setSize(800, 1500);
        frame.setLocation(300, 200);
        frame.setResizable(true);  // 允许修改窗口的大小
        frame.setVisible(true);
//        给JSlider设置监听
        heartRate.addChangeListener(this);
        pRRate.addChangeListener(this);
//        给按钮设置监听
        AAI.addActionListener(this);
        VVI.addActionListener(this);
//        DDD.addActionListener(this);
        on.addActionListener(this);
        off.addActionListener(this);
        modify.addActionListener(this);


    }


    public void on() {
        ON = true;
        off.setEnabled(true);
        on.setEnabled(false);
        AAI.setEnabled(true);
        VVI.setEnabled(true);
        DDD.setEnabled(true);
        modify.setEnabled(false);
        int rate = heartRate.getValue();
        int pr = pRRate.getValue();
        int pp = controller.getPP(rate);
        int pp_pr = pp - pr;
        resultText.setText("PR interval:" + pr + "  QT interval+X:" + pp_pr);
    }

    public void off() {
        ON = false;
        on.setEnabled(true);
        off.setEnabled(false);
        AAI.setEnabled(false);
        VVI.setEnabled(false);
        DDD.setEnabled(false);
        modify.setEnabled(false);
        resultText.setText("心电图区域");
        pacemakerText.setText("pacemaker off");
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int rate;
        int pr;
        int pp;
        int pp_pr;
        if(e.getSource() == heartRate) {
            rate = heartRate.getValue();
            pr = pRRate.getValue();
            pp = controller.getPP(rate);
            pp_pr = pp - pr;
            System.out.println("Heart Rate:" + rate);

            rateInit.setText("Heart Rate:" + rate + "bpm");
            if(ON == true) {
//                最后需要换成心率图
                resultText.setText("PR interval:" + pr + "  QT interval+X:" + pp_pr);
//            AAIMode();
                int lowerRate = controller.getLowerRate();
                System.out.println("lowerRate:" + lowerRate);
                if(lowerRate != 0) {
                    if(rate < lowerRate) {
                        //最终需要显示心电图
                        pacemakerText.setText("AAI 开启" + " lowerRate:" + lowerRate + "状态：心房起搏");
                    } else if(rate > lowerRate) {
                        //最终需要显示心电图
                        pacemakerText.setText("AAI 开启" + " lowerRate:" + lowerRate + "状态：抑制起搏");
                    }
                }
                int lowRate = controller.getLowRate();
                System.out.println("lowRate:" + lowerRate);
                if(lowRate != 0) {
                    if(rate < lowRate) {
                        //最终需要显示心电图
                        pacemakerText.setText("VVI 开启" + " lowRate:" + lowRate + "状态：心室起搏");
                    } else if(rate > lowRate) {
                        //最终需要显示心电图
                        pacemakerText.setText("VVI 开启" + " lowRate:" + lowRate + "状态：抑制起搏");
                    }

                }


            } else {
                resultText.setText("心电图区域");
            }

//            最后需要换成心率图
            /*resultText.setText("PR interval:" + pr + "  QT interval+X:" + pp_pr);
//            AAIMode();
            int lowerRate=controller.getLowerRate();
            System.out.println("lowerRate:" + lowerRate);
            if(lowerRate!=0){
                if(rate<lowerRate){
                    //最终需要显示心电图
                    pacemakerText.setText("AAI 开启"+" lowerRate:"+lowerRate+ "状态：心房起搏");
                }else if(rate>lowerRate){
                    //最终需要显示心电图
                    pacemakerText.setText("AAI 开启"+" lowerRate:"+lowerRate+ "状态：抑制起搏");
                }
            }
            int lowRate=controller.getLowRate();
            System.out.println("lowRate:" + lowerRate);
            if(lowRate!=0){
                if(rate<lowRate){
                    //最终需要显示心电图
                    pacemakerText.setText("VVI 开启"+" lowRate:"+lowRate+ "状态：心房起搏");
                }else if(rate>lowRate){
                    //最终需要显示心电图
                    pacemakerText.setText("VVI 开启"+" lowRate:"+lowRate+ "状态：抑制起搏");
                }

            }*/


        } else if(e.getSource() == pRRate) {
            rate = heartRate.getValue();
            pr = pRRate.getValue();
            pp = controller.getPP(rate);
            pp_pr = pp - pr;
            System.out.println("PR interval:" + pr);
            pRrateInit.setText("PR interval:" + pr + "ms");
            //            最后需要换成心率图
            if(ON == true) {
                resultText.setText("PR interval:" + pr + "  QT interval+X:" + pp_pr);
            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == AAI) {
//            设置默认的最低起搏模式
            controller.setLowRate(0);
            controller.setLowerRate(60);
            AAIMode();
            VVI.setEnabled(false);
            DDD.setEnabled(false);
            modify.setEnabled(true);
        } else if(e.getSource() == VVI) {
            controller.setLowerRate(0);
            controller.setLowRate(60);
            VVIMode();
            AAI.setEnabled(false);
            DDD.setEnabled(false);
            modify.setEnabled(true);

        } else if(e.getSource() == on) {
            on();
        } else if(e.getSource() == off) {
            off();
//            resultText.setText("心电图区域");

        } else if(e.getSource() == modify) {
//            点击修改之后弹窗，不太会写


//            JDialog frame = new JDialog();//构造一个新的JFrame，作为新窗口。
//            JLabel jl = new JLabel();// 注意类名别写错了。
//            JButton jb=new JButton("Confirm");
//            // 参数 APPLICATION_MODAL：阻塞同一 Java 应用程序中的所有顶层窗口（它自己的子层次
//            JTextField text = new JTextField("please iuput the right answer!");
//            jl.add(new JLabel("Doctor:"));
//            jl.add(text);
//            jl.add(jb);
//            jb.addActionListener(this);
//            frame.getContentPane().add(jl,BorderLayout.CENTER);
//            frame.setTitle("input");
//            frame.setSize(300, 250);
//            frame.setLocation(400, 300);
////            frame.setResizable(true);  // 允许修改窗口的大小
//            frame.setVisible(true);
//            frame.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        }

    }

    public void AAIMode() {
        int lowerRate = controller.getLowerRate();
        heartRate.addChangeListener(this);
        int rate = heartRate.getValue();
        if(rate < lowerRate) {
            //最终需要显示心电图
            pacemakerText.setText("AAI 开启" + " lowerRate:" + lowerRate + "状态：心房起搏");
        } else if(rate > lowerRate) {
            //最终需要显示心电图
            pacemakerText.setText("AAI 开启" + " lowerRate:" + lowerRate + "状态：抑制起搏");
        }

    }

    public void VVIMode() {
        int lowRate = controller.getLowRate();
        heartRate.addChangeListener(this);
        int rate = heartRate.getValue();
        if(rate < lowRate) {
            //最终需要显示心电图
            pacemakerText.setText("VVI 开启" + " lowRate:" + lowRate + "状态：心室起搏");
        } else if(rate > lowRate) {
            //最终需要显示心电图
            pacemakerText.setText("VVI 开启" + " lowRate:" + lowRate + "状态：抑制起搏");
        }

    }
}
