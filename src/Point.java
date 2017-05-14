package com.bot;

        import javax.swing.*;
        import java.awt.*;

public class Point extends JPanel {
    public int x;
    public int y;
    Point(int x, int y){
        this.x=x;
        this.y=y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void paint(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(0,0,5,5);
        g.setColor(Color.red);
    }
    static double Distanse(Point a,Point b){
        return Math.sqrt((a.getX()-b.x)*(a.getX()-b.x)+(a.getY()-b.y)*(a.getY()-b.y));
    }
}