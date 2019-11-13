package game;

import engine.entity.Entity;
import engine.input.Keyboard;
import engine.interfaces.Paintable;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class FarmPlayer extends Entity implements Paintable {
    private static final Color PLAYER_COLOR = Color.BLACK;
    private static final int PLAYER_WIDTH = 50;
    private static final int PLAYER_HEIGHT = 20;
    private static final float ROTATION_SPEED = .0025f;
    private static final float MOVE_SPEED = .05f;
    private BufferedImage playerImage = new BufferedImage(PLAYER_WIDTH, PLAYER_HEIGHT, BufferedImage.TYPE_INT_RGB);
    private AffineTransform playerTransform;
    private float angle = 0;
    public FarmPlayer() {
        super(500,500,50,20);
        playerTransform = AffineTransform.getTranslateInstance(200,200);
    }

    public void rotate(float theta){
        playerTransform.rotate(theta, 25, 10);
        angle+=theta;
    }

    @Override
    public void update(long delta) {
        if(Keyboard.isKeyPressed(KeyEvent.VK_E)){
            rotate(ROTATION_SPEED * delta);
        }
        if(Keyboard.isKeyPressed(KeyEvent.VK_Q)){
            rotate(-ROTATION_SPEED * delta);
        }
        if(Keyboard.isKeyPressed(KeyEvent.VK_W)){
            playerTransform.translate(0, -MOVE_SPEED * delta);
        }
        if(Keyboard.isKeyPressed(KeyEvent.VK_S)){
            playerTransform.translate(0, MOVE_SPEED * delta);
        }
    }

    @Override
    public void onPaint(Graphics2D g) {
        g.setColor(PLAYER_COLOR);
        g.drawImage(playerImage, playerTransform, null);
    }
}
