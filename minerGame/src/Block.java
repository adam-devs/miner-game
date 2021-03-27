public abstract class Block extends GameObject {

  int size = 50;

  public Block(int x, int y, ID id) {
    super(x, y, id);
  }

  public abstract void blockAttribute();
}
