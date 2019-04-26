package com.ljb.imgfull;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class FullScreen extends JFrame {

    private Image img001;
    private Image img002;
    private Image imgEnd;
    private Image imgBg = null;
    private boolean isEnd;

    private int sWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();  //获得屏幕宽度
    private int sHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();//获得屏幕高度

    public FullScreen() {
        this.getContentPane().add(new JPanel()); //在容器中添加画布

        //获得自定义图片
        img001 = Toolkit.getDefaultToolkit().getImage(FullScreen.class.getResource("img/img50.jpg"));
        img002 = Toolkit.getDefaultToolkit().getImage(FullScreen.class.getResource("img/img100.jpg"));
        imgEnd = Toolkit.getDefaultToolkit().getImage(FullScreen.class.getResource("img/imgEnd.png"));

        try {
            Robot rt = new Robot();
            Toolkit tk = Toolkit.getDefaultToolkit();  //获得工具包
            Dimension dt = tk.getScreenSize();   //获得屏幕大小
            imgBg = rt.createScreenCapture(new Rectangle(dt));  //获得背景图片
        } catch (AWTException e) {
            e.printStackTrace();
        }

        this.setUndecorated(true);        //去掉标题栏
        this.setSize(sWidth, sHeight);    //设置容器大小
        this.setVisible(true);            //显示容器	【自动调用paint()方法】

        isEnd = false;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isEnd = true;
            }
        }, 6000L);
    }

    public void paint(Graphics g) {
        g.drawImage(imgBg, 0, 0, this);  //绘制背景图片

        while (true) {
            try {
                Thread.sleep(200);        //休息200毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isEnd) {
                g.drawImage(imgEnd, sWidth / 2 - imgEnd.getWidth(null) / 2, sHeight / 2 - imgEnd.getHeight(null) / 2, null);
            } else {
                //绘制自定义图片
                g.drawImage(img001, getRandom(-100, sWidth + 100), getRandom(-100, sHeight + 100), null);
                g.drawImage(img002, getRandom(-100, sWidth + 100), getRandom(-100, sHeight + 100), null);
            }
        }
    }

    public int getRandom(int min, int max) {
        return new Random().nextInt(max) % (max - min + 1) + min;
    }


    /*************************
     **********开始程序********
     **************************/
    public static void main(String[] args) {
        new FullScreen();
    }
}
