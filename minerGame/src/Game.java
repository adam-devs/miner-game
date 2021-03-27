import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

  public static final int width = 1000;
  public static final int height = roundTo50(width / 12 * 9);

  public static final int blockSize = 50;

  private static int roundTo50(int x) {
    if (x % 50 < 25) {
      return x - (x % 50);
    } else if (x % 50 > 25) {
      return x + (50 - (x % 50));
    } else if (x % 50 == 25) {
      return x + 25;
    }
    return 0;
  }

  private Thread thread;
  private boolean running = false;

  private Handler handler;

  public Game() {
    new Window(width, height, "Miner Game", this);

    handler = new Handler();
    this.addKeyListener(new KeyInput(handler));

    // blocks added:
    Block[][] blocks = new Block[width / 50][height / 50];

    int offset = height / 4;

    // initialises blocks
    for (int i = 0; i < blocks.length; i++) {
      for (int j = 0; j < blocks[0].length; j++) {
        blocks[i][j] = new NormalBlock(i * 50, j * 50 + offset, ID.BLOCK);
      }
    }

    Random r = new Random();
    int x, y;

    // 5 Gold Blocks
    for (int i = 0; i < 5; i++) {
      x = r.nextInt(blocks.length);
      y = r.nextInt(blocks[0].length);
      blocks[x][y] = new GoldBlock(x * 50, y * 50 + offset, ID.GOLD_BLOCK);
    }

    // 5 Bomb Blocks
    for (int i = 0; i < 5; i++) {
      x = r.nextInt(blocks.length);
      y = r.nextInt(blocks[0].length);
      blocks[x][y] = new BombBlock(x * 50, y * 50 + offset, ID.BOMB_BLOCK);
    }

    // 5 O2 Blocks
    for (int i = 0; i < 5; i++) {
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

    handler.addObject(new Miner(width / 2, height / 2, ID.MINER, handler));

    handler.addObject(new HUD(0, 0, null));
  }

  public synchronized void start() {
    thread = new Thread(this);
    thread.start();
    running = true;
  }

  public synchronized void stop() {
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
        System.out.println("FPS: " + frames);
        frames = 0;
      }
    }
    stop();
  }

  private void tick() {
    handler.tick();
  }

  private void render() {
    BufferStrategy bs = this.getBufferStrategy();
    if (bs == null) {
      this.createBufferStrategy(3);
      return;
    }

    Graphics g = bs.getDrawGraphics();

    handler.render(g);

    g.dispose();
    bs.show();
  }

  public static int clamp(int var, int min, int max) {
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
