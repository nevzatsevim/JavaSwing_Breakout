package breakout;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Paddle extends Rectangle {

    Image pic;

    Paddle(int x, int y, int w, int h, String s) {
        this.x = x;
        this.y = y;

        this.width = w;
        this.height = h;

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