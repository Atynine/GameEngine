package game;

import engine.Screen;
import engine.particle.CollisionParticle;
import engine.particle.Particle;
import game.particles.Sand;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game extends Screen {

    private CopyOnWriteArrayList<CollisionParticle> particles;
    private long deltaTotal = 0;


    public Game(String title, int width, int height) {
        super(title, width, height);
    }
    @Override
    public void onStart() {
        particles = new CopyOnWriteArrayList<>();
    }

    @Override
    public void onPaint(Graphics2D g) {
        for(CollisionParticle sand : particles) sand.onPaint(g);
    }

    @Override
    public void update(long delta) {
        for(CollisionParticle sand : particles) sand.update(delta, particles);
        deltaTotal+=delta;
        if(deltaTotal > 500){
            deltaTotal = 0;
            for(int x = 100; x < 130; x+=5){
                particles.add(new Sand(x,100,4,4));
            }
        }
    }

    public static void main(String[] args){
        Game game = new Game("Game",640,480);
    }
}
