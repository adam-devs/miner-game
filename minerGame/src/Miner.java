import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Miner extends GameObject {
  Handler handler;

  private final int size = Game.blockSize / 2;
  Image image;

  public Miner(int x, int y, ID id, Handler handler) {
    super(x, y, id);
    this.handler = handler;
    try {
      File file = new File("/Users/adama/Downloads/miner-game/minerGame/src/miner.png");
      this.image = ImageIO.read(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void tick() {
    // need to change health and oxygen depending on what happens with the minor
    x += velX;
    y += velY;
    x = Game.clamp(x, 0, Game.width - 50);
    y = Game.clamp(y, 0, Game.height - 50);
    collision();
    if (HUD.OXYGEN == 0 || HUD.HEALTH == 0) {
      Game.stop();
    }
    if (getY() >= 500) {
      setY(getY() - 200);
      Game.moveUp();
    }
  }

  private void collision() {
    for (int i = 0; i < handler.objects.size(); i++) {

      GameObject tempObject = handler.objects.get(i);

      if (tempObject.id == ID.BLOCK) {
        if (getBounds().intersects(tempObject.getBounds())) {
          NormalBlock obj = (NormalBlock) tempObject;
          obj.setMined();
          handler.removeObject(obj);
        }
      }
      if (tempObject.id == ID.BOMB_BLOCK) {
        if (getBounds().intersects(tempObject.getBounds())) {
          BombBlock obj = (BombBlock) tempObject;
          obj.setMined();
          handler.removeObject(obj);
        }
      }
      if (tempObject.id == ID.GOLD_BLOCK) {
        if (getBounds().intersects(tempObject.getBounds())) {
          GoldBlock obj = (GoldBlock) tempObject;
          obj.setMined();
          handler.removeObject(obj);
        }
      }
      if (tempObject.id == ID.O2BLOCK) {
        if (getBounds().intersects(tempObject.getBounds())) {
          O2Block obj = (O2Block) tempObject;
          obj.setMined();
          handler.removeObject(obj);
        }
      }
    }
  }

  @Override
  public void render(Graphics g) {

//  miner
    g.setColor(Color.blue);
    g.drawImage(image, getX(), getY(), size, size, null);
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle(x, y, size, size);
  }
}
