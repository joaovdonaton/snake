import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Game extends Canvas implements ActionListener, KeyListener {
    private final int FATNESS = 15; // size in pixels^2
    private final int INITIAL_LENGTH = 4;
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    private Snake s = new Snake(INITIAL_LENGTH, FATNESS);
    private int[] apple;
    private int score = 0;
    private static Gui gui;

    private Game(){
        this.addKeyListener(this);
        Timer timer = new Timer(100, this);
        timer.setRepeats(true);
        timer.start();

        apple = generate_apple();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Canvas canvas = new Game();
        canvas.setBackground(Color.BLACK);
        canvas.setSize(WIDTH, HEIGHT);
        gui = new Gui();
        frame.add(gui);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        //draw snake
        g.setColor(Color.green);
        for (int[] i : s.parts) {
            g.fillRect(i[0], i[1], s.FATNESS, s.FATNESS);
        }

        //draw apple
        g.setColor(Color.red);
        g.fillRect(apple[0], apple[1], s.FATNESS, s.FATNESS);

        s.move_forward();
        if(s.check_collision(apple)){
            score++;
            apple = generate_apple();
            s.increase_size();
            gui.update_score(score);
        }
    }

    private int[] generate_apple(){
        Random rand = new Random();
        java.util.List<int[]> available_pos = new ArrayList<>();
        for(int i = 0; i < Math.floor(WIDTH/FATNESS); i++){
            for(int j = (int) (Math.floor(HEIGHT*0.15)/FATNESS); //idk either
                j < Math.floor(HEIGHT/FATNESS); j++){
                available_pos.add(new int[]{FATNESS*i, FATNESS*j});
            }
        }

        return new int[]{available_pos.get(rand.nextInt(available_pos.size()))[0],
                available_pos.get(rand.nextInt(available_pos.size()))[1]};
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
