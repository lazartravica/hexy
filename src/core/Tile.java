package core;

import rafgfxlib.Util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@SuppressWarnings("unused")
public abstract class Tile {
    public class Flower {
        public BufferedImage image;
        public int positionX;
        public int positionY;


        public Flower(int tileWidth, int tileHeight) {
            Random r = new Random();

            Integer imageIndex = r.nextInt(5) + 1;
            image = Util.loadImage("hexyAssets/flowers/" + imageIndex.toString() + ".png");

            positionX = Math.abs(r.nextInt(tileWidth) - 12);
            positionY = Math.abs(r.nextInt((int) Math.round(tileHeight / 2)) + (int) Math.round(tileHeight * 0.25) - 12);
        }
    }

    public class Tree {
        public BufferedImage image;
        public int positionX;
        public int positionY;

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

    public static final int tileWidth = 65;
    public static final int tileHeight = 65;

    public BufferedImage image = null;
    public int positionX = 0;
    public int positionY = 0;

    public int centerX, centerY;
    public int size;

    public Polygon hex;

    protected Graphics2D g;

    public Flower[] flowers;
    public Tree tree;

    public int deltaZ = 50;
    public int offsetZ = 900;
    public int desiredOffsetZ = 0;
    public int offsetZAnimationDelay;

    public Tile(String fileName, int coordX, int coordY, Graphics2D g) {
        image = Util.loadImage(fileName);

        positionX = (coordX * tileWidth) + (coordY * tileWidth / 2);
        positionY = coordY * (int) Math.round(tileHeight * 0.75);

        centerX = positionX + tileWidth / 2;
        centerY = positionY + tileHeight / 2;
        size = (int) (tileWidth / Math.sqrt(3));
        hex = new Polygon();

        this.g = g;

        generateDoodads();
    }

    public boolean containsPoint(Point a) {
        return hex.contains(a);
    }

    protected void generateTree() {
        Random r = new Random();
        if (r.nextInt(5) == 4)
            tree = new Tree(tileWidth, tileHeight);
        else
            tree = null;
    }

    protected void generateFlowers() {
        Random r = new Random();

        int numberOfFlowers = r.nextInt(4);
        flowers = new Flower[numberOfFlowers];

        for (int i = 0; i < numberOfFlowers; i++) {
            flowers[i] = new Flower(tileWidth, tileHeight);
        }
    }

    public boolean updateZOffset() {
        if(desiredOffsetZ != offsetZ) {
            if (--offsetZAnimationDelay <= 0)
                if(desiredOffsetZ  < offsetZ)
                    offsetZ -= deltaZ;
                else
                    offsetZ += deltaZ;
            return true;
        }
        return false;
    }

    protected abstract void generateDoodads();

}
