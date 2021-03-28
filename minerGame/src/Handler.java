import java.awt.*;
import java.util.LinkedList;

public class Handler {
  LinkedList<GameObject> objects = new LinkedList<>();

  public synchronized void tick() {
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).tick();
    }
  }

  public synchronized void render(Graphics g) {
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).render(g);
    }
  }

  public synchronized void addObject(GameObject object) {
    this.objects.add(object);
  }

  public synchronized void removeObject(GameObject object) {
    this.objects.remove(object);
  }

  public int getSize() {
    return objects.size();
  }

  public GameObject getObject(int index) {
    return objects.get(index);
  }
}
