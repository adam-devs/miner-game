import java.awt.*;


public class HUD extends GameObject {
  public static int HEALTH = 100;
  public static int OXYGEN = 100;

  public HUD(int x, int y, ID id) {
    super(x, y, id);
  }


  @Override
  public void tick(){
    HEALTH--;
    OXYGEN--;
    HEALTH = Game.clamp(HEALTH,0,100);
    HEALTH = Game.clamp(OXYGEN,0,100);


  }

  @Override
  public void render(Graphics g){

    // Health
    g.setColor(Color.BLACK);
    g.drawString("Health level: " + HEALTH, 10, 10);
    g.setColor(Color.red);
    g.fillRect(10, 20, 100 * 2, 20);
    g.setColor(Color.green);
    g.fillRect(10, 20, HEALTH * 2, 20);
    g.setColor(Color.BLACK);
    g.drawRect(10, 20, 100 * 2, 20);

    // Miner

    g.setColor(Color.BLACK);
    g.drawString("Oxygen level: " + OXYGEN, Game.width - 200 - 10, 10);
    g.setColor(Color.red);
    g.fillRect(Game.width - 200 - 10, 20, 100 * 2, 20);
    g.setColor(Color.green);
    g.fillRect(Game.width - 200 - 10, 20, OXYGEN * 2, 20);
    g.setColor(Color.BLACK);
    g.drawRect(Game.width - 200 - 10, 20, 100 * 2, 20);


  }

  @Override
  public Rectangle getBounds() {
    return null;
  }
}
