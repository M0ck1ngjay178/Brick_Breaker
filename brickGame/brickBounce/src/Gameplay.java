import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Image;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.font.GraphicAttribute;
//import java.util.*; 
import java.awt.Rectangle;

//min 28
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

    private Map map;
    private Image background;

    public Gameplay(){

        map = new Map(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();

        // Load the background image (replace with actual path)
        background = new ImageIcon("img/space.png").getImage();


    }

    public void paint(Graphics g){
        // background
        //g.setColor(Color.black);
        //g.fillRect(1,1, 692, 592);
        // Draw background image
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

        //draw map
        map.draw((Graphics2D)g);

        //borders
        g.setColor(Color.blue);
        g.fillRect(0,0, 3, 592);
        g.fillRect(0,0, 692, 3);
        g.fillRect(680,0, 3, 592); //moved into frame reduced 692 to 680
        
        //scores
        g.setColor(Color.white);
        g.setFont(new Font("Serif", Font.BOLD, 25));
        g.drawString(""+score, 590, 30);

        //base
        g.setColor(Color.CYAN);
        g.fillRect(playerX,550, 100, 8);

        //ball
        g.setColor(Color.WHITE);
        g.fillOval(ballposX,ballposY, 20, 20);

        //Win
        if(totalBricks <=0){
            play = false;
            ballXdir =0;
            ballYdir =0;
            g.setColor(Color.green);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You WON!! ",260,300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press ENTER to Restart",230,350);

        }

        //Game Over
        if(ballposY > 570){
            play = false;
            ballXdir =0;
            ballYdir =0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Scores: ",190,300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press ENTER to Restart",230,350);
        }

        g.dispose();
    }


    public void actionPerformed(ActionEvent e){
        timer.start();

        if(play){
            if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))){
                ballYdir = -ballYdir;
            }

            A: for(int i = 0; i < map.map.length; i++){
                //stopped at 40:33
                //picked up 12/26/24-
                for(int j = 0; j< map.map[0].length; j++)
                {
                    if(map.map[i][j]> 0){
                        int brickX = j* map.brickWidth +80;
                        int brickY = i* map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight =  map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickRect = rect;
                        
                        if(ballRect.intersects(brickRect)){
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score +=5;

                            if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width){
                                ballXdir = -ballXdir;
                            }else{
                                ballYdir = -ballYdir;
                            }
                            break A;
                        }

                    }   

                }
            }


           ballposX += ballXdir;
           ballposY += ballYdir;
           
           if(ballposX < 0){
              ballXdir = -ballXdir;   
           }
           if(ballposY < 0){
             ballYdir = -ballYdir;
           }
           if(ballposX > 670){
             ballXdir = -ballXdir;   
            }
        }

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
        }//end if
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX < 10){
                playerX = 10;
            }else{
                moveLeft();
            }//end else
            
        }//end if
        //Reset Game and Map upon Game Over
         if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                play = true;
                ballposX = 120;
                ballposY = 350;
                ballXdir =-1;
                ballYdir = -2;
                playerX = 310;
                score =0;
                totalBricks = 21;
                map = new Map(3, 7);

                repaint();
            }
         }


    }//end key pressed

    public void moveRight(){
        play = true;
        playerX += 20;
    }
    public void moveLeft(){
        play = true;
        playerX -= 20;
    }
    
    
}
