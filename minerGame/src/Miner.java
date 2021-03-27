import java.awt.*;

public class Miner extends GameObject {

  private int health;
  private int oxygen;

  public Miner(int x, int y, ID id, int health, int oxygen){
    super (x, y, id);
    this.health = health;
    this.oxygen = oxygen;
  }

  @Override
  public void tick(){


  }

  @Override
  public void render(Graphics g){


  }

  public int getX(){
    return this.x;
  }

  public void setX(int x){
    this.x = x;
  }

  public int getY(){
    return this.x;
  }

  public void setY(int x){
    this.x = x;
  }

  public int getID(){
    return this.x;
  }

  public void setID(ID id){
    this.id = id;
  }





}
