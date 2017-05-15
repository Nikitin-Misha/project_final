package com.bot;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

public class Line extends JPanel {
    public Point a;
    public Point b;
    public Line(Point a, Point b) {
        this.a = a;
        this.b = b;
    }
    @Override
    public void paint(Graphics g){
        g.setColor(Color.RED );
        g.drawLine(a.x, a.y, b.x, b.y);
        g.setColor(Color.red);
    }
}
