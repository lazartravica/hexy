package states.menu;

import java.awt.*;
import java.util.Random;

import core.Tile;
import core.Tileset;

public class MenuTileset extends Tileset {

    public MenuTileset() {
        super(11, 11);

        offsetX = 25;
        offsetY = 80;

        generateTiles();
    }

    @Override
    protected Tile createTile(String imagePath, int positionX, int positionY, Graphics2D g) {
        return new MenuTile(imagePath, positionX, positionY, g);
    }
}
