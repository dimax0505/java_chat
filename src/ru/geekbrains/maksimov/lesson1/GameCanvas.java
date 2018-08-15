package ru.geekbrains.maksimov.lesson1;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {
    private MainWindow mainWindow;
    private long lastFrameTime;

    GameCanvas(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        lastFrameTime = System.nanoTime();
        setBackground(Color.BLUE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;
        lastFrameTime = currentTime;

        // friday magic
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mainWindow.onDrawFrame(this, g, deltaTime);

        repaint();
    }

    public int getLeft() { return 0; }
    public int getRight() { return getWidth() - 1; }
    public int getTop() { return 0; }
    public int getBottom() { return getHeight() - 1; }
}
