package states.game;

import core.Tile;
import core.Tileset;

import java.awt.*;

public class GameTileset extends Tileset {

    public GameTileset(int horizontalSize, int verticalSize) {
        super(horizontalSize, verticalSize);
    }

    @Override
    protected void generateTiles() {

    }

    @Override
    protected void renderTileset(Graphics2D g) {

    }

    @Override
    protected Tile renderTile(Tile tile, Graphics2D g) {
        return null;
    }
}
