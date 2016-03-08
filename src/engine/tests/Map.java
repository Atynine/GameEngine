package engine.tests;

import engine.terrain.SimplexNoise;

public class Map {
    private static final int CHUNK_SIZE = 64;
    private static final int OCTAVES = 60;
    private static final float ROUGHNESS = .04f;
    private static final float SCALE = .01f;
    private Chunk[][] map;
    public Map(int size){
        map = new Chunk[size][size];
    }
    public boolean isGenerated(int x, int y){
        return map[x][y] != null;
    }
    public void generate(int x, int y){
        if(isGenerated(x,y)) return;
        map[x][y] = new Chunk(x,y,SimplexNoise.generateOctavedSimplexNoise(CHUNK_SIZE,CHUNK_SIZE,x*CHUNK_SIZE,y*CHUNK_SIZE,OCTAVES,ROUGHNESS,SCALE));
    }
    public float[][] getChunk(int x, int y){
        if(!isGenerated(x,y)){
            System.out.println("Map not generated. Generating");
            generate(x,y);
            return new float[x][y];
        }
        return map[x][y].getValues();
    }
}
