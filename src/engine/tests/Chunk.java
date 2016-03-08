package engine.tests;

public class Chunk {
    private float[][] vals;
    private int x, y;
    public Chunk(int x, int y, float[][] vals){
        this.x = x;
        this.y = y;
        this.vals = vals;
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
}
