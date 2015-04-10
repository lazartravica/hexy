package states.game;

import core.Tile;

import java.awt.*;

public class GameTile extends Tile {

    public GameTile(String fileName, int coordX, int coordY, int tileWidth, int tileHeight, Graphics2D g) {
        super(fileName, coordX, coordY, tileWidth, tileHeight, g);
    }
}
