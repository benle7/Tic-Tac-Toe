// ID: 318811304

import java.util.ArrayList;
import biuoop.DrawSurface;

/**
 * @author Ben Levi
 * Class Operation : the class represent list of sprites.
 */
public class SpriteCollection {
    private final java.util.List<Sprite> allSprites;

    /**
     * Function Name : SpriteCollection.
     * Function Operation : constructor.
     */
    public SpriteCollection() {
        this.allSprites = new ArrayList<>();
    }

    /**
     * Function Name : addSprite.
     * Function Operation : the function add sprite to the list.
     * @param s sprite.
     */
    public void addSprite(Sprite s) {
        this.allSprites.add(s);
    }

    /**
     * Function Name : notifyAllTimePassed.
     * Function Operation : call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite s : this.allSprites) {
            s.timePassed();
        }
    }

    /**
     * Function Name : drawAllOn.
     * Function Operation : call drawOn(d) on all sprites.
     * @param d drawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.allSprites) {
            s.drawOn(d);
        }
    }

    public Sprite getFirst() {
        return this.allSprites.get(0);
    }
}
