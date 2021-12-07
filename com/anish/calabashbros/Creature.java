package com.anish.calabashbros;

import java.awt.Color;

public class Creature extends Thing {

    Creature(Color color, char glyph, World world, int lives) {
        super(color, glyph, world, lives);
    }

    public void moveTo(int xPos, int yPos) {

        if( this.world.isFloor(this.getX() + xPos, this.getY() + yPos) )
        {
            int x = this.getX();
            int y = this.getY();
            this.world.put(this, x + xPos, y + yPos);
            this.world.put(new Floor(this.world), x, y);
        }
        else if(this.world.isHeart(this.getX() + xPos, this.getY() + yPos))
        {
            int x = this.getX();
            int y = this.getY();
            this.world.put(this, x + xPos, y + yPos);
            this.lives+=2;
            this.world.put(new Floor(this.world), x, y);
        }
    }

}
