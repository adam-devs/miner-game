import java.awt.*;
import java.util.LinkedList;

public class Handler {
  LinkedList<GameObject> objects = new LinkedList<>();
  private int size = 0;

  public void tick() {
    for (GameObject tempObject : objects) {
      tempObject.tick();
    }
  }

  public void render(Graphics g) {
    for (GameObject tempObject : objects) {
      tempObject.render(g);
    }
  }

  public void addObject(GameObject object) {
    this.objects.add(object);
  }

  public void removeObject(GameObject object) {
    this.objects.remove(object);
  }

  //TODO: Gives thread error when trying to move all the objects up :(
  public void moveObjects() {
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i).getID() == ID.SKY || objects.get(i).getID() == ID.GROUND) {
        removeObject(objects.get(i));
      } else {
        objects.get(i).setY(objects.get(i).getY() - Game.blockSize * 10);
      }
    }

    for (GameObject obj : objects) {
      if (obj.getY() < 0) {
        removeObject(obj);
      }
    }
  }
}
