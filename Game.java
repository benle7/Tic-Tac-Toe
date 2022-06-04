// ID: 318811304

import biuoop.GUI;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Scanner;

/**
 * @author Ben Levi
 * Class Operation : the class represent new game and his construction.
 */
public class Game {

    static final int GUI_WIDTH = 600;
    static final int GUI_HEIGHT = 500;


    private final SpriteCollection sprites;
    private final GUI gui;
    private int index;
    private int turn;
    private final int[] arr;

    /**
     * Function Name : Game.
     * Function Operation : constructor.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.gui = new GUI("Game", GUI_WIDTH, GUI_HEIGHT);
        this.index = 1;
        this.turn = 1;
        this.arr = new int[10];
    }

    /**
     * Function Name : addSprite.
     * Function Operation : the function add sprite to the collection.
     * @param s sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }


    /**
     * Function Name : buildFrame.
     * Function Operation : the function create the frame.
     */
    private void buildFrame() {
        Sprite frame = new Block(new Rectangle(0, 0, GUI_WIDTH, GUI_HEIGHT), Color.blue);

        Sprite b1 = new Block(new Rectangle(195, 0, 20, 500), Color.black);
        Sprite b2 = new Block(new Rectangle(390, 0, 20, 500), Color.black);
        Sprite b3 = new Block(new Rectangle(0, 0, 20, 500), Color.black);
        Sprite b4 = new Block(new Rectangle(580, 0, 20, 500), Color.black);

        Sprite b5 = new Block(new Rectangle(0, 160, 600, 20), Color.black);
        Sprite b6 = new Block(new Rectangle(0, 320, 600, 20), Color.black);
        Sprite b7 = new Block(new Rectangle(0, 0, 600, 20), Color.black);
        Sprite b8 = new Block(new Rectangle(0, 480, 600, 20), Color.black);

        addSprite(frame);
        addSprite(b1);
        addSprite(b2);
        addSprite(b3);
        addSprite(b4);
        addSprite(b5);
        addSprite(b6);
        addSprite(b7);
        addSprite(b8);
    }


    public void initialize() {
        buildFrame();
        this.arr[0] = 3;
        for (int n = 1; n < 10; n++) {
            this.arr[n] = 0;
        }
    }

    private void turnX() {
        Scanner s = new Scanner(System.in);
        int choose = -1;
        System.out.println("X turn, Enter squere choose :");
        while ((choose < 1) || (choose > 9) || (this.arr[choose] != 0)) {
            if (choose != -1) {
                System.out.println("try again !");
            }
        choose = s.nextInt();
        }
        addSprite(new Squere(choose, 1));
        this.arr[choose] = 1;
    }

    private void turnO() {
        Scanner s = new Scanner(System.in);
        int choose = -1;
        System.out.println("O turn, Enter squere choose :");
        while ((choose < 1) || (choose > 9) || (this.arr[choose] != 0)) {
            if (choose != -1) {
                System.out.println("try again !");
            }
            choose = s.nextInt();
        }
        addSprite(new Squere(choose, 2));
        this.arr[choose] = 2;
    }


    private void doTurn() {
        if (this.turn == 1) {
            turnX();
            this.turn++;
        } else {
            turnO();
            this.turn--;
        }
    }

    private int win() {
        if (((arr[1] == 1) && (arr[2] == 1) && (arr[3] == 1))
                || ((arr[4] == 1) && (arr[5] == 1) && (arr[6] == 1))
                || ((arr[7] == 1) && (arr[8] == 1) && (arr[9] == 1))
                || ((arr[1] == 1) && (arr[4] == 1) && (arr[7] == 1))
                || ((arr[2] == 1) && (arr[5] == 1) && (arr[8] == 1))
                || ((arr[3] == 1) && (arr[6] == 1) && (arr[9] == 1))
                || ((arr[1] == 1) && (arr[5] == 1) && (arr[9] == 1))
                || ((arr[7] == 1) && (arr[5] == 1) && (arr[3] == 1))) {
            return 1;
        } else if (((arr[1] == 2) && (arr[2] == 2) && (arr[3] == 2))
                || ((arr[4] == 2) && (arr[5] == 2) && (arr[6] == 2))
                || ((arr[7] == 2) && (arr[8] == 2) && (arr[9] == 2))
                || ((arr[1] == 2) && (arr[4] == 2) && (arr[7] == 2))
                || ((arr[2] == 2) && (arr[5] == 2) && (arr[8] == 2))
                || ((arr[3] == 2) && (arr[6] == 2) && (arr[9] == 2))
                || ((arr[1] == 2) && (arr[5] == 2) && (arr[9] == 2))
                || ((arr[7] == 2) && (arr[5] == 2) && (arr[3] == 2))) {
            return 2;
        }
        return 0;
    }

    private Color generateColor(int i) {
        Color[] colors = {Color.blue, new Color(138, 255, 156),
                new Color(64, 213, 238),
                new Color(138, 181, 255),
                new Color(255, 222, 138),
                new Color(151, 72, 239)};
        return colors[i % 5];
    }

    public void run() {
        int check = 0;
        int stop = 0;
        while (stop != 2) {
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);

            if (stop == 1) {
                break;
            }

            doTurn();
            this.index++;
            this.sprites.getFirst().setColor(generateColor(this.index));
            check = win();
            if ((this.index > 9) || (check != 0)) {
                stop++;
            }
        }

        if (check == 1) {
            System.out.println("X WIN !");
        } else if (check == 2) {
            System.out.println("O WIN !");
        } else {
            System.out.println("NO WIN !");
        }
        System.out.println("press 0 + enter to exit.");
        Scanner s = new Scanner(System.in);
        s.nextInt();
        this.gui.close();
    }
}
