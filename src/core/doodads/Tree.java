package core.doodads;

import rafgfxlib.Util;

import java.awt.image.BufferedImage;
import java.util.Random;

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

