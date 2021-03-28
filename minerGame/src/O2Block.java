import java.awt.*;

public class O2Block extends Block {
  protected boolean isMined = false;

  public O2Block(int x, int y, ID id) {
    super(x, y, id);
  }

  @Override
  public void tick(){

  }

  public void render(Graphics g) {
    if (!isMined) {
      g.setColor(new Color(255, 255, 255));
      g.fillRect(x, y, Game.blockSize, Game.blockSize);
      g.setColor(new Color(33, 168, 152));
      g.fillRect(x + 3, y + 3, Game.blockSize - 6, Game.blockSize - 6);
    }
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle(x,y,size,size);
  }

  @Override
  public void blockAttribute() {
    HUD.OXYGEN+=5;
  }

  public void setMined(){
    this.isMined = true;
    blockAttribute();
  }
}
