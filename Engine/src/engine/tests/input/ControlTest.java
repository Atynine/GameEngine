package engine.tests.input;

import engine.Screen;

import java.awt.*;

public class ControlTest extends Screen {
    private ControlTest_Player player;
    public ControlTest(String title, int width, int height) {
        super(title, width, height);
    }

    @Override
    public void onStart() {
        player = new ControlTest_Player(100,100,5,5);
    }

    @Override
    public void onPaint(Graphics2D g) {
        player.onPaint(g);
    }

    @Override
    public void update(long delta) {
        player.update(delta);
    }

    public static void main(String[] args){
        ControlTest test = new ControlTest("Control Test", 500,500);
    }
}
