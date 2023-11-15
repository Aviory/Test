package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Monster extends JPanel {
    Points points = new Points();
    private int xPosition = -65;
    private int yPosition = 0;
    private int width = 200;
    private int height = 235;

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
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
            image = ImageIO.read(new File("res/Wraith.png"));
            g.drawImage(image,getxPosition(),getyPosition(),null);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
///gfasgag
    class IdleAnimation extends Thread{
        @Override
        public void run() {
            super.run();
            setyPosition(0);

            while (true){
                setxPosition(-65);
                for (int i = 0; i < points.listXWraith1Idle.length; i++) {
                    setxPosition(points.listXWraith1Idle[i]);
                    repaint();

                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
