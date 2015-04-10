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

    @Override
    public void generateTiles() {
        for (int iX = 0; iX < horizontalSize; iX++) {
            for (int iY = 0; iY < verticalSize; iY++) {
                tileset[iX][iY] = new GameTile("hexyAssets/tiles/1.png", iX, iY, g);
                tileset[iX][iY].offsetZAnimationDelay = 4 * (iX + iY) + 2 * (10 - iX);
                tileset[iX][iY].hex = getHex(tileset[iX][iY].centerX, tileset[iX][iY].centerY, tileset[iX][iY].size);
            }
        }
    }

    public void removeNudges() {
        for (int iX = 0; iX < horizontalSize; iX++) {
            for (int iY = 0; iY < verticalSize; iY++) {
                tileset[iX][iY].desiredOffsetZ = 0;
                tileset[iX][iY].image = Util.loadImage("hexyAssets/tiles/1.png");
            }
        }
    }
}
