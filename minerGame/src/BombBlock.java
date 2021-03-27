import java.awt.*;

public class BombBlock extends Block {

  protected boolean isMined = false;

  public BombBlock(int x, int y, ID id) {
    super(x, y, id);
  }

  @Override
  public void tick(){

  }

  public void render(Graphics g) {
    if (!isMined) {
      g.setColor(new Color(26, 26, 26));
      g.fillRect(x, y, Game.blockSize, Game.blockSize);
      g.setColor(new Color(168, 33, 33));
      g.fillRect(x + 3, y + 3, Game.blockSize - 6, Game.blockSize - 6);
    } else {
      g.setColor(new Color(137, 103, 103));
      g.fillRect(x + 3, y + 3, Game.blockSize - 6, Game.blockSize - 6);
    }
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle(x,y,size,size);
  }


  public void blockAttribute() {
    HUD.HEALTH-= 10;
    System.out.println("Ouch");
  }

  public void setMined(){
    this.isMined = true;
    blockAttribute();

  }
}
