import java.awt.*;
import javax.swing.JFrame;

public class Window extends Canvas {

  public Window(int w, int h, String title,Game game) {
    JFrame frame = new JFrame(title);

    frame.setPreferredSize(new Dimension(w, h));
    frame.setMinimumSize(new Dimension(w, h));
    frame.setMaximumSize(new Dimension(w, h));

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.add(game);
    frame.setVisible(true);
    game.start();

  }



}
