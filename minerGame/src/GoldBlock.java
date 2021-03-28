import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GoldBlock extends Block {

  protected boolean isMined = false;
  Image image;

  public GoldBlock(int x, int y, ID id) {
    super(x, y, id);
    try {
      File file = new File("/Users/adama/Downloads/miner-game/minerGame/src/ethereum.png");
      this.image = ImageIO.read(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void tick() {}

  @Override
  public void render(Graphics g) {
    if (!isMined) {
      g.setColor(new Color(57, 66, 104));
      g.fillRect(x, y, Game.blockSize, Game.blockSize);
      g.drawImage(image, x + 3, y + 3, 44, 44, null);
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
