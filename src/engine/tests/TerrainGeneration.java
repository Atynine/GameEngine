package engine.tests;

import engine.Screen;
import engine.terrain.SimplexNoise;

import java.awt.*;

public class TerrainGeneration extends Screen {
    private enum TerrainSetting{
        UNDERWATER_TRENCH(new Color(11,15,55),-.05f),
        DEEP_OCEAN(new Color(22,32,118),.05f),
        DEEP_OCEAN_STATIC(new Color(22,32,118),.4f),
        OCEAN(new Color(74,89,221),.6f),
        SAND(new Color(234,219,151),.65f),
        GRASSLAND(new Color(13,69,12),.7f),
        GRASSLAND_STATIC(new Color(13,69,12),.8f),
        MOUNTAIN(new Color(72,67,60),.85f),
        MOUNTAIN_STATIC(new Color(72,67,60),.95f),
        MOUNTAIN_TOP(new Color(194,189,182),1f);

        private Color base;
        private float point;
        TerrainSetting(Color baseColor, float point){
            this.base = baseColor;
            this.point = point;
        }
        public float getPoint(){
            return point;
        }
        public Color getBaseColor(){
            return base;
        }
    }
    Map map = new Map(64);
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
        for(int x = 0; x < 30; x++){
            for(int y = 0; y < 17; y++){
                if(!map.isGenerated(x,y)){
                    int width, height;
                    width = g.getClipBounds().width;
                    height = g.getClipBounds().height;

                    g.setColor(TerrainSetting.DEEP_OCEAN.base);
                    g.fillRect(0,0,width,height);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
                    g.drawString("Generating map...", width/2-(g.getFontMetrics().stringWidth("Generating map...")/2) ,height/2-(g.getFontMetrics().getHeight()/2));
                    return;
                }
            }
        }
        for(int i = 0; i < 30; i++){
            for(int j = 0; j < 17; j++){
                float[][] vals = map.getChunk(i,j);
                for(int x = 0; x < vals.length; x++){
                    for(int y = 0; y < vals[x].length; y++){
                        g.setColor(getColor(vals[x][y]));
                        g.fillRect((i*64)+x,(j*64)+y,1,1);
                    }
                }
            }
        }
    }
    int currentX = 0, currentY = 0;
    @Override
    public void update(long delta) {
        if(currentY < 64){
            map.generate(currentX,currentY);
            currentX++;
            if(currentX > 63){
                currentX = 0;
                currentY++;
            }
        }
    }



    public Color getColor(float t){
        t=(t+1)/2;
//        if(t > 1) t = 1;
//        if(t < 0) t = 0;
        for(int i = 0; i < TerrainSetting.values().length; i++){
            TerrainSetting current = TerrainSetting.values()[i];
            if(i+1 >= TerrainSetting.values().length) return TerrainSetting.values()[i].getBaseColor();
            if(t >= current.getPoint()){
                TerrainSetting next = TerrainSetting.values()[i+1];
                if(t <= next.getPoint()){
                    t = normalize(t,current.getPoint(),next.getPoint());
                    float u = 1 - t;
                    return new Color((int)(current.getBaseColor().getRed() * u + next.getBaseColor().getRed() * t),
                            (int)(current.getBaseColor().getGreen() * u + next.getBaseColor().getGreen() * t),
                            (int)(current.getBaseColor().getBlue() * u + next.getBaseColor().getBlue() * t));
                }
            }
        }
        return Color.BLACK;
    }
    public float normalize(float val, float min, float max){
        return (val-min)/(max-min);
    }


    public static void main(String[] args){
        TerrainGeneration game = new TerrainGeneration("Terrain Test", 500,500);
    }
}
