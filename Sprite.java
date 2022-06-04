// ID: 318811304

import biuoop.DrawSurface;

import java.awt.*;

/**
 * @author Ben Levi
 * Interface Operation : the interface represent game object that
 * can be drawn to the screen.
 */
public interface Sprite {

    /**
     * Function Name : drawOn.
     * Function Operation : draw the sprite to the screen.
     * @param d drawSurface.
     */
    void drawOn(DrawSurface d);

    /**
     * Function Name : timePassed.
     * Function Operation : notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Function Name : addToGame.
     * Function Operation : add the sprite to the game objects.
     * @param g Game.
     */
    void addToGame(Game g);

    void setColor(Color c);
}
