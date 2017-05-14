package com.bot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.awt.image.renderable.RenderableImage;
import java.io.FileNotFoundException;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static ArrayList<Point> points = new ArrayList<Point>();
    static int Max1 = 0;
    static int Max2 = 0;

    private static ArrayList<com.bot.Line> lines;
    public static void createGUI() throws FileNotFoundException {
        lines = new ArrayList<com.bot.Line>();
        final JFrame frame = new JFrame("Testframe");
        frame.setPreferredSize(new Dimension(700,700));
        JPanel panel = new JPanel(new BorderLayout());
        Panel butPanel = new Panel();
        butPanel.setLayout(null);
        butPanel.setPreferredSize(new Dimension(250,700));
        final Panel pointpane   = new Panel();
        pointpane.setLayout(null);
        //pointpane.setPreferredSize(new Dimension(350,700));

        JLabel addPointwithCoords = new JLabel("Добавить точку по координатам");
        addPointwithCoords.setBounds(2,2,300,25);
        butPanel.add(addPointwithCoords);
        JLabel addRandomPoints = new JLabel("Добавить рандомное количество точек");
        addRandomPoints.setBounds(2,50,300,25);
        butPanel.add(addRandomPoints);
        JLabel X = new JLabel("X:");
        X.setBounds(2,25,15,25);
        butPanel.add(X);
        JLabel Y = new JLabel("Y:");
        Y.setBounds(45,25,15,25);
        butPanel.add(Y);
        JLabel N = new JLabel("NUM:");
        N.setBounds(2,70,30,25);
        butPanel.add(N);
        final JTextField x = new JTextField();
        x.setBounds(17,25, 25,25);
        butPanel.add(x);
        final JTextField y = new JTextField();
        y.setBounds(60,25, 25,25);
        butPanel.add(y);
        final JTextField n = new JTextField();
        n.setBounds(35,70,25,25);
        butPanel.add(n);



        JButton button1 = new JButton("Добавить точку(и)");
        button1.setBounds(2,100,160,40);
        butPanel.add(button1);
        button1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int X = (!x.getText().equals("")?Integer.parseInt(x.getText()):0);
                int Y= (!y.getText().equals("")?Integer.parseInt(y.getText()):0);
                int N = (!n.getText().equals("")?Integer.parseInt(n.getText()):0);
                if ((X>0)&&(Y>0)) {
                    Point b = new Point(X, Y);
                    points.add(b);
                    b.setBounds(b.x,b.y,b.x+3,b.y+3);
                    pointpane.add(b);
                    pointpane.revalidate();
                    pointpane.repaint();
                }
                else {
                    if (N>0){
                        for (int i=0;i<N;i++){
                            Point b = new Point((int)(Math.random()*(frame.getWidth()-250)), (int)(Math.random()*frame.getHeight()));
                            points.add(b);
                            b.setBounds(b.x,b.y,b.x+3,b.y+3);
                            pointpane.add(b);
                            pointpane.revalidate();
                            pointpane.repaint();
                        }
                    }
                }

            }
        });
        JButton button2 = new JButton("очистить");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < points.size(); i++) {
                    while (points.size() > 0) {
                        int index = points.size() - 1;
                        Point point = points.remove(index);
                        pointpane.remove(point);
                        pointpane.repaint();
                        pointpane.revalidate();
                    }
                }
                for (
                        int i = 0; i < lines.size(); i++) {
                    while (lines.size() > 0) {
                        int index = lines.size() - 1;
                        com.bot.Line line = lines.remove(index);
                        pointpane.remove(line);
                        pointpane.repaint();
                        pointpane.revalidate();
                    }
                }
            }

        });
        final JLabel answerL = new JLabel("Ответ:");
        answerL.setBounds(2,550,300,25);
        butPanel.add(answerL);

        button2.setBounds(2,150,160,40);
        butPanel.add(button2);


        JButton button3 = new JButton("Найти max расстояние");
        button3.addActionListener(new ActionListener() {
                                      @Override
                                      public void actionPerformed(ActionEvent e) {
                                          double max = 0;
                                          for(int i=0;i<points.size();i++){
                                              for(int j=i+1;i<points.size();i++){//просчитываем всевозможные вариации с точками
                                                  double d = Point.Distanse(points.get(i),points.get(j));
                                                  if(max<d){
                                                      max=d;//если нашли больше, то новый max
                                                      Max1 = i;
                                                      Max2 = j;
                                                  }
                                              }
                                          }
                                          //System.out.println(max);//вывод max

                                          com.bot.Line l = new com.bot.Line(points.get(Max1), points.get(Max2));
                                          lines.add(l);
                                          l.setBounds(Max1,Max2,frame.getWidth(),frame.getHeight());
                                          pointpane.add(l);
                                          pointpane.revalidate();
                                          pointpane.repaint();
                                          answerL.setText("Ответ: "+max);

                                      }
                                  }

        );
        button3.setBounds(2,500,200,40);
        butPanel.add(button3);

        JButton button4 = new JButton("Загрузить из файла");
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        button4.setBounds(2,300,200,40);
        butPanel.add(button4);

        JButton button5 = new JButton("Загрузить в файл");
        button5.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        button5.setBounds(2,350,200,40);
        butPanel.add(button5);

        panel.add(pointpane,BorderLayout.CENTER);
        panel.add(butPanel,BorderLayout.EAST);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                try {
                    createGUI();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}