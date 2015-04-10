package core;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.LinkedList;

public abstract class Tileset {

    // Example: 11x11 grid
    protected int horizontalSize;
    protected int verticalSize;
    // If the tileset should be rendered from the top left corner: 0px 0px;
    protected int offsetX;
    protected int offsetY;

    // The 2d array which will store all the tiles.
    protected Tile[][] tileset;

    protected Graphics2D g;

    public Tileset(int horizontalSize, int verticalSize) {
        this.horizontalSize = horizontalSize;
        this.verticalSize = verticalSize;
        tileset = new Tile[horizontalSize][verticalSize];

    }


    protected abstract void generateTiles();

    protected abstract void renderTileset(Graphics2D g);

    protected void renderTiles(Graphics2D g) {
        for (int iY = 0; iY < verticalSize; iY++) {
            for (int iX = 0; iX < horizontalSize; iX++) {
                tileset[iX][iY].updateZOffset();
                tileset[iX][iY] = renderTile(tileset[iX][iY], g);
            }
        }
    }

    protected abstract Tile renderTile(Tile tile, Graphics2D g);

    public Point getTilePosition(Tile t) {
        for (int i = 0; i < horizontalSize; i++)
            for (int j = 0; j < verticalSize; j++)
                if (tileset[i][j] == t)
                    return new Point(i, j);
        return new Point(-1, -1);
    }

    public Tile getTileFromPosition(int i, int j) {
        int xLength = tileset.length;
        int yLength = tileset[0].length;

        if (i > 0 && i < xLength && j > 0 && j < yLength)
            return tileset[i][j];
        return null;
    }

    protected Polygon getHex(int posX, int posY, int size) {
        Polygon hex = new Polygon();
        for (int i = 0; i < 6; i++) {
            hex.addPoint((int) (posX + offsetX + (size * Math.cos((1 + i * 2) * Math.PI / 6))),
                    (int) (posY + 1 + offsetY + ((size - 3.5) * Math.sin((1 + i * 2) * Math.PI / 6))));
        }
        return hex;
    }


    public Point getTilePosition(Point a) {
        for (int i = 0; i < horizontalSize; i++)
            for (int j = 0; j < verticalSize; j++) {
                if (tileset[i][j].containsPoint(a))
                    return new Point(i, j);
            }
        return new Point(-1, -1);
    }

    private int[][] directions = new int[][]{{0, -1}, {-1, 0}, {1, -1}, {-1, 1}, {0, 1}, {1, 0}};

    public boolean isEdge(Tile t) {
        return (getTilePosition(t).x == 0 || getTilePosition(t).x == 10 || getTilePosition(t).y == 0 || getTilePosition(t).y == 10);
    }

    public int[][] neighbours(Tile t) {
        if (t == null)
            throw new IllegalArgumentException("Error: tile is out of bounds.");

        boolean badNeighbour;
        int[] tcoords = new int[]{(int) getTilePosition(t).getX(), (int) getTilePosition(t).getY()};
        int[][] potentialNeighbours = new int[6][2];
        LinkedList<Point> realNeighbours = new LinkedList<Point>();
        int n = 6;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                if (tcoords[j] + directions[i][j] >= 0 && tcoords[j] + directions[i][j] <= 10)
                    potentialNeighbours[i][j] = tcoords[j] + directions[i][j];
                else {
                    potentialNeighbours[i][j] = -1;
                    badNeighbour = true;
                }
            }
        }
        return potentialNeighbours;
    }
}
    
