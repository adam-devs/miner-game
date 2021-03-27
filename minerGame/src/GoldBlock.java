import java.awt.*;

public class GoldBlock extends Block {

  protected boolean isMined = false;

  public GoldBlock(int x, int y, ID id) {
    super(x, y, id);
  }

  @Override
  public void tick() {}

  @Override
  public void render(Graphics g) {
    if (!isMined) {
      g.setColor(new Color(99, 76, 19));
      g.fillRect(x, y, Game.blockSize, Game.blockSize);
      g.setColor(new Color(168, 133, 33));
      g.fillRect(x + 3, y + 3, Game.blockSize - 6, Game.blockSize - 6);
    } else {
      g.setColor(new Color(147, 135, 99));
      g.fillRect(x, y, Game.blockSize, Game.blockSize);
    }
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle(x, y, size, size);
  }

  @Override
  public void blockAttribute() {
    HUD.score += 100;
  }

  public void setMined() {
    this.isMined = true;
    blockAttribute();
  }
}
