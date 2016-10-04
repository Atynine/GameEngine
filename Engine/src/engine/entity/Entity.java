package engine.entity;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public abstract class Entity extends Rectangle2D.Float {
    protected boolean alive = true;

    public Entity(float x, float y, float w, float h){
        super(x,y,w,h);
    }

    public abstract void update(long delta);

    public void setLocation(float x , float y){
        this.x = x;
        this.y = y;
    }
    public Point2D.Float getLocation(){
        return new Point2D.Float(this.x,this.y);
    }
    public boolean isAlive(){
        return this.alive;
    }
    public void setAlive(boolean alive){
        this.alive = alive;
    }
}
