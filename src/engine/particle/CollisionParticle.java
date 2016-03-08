package engine.particle;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class CollisionParticle extends Particle {
    public CollisionParticle(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
    public CollisionParticle(float x, float y, float width, float height, Color color) {
        super(x, y, width, height, color);
    }

    @Deprecated
    public void update(long delta){
        update(delta,new CopyOnWriteArrayList<>());
    }
    public abstract void update(long delta, CopyOnWriteArrayList<CollisionParticle> particles);
}
