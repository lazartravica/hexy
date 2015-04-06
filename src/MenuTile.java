import rafgfxlib.Util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MenuTile extends Tile {

    public class Flower {
        BufferedImage image;
        int positionX;
        int positionY;

        public Flower(int tileWidth, int tileHeight) {
            Random r = new Random();

            Integer imageIndex = r.nextInt(5) + 1;
            image = Util.loadImage("hexyAssets/flowers/" + imageIndex.toString() + ".png");

            positionX = Math.abs(r.nextInt(tileWidth) - 12);
            positionY = Math.abs(r.nextInt((int) Math.round(tileHeight / 2)) + (int) Math.round(tileHeight * 0.25) - 12);
        }
    }

    public class Tree {
        BufferedImage image;
        int positionX;
        int positionY;

        public Tree(int tileWidth, int tileHeight) {
            Random r = new Random();

            Integer imageIndex = r.nextInt(21) + 1;
            image = Util.loadImage("hexyAssets/trees/" + imageIndex.toString() + ".png");

            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();

            positionX = (tileWidth - imageWidth) / 2;
            positionY = tileHeight / 2 - imageHeight;
        }
    }

    public static int width = 65;
    public static int height = 65;

    public Flower[] flowers;

    public Tree tree;

    public MenuTile(String fileName, int coordX, int coordY, Graphics2D g) {
        super(fileName, coordX, coordY, width, height, g);

        generateFlowers();
        generateTree();
    }

    private void generateTree() {
        Random r = new Random();
        if (r.nextInt(5) == 4)
            tree = new Tree(width, height);
        else
            tree = null;
    }

    private void generateFlowers() {
        Random r = new Random();

        int numberOfFlowers = r.nextInt(4);
        flowers = new Flower[numberOfFlowers];

        for (int i = 0; i < numberOfFlowers; i++) {
            flowers[i] = new Flower(width, height);
        }
    }
}
