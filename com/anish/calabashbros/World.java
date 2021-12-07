package com.anish.calabashbros;
import asciiPanel.AsciiPanel;
public class World {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public int Score=0;
    public static int map[][]= {
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, // 0
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1}, // 6
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1}, // 11
        {1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1}, // 16
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    } ; 

    private Tile<Thing>[][] tiles;
    //private int[][] maze;

    public int victory = 0;

    public World() {

        if (tiles == null) {
            tiles = new Tile[WIDTH][HEIGHT];
        }
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tiles[i][j] = new Tile<>(i, j);
                if(map[i][j]==1)
                {
                    tiles[i][j].setThing(new Wall(this));
                }
                else if(map[i][j]==0)
                {
                    tiles[i][j].setThing(new Floor(this));
                }
            }
        }
    }

    public Thing get(int x, int y) {
        return this.tiles[x][y].getThing();
    }

    public void put(Thing t, int x, int y) {
        this.tiles[x][y].setThing(t);
    }

    public boolean isFloor(int x,int y) {
        return (tiles[x][y].getThing().getColor() == AsciiPanel.black);
    }
    public boolean isWall(int x,int y) {
        return (tiles[x][y].getThing().getColor() == AsciiPanel.cyan);
    }

    public Tile<Thing> tile(int x, int y) {
        return tiles[x][y];
    }

    public boolean isMons(int x, int y) {
        return (tiles[x][y].getThing().getColor() == AsciiPanel.brightGreen);
    }

    public boolean isBullet(int x, int y) {
        return (tiles[x][y].getThing().getColor() == AsciiPanel.brightRed);
    }

    public boolean isCala(int x, int y) {
        return (tiles[x][y].getThing().getColor() == AsciiPanel.brightWhite);
    }

    public boolean isHeart(int x, int y) {
        return (tiles[x][y].getThing().getColor() == AsciiPanel.brightRed);
    }
}
