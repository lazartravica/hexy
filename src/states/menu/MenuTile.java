package states.menu;

import java.awt.*;
import java.util.Random;

import core.Tile;

public class MenuTile extends Tile {

    public MenuTile(String fileName, int positionX, int positionY, Graphics2D g) {
        super(fileName, positionX, positionY, g);
    }

    @Override
    protected void generateDoodads() {
        generateFlowers();
        generateTree();
    }
}
