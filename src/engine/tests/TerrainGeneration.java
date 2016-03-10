package engine.tests;

import engine.Screen;
import engine.terrain.SimplexNoise;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

public class TerrainGeneration extends Screen {
    private static final TreeMap<Float,Color> terrain = new TreeMap<Float,Color>();
    {
        //Insert layers into terrain map

        terrain.put(0f,Color.BLACK);
        terrain.put(.1f,Color.RED);
        terrain.put(.2f,Color.ORANGE);
        terrain.put(.3f,Color.YELLOW);
        terrain.put(.4f,Color.GREEN);
        terrain.put(.5f,Color.CYAN);
        terrain.put(.6f,Color.BLUE);
        terrain.put(.7f,Color.PINK);
        terrain.put(.8f,Color.MAGENTA);
        terrain.put(.9f,Color.RED);
        terrain.put(1f,Color.WHITE);


//        terrain.put(0f,new Color(11,15,55));
//        terrain.put(.05f,new Color(22,32,118));
//        terrain.put(.3f,new Color(22,32,118));
//        terrain.put(.5f,new Color(74,89,221));
//        terrain.put(.55f,new Color(234,219,151));
//        terrain.put(.7f,new Color(13,69,12));
//        terrain.put(1.1f,new Color(72,67,60));
//        terrain.put(1.25f,Color.WHITE);
    }
    Map map = new Map(256);
    private int currentXGeneration = 0, currentYGeneration = 0;
    private int containerHeight = 1;
    private int containerWidth = 1;
    private boolean saved = false;
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
        for(int x = 0; x < 30; x++){
            for(int y = 0; y < 17; y++){
                if(!map.isGenerated(x,y)){
                    int width, height;
                    width = g.getClipBounds().width;
                    height = g.getClipBounds().height;

                    g.setColor(Color.BLUE);
                    g.fillRect(0,0,width,height);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
                    g.drawString("Generating map...", width/2-(g.getFontMetrics().stringWidth("Generating map...")/2) ,height/2-(g.getFontMetrics().getHeight()/2));
                    return;
                }
            }
        }
        for(int i = 0; i < Math.min(containerWidth/map.getChunkSize()+1, map.getSize()); i++){
            for(int j = 0; j < Math.min(containerHeight/map.getChunkSize()+1, map.getSize()); j++){
                Chunk chunk = map.getChunk(i,j);
                if(chunk == null) continue;
                g.drawImage(chunk.getImage(), i*map.getChunkSize(),j*map.getChunkSize(),null);
            }
        }
        g.setColor(new Color(0,0,0,75));
        g.fillRect(0,0,10000,100000);
        //g.drawString("Last FPS: " + lastFrames,5,15);
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
                System.out.println("Generating row: " + currentYGeneration);
            }
        }else if(!saved){
            System.out.println("Attempting to save as image");
            BufferedImage img = new BufferedImage(map.getChunkSize()*map.getSize(),map.getChunkSize()*map.getSize(),BufferedImage.TYPE_INT_RGB);
            Graphics g = img.getGraphics();
            for(int i = 0; i < map.getSize(); i++){
                for(int j = 0; j < map.getSize(); j++){
                    Chunk chunk = map.getChunk(i,j);
                    if(chunk == null) continue;
                    g.drawImage(chunk.getImage(), i*map.getChunkSize(),j*map.getChunkSize(),null);
                }
            }
            try {
                ImageIO.write(img,"png",new File("save2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Saved.");
            saved = true;
        }
    }


    public static void main(String[] args){
        TerrainGeneration game = new TerrainGeneration("Terrain Test", 500,500);
    }
}
