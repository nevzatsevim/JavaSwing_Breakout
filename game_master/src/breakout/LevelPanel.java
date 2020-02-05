package breakout;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static java.awt.Font.BOLD;

// This class creates the game panel and is responsible for the game play
public class LevelPanel extends JPanel implements KeyListener {

    ArrayList<Block> levelBlocks;
    Ball pow1,pow2,pow3, ball;
    Paddle paddle;

    int noDestroyed, score, lives, level, noPowers, paddleSpeed, totalBlocks, maxLevelScore;
    int gameHeight = Main.gameHeight, gameWidth = Main.gameWidth;

    JFrame l1Frame, endScreen, startScreen;
    Thread thread;

    void reset(int lev) {
        int blockNo = 10;  // blockNo - 1 gives the number of blocks found in each row
        totalBlocks = 47; // total # of blocks to destroy to clear out the level
        maxLevelScore = 55; // total # of points that is to be collected in a single level
        lives = 3;
        level = lev; // important in the configuration of blocks
        noDestroyed = 0; // # of blocks that are currently destroyed
        noPowers = 0; // # of powerUps used so far
        paddleSpeed = 25;
        score = (lev-1)*maxLevelScore; // score is also used to determine level changes

        levelBlocks = new ArrayList<Block>();

        ball = new Ball(gameWidth/2, 18 * gameHeight/20, 15, 15, "ball.png", 0);
        pow1 = new Ball(-gameWidth, -gameWidth, 25, 25, "power.png", new java.util.Random().nextInt(3));
        pow2 = new Ball(-gameWidth, -gameWidth, 25, 25, "power.png", new java.util.Random().nextInt(3));
        pow3 = new Ball(-gameWidth, -gameWidth, 25, 25, "power.png", new java.util.Random().nextInt(3));

        paddle = new Paddle(gameWidth/2, 18* gameHeight/20, 150, 25, "paddle.png");

        //Extra Point Blocks
        levelBlocks.add(new Block(3*(gameWidth / blockNo), -10, 80, 80, "dia.png", 1,5));
        levelBlocks.add(new Block(6*(gameWidth / blockNo), -10, 80, 80, "dia.png", 1,5));

        setBlocks(lev, blockNo);
        addKeyListener(this);
        setFocusable(true);
    }

