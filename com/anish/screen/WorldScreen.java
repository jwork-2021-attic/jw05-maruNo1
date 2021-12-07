package com.anish.screen;

import java.awt.event.KeyEvent;
import java.util.Random;

//import com.anish.calabashbros.Bullet;
import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.Heart;
import com.anish.calabashbros.Monster;
//import com.anish.calabashbros.Monster;
import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Calabash bros;
    private Monster mons;
    private Heart heart;
    //private Bullet bullets;

    public WorldScreen() {
        world = new World();
        bros = new Calabash(AsciiPanel.brightWhite, 4, world, 2);  
        //mons = new Monster(AsciiPanel.brightGreen, 4, world, 2); 
        //bullets = new Bullet(new Color(200, 0, 0), 16, world);
        world.put(bros, 1, 1);
        //world.put(mons, 9, 9);
        new Thread(new MonsPlant()).start();
        new Thread(new HeartPlant()).start();
        //new Thread(mons).start();
    }

    private class MonsPlant implements Runnable{
        @Override
        public void run() {
            while(world.Score<=10){
                try{
                Thread.sleep(2000);  //每隔50毫秒刷新画面一次
                }catch(Exception e){
                e.printStackTrace();
                }
                Random r = new Random();
                int posX = r.nextInt(19);
                int posY = r.nextInt(19);
                if(world.isFloor(posX, posY))
                {
                    mons = new Monster(AsciiPanel.brightGreen, posX%2+1, world, 2); 
                    world.put(mons, posX, posY); 
                    new Thread(mons).start();
                }
            }
        }
    }

    
    private class HeartPlant implements Runnable{
        @Override
        public void run() {
            while(world.Score<=10){
                try{
                Thread.sleep(3000);  //每隔50毫秒刷新画面一次
                }catch(Exception e){
                e.printStackTrace();
                }
                Random r = new Random();
                int posX = r.nextInt(19);
                int posY = r.nextInt(19);
                if(world.isFloor(posX, posY))
                {
                    heart = new Heart(AsciiPanel.brightRed, 3, world, 2); 
                    world.put(heart, posX, posY); 
                }
            }
        }
    }


    @Override
    public void displayOutput(AsciiPanel terminal) {
        // displayTiles
        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {
                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());
            }
        }
        displayMessages(terminal);               
    }

    private void displayMessages(AsciiPanel terminal) {
       
        String s2 = Integer.toString(bros.lives);

        String s1 = Integer.toString(world.Score);

        terminal.write("Score:",22, 1);

        terminal.write( s1 , 29 , 1);

        terminal.write("lives:", 22, 3);

        terminal.write( s2 , 29 , 3);
    
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        if(this.world.victory == 0){
            switch (key.getKeyCode()) {
                case KeyEvent.VK_J:
                    bros.shoot();
                    break;
                case KeyEvent.VK_LEFT:
                    bros.setdirect(2);
                    bros.moveTo(-1, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    bros.setdirect(3);
                    bros.moveTo(1, 0);
                    break;
                case KeyEvent.VK_UP:
                    bros.setdirect(0);
                    bros.moveTo(0, -1);
                    break;
                case KeyEvent.VK_DOWN:
                    bros.setdirect(1);
                    bros.moveTo(0, 1);
                    break;
                case KeyEvent.VK_G:
                    return new WinScreen();
            }
        }
        else if(this.world.victory == 1){
                    return new WinScreen();
        }
        else if(this.world.victory == -1){
            return new LoseScreen();
        }
        
        return this;
    }

}
