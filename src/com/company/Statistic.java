package com.company;

import javax.swing.*;

public class Statistic extends JFrame {
    private Engine engine;
    JTextField timeStr = new JTextField(6);
    JTextField timeTxt = new JTextField(7);
    public Statistic(Engine engine) {
        this.engine = engine;

        setTitle("Time, that you lived");
        setSize(350,100);
        setLayout(null);

        timeStr.setBounds(0,0,50,50);
        timeTxt.setBounds(50,0,50,50);

        add(timeStr);
        add(timeTxt);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void setTxt(int time){
        timeStr.setText("Time: ");
        timeTxt.setText(time+" sec");
        setVisible(true);
        toFront();
    }
}
