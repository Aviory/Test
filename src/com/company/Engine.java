package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Engine extends JPanel {
    StartAnimation startAnimation;
    StartReversedAnimation startReversedAnimation;
    Points points = new Points();

    private int timer = 0;
    private int x = -96;
    private int y = -22;
    private int width = 97;
    private int height = 110;
    private int countOfFrames;
    private String currentAnim;
    private boolean isAnimationAlive;

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("res/DarthVader.png"));
            g.drawImage(image, x, y, null);
            System.out.println("Attention! Frame is changing >>>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultStand(){
        x = -972;
        y = -22;
    }

    //find correct list to find coordinates
    public int findCurrentAnim(int i) {
        switch (currentAnim) {
            case "OpenOrCloseLightSword":
                return points.listXOpenOrCloseSwordAnimation[i];
            case "PowerfulLongAttack":
                return points.listXPowerfulLongAttack[i];
            case "DeathAttack":
                return points.listXDeathAttack[i];
            default:
                System.out.println("unknown animation");
                return -1;
        }
    }

    //starting animation
    class StartAnimation extends Thread {
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < countOfFrames; i++) {
                x = findCurrentAnim(i);
                repaint();
                System.out.println("x >>> " + x);
                try {
                    Thread.sleep(125);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            setDefaultStand();
            repaint();
        }
    }

    class StartReversedAnimation extends Thread {
        @Override
        public void run() {
            super.run();
            for (int i = countOfFrames - 1; i >= 0; i--) {
                x = findCurrentAnim(i);
                System.out.println("x >>> " + x);
                repaint();
                try {
                    Thread.sleep(125);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            countOfFrames = 0;
            isAnimationAlive = false;
        }
    }

    //actions
    class OpenLightSword extends Thread {
        @Override
        public void run() {
            super.run();
            if (x == -96 && y == -22 && !isAnimationAlive) {
                countOfFrames = 8;
                currentAnim = "OpenOrCloseLightSword";

                startAnimation = new StartAnimation();
                startAnimation.start();
            }
        }
    }
    class CloseLightSword extends Thread{
        @Override
        public void run() {
            super.run();
            if (x == -972 && y == -22 && !isAnimationAlive){
                countOfFrames = 8;
                currentAnim = "OpenOrCloseLightSword";

                startReversedAnimation = new StartReversedAnimation();
                startReversedAnimation.start();
            }
        }
    }
    class PowerfulLongAttack extends Thread{
        @Override
        public void run() {
            super.run();
            if (x == -972 && y == -22 && !isAnimationAlive){
                y = -167;
                countOfFrames = 13;
                currentAnim = "PowerfulLongAttack";

                startAnimation = new StartAnimation();
                startAnimation.start();
            }
        }
    }

    class DeathAttack extends Thread{
        @Override
        public void run() {
            super.run();
            if (x == -972 && y == -22 && !isAnimationAlive){
                countOfFrames = 18;
                y = -692;
                currentAnim = "DeathAttack";

                startAnimation = new StartAnimation();
                startAnimation.start();
            }
        }
    }
    private Statistic statistic;

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }
    public void updateTimer() {
        timer++;
        if (statistic != null) {
            statistic.setTxt(timer);
        }
    }
    class Timer extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                while (true) {
                    Thread.sleep(1000);
                    updateTimer();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
