package core;

import states.menu.MenuTile;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.LinkedList;
import java.util.Random;

public abstract class Tileset {

    // Example: 11x11 grid
    protected int horizontalSize;
    protected int verticalSize;
    // If the tileset should be rendered from the top left corner: 0px 0px;
    protected int offsetX;
    protected int offsetY;

    public boolean animationInProgress = false;

    // The 2d array which will store all the tiles.
    protected Tile[][] tileset;

    protected Graphics2D g;

    protected abstract Tile createTile(String imagePath, int positionX, int positionY, Graphics2D g);

    public Tileset(int horizontalSize, int verticalSize) {
        this.horizontalSize = horizontalSize;
        this.verticalSize = verticalSize;
        tileset = new Tile[horizontalSize][verticalSize];

        generateTiles();
    }

    public void generateTiles() {
        for (int iX = 0; iX < horizontalSize; iX++) {
            for (int iY = 0; iY < verticalSize; iY++) {
                int positionX = (iX * Tile.tileWidth) + (iY * Tile.tileWidth / 2) + offsetX;
                int positionY = iY * (int) Math.round(Tile.tileHeight * 0.75) + offsetY;
                tileset[iX][iY] = createTile("hexyAssets/tiles/1.png", positionX, positionY, g);
                tileset[iX][iY].offsetZAnimationDelay = 4 * (iX + iY) + 2 * (10 - iX);
                tileset[iX][iY].hexagon = tileset[iX][iY].getHexagon();
            }
        }
    }

    public void renderTileset(Graphics2D g) {
        boolean didAnimation = false;
        for (int iY = 0; iY < verticalSize; iY++) {
            for (int iX = 0; iX < horizontalSize; iX++) {
                didAnimation = tileset[iX][iY].updateZOffset() || didAnimation;
                tileset[iX][iY] = tileset[iX][iY].render(g);
            }
        }
        animationInProgress = didAnimation;
    }

    public Tile pixelToTile(int x, int y) {
        for (int iY = 0; iY < verticalSize; iY++) {
            for (int iX = 0; iX < horizontalSize; iX++) {
                if (tileset[iX][iY].containsPixel(x, y))
                    return tileset[iX][iY];
            }
        }
        return null;
    }

    public int[] getTileIndices(Tile tile) {
        for (int iY = 0; iY < verticalSize; iY++) {
            for (int iX = 0; iX < horizontalSize; iX++) {
                if (tileset[iX][iY] == tile)
                    return new int[] {iX, iY};
            }
        }
        return new int[] {-1, -1};
    }

    public int[][] neighbours(Tile tile) {
        int[][] directions = new int[][]{{0, -1}, {-1, 0}, {1, -1}, {-1, 1}, {0, 1}, {1, 0}};

        if (tile == null)
            throw new IllegalArgumentException("Error: tile is out of bounds.");

        boolean badNeighbour;
        int[] tileIndices = getTileIndices(tile);
        int[][] potentialNeighbours = new int[6][2];
        LinkedList<Point> realNeighbours = new LinkedList<Point>();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                if (tileIndices[j] + directions[i][j] >= 0 && tileIndices[j] + directions[i][j] <= 10)
                    potentialNeighbours[i][j] = tileIndices[j] + directions[i][j];
                else {
                    potentialNeighbours[i][j] = -1;
                    badNeighbour = true;
                }
            }
        }
        return potentialNeighbours;
    }

    public void startExitAnimation() {
        animationInProgress = true;

        Random r = new Random();
        for (int iY = 0; iY < verticalSize; iY++) {
            for (int iX = 0; iX < horizontalSize; iX++) {
                tileset[iX][iY].offsetZAnimationDelay = 4 * (20 - iX - iY) + iX;
                tileset[iX][iY].desiredOffsetZ = 900;
                tileset[iX][iY].deltaZ = 50;
            }
        }
    }
}
