import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;
//import java.awt.font.GraphicAttribute;
import java.util.*; 


public class Gameplay extends JPanel implements KeyListener, ActionListener{
    private boolean play   = false;
    private int score  = 0;
    private int totalBricks = 21;

    private Timer timer;
    private int delay = 8;

    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir  = -1;
    private int ballYdir  = -2;


    public Gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g){
        // background
        g.setColor(Color.black);
        g.fillRect(1,1, 692, 592);

        //borders
        g.setColor(Color.green);
        g.fillRect(0,0, 3, 592);
        g.fillRect(0,0, 692, 3);
        g.fillRect(680,0, 3, 592); //moved into frame reduced 692 to 680

        //base
        g.setColor(Color.CYAN);
        g.fillRect(playerX,550, 100, 8);

        //ball
        g.setColor(Color.ORANGE);
        g.fillOval(ballposX,ballposY, 20, 20);
        g.dispose();
    }


    public void actionPerformed(ActionEvent e){
        timer.start();
        repaint();

        
    }

    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >= 600){
                playerX = 600;
            }else{
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX < 10){
                playerX = 10;
            }else{
                moveLeft();
            }
            
        }
    }

    public void moveRight(){
        play = true;
        playerX += 20;
    }
    public void moveLeft(){
        play = true;
        playerX -= 20;
    }
    

    
    
}