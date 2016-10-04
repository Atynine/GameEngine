package engine;

import engine.input.Keyboard;
import engine.input.Mouse;

import javax.swing.*;
import java.awt.*;

public abstract class Screen extends JPanel implements Runnable{
    private JFrame frame;
    public Screen(String title, int width, int height){
        //Setup the screen
        this.frame = new JFrame(title);
        this.frame.setSize(width,height);
        this.setSize(width,height);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.add(this);
        this.frame.setVisible(true);
        this.addMouseListener(new Mouse());
        frame.addKeyListener(new Keyboard());
        this.onStart();
        new Thread(this).start();
    }
    public abstract void onStart();
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        onPaint(g2d);
        super.repaint();
    }
    public abstract void onPaint(Graphics2D g);
    public abstract void update(long delta);


    @Override
    public void run() {
        long lastUpdate = System.currentTimeMillis();
        while(this.frame.isVisible()){
            long delta = (System.currentTimeMillis()-lastUpdate);
            //Update last time updated
            lastUpdate = System.currentTimeMillis();
            this.update(delta);
        }
    }
}
