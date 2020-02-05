game
====

This project implements the game of Breakout.

Name: Nevzat Sevim

### Overview

* As the sole member of this projecet's developement team, my goal was to design a breakout game with cool features.
* My design goal of the game is to create an aestatically pleasing game with smooth game play. I also embelished the game with cool features and cheat codes. Any 
new developer that uses my project as a template can add new features to their liking.
* The game is esentially made up of three panels that are declared in the main method. Hence the game is played by running the main class. These panels are the 
start screen, game screen and the end screen. Most of the game desing happens inside the game panel class. When this class is fired up, it interacts with onjects 
such as ball, paddle and blocks. The game panel is where the graphical interface happens, where the user gives key inputs and where the game updates itself with a thread.
Therefore this is the most important class.
* I have decided to run the entire game inside the game panel with the help of other objects that are created independently of this class. This decision made 
implementing new features very easy.
*I can proudly say that I have implemented all the features that were required of me. That being said if I had more tim, I would have implemented many other cool 
futures such as multiple balls or a paddle that can shoot. These would have required some tweaks in my levelPanel class, but they felt ultimately doable.