import biuoop.DrawSurface;
import java.awt.Color;

public class Squere implements Sprite {
    private final int num;
    private final int shape;

    public Squere(int num, int shape) {
        this.num = num;
        this.shape = shape;
    }
    public void drawOn(DrawSurface d) {
        if (this.shape == 1) {
            drawX(d);
        } else {
            drawO(d);
        }
    }
    private void drawX(DrawSurface d) {
        int x, y;
        if (this.num <= 3) {
            y = 110;
        } else if (this.num <= 6) {
            y = 275;
        } else {
            y = 440;
        }
        x = 85 + (((this.num - 1) % 3) * 195);
        d.setColor(Color.red);
        d.drawText(x, y, "X", 70);

    }
    private void drawO(DrawSurface d) {
        int x, y;
        if (this.num <= 3) {
            y = 90;
        } else if (this.num <= 6) {
            y = 250;
        } else {
            y = 410;
        }
        x = 110 + (((this.num - 1) % 3) * 192);

        d.setColor(Color.orange);
        d.fillCircle(x, y, 30);
        d.setColor(Color.black);
        d.drawCircle(x, y, 30);
    }

    public void setColor(Color c) {
    }

    public void timePassed() {
    }


    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
