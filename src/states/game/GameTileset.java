package states.game;

import core.Tile;
import core.Tileset;
import rafgfxlib.Util;

import java.awt.*;

public class GameTileset extends Tileset {

    public GameTileset() {
        super(11, 11);

        offsetX = 25;
        offsetY = 80;

        generateTiles();
    }

    public void removeNudges() {
        for (int iX = 0; iX < horizontalSize; iX++) {
            for (int iY = 0; iY < verticalSize; iY++) {
                tileset[iX][iY].desiredOffsetZ = 0;
                tileset[iX][iY].image = Util.loadImage("hexyAssets/tiles/1.png");
            }
        }
    }

    @Override
    protected Tile createTile(String imagePath, int positionX, int positionY, Graphics2D g) {
        return new GameTile(imagePath, positionX, positionY, g);
    }
}
