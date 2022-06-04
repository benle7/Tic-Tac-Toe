// ID: 318811304

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Ben Levi
 * Class Operation : the class represent properties and methods of
 * Block. the class implements Sprite interface.
 */
public class Block implements Sprite {
    private final Rectangle block;
    private java.awt.Color color;
    private int i;

    /**
     * Function Name : Block.
     * Function Operation : constructor.
     * @param block the properties of the block (rectangle).
     * @param color the color.
     */
    public Block(Rectangle block, java.awt.Color color) {
        this.block = block;
        this.color = color;
    }

    /**
     * Function Name : Block.
     * Function Operation : constructor.
     * @param upperLeft point.
     * @param width the width.
     * @param height the height.
     * @param color the color.
     */
    public Block(Point upperLeft, double width, double height, java.awt.Color color) {
        this.block = new Rectangle(upperLeft, width, height);
        this.color = color;
    }

    /**
     * Function Name : Block.
     * Function Operation : constructor.
     * @param block the properties of the block (rectangle).
     */
    public Block(Rectangle block) {
        this.block = block;
        this.color = Color.black;
    }

    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        int x = (int) this.block.getUpperLeft().getX();
        int y = (int) this.block.getUpperLeft().getY();
        int width = (int) this.block.getWidth();
        int height = (int) this.block.getHeight();
        surface.fillRectangle(x, y, width, height);
        surface.setColor(Color.black);
        surface.drawRectangle(x, y, width, height);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
