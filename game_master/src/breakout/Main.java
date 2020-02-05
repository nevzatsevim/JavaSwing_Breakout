package breakout;

import javax.swing.*;
import java.awt.*;

import static java.awt.Font.BOLD;

public class Main {

    public static final int gameWidth = 1000, gameHeight = 600;


    public static void main(String[] args) {
        JFrame levelframe = new JFrame("BreakOut");
        JFrame endScreen = new JFrame();
        JFrame startScreen = new JFrame();
        String txt = "<html><h1 align='center'>BREAKOUT by NEVZAT SEVIM</h1>";
        txt = txt + "<h1 style=\"color: blue;\">Press Mouse to Start</h1>";
        txt = txt + "Use left and right arrows to control the paddle<br/>";
        txt = txt + "Use up and down arrows to control the paddle weirdly; but like it's a cheat and it's a little buggy  <br/>";
        txt = txt + "Press l for more lives<br/>";
        txt = txt + "Press r to reset the ball<br/>";
        txt = txt + "Destroy all the bricks for the next level, including the diamonds <br/>";
        txt = txt + "press 1,2,3 to toggle between levels (but, if you do this many times the game crashes eventually)<br/>";
        txt = txt + "The game has powerUps and its actually winnable, ENJOY! <br/>";

        JButton start = new JButton(txt);

        JButton end = new JButton("You Win");

        LevelPanel panel1 = new LevelPanel(levelframe,endScreen,startScreen);

        start.addActionListener(listener -> {
            startScreen.setVisible(false);
            panel1.reset(1);
            levelframe.setVisible(true);
        });
        end.addActionListener(listener -> {
            startScreen.setVisible(true);
            endScreen.setVisible(false);
        });

        levelframe.getContentPane().add(panel1);
        levelframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel1.setBackground(Color.black);
        levelframe.setVisible(false);
        levelframe.setResizable(false);
        levelframe.setSize(gameWidth, gameHeight);

        startScreen.getContentPane().add(start);
        startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startScreen.setVisible(true);
        startScreen.setResizable(false);
        startScreen.setSize(gameWidth, gameHeight);

        endScreen.getContentPane().add(end);
        endScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endScreen.setVisible(false);
        endScreen.setResizable(false);
        endScreen.setSize(gameWidth, gameHeight);
    }

}
