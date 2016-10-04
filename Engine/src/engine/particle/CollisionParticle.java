package engine.particle;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

/*
@author Rylan Hoss
@description A particle that can collide with other collision enabled objects within the game
 */

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
