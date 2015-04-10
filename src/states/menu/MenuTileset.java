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
    public void generateTiles() {
        for (int iX = 0; iX < horizontalSize; iX++) {
            for (int iY = 0; iY < verticalSize; iY++) {
                tileset[iX][iY] = new MenuTile("hexyAssets/tiles/1.png", iX, iY, g);
                tileset[iX][iY].offsetZAnimationDelay = 4 * (iX + iY) + 2 * (10 - iX);
                tileset[iX][iY].hex = getHex(tileset[iX][iY].centerX, tileset[iX][iY].centerY, tileset[iX][iY].tileWidth);
            }
        }
    }
}
