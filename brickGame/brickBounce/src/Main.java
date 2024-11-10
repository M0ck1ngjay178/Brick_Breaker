/*Margo Bonal Java/ Git Practice */
/* Brick Bounce Tutorial         */
/* Tutorial: https://youtu.be/K9qMm3JbOH0?si=JpPWC4BX1ml91q_u */
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        JFrame obj = new JFrame();
        Gameplay gamePlay = new Gameplay();
        obj.setBounds(10,10,700,600);
        obj.setTitle("BrickBounceGame");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay);
    }
}
