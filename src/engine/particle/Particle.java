package engine.particle;

import engine.entity.Entity;
import engine.entity.Paintable;

import java.awt.*;

public abstract class Particle extends Entity implements Paintable{
    protected Color color = new Color(50,50,50);
    protected float speed = 1;
    public Particle(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
    public Particle(float x, float y, float width, float height, Color color) {
        super(x, y, width, height);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public float getSpeed() {
        return speed;
    }
    public void setSpeed(long speed) {
        this.speed = speed;
    }
}
