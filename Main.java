
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.anish.calabashbros.World;
import com.anish.screen.Screen;
import com.anish.screen.StartScreen;

import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;

public class Main extends JFrame implements KeyListener {

    private AsciiPanel terminal;
    private Screen screen;

    public Main() {
        super();
        terminal = new AsciiPanel(40, World.HEIGHT, AsciiFont.TALRYTH_15_15);
        add(terminal);
        pack();
        screen = new StartScreen();
        addKeyListener(this);
        new Thread(new PaintThread()).start();
    }

    @Override
    public void repaint() {
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }

private class PaintThread implements Runnable{
        @Override
        public void run() {
            while(true){
                repaint();  
                try{
                Thread.sleep(50);  //每隔50毫秒刷新画面一次
                }catch(Exception e){
                e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        screen = screen.respondToUserInput(e);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
 
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }

}