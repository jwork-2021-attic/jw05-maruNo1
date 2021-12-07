package com.anish.calabashbros;

import java.awt.Color;

import asciiPanel.AsciiPanel;

public class Calabash extends Creature {
    public int direct;

    public Calabash(Color color, int glyph, World world,int lifes) {
        super(color, (char) glyph, world ,lifes);
    }

    public void setdirect(int d)
    {
        this.direct = d;
    }

    public void shoot() {
        //Bullet bullet = new Bullet(AsciiPanel.brightRed, 16, world, direct);
        if(this.direct==0) // ио
        {
            if(this.world.isWall(this.getX(), this.getY()-1)==false)
            {
                Bullet bullet = new Bullet(AsciiPanel.brightRed, 30, world, direct);
                world.put(bullet, this.getX(), this.getY()-1);
                new Thread(bullet).start();
            }
        }
        else if(this.direct==1) // об
        {
            if(this.world.isWall(this.getX(), this.getY()+1)==false)
            {
                Bullet bullet = new Bullet(AsciiPanel.brightRed, 31, world, direct);
                world.put(bullet, this.getX(), this.getY()+1);
                new Thread(bullet).start();
            }
        }
        else if(this.direct==2) // вС
        {
            if(this.world.isWall(this.getX()-1, this.getY())==false)
            {
                Bullet bullet = new Bullet(AsciiPanel.brightRed, 17, world, direct);
                world.put(bullet, this.getX()-1, this.getY());
                new Thread(bullet).start();
            }
        }
        else if(this.direct==3) // ср
        {
            if(this.world.isWall(this.getX()+1, this.getY())==false)
            {
                Bullet bullet = new Bullet(AsciiPanel.brightRed, 16, world, direct);
                world.put(bullet, this.getX()+1, this.getY());
                new Thread(bullet).start();
            }
        }
        //new Thread(bullet).start();
    }
}
