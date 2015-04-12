package core;

import core.doodads.Flower;
import core.doodads.Tree;
import rafgfxlib.Util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@SuppressWarnings("unused")
public abstract class Tile {

    public static final int tileWidth = 65;
    public static final int tileHeight = 65;

    public BufferedImage image = null;
    public int positionX = 0;
    public int positionY = 0;

    public int centerX, centerY;
    public int size;

    public Polygon hexagon;

    protected Graphics2D g;

    public Flower[] flowers;
    public Tree tree;

    public int deltaZ = 100;
    public int offsetZ = 900;
    public int desiredOffsetZ = 0;
    public int offsetZAnimationDelay;

    public Tile(String fileName, int positionX, int positionY, Graphics2D g) {
        image = Util.loadImage(fileName);

        this.positionX = positionX;
        this.positionY = positionY;

        centerX = this.positionX + tileWidth / 2;
        centerY = this.positionY + tileHeight / 2;
        size = (int) (tileWidth / Math.sqrt(3));
        hexagon = new Polygon();

        this.g = g;

        generateDoodads();
    }

    public boolean containsPixel(int x, int y) {
        return hexagon.contains(new Point(x, y));
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

    public void render(Graphics2D g) {
        g.drawImage(image, positionX, positionY + offsetZ, null);

        for (Flower flower : flowers) {
            g.drawImage(flower.image, positionX + flower.positionX, positionY + flower.positionY + offsetZ, null);
        }

        if (tree != null) {
            g.drawImage(tree.image, positionX + tree.positionX, positionY + tree.positionY + offsetZ, null);
        }
    }

    public Polygon getHexagon() {
        Polygon hexagon = new Polygon();
        for (int i = 0; i < 6; i++) {
            hexagon.addPoint((int) (centerX + (size * Math.cos((1 + i * 2) * Math.PI / 6))),
                    (int) (centerY + 1 + ((size - 3.5) * Math.sin((1 + i * 2) * Math.PI / 6))));
        }
        return hexagon;
    }
}
