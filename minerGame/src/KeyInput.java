import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

  private Handler handler;

  public KeyInput(Handler handler) {
    this.handler = handler;
  }

  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    int amount = 7;

    for (int i = 0; i < handler.objects.size(); i++) {
      GameObject tempObject = handler.objects.get(i);

      if (tempObject.getID() == ID.MINER) {
        // key events for players

        if (key == KeyEvent.VK_W) {
          tempObject.setVelY(-amount);
        }
        if (key == KeyEvent.VK_S) {
          tempObject.setVelY(amount);
        }
        if (key == KeyEvent.VK_D) {
          tempObject.setVelX(amount);
        }
        if (key == KeyEvent.VK_A) {
          tempObject.setVelX(-amount);
        }
      }
    }
  }

  public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();

    for (int i = 0; i < handler.objects.size(); i++) {
      GameObject tempObject = handler.objects.get(i);

      if (tempObject.getID() == ID.MINER) {
        // key events for players

        if (key == KeyEvent.VK_W) {
          tempObject.setY(Game.roundTo50(tempObject.getY()));
          tempObject.setVelX(0);
          tempObject.setVelY(0);
        }
        if (key == KeyEvent.VK_S) {
          tempObject.setY(Game.roundTo50(tempObject.getY()));
          tempObject.setVelX(0);
          tempObject.setVelY(0);
        }
        if (key == KeyEvent.VK_D) {
          tempObject.setX(Game.roundTo50(tempObject.getX()) + 12);
          tempObject.setVelX(0);
          tempObject.setVelY(0);
        }
        if (key == KeyEvent.VK_A) {
          tempObject.setX(Game.roundTo50(tempObject.getX()) + 12);
          tempObject.setVelX(0);
          tempObject.setVelY(0);
        }
      }
    }
  }
}
