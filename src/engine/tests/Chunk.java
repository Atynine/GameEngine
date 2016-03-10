package engine.tests;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Map.Entry;
import java.util.SortedMap;

public class Chunk {
    private BufferedImage img;
    private float[][] vals;
    private int x, y;
    public Chunk(int x, int y, float[][] vals){
        this.x = x;
        this.y = y;
        this.vals = vals;
        this.img = new BufferedImage(vals.length,vals[0].length, BufferedImage.TYPE_INT_ARGB);
    }
    public void createImage(SortedMap<Float, Color> map){
        Graphics g = img.getGraphics();
        for(int x = 0; x < vals.length; x++){
            for(int y = 0; y < vals[x].length; y++){
                g.setColor(getColor(vals[x][y],map));
                g.fillRect(x,y,1,1);
            }
        }
        //Save memory
        vals = new float[0][0];
    }
    private Color getColor(float t, SortedMap<Float, Color> map){
        t=(t+1)/2;
        if(t < 0) t = 0;
        Object[] entries = map.entrySet().toArray();
        for(int i = 0; i < entries.length; i++){
            Entry<Float,Color> current = (Entry<Float,Color>)entries[i];
            if(i+1 >= entries.length) return current.getValue();
            if(t >= current.getKey()){
                Entry<Float,Color> next = (Entry<Float,Color>)entries[i+1];
                if(t <= next.getKey()){
                    t = normalize(t,current.getKey(),next.getKey());
                    float u = 1 - t;
                    return new Color((int)(current.getValue().getRed() * u + next.getValue().getRed() * t),
                            (int)(current.getValue().getGreen() * u + next.getValue().getGreen() * t),
                            (int)(current.getValue().getBlue() * u + next.getValue().getBlue() * t));
                }
            }
        }
       
        return Color.BLACK;
    }
     public float normalize(float val, float min, float max){
        return (val-min)/(max-min);
    }
    public float[][] getValues(){
        return vals;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public BufferedImage getImage(){
        return img;
    }
}
