package states.game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import rafgfxlib.Util;
import core.Tile;
import core.doodads.Flower;

public class GameTile extends Tile {

    public Player player = Player.NONE;

    public boolean visited = false;
    Random r = new Random();

    public BufferedImage RED_IMAGE = Util.loadImage("hexyAssets/tiles/red.png");
    public BufferedImage BLUE_IMAGE = Util.loadImage("hexyAssets/tiles/blue.png");

    public int frameCounter = r.nextInt(60);

    public GameTile(String fileName, int coordX, int coordY, Graphics2D g) {
        super(fileName, coordX, coordY, g);
    }

    @Override
    protected void generateDoodads() {
        generateFlowers();
        generateTree();
    }

    public void nudgeTile() {
        desiredOffsetZ = -24;
        deltaZ = 6;
        image = Util.loadImage("hexyAssets/tiles/selected.png");
    }


    public void render(Graphics2D g) {
        if (player == Player.NONE) {
            g.drawImage(image, positionX, positionY + offsetZ, null);

            for (Flower flower : flowers) {
                g.drawImage(flower.image, positionX + flower.positionX, positionY + flower.positionY + offsetZ, null);
            }

            if (tree != null) {
                g.drawImage(tree.image, positionX + tree.positionX, positionY + tree.positionY + offsetZ, null);
            }
        } else if (player == Player.RED)
            if (visited)
                g.drawImage(lavaSprite.getSubimage(frameCounter * 65, 0, 65, 89), positionX, positionY + offsetZ, null);
            else
                g.drawImage(RED_IMAGE, positionX, positionY + offsetZ, null);
        else if (player == Player.BLUE)
            if (visited)
                g.drawImage(waterSprite.getSubimage(frameCounter * 65, 0, 65, 89), positionX, positionY + offsetZ, null);
            else
                g.drawImage(BLUE_IMAGE, positionX, positionY + offsetZ, null);
        if(visited)
            frameCounter = (frameCounter + 1) % 60;
}

    public boolean changeState(Player currentTurn) {
        if (player == Player.NONE) {
            player = currentTurn;
            return true;
        }
        return false;
    }
}
