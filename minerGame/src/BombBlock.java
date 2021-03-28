import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BombBlock extends Block {

  protected boolean isMined = false;
  Image image;

  public BombBlock(int x, int y, ID id) {
    super(x, y, id);
    try {
      File file = new File("/Users/adama/Downloads/miner-game/minerGame/src/bomb.png");
      this.image = ImageIO.read(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void tick() {}

  public void render(Graphics g) {
    if (!isMined) {
      g.setColor(new Color(26, 26, 26));
      g.fillRect(x, y, Game.blockSize, Game.blockSize);
      g.drawImage(image, x + 3, y + 3, 44, 44, null);
    }
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle(x, y, size, size);
  }

  public void blockAttribute() {
    HUD.HEALTH -= 10;
    System.out.println("Ouch");
  }

  public void setMined() {
    this.isMined = true;
    blockAttribute();
  }
}
