package rs.norm.lazar.hex.states.menu;

import rs.norm.lazar.hex.core.Tile;
import rs.norm.lazar.hex.core.Tileset;

import java.awt.*;

public class MenuTileset extends Tileset {

    private int screenHeight;
    private int[][] zPositions;

    public MenuTileset(int screenHeight) {
        super(11, 11);

        offsetX = 25;
        offsetY = 80;

        this.screenHeight = screenHeight;

        generateTiles();
    }

    @Override
    public void generateTiles() {
        for (int iX = 0; iX < horizontalSize; iX++) {
            for (int iY = 0; iY < verticalSize; iY++) {
                int positionZ = 200 * (iX + iY) + 100 * (10 - iX);
                tileset[iX][iY] = new MenuTile("hexyAssets/tiles/1.png", iX, iY, positionZ, g);
            }
        }
    }

    @Override
    protected void renderTileset(Graphics2D g) {
        renderTiles(g);
    }

    @Override
    protected Tile renderTile(Tile tile, Graphics2D g) {
        MenuTile menuTile = (MenuTile) tile;
        if(menuTile.positionZ > 0)
            menuTile.positionZ -= 50;

        g.drawImage(tile.image, tile.positionX + offsetX, tile.positionY + offsetY + menuTile.positionZ, null);

        for (MenuTile.Flower flower : menuTile.flowers) {
            g.drawImage(flower.image, tile.positionX + flower.positionX + offsetX, tile.positionY + flower.positionY + offsetY + menuTile.positionZ, null);
        }

        if (menuTile.tree != null) {
            MenuTile.Tree tree = menuTile.tree;
            g.drawImage(tree.image, tile.positionX + tree.positionX + offsetX, tile.positionY + tree.positionY + offsetY + menuTile.positionZ, null);
        }

        return menuTile;
    }
}