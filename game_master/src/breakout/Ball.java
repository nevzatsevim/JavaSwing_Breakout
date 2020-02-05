package breakout;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ball extends Rectangle {

    Image pic;
    int type;
    int movX,movY;

    Ball(int x, int y, int w, int h, String s, int t) {
        this.x = x;
        this.y = y;

        movX = -1;
        movY = -1;

        this.width = w;
        this.height = h;

        this.type = t;

        try {
            pic = ImageIO.read(new File("src/breakout/"+s));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g, Component c) {
            g.drawImage(pic, x, y, width, height, c);
    }
}
