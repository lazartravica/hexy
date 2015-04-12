package states.game;

import core.Tile;
import core.doodads.Flower;
import rafgfxlib.Util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameTile extends Tile {

    public Player player = Player.NONE;

    public boolean visited = false;

    public BufferedImage RED_IMAGE = Util.loadImage("hexyAssets/tiles/red.png");
    public BufferedImage BLUE_IMAGE = Util.loadImage("hexyAssets/tiles/blue.png");

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
        } else if(player == Player.RED)
            g.drawImage(RED_IMAGE, positionX, positionY + offsetZ, null);
        else if(player == Player.BLUE)
            g.drawImage(BLUE_IMAGE, positionX, positionY + offsetZ, null);
    }

    public boolean changeState(Player currentTurn) {
        if(player == Player.NONE) {
            player = currentTurn;
            return true;
        }
        return false;
    }
}
