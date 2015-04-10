package core.doodads;

import rafgfxlib.Util;

import java.awt.image.BufferedImage;
import java.util.Random;

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

