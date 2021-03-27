import java.awt.*;

public class HUD extends GameObject {
  public static int HEALTH = 100;
  public static int OXYGEN = 100;
  private int countdown = 0;
  public static int score = 0;

  public HUD(int x, int y, ID id) {
    super(x, y, id);
  }

  @Override
  public void tick() {
    countdown++;
    System.out.println(countdown);

    if (countdown % 100 == 0) {
      OXYGEN--;
    }

    HEALTH = Game.clamp(HEALTH, 0, 100);
    OXYGEN = Game.clamp(OXYGEN, 0, 100);
    //score++;
  }

  @Override
  public void render(Graphics g) {
    int margin = 20;

    // Health
    g.setColor(Color.BLACK);
    g.drawString("Health level: " + HEALTH, margin, margin);
    g.setColor(Color.red);
    g.fillRect(margin, margin + 10, 100 * 2, 20);
    g.setColor(Color.green);
    g.fillRect(margin, margin + 10, HEALTH * 2, 20);
    g.setColor(Color.BLACK);
    g.drawRect(margin, margin + 10, 100 * 2, 20);

    // Oxygen
    g.setColor(Color.BLACK);
    g.drawString("Oxygen level: " + OXYGEN, Game.width - 200 - margin, margin);
    g.setColor(Color.red);
    g.fillRect(Game.width - 200 - margin, margin + 10, 100 * 2, 20);
    g.setColor(Color.green);
    g.fillRect(Game.width - 200 - margin, margin + 10, OXYGEN * 2, 20);
    g.setColor(Color.BLACK);
    g.drawRect(Game.width - 200 - margin, margin + 10, 100 * 2, 20);

    g.drawString("Score: " + score, Game.width / 2 - 32, margin);
  }

  @Override
  public Rectangle getBounds() {
    return null;
  }
}
