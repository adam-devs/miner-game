import java.awt.*;

public class Ground extends GameObject {


  public Ground(int x, int y) {
    super(x, y, ID.GROUND);
  }

  @Override
  public void tick() {

  }

  @Override
  public void render(Graphics g) {
    // ground
    g.setColor(new Color(42, 95, 31));
    g.fillRect(0, Game.height / 4, Game.width, 10);
  }

  @Override
  public Rectangle getBounds() {
    return null;
  }
}
