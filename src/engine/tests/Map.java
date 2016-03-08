package engine.tests;

import engine.terrain.SimplexNoise;

public class Map {
    private static final int CHUNK_SIZE = 32;
    private static final int OCTAVES = 60;
    private static final float ROUGHNESS = .3f;
    private static final float SCALE = .01f;
    private Chunk[][] map;
    private int size;
    public Map(int size){
        map = new Chunk[size][size];
        this.size = size;
    }
    public boolean isGenerated(int x, int y){
        return map[x][y] != null;
    }
    public void generate(int x, int y){
        if(isGenerated(x,y)) return;
        map[x][y] = new Chunk(x,y,SimplexNoise.generateOctavedSimplexNoise(CHUNK_SIZE,CHUNK_SIZE,x*CHUNK_SIZE,y*CHUNK_SIZE,OCTAVES,ROUGHNESS,SCALE));
    }
    public Chunk getChunk(int x, int y){
        if(!isGenerated(x,y)){
            return null;
        }
        return map[x][y];
    }
    public int getChunkSize(){
        return CHUNK_SIZE;
    }
    public int getSize(){
        return this.size;
    }
}
