package game;

import engine.Screen;
import engine.terrain.Chunk;
import engine.terrain.Map;
import engine.terrain.SimplexNoise;

import java.awt.*;
import java.util.TreeMap;

public class FarmGame extends Screen {
    private static final int MAX_X = 20;
    private static final int MAX_Y = 11;
    private static final float WHEAT_HEIGHT = .6f;
    private static final Color COLOR_GRASS = new Color(37, 106, 52);
    private static final Color COLOR_WHEAT = new Color(144, 164, 42);
    private static final TreeMap<Float,Color> colorMap = new TreeMap<>();
    {
        colorMap.put(0f,COLOR_GRASS);
        colorMap.put(WHEAT_HEIGHT,COLOR_GRASS);
        colorMap.put(WHEAT_HEIGHT+.001f,COLOR_WHEAT);
        colorMap.put(1f,COLOR_WHEAT);
    }

    private Map terrain = new Map(64);
    private int xGen, yGen;

    private FarmPlayer player;

    public FarmGame(String title, int width, int height) {
        super(title, width, height);
    }

    @Override
    public void onStart() {
        SimplexNoise.seed();
        terrain = new Map(64);
        player = new FarmPlayer();
    }

    @Override
    public void onPaint(Graphics2D g) {
        for(int x = 0; x < MAX_X; x++){
            for(int y = 0; y < MAX_Y; y++){
                if(terrain.isGenerated(x,y)){
                    drawChunk(g, terrain.getChunk(x,y));
                }
            }
        }
        player.onPaint(g);
    }

    public void drawChunk(Graphics2D g, Chunk chunk){
        float[][] heightVals = chunk.getValues();
        for(int x = 0; x < terrain.getChunkSize(); x++){
            for(int y = 0; y < terrain.getChunkSize(); y++){
                g.setColor(Chunk.getColor(heightVals[x][y], colorMap));
                g.fillRect(chunk.getX()*terrain.getChunkSize() + x, chunk.getY()*terrain.getChunkSize() + y, 1, 1);
            }
        }
    }

    @Override
    public void update(long delta) {
        if(delta == 0) delta = 1;
        if(xGen == MAX_X && yGen == MAX_Y){
            player.update(delta);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
        //Generate map
        terrain.generate(xGen, yGen);
        xGen++;
        if(xGen > MAX_X){
            xGen = 0;
            yGen++;
        }
    }

    public static void main(String[] args){
        FarmGame game = new FarmGame("Farm", 1280,720);
    }
}
