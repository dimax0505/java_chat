package ru.geekbrains.maksimov.lesson1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {
    private static final int POS_X = 600;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow();
            }
        });
    }

    MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X,POS_Y,WINDOW_WIDTH,WINDOW_HEIGHT);
        setTitle("Пузырики");

        GameCanvas gameCanvas = new GameCanvas(this);
        add(gameCanvas, BorderLayout.CENTER);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON3) {
                    sprites[currentBalls]=new Ball();
                    currentBalls++;
                    if (currentBalls==sprites.length-5){
                        Sprite[] spritestemp = new Sprite[currentBalls*2];
                        for (int i=0; i<sprites.length; i++) {
                            spritestemp[i]=sprites[i];
                        }
                        sprites = spritestemp;
                    }
                }
                if (e.getButton()==MouseEvent.BUTTON1) {
                    sprites[currentBalls]=null;
                    currentBalls--;
                }

            }
        });
        repaint();
        initGame();
        setVisible(true);
    }
    int currentBalls = 5;
    int maxBalls = 20;
    Sprite[] sprites = new Sprite[maxBalls];
    Background background;

    private void initGame() {
        for (int i = 0; i < currentBalls; i++) {
            sprites[i] = new Ball();
        }
        background = new Background();
    }

    public void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(GameCanvas canvas, float deltaTime) {
        background.update();
        for (int i = 0; i < currentBalls; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        background.render(canvas,g);
        for (int i = 0; i < currentBalls; i++) {
            sprites[i].render(canvas, g);

        }
    }
}
