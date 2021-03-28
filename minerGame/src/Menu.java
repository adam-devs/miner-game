import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.ParameterDescriptor;
import java.util.Random;

public class Menu extends MouseAdapter {

  Game game;

  public static final int width = 1000;
  public static final int height = roundTo50(width / 12 * 9) + 13;
  Handler handler;


  public Menu(Game game,Handler handler) {
    this.game = game;
    this.handler = handler;
  }


  public void mousePressed(MouseEvent e) {
    int mx = e.getX();
    int my = e.getY();


    if (game.gameState == Game.STATE.MENU){
      //Play Button
      if (mouseOver(mx, my, 210, 150, 200, 64)) {
        game.gameState = Game.STATE.GAME;
        new Game();
      }

      //Donations Button
      if (mouseOver(mx,my,210,250,200,60)){
        game.gameState = Game.STATE.DONATE;
      }

      if (game.gameState == Game.STATE.DONATE){
        if (mouseOver(mx,my,210,350,200,60)){
          game.gameState = Game.STATE.MENU;
          return;
        }
      }
    }



  }

  public static int roundTo50(int x) {
    if (x % 50 < 25) {
      return x - (x % 50);
    } else if (x % 50 > 25) {
      return x + (50 - (x % 50));
    } else {
      return x + 25;
    }
  }

  public void mouseReleased(MouseEvent e) {}

  private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
    if (mx > x && mx < x + width) {
      if (my > y && my < y + height) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  public void render(Graphics g) {
    if (game.gameState == Game.STATE.MENU){
      Font font = new Font("arial", 1, 50);
      Font font2 = new Font("arial", 1, 30);

      g.setFont(font);
      g.setColor(Color.gray);
      g.drawString("Menu", 240, 70);

      g.setFont(font2);
      g.drawRect(210, 150, 200, 64);
      g.drawString("Play", 270, 190);

      g.drawRect(210, 250, 200, 64);
      g.drawString("Help", 270, 290);

      g.setColor(Color.gray);
      g.drawRect(210, 350, 200, 64);
      g.drawString("Quit", 270, 390);
    }else if (game.gameState == Game.STATE.GAME){
      Font font = new Font("arial", 1, 50);

      g.setFont(font);
      g.setColor(Color.gray);
      g.drawString("DONATE", 240, 70);

      Font font2 = new Font("arial", 1, 30);
      g.setFont(font2);
      g.drawRect(210, 350, 200, 64);
      g.drawString("Back", 270, 390);


    }

  }

  public void tick() {}
}
