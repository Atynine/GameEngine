package game.particles;

import engine.entity.Paintable;
import engine.particle.CollisionParticle;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.CopyOnWriteArrayList;

public class Sand extends CollisionParticle implements Paintable {
    private int maxX, maxY;
    public Sand(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.setSpeed(0);
        this.setColor(new Color(155,0,155));
    }

    @Override
    public void update(long delta, CopyOnWriteArrayList<CollisionParticle> particles) {
        Point2D.Float p = getNextLocation(delta,particles);
        this.speed+=delta/.98;
        this.setRect(p.x,p.y,this.width,this.height);
        if(this.getMaxY()>=this.maxY-1){
            //We've hit the bottom of the screen.
            this.y = this.maxY-this.width-1;
            this.speed = 0;
        }
    }
    private Point2D.Float getNextLocation(long delta, CopyOnWriteArrayList<CollisionParticle> particles){
        float y = this.y+(delta*this.getSpeed())/1000;
        for(CollisionParticle p : particles){
            if(!p.equals(this) && p.intersects(this)){
                return new Point2D.Float(this.x, p.y-p.height);
            }
        }
        return new Point2D.Float(this.x, y);
    }

    @Override
    public void onPaint(Graphics2D g) {
        this.maxX = g.getClipBounds().width;
        this.maxY = g.getClipBounds().height;
        g.setColor(this.getColor());
        g.fill(this);
        g.setColor(Color.black);
        g.draw(this);
    }
}
