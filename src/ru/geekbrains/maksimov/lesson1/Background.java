package ru.geekbrains.maksimov.lesson1;

import java.awt.*;
import java.util.Random;

public class Background {
    protected int r = 1;
    protected int g = 1;
    protected int b = 1;
    protected int sign = 1;
    private Color color = new Color(r,g,b);
    private Random random = new Random();

    Background() {}

    int change (int ch){
       switch (ch){
           case 0: sign = 1;
           break;
           case 255: sign = -1;
           break;
       }
       return ch+sign;
    }

    void update() {
        switch (random.nextInt(2)){
            case 0: r = change(r);
            break;
            case 1: g = change(g);
            break;
            case 2: b = change(b);
            break;
        }
        color = new Color(r,g,b);
    }

    void render(ru.geekbrains.maksimov.lesson1.GameCanvas canvas, Graphics g) {
        g.setColor(color);
        g.fillRect(canvas.getX(),canvas.getY(),canvas.getWidth(),canvas.getHeight());
    }
}
