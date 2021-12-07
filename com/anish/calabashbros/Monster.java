package com.anish.calabashbros;

import java.awt.Color;

public class Monster extends Creature implements Runnable{

    public Monster(Color color, int glyph, World world,int lifes) {
        super(color, (char) glyph, world ,lifes);
    }

    private int nextstepX;
    private int nextstepY;

    public void moveTo(int xPos, int yPos) {

        if( this.world.isFloor(this.getX() + xPos, this.getY() + yPos) )
        {
            int x = this.getX();
            int y = this.getY();
            this.world.put(this, x + xPos, y + yPos);
            this.world.put(new Floor(this.world), x, y);
        }
        else if(this.world.isCala((this.getX() + xPos), this.getY() + yPos))
        {
            this.world.get(this.getX()+xPos, this.getY()+yPos ).lives -= this.lives;
            if(this.world.get(this.getX()+xPos, this.getY()+yPos).lives < 0)
            {
                this.world.victory = -1;
            }
            this.lives = 0;
            this.live = false;
        }
        else if(this.world.isHeart(this.getX() + xPos, this.getY() + yPos))
        {
            int x = this.getX();
            int y = this.getY();
            this.world.put(this, x + xPos, y + yPos);
            this.lives+=this.world.get( x + xPos, y + yPos).lives;
            this.world.put(new Floor(this.world), x, y);
        }
    }

    public void FindWay()
    {
        int destx = 18;
        int desty = 18;
        for(int i=0;i<20;i++)
        {
            for(int j=0;j<20;j++)
            {
                if(this.world.isCala(i, j)==true)
                {
                    destx = i;
                    desty = j;
                }
            }
        }
        int[][] map1 = new  int[20][20];
        for(int i=0;i<20;i++)
        {
            for(int j=0;j<20;j++)
            {
                map1[i][j] = World.map[i][j];
            }
        }
        Maze maze = new Maze(map1, this.getX(), this.getY(), destx, desty);
		maze.bfs();
        this.nextstepX = maze.stepX - this.getX();
        this.nextstepY = maze.stepY - this.getY();
    }

    @Override
    public void run() {

        while (this.live == true){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
            this.FindWay();
            this.moveTo(nextstepX, nextstepY);
        }
        if(this.live == false)
        {
            this.world.Score+=2;
            if(this.world.Score>=10)
            {
                this.world.victory = 1;
            }
            this.world.put(new Floor(this.world), this.getX(), this.getY());
        }

    }
}
