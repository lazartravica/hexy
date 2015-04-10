package states.menu;

import java.awt.*;
import java.util.Random;

import core.Tile;

public class MenuTile extends Tile {

    public MenuTile(String fileName, int coordX, int coordY, Graphics2D g) {
        super(fileName, coordX, coordY, g);
    }

    @Override
    protected void generateDoodads() {
        generateFlowers();
        generateTree();
    }
}
