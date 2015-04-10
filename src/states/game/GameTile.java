package states.game;

import core.Tile;
import rafgfxlib.Util;

import java.awt.*;

public class GameTile extends Tile {

    public enum Player {
        RED_PLAYER,
        BLUE_PLAYER,
        NONE
    }

    public Player player = Player.NONE;

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
        deltaZ = 3;
        image = Util.loadImage("hexyAssets/tiles/2.png");
    }
}
