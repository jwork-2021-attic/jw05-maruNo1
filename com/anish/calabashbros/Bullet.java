package com.anish.calabashbros;

import java.awt.Color;

public class Bullet extends Creature implements Runnable{

    private int direct;

    public Bullet(Color color, int glyph, World world,int direct) {
        super(color, (char) glyph, world,0);
        this.direct=direct;
    }
    
    public void moveTo(int xPos, int yPos) {

        if( this.world.isFloor(this.getX() + xPos, this.getY() + yPos) )
        {
            int x = this.getX();
            int y = this.getY();
            this.world.put(this, x + xPos, y + yPos);
            this.world.put(new Floor(this.world), x, y);
        }
        else if(this.world.isWall(this.getX() + xPos, this.getY() + yPos))
        {
            int x = this.getX();
            int y = this.getY();
            this.live = false;
            this.world.put(new Floor(this.world), x, y);
        }
        else if(this.world.isMons(this.getX() + xPos, this.getY() + yPos))
        {
            int x = this.getX();
            int y = this.getY();
            this.live = false;
            this.world.get(x + xPos, y + yPos).lives--;
            if(this.world.get(x + xPos, y + yPos).lives==0)
            {
                this.world.get(x + xPos, y + yPos).live=false;
                this.world.put(new Floor(this.world), x + xPos, y + yPos);
            }
            //this.world.get(x + xPos, y + yPos).live = false;
            //this.world.put(new Floor(this.world), x + xPos, y + yPos);
            this.world.put(new Floor(this.world), x, y);
        }
        else if(this.world.isHeart(this.getX() + xPos, this.getY() + yPos))
        {
            int x = this.getX();
            int y = this.getY();
            this.world.put(this, x + xPos, y + yPos);
            this.world.put(new Floor(this.world), x, y);
        }
    }

    @Override
    public void run() {
        while (this.live == true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            if(this.direct==0)
            {
                this.moveTo(0, -1);
            }
            else if(this.direct==1)
            {
                this.moveTo(0, 1);
            }
            else if(this.direct==2)
            {
                this.moveTo(-1, 0);
            }
            else if(this.direct==3)
            {
                this.moveTo(1, 0);
            }
		}

    }
}
