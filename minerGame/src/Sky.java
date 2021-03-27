import java.awt.*;

public class Sky extends GameObject {

  public Sky(int x, int y) {
    super(x, y, ID.SKY);
  }

  @Override
  public void tick() {

  }

  @Override
  public void render(Graphics g) {
    // sky
    g.setColor(new Color(31, 146, 208));
    g.fillRect(0, 0, Game.width, Game.height / 4);
  }

  @Override
  public Rectangle getBounds() {
    return null;
  }
}
