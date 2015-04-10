package states.menu;

import java.awt.*;
import java.util.Random;

import core.Tile;

public class MenuTile extends Tile {



    public int positionZ; // Used for the entry animation of the tile (slides from the bottom of the screen)

    public MenuTile(String fileName, int coordX, int coordY, int screenHeight, Graphics2D g) {
        super(fileName, coordX, coordY, g);
        this.positionZ = screenHeight + 100;
    }

    @Override
    protected void generateDoodads() {
        generateFlowers();
        generateTree();
    }
}
