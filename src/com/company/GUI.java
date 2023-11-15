package com.company;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI extends JFrame implements KeyListener {
    Statistic statistic;
    Engine engine = new Engine();
    Monster monster = new Monster();
    Monster.IdleAnimation idleAnimation = monster.new IdleAnimation();
    private int xPosDarth = 325;
    private int yPosDarth = 325;
    private int xPosMonster = 50;
    private int yPosMonster = 50;
    private boolean isStatisticActivated;
    GUI(){
        Engine.Timer timer = engine.new Timer();
        timer.start();

        setTitle("Anim-Game");
        setSize(750,750);
        setLayout(null);

        monster.setBounds(xPosMonster,yPosMonster,monster.getWidth(),monster.getHeight());
        engine.setBounds(xPosDarth, yPosDarth,engine.getWidth(),engine.getHeight());

        add(engine);
        add(monster);

        addKeyListener(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public boolean isCollision() {
        int engineRight = xPosDarth + engine.getWidth();
        int engineBottom = yPosDarth + engine.getHeight();
        int monsterRight = xPosMonster + monster.getWidth();
        int monsterBottom = yPosMonster + monster.getHeight();

        if (xPosDarth < monsterRight && engineRight > xPosMonster &&
                yPosDarth < monsterBottom && engineBottom > yPosMonster) {
            return false;
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()){
            //movement
            case 'w':
                System.out.println("key 'w' pressed");
                yPosDarth -=5;
                engine.setBounds(xPosDarth, yPosDarth,engine.getWidth(),engine.getHeight());
                break;
            case 's':
                System.out.println("key 's' pressed");
                yPosDarth +=5;
                engine.setBounds(xPosDarth, yPosDarth,engine.getWidth(),engine.getHeight());
                break;
            case 'a':
                System.out.println("key 'a' pressed");
                xPosDarth -=5;
                engine.setBounds(xPosDarth, yPosDarth,engine.getWidth(),engine.getHeight());
                break;
            case 'd':
                System.out.println("key 'd' pressed");
                xPosDarth +=5;
                engine.setBounds(xPosDarth, yPosDarth,engine.getWidth(),engine.getHeight());
                break;

            case 't':
                System.out.println("key 't' pressed");
                idleAnimation.start();
                break;
            //animation
            case 'e':
                System.out.println("key 'e' pressed");
                Engine.OpenLightSword openLightSword = engine.new OpenLightSword();
                openLightSword.start();
                break;
            case 'r':
                System.out.println("key 'r' pressed");
                Engine.CloseLightSword closeLightSword = engine.new CloseLightSword();
                closeLightSword.start();
                break;
            case 'x':
                System.out.println("key 'x' pressed");
                Engine.PowerfulLongAttack powerfulLongAttack = engine.new PowerfulLongAttack();
                powerfulLongAttack.start();
                monster.setVisible(isCollision());
                break;
            case 'f':
                System.out.println("key 'f' pressed");
                Engine.DeathAttack deathAttack = engine.new DeathAttack();
                deathAttack.start();
                monster.setVisible(isCollision());
                break;
            case 'l':
                System.out.println("key 'l' pressed >>> monster is visible");
                monster.setVisible(true);
                break;

            case 'q':
                System.out.println("key 'q' pressed");
                if (!isStatisticActivated) {
                    statistic = new Statistic(engine);
                    isStatisticActivated = true;
                }
                statistic.setTxt(engine.getTimer());
                break;
            default:
                System.out.println("unknown key/button");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
