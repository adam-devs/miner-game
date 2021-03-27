import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Miner extends GameObject {

  private int health;
  private int oxygen;
  Handler handler;

  private final int size = Game.blockSize / 2;

  public Miner(int x, int y, ID id,Handler handler) {
    super(x, y, id);
    this.handler = handler;
  }

  @Override
  public void tick() {
    // need to change health and oxygen depending on what happens with the minor
    x += velX;
    y += velY;
    System.out.println(x + ", " + y);
    x = Game.clamp(x,0,Game.width-50);
    y = Game.clamp(y,0,Game.height-50);

    System.out.println(x + ", " + y);

    collision();
  }

  private void collision(){
    for (int i = 0; i < handler.objects.size(); i++) {

      GameObject tempObject = handler.objects.get(i);

      if (tempObject.id == ID.BLOCK){
        if(getBounds().intersects(tempObject.getBounds())){
          NormalBlock obj = (NormalBlock) tempObject;
          obj.setMined();
        }

        if (tempObject.id == ID.BOMB_BLOCK){
          if(getBounds().intersects(tempObject.getBounds())){
            BombBlock obj = (BombBlock) tempObject;
            obj.setMined();
          }

          if (tempObject.id == ID.GOLD){
            if(getBounds().intersects(tempObject.getBounds())){
              NormalBlock obj = (NormalBlock) tempObject;
              obj.setMined();
            }

            if (tempObject.id == ID.O2BLOCK){
              if(getBounds().intersects(tempObject.getBounds())){
                (BombBlock) tempObject.setMined();
              }
              }
            }
}

      }
      //
    }
  }

  @Override
  public void render(Graphics g) {

    // miner
    BufferedImage image = null;
    try {
      File file = new File("/Users/adama/Downloads/miner-game/minerGame/src/avatar.png");
      image = ImageIO.read(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
    g.setColor(Color.blue);
    g.drawImage(image, getX(), getY(), size, size, null);
    g.fillRect(getX(), getY(), size, size);
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle(x,y,size,size);
  }


}
