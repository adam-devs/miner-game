import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Game extends Canvas implements Runnable {

  public static final int width = 1000;
  public static final int height = roundTo50(width / 12 * 9) + 13;

  private static Handler handler = new Handler();
  private Menu menu;

  public enum STATE {
    MENU,
    GAME,
    DONATE
  }

  public STATE gameState = STATE.GAME;

  public static final int blockSize = 50;
  private Random r;

  private static Thread thread;
  private static boolean running = false;

  private static Block[][] blocks = new Block[width / 50][height / 50 + 8];

  public Game() {
    new Window(width, height, "Miner Game", this);

    this.addKeyListener(new KeyInput(handler));
    menu = new Menu(this, handler);
    this.addMouseListener(menu);

    if (gameState == STATE.GAME) {

      // blocks added:
      blocks = new Block[width / 50][height / 50 + 4];

      int offset = height / 4;

      // initialises blocks
      for (int i = 0; i < blocks.length; i++) {
        for (int j = 0; j < blocks[0].length; j++) {
          blocks[i][j] = randomBlock(i * 50, j * 50 + offset);
        }
      }

      r = new Random();
      int x;
      int y;

      // 5 Gold Blocks
      for (int i = 0; i < 10; i++) {
        x = r.nextInt(blocks.length);
        y = r.nextInt(blocks[0].length);
        blocks[x][y] = new GoldBlock(x * 50, y * 50 + offset, ID.GOLD_BLOCK);
      }

      // 30 Bomb Blocks
      for (int i = 0; i < 30; i++) {
        x = r.nextInt(blocks.length);
        y = r.nextInt(blocks[0].length);
        blocks[x][y] = new BombBlock(x * 50, y * 50 + offset, ID.BOMB_BLOCK);
      }

      // 5 O2 Blocks
      for (int i = 0; i < 10; i++) {
        x = r.nextInt(blocks.length);
        y = r.nextInt(blocks[0].length);
        blocks[x][y] = new O2Block(x * 50, y * 50 + offset, ID.O2BLOCK);
      }

      // adds the blocks to the game
      for (int i = 0; i < blocks.length; i++) {
        for (int j = 0; j < blocks[0].length; j++) {
          handler.addObject(blocks[i][j]);
        }
      }

      handler.addObject(new Sky(0, 0));

      handler.addObject(new Ground(0, 0));

      handler.addObject(new Miner(roundTo50(width / 2) + 12, 100, ID.MINER, handler));

      handler.addObject(new HUD(0, 0, null));
    }
  }

  public synchronized static int roundTo50(int x) {
    if (x % 50 < 25) {
      return x - (x % 50);
    } else if (x % 50 > 25) {
      return x + (50 - (x % 50));
    } else {
      return x + 25;
    }
  }

  private static Block randomBlock(int x, int y) {
    Random random = new Random();
    int id = random.nextInt(100);

    if (id < 20) {
      return new BombBlock(x, y, ID.BOMB_BLOCK);
    } else if (id < 30) {
      return new O2Block(x, y, ID.O2BLOCK);
    } else if (id < 40) {
      return new GoldBlock(x, y, ID.GOLD_BLOCK);
    } else {
      return new NormalBlock(x, y, ID.BLOCK);
    }
  }

  public static synchronized void moveUp() {
    // removes design blocks
    for (int i = 0; i < handler.getSize(); i++) {
      if (handler.getObject(i).getID() == ID.SKY || handler.getObject(i).getID() == ID.GROUND) {
        handler.removeObject(handler.getObject(i));
      }
    }

    // moves every block up
    for (int i = 0; i < blocks.length; i++) {
      for (int j = 0; j < blocks[0].length; j++) {
        handler.removeObject(blocks[i][j]);
        GameObject temp = blocks[i][j];
        blocks[i][j].setY(temp.getY() - 200);
        handler.addObject(blocks[i][j]);
      }
    }

    // removes the blocks at the top which are off the screen
    // then adds new ones to replace them
    for (int i = 0; i < blocks.length; i++) {
      for (int j = 0; j < blocks[0].length; j++) {
        if (blocks[i][j].getY() < 0) {
          handler.removeObject(blocks[i][j]);
          GameObject temp = blocks[i][j];
          blocks[i][j] = randomBlock(temp.getX(), temp.getY() + 800);
          handler.addObject(blocks[i][j]);
        }
      }
    }
  }

  private static ID randomType() {
    Random random = new Random();
    int id = random.nextInt(100);

    if (id < 20) {
      return ID.BOMB_BLOCK;
    } else if (id < 30) {
      return ID.O2BLOCK;
    } else if (id < 40) {
      return ID.GOLD_BLOCK;
    } else {
      return ID.BLOCK;
    }
  }

  public synchronized void start() {
    thread = new Thread(this);
    thread.start();
    running = true;
  }

  public static synchronized void stop() {
    File file = new File("output.txt");
    try {
      FileWriter myWriter = new FileWriter("output.txt");
      myWriter.write((String.valueOf(HUD.score)));
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    System.out.println("GAME OVER");
    try {
      thread.join();
      running = false;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void run() {
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long timer = System.currentTimeMillis();
    int frames = 0;
    while (running) {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      while (delta >= 1) {
        tick();
        delta--;
      }
      if (running) render();
      frames++;
      if (System.currentTimeMillis() - timer > 1000) {
        timer += 1000;
        //        System.out.println("FPS: " + frames);
        frames = 0;
      }
    }
    stop();
  }

  private void tick() {
    if (gameState == STATE.GAME) {
      handler.tick();
    } else if (gameState == STATE.MENU) {
      menu.tick();
    }
  }

  private void render() {
    BufferStrategy bs = this.getBufferStrategy();
    if (bs == null) {
      this.createBufferStrategy(3);
      return;
    }

    Graphics g = bs.getDrawGraphics();

    g.setColor(new Color(113, 102, 98));
    g.fillRect(0, 0, width, height);

    if (gameState == STATE.GAME) {
      handler.render(g);
    } else if (gameState == STATE.MENU || gameState == STATE.DONATE) {
      menu.render(g);
    }

    g.dispose();
    bs.show();
  }

  public static synchronized int clamp(int var, int min, int max) {
    if (var >= max) {
      return var = max;
    } else if (var <= min) {
      return var = min;
    } else {
      return var;
    }
  }

  public static void main(String[] args) {
    new Game();
  }
}
