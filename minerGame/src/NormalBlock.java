import java.awt.*;

public class NormalBlock extends Block {

  protected boolean isMined = false;

  public NormalBlock(int x, int y, ID id) {
    super(x, y, id);
  }

  @Override
  public void tick() {}

  @Override
  public void render(Graphics g) {
    if (!isMined) {
      g.setColor(new Color(56, 39, 28));
      g.fillRect(x, y, Game.blockSize, Game.blockSize);
      g.setColor(new Color(78, 58, 39));
      g.fillRect(x + 3, y + 3, Game.blockSize - 6, Game.blockSize - 6);
    }
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle(x, y, size, size);
  }

  @Override
  public void blockAttribute() {
    // Should be Empty
  }

  public void setMined() {
    this.isMined = true;
    blockAttribute();
  }
}
