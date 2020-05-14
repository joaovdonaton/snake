import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Game extends Canvas implements ActionListener, KeyListener {
    private final int FATNESS = 15; // size in pixels^2
    private final int INITIAL_LENGTH = 15;
    private Snake s = new Snake(INITIAL_LENGTH, FATNESS);

    private Game(){
        this.addKeyListener(this);
        Timer timer = new Timer(100, this);
        timer.setRepeats(true);
        timer.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Canvas canvas = new Game();
        canvas.setBackground(Color.BLACK);
        canvas.setSize(800, 600);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        g.setColor(Color.green);
        for (int[] i : s.parts) {
            g.fillRect(i[0], i[1], s.FATNESS, s.FATNESS);
        }
        /*if (!s.move_forward()) { //move forward on every render (direction is based on Snake.FACING)
            System.out.println("Game Over");
            System.exit(1);
        }*/
        s.move_forward();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                if(s.FACING != 'S') { s.FACING = 'N'; }
                break;
            case KeyEvent.VK_DOWN:
                if(s.FACING != 'N') { s.FACING = 'S'; }
                break;
            case KeyEvent.VK_LEFT:
                if(s.FACING != 'E') { s.FACING = 'W'; }
                break;
            case KeyEvent.VK_RIGHT:
                if(s.FACING != 'W') { s.FACING = 'E'; }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
