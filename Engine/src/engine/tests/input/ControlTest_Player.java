package engine.tests.input;

import engine.entity.Entity;
import engine.entity.Paintable;
import engine.input.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ControlTest_Player extends Entity implements Paintable{
    public ControlTest_Player(float x, float y, float w, float h) {
        super(x, y, w, h);
    }
    private float xSpeed, ySpeed;
    private float acceleration = 500f;
    private static final float MAX_SPEED = 250;

    @Override
    public void update(long delta) {
        float d = (delta/1000f);

        updateXSpeed(d);
        updateYSpeed(d);

        if(xSpeed > MAX_SPEED) xSpeed = MAX_SPEED;
        if(xSpeed < -MAX_SPEED) xSpeed = -MAX_SPEED;
        if(ySpeed > MAX_SPEED) ySpeed = MAX_SPEED;
        if(ySpeed < -MAX_SPEED) ySpeed = -MAX_SPEED;

        this.x+=xSpeed*d;
        this.y+=ySpeed*d;
    }
    private void updateXSpeed(float d){
        if(Keyboard.isKeyPressed(KeyEvent.VK_A)){
            if(this.xSpeed > 0){
                this.xSpeed-=acceleration*2*d;
            }else{
                this.xSpeed-=acceleration*d;
            }
        }else if(Keyboard.isKeyPressed(KeyEvent.VK_D)){
            if(this.xSpeed < 0){
                this.xSpeed+=acceleration*2*d;
            }else{
                this.xSpeed+=acceleration*d;
            }
        }else if(this.xSpeed < 0){
            this.xSpeed+=acceleration*d;
        }else if(this.xSpeed > 0){
            this.xSpeed-=acceleration*d;
        }
    }
    private void updateYSpeed(float d){
        if(Keyboard.isKeyPressed(KeyEvent.VK_W)){
            if(this.ySpeed > 0){
                this.ySpeed-=acceleration*2*d;
            }else{
                this.ySpeed-=acceleration*d;
            }
        }else if(Keyboard.isKeyPressed(KeyEvent.VK_S)){
            if(this.ySpeed < 0){
                this.ySpeed+=acceleration*2*d;
            }else{
                this.ySpeed+=acceleration*d;
            }
        }else if(this.ySpeed < 0){
            this.ySpeed+=acceleration*d;
        }else if(this.ySpeed > 0){
            this.ySpeed-=acceleration*d;
        }
    }

    @Override
    public void onPaint(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect((int)x,(int)y,(int)width,(int)height);
    }
}
