import javax.swing.*;
import java.awt.*;

class Gui extends JPanel {
    private JLabel score;
    Gui(){
        this.setLayout(new FlowLayout());
        score = new JLabel("Score: " + "");
        score.setFont(new Font("Arial", Font.PLAIN, 25));
        score.setForeground(Color.green);

        this.add(score);
        this.setSize(Game.WIDTH, Game.HEIGHT/10);
        this.setBackground(Color.gray);
    }

    void update_score(int value){
        score.setText("Score: " + value);
    }
}