    LevelPanel(JFrame frame1, JFrame endScreen, JFrame startScreen) {

        this.l1Frame = frame1;
        this.endScreen = endScreen;
        this.startScreen = startScreen;

        reset(1);

        thread = new Thread(() -> {
            while (true) {
                update();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void paintComponent(Graphics g) {
        levelBlocks.forEach(block -> {
            block.draw(g, this);
        });
        ball.draw(g, this);
        paddle.draw(g, this);
        pow1.draw(g, this);
        pow2.draw(g, this);
        pow3.draw(g, this);
        g.setColor(Color.white);
        Font myFont = new Font("Arial", BOLD, 24);
        g.setFont(myFont);
        g.drawString("Lives  " + lives , 1* gameWidth/10, 30);
        g.drawString("Level  " + level , 4* gameWidth/10, 30);
        g.drawString("Score  " + score , 8* gameWidth/10, 30);
    }

    /* setBlocks method is used to generate a custom block configuration for each game level.
    It is by far the longest, ugliest and the most repetitive piece of code in this class.
    However, I specifically did not refactor this method so that the creator of the game can easily
    modify every individual game level without worrying about messing up the other levels.
    Like I am aware of the duplications, there are duplicates because I designed my levels to be similar */

    private void setBlocks(int lev, int blockNo) {
        int spacer = 10;
        //The Block Configuration for LEVEL 1
        if(lev == 1) {
            for (int i = 0; i < blockNo - 1; i++) {
                levelBlocks.add(new Block(spacer + i * (gameWidth / blockNo), 2 * (gameHeight / 20), gameWidth / blockNo, gameHeight / 20, "rf.png", 1,1));
                levelBlocks.add(new Block(spacer + i * (gameWidth / blockNo), 3 * (gameHeight / 20) + 10, gameWidth / blockNo, gameHeight / 20, "of.png", 1,1));
                levelBlocks.add(new Block(spacer + i * (gameWidth / blockNo), 4 * (gameHeight / 20) + 20, gameWidth / blockNo, gameHeight / 20, "yf.png", 1,1));
                levelBlocks.add(new Block(spacer + i * (gameWidth / blockNo), 5 * (gameHeight / 20) + 30, gameWidth / blockNo, gameHeight / 20, "gf.png", 1,1));
                levelBlocks.add(new Block(spacer + i * (gameWidth / blockNo), 6 * (gameHeight / 20) + 40, gameWidth / blockNo, gameHeight / 20, "bf.png", 1,1));
                spacer += 10;
            }
        }
        //The BLock Configuration for LEVEL 2
        if(lev == 2){
            for (int i = 0; i < blockNo - 1; i++) {
                levelBlocks.add(new Block(spacer + i * (gameWidth / blockNo), 2 * (gameHeight / 20), gameWidth / blockNo, gameHeight / 20, "red.png", 2,1));
                levelBlocks.add(new Block(spacer + i * (gameWidth / blockNo), 3 * (gameHeight / 20) + 10, gameWidth / blockNo, gameHeight / 20, "orange.png", 2,1));
                levelBlocks.add(new Block(spacer + i * (gameWidth / blockNo), 4 * (gameHeight / 20) + 20, gameWidth / blockNo, gameHeight / 20, "yellow.png", 2,1));
                levelBlocks.add(new Block(spacer + i * (gameWidth / blockNo), 5 * (gameHeight / 20) + 30, gameWidth / blockNo, gameHeight / 20, "gf.png", 1,1));
                levelBlocks.add(new Block(spacer + i * (gameWidth / blockNo), 6 * (gameHeight / 20) + 40, gameWidth / blockNo, gameHeight / 20, "bf.png", 1,1));
                spacer += 10;
            }
        }
        //The BLock Configuration for LEVEL 3
        if(lev == 3){
            for (int i = 0; i < blockNo - 1; i++) {
                levelBlocks.add(new Block(spacer + i * (gameWidth / blockNo), 2 * (gameHeight / 20), gameWidth / blockNo, gameHeight / 20, "red.png", 2,1));
                levelBlocks.add(new Block(spacer + i * (gameWidth / blockNo), 3 * (gameHeight / 20) + 10, gameWidth / blockNo, gameHeight / 20, "orange.png", 2,1));
                levelBlocks.add(new Block(spacer + i * (gameWidth / blockNo), 4 * (gameHeight / 20) + 20, gameWidth / blockNo, gameHeight / 20, "yellow.png", 2,1));
                levelBlocks.add(new Block(spacer + i * (gameWidth / blockNo), 5 * (gameHeight / 20) + 30, gameWidth / blockNo, gameHeight / 20, "green.png", 2,1));
                levelBlocks.add(new Block(spacer + i * (gameWidth / blockNo), 6 * (gameHeight / 20) + 40, gameWidth / blockNo, gameHeight / 20, "blue.png", 2,1));
                spacer += 10;
            }
        }
    }

    public void update() {
        levelChecker();
        //numbers 11,23,35 are decided randomly and can be changed without consequence (they aren't magic)
        powerUpChecker(11, 0, pow1);
        powerUpChecker(23, 1, pow2);
        powerUpChecker(35, 2, pow3);

        ball.x += ball.movX;

        ballWallIntersection();
        ballPaddleIntersection();

        ball.y += ball.movY;

        loseCondition();
        ballBrickIntersection();
        repaint();
    }

    private void ballBrickIntersection() {
        levelBlocks.forEach(Block -> {
            if(ball.intersects(Block) && !Block.destroyed) {
                Block.level -= 1;
                if(ball.y > Block.y & ball.y < Block.y + Block.height - 2)
                    ball.movX *= -1;
                else
                    ball.movY *=-1;
                if(Block.level < 1) {
                    Block.destroyed = true;
                    score += Block.points;
                    noDestroyed +=1;
                }
            }
        });
    }

    private void loseCondition() {
        if(ball.y > gameHeight) {
            thread = null;
            if(lives > 0){
                lives -= 1;
                ball.y = gameHeight/2;
                ball.x = gameWidth/2;
            }
            else {
                reset(1);
                l1Frame.setVisible(false);
                startScreen.setVisible(true);
            }
        }
    }

    private void ballWallIntersection() {
        if(ball.x <= 0 || ball.x >= gameWidth - ball.width)
            ball.movX *= -1;
        if(ball.y <= 0)
            ball.movY *= -1;
    }

    private void ballPaddleIntersection() {
        if(ball.intersects(paddle)) {
            //if the ball falls to the edges of the paddle
            if((ball.x < paddle.x + paddle.width * 0.25 & ball.movX > 0) ||
                    (ball.x > paddle.x + paddle.width * 0.75 & ball.movX < 0)) {
                if(Math.abs(ball.movX) < 2)
                    ball.movX *= -2;
                else
                    ball.movX *= -1;
            }
            //if the ball falls to the middle of the paddle
            else if((ball.x < paddle.x + paddle.width/2 & ball.x > paddle.x + paddle.width/2 & ball.movX > 0)||
                    (ball.x > paddle.x + paddle.width/2 & ball.x < paddle.x + 3*paddle.width/4 & ball.movX < 0)) {
                if(Math.abs(ball.movX) > 0.5)
                    ball.movX *= -0.5;
                else
                    ball.movX *= -1;
            }
            //if the ball has no horizontal movement for any reason
            if(ball.movX == 0)
                ball.movX = 1;
            ball.movY *= -1.05;
        }
    }

    private void levelChecker() {
        if(noDestroyed == totalBlocks && score == maxLevelScore) {
            reset(2);
            ball.movY *= 1.5;
        }
        if(noDestroyed == totalBlocks && score == maxLevelScore*2) {
            reset(3);
            ball.movY *= 2;
        }
        if(noDestroyed == totalBlocks && score == maxLevelScore*3) {
            l1Frame.setVisible(false);
            endScreen.setVisible(true);
        }
    }

    public void powerUpChecker(int destroyNeeded, int powersNeeded, Ball b){
        if(noDestroyed==destroyNeeded && noPowers == powersNeeded){
            noPowers += 1;
            b.x = ball.x;
            b.y = ball.y;
        }
        if(b.intersects(paddle)){
            b.x = -gameHeight;
            b.y = -gameHeight;
            if(b.type == 0)
                paddle.width *= 1.5;
            if(b.type == 1)
                paddleSpeed *= 2;
            if(b.type == 2) {
                ball.width *= 2;
                ball.height *= 2;
            }
        }
        if(b.y > gameHeight) {
            b.x = -gameHeight;
            b.y = -gameHeight;
        }
        b.y += 2;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (gameWidth - paddle.width)) //move the paddle right
            paddle.x += paddleSpeed;
        if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0) //move the paddle left
            paddle.x -= paddleSpeed;
        if (e.getKeyCode() == KeyEvent.VK_DOWN &&  paddle.y < gameHeight - 2* paddle.height) //move the paddle up
            paddle.y += paddleSpeed/2;
        if (e.getKeyCode() == KeyEvent.VK_UP && paddle.y > 0)  //move the paddle down
            paddle.y -= paddleSpeed/2;
        if (e.getKeyCode() == KeyEvent.VK_L)  //add an extra life
            lives += 1;
        if (e.getKeyCode() == KeyEvent.VK_1) { //reset to level 1
            reset(1);
            paddleSpeed *= 0.25; }
        if (e.getKeyCode() == KeyEvent.VK_2) { //reset to level 2
            reset(2);
            ball.movY *= 1.5;
            paddleSpeed *= 0.25; }
        if (e.getKeyCode() == KeyEvent.VK_3) { //reset to level 3
            reset(3);
            ball.movY*= 2;
            paddleSpeed *= 0.25; }
        if (e.getKeyCode() == KeyEvent.VK_R) { //reset the ball/paddle position
            ball.x =gameWidth/2;
            ball.y = gameHeight/2;
            paddle.x = gameWidth/2;
            paddle.y = 18*gameHeight/20; }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }
}