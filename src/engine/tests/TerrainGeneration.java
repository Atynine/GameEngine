package engine.tests;

import engine.Screen;
import engine.terrain.SimplexNoise;

import java.awt.*;
import java.util.TreeMap;

public class TerrainGeneration extends Screen {
    private static final TreeMap<Float,Color> terrain = new TreeMap<Float,Color>();
    {
        terrain.put(0f,new Color(11,15,55));
        terrain.put(.05f,new Color(22,32,118));
        terrain.put(.4f,new Color(22,32,118));
        terrain.put(.6f,new Color(74,89,221));
        terrain.put(.65f,new Color(234,219,151));
        terrain.put(.7f,new Color(13,69,12));
        terrain.put(.8f,new Color(13,69,12));
        terrain.put(.85f,new Color(72,67,60));
        terrain.put(.95f,new Color(72,67,60));
        terrain.put(1f,new Color(194,189,182));
    }
    Map map = new Map(64);
    private int currentXGeneration = 0, currentYGeneration = 0;
    private int containerHeight = 1;
    private int containerWidth = 1;
    int frames = 0;
    public TerrainGeneration(String title, int width, int height) {
        super(title, width, height);
    }
    @Override
    public void onStart() {
        map = new Map(64);
        SimplexNoise.seed();
        map.generate(0,0);
    }
    @Override
    public void onPaint(Graphics2D g) {
        //Check if map is generated enough to show
        if(map == null) return;
        containerWidth = g.getClipBounds().width;
        containerHeight = g.getClipBounds().height;
//        for(int x = 0; x < 30; x++){
//            for(int y = 0; y < 17; y++){
//                if(!map.isGenerated(x,y)){
//                    int width, height;
//                    width = g.getClipBounds().width;
//                    height = g.getClipBounds().height;
//
//                    g.setColor(TerrainSetting.DEEP_OCEAN.base);
//                    g.fillRect(0,0,width,height);
//                    g.setColor(Color.WHITE);
//                    g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
//                    g.drawString("Generating map...", width/2-(g.getFontMetrics().stringWidth("Generating map...")/2) ,height/2-(g.getFontMetrics().getHeight()/2));
//                    return;
//                }
//            }
//        }
        for(int i = 0; i < Math.min(containerWidth/map.getChunkSize()+1, map.getSize()); i++){
            for(int j = 0; j < Math.min(containerHeight/map.getChunkSize()+1, map.getSize()); j++){
                Chunk chunk = map.getChunk(i,j);
                if(chunk == null) continue;
                g.drawImage(chunk.getImage(), i*map.getChunkSize(),j*map.getChunkSize(),null);
            }
        }
        g.setColor(Color.WHITE);
        g.drawString("Last FPS: " + lastFrames,5,15);
        frames++;
    }
    
    public int updateDelta = 0;
    public int lastFrames = 0;
    @Override
    public void update(long delta) {
        //Generate map chunks
        updateDelta+=delta;
        if(updateDelta > 1000){
            lastFrames=frames;
            frames = 0;
            updateDelta = 0;
        }
        if(currentYGeneration < map.getSize()){
            map.generate(currentXGeneration,currentYGeneration);
            map.getChunk(currentXGeneration,currentYGeneration).createImage(terrain);
            currentXGeneration++;
            if(currentXGeneration >= map.getSize()){
                currentXGeneration = 0;
                currentYGeneration++;
            }
        }
    }


    public static void main(String[] args){
        TerrainGeneration game = new TerrainGeneration("Terrain Test", 500,500);
    }
}
