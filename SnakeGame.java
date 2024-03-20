import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {

    private MainFrame mainFrame;
    private JPanel pauseMenu;

    private Sound sound = new Sound();

    //PauseMenuPanel pauseMenu;
    int boardWidth;
    int boardHeight;
    int tileSize = 25; //kocka merete

    private class Tile {
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;

        }
    }

    //SNAKE
    Tile snakeHead;
    ArrayList<Tile> snakeBody;
    Tile food;

    Random random; //hasznaljuk az etelnel

    Timer gameLoop; //timer -> jatek loop start es stop
    int velocityX;
    int velocityY;
    boolean gameOver;

    void initVariables() {
        this.mainFrame = mainFrame;
        pauseMenu = new JPanel();

    }

    public SnakeGame(MainFrame mainFrame, int width, int height) {
        this.mainFrame = mainFrame;
        //pauseMenu = new PauseMenuPanel();

        //initVariables();
        pauseMenu = new JPanel();
        JButton resumeButton = new JButton("Resume");
        JButton quitButton = new JButton("Quit");

        // ActionListener hozzáadása a gombokhoz
        resumeButton.addActionListener(e -> resumeGame());
        quitButton.addActionListener(e -> quitGame());

        pauseMenu.add(resumeButton);
        pauseMenu.add(quitButton);

        //menuPanel.add(pauseMenu, "PauseMenu");

        boardWidth = width;
        boardHeight = height;

        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);
        this.snakeHead = new Tile(10, 10);
        this.snakeBody = new ArrayList<Tile>();
        this.food = new Tile(10, 10);
        random = new Random();
        placeFood();

        velocityX = 0;
        velocityY = 0;

        gameLoop = new Timer(100, this); //0.1 s
        gameLoop.start();
    }

    private void placeFood() {

        //le kell kezelni hogy ne a kigyora tegye

        food.x = random.nextInt((boardWidth / tileSize) - 2);
        food.y = random.nextInt((boardWidth / tileSize) - 2);
        food.x = food.x + 1;// + tileSize; //0 es 25 kozotti pozicio mar nem palya
        food.y = food.y + 1;// + tileSize;

    }

    public boolean collision(Tile tile1, Tile tile2) {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        //negyzetek
        for (int i = 0; i <= boardWidth / tileSize; i++) {
            //(x1,x2,y1,y2)
            //Kirajzoljuk a palyat
            g.drawLine(i * tileSize, tileSize, i * tileSize, boardHeight);
            g.drawLine(tileSize, i * tileSize, boardWidth, i * tileSize);
        }

        //etel
        g.setColor(Color.red);
        g.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize);

        //kigyo feje
        g.setColor(Color.green);
        //tileSize - mekkora legyen a "kocka" merete
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);

        //kigyo body
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            g.fillRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize);
        }

        //score && gameover
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        if (gameOver) {
            g.setColor(Color.red);
            //g.drawString("Game over: " + String.valueOf(snakeBody.size()), tileSize-16, tileSize);
            g.drawString("Game over: " + String.valueOf(snakeBody.size()), 25 * tileSize, 5 * tileSize);

            //gameover hang
            SoundGameOver();
        } else {
            g.drawString("Score: " + String.valueOf(snakeBody.size()), 25 * tileSize, tileSize);
        }

    }

    public void move() {

        //ha a kigyo feje elerte az etelt, ugyanazon a mezon vannak akkor hozzaadjuk a kigyo testehez
        if (collision(snakeHead, food)) {
            snakeBody.add(new Tile(food.x, food.y));

            //sound.SetSound(1);
            //sound.Play();
            SoundEffect();
            placeFood();
        }

        //snake body
        for (int i = snakeBody.size() - 1; i >= 0; i--) {
            Tile snakePart = snakeBody.get(i);

            if (i == 0) { //elso elem
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            } else {

                Tile prevSnakePart = snakeBody.get(i - 1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;

            }
        }

        //snake head
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        //game over
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            //ha a fejjel belemegyunk a testbe

            if (collision(snakeHead, snakePart)) {
                gameOver = true;
            }
        }

        if (snakeHead.x * tileSize <= 0 || snakeHead.x * tileSize >= boardWidth || snakeHead.y * tileSize <= 0 || snakeHead.y * tileSize >= boardHeight) //ha kimegyunk a palyarol
        {
            gameOver = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        move();
        repaint();

        if (gameOver) {
            gameLoop.stop();
        }
    }

    @Override
    //nem kell
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) && velocityY != 1) {

            velocityX = 0;
            velocityY = -1;
        } else if ((e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
        } else if ((e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        } else if ((e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_H) {

            mainFrame.PauseCard();
            pauseGame();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    void pauseGame() {
        gameLoop.stop();
        mainFrame.PauseCard();
        //mainFrame.show(mainFrame, "PauseMenu");  // valtas -> pausemenu
    }

    void resumeGame() {
        mainFrame.SnakeCard();
        //layout.show(menuPanel, "P6");  // folytatas
        gameLoop.start();
    }

    void resetGame() {
        snakeHead = new Tile(10, 10);
        snakeBody.clear();
        placeFood();
        velocityX = 0;
        velocityY = 0;
        gameOver = false;
        gameLoop.start();
        //ujrainicializasas
        //gameLoop = new Timer(100, this);
        //gameLoop.start();
    }

    void SoundEffect() {
        if (!mainFrame.isMuted()) {
            sound.SetSound(1);
            this.sound.Play();
        }
    }

    void SoundGameOver() {
        if (!mainFrame.isMuted()) {
            sound.SetSound(2);
            this.sound.Play();
        }
    }

    private void quitGame() {
        mainFrame.dispose();  //
    }


}
