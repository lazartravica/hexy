package states.game;

import com.sun.javafx.geom.Point2D;
import core.Tile;
import core.Tileset;
import rafgfxlib.Util;

import java.awt.*;
import java.util.LinkedList;

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

    public boolean isPlayersTile(int x, int y, Player player) {
        GameTile gameTile = (GameTile) tileset[x][y];
        return gameTile.player == player;
    }

    public boolean isVisited(int x, int y) {
        GameTile gameTile = (GameTile) tileset[x][y];
        if (!gameTile.visited) {
            gameTile.visited = true;
            return false;
        }
        return true;
    }

    public boolean checkEdge(int iX, int iY, Player player, Edge edge) {
        if (!isPlayersTile(iX, iY, player) || isVisited(iX, iY))
            return false;

        switch (edge) {
            case EAST:
                if (iX == horizontalSize - 1)
                    return true;
            case SOUTH:
                if (iY == verticalSize - 1)
                    return true;
        }

        boolean connectsToEdge = false;
        for (Point neighbour : neighbours(iX, iY)) {
            connectsToEdge = connectsToEdge || checkEdge(neighbour.x, neighbour.y, player, edge);
        }

        return connectsToEdge;
    }

    public boolean checkWinningCondition(Player player) {
        for (int iX = 0; iX < horizontalSize; iX++) {
            for (int iY = 0; iY < verticalSize; iY++) {
                if(isPlayersTile(iX, iY, player)) {
                    GameTile gameTile = (GameTile) tileset[iX][iY];
                    gameTile.visited = false;
                }
            }
        }

        switch (player) {
            case RED:
                for (int iY = 0; iY < horizontalSize; iY++) {
                    if (checkEdge(0, iY, player, Edge.EAST))
                        return true;
                }
                break;
            case BLUE:
                for (int iX = 0; iX < verticalSize; iX++) {
                    if (checkEdge(iX, 0, player, Edge.SOUTH))
                        return true;
                }
                break;
        }

        return false;
    }

    public LinkedList<Point> neighbours(int x, int y) {
        LinkedList<Point> neighbours = new LinkedList<Point>();

        for (int iX = -1; iX <= 1; iX++) {
            for (int iY = -1; iY <= 1; iY++) {
                if(iX != iY)
                    if (x + iX >= 0 && x + iX < horizontalSize && y + iY >= 0 && y + iY < verticalSize)
                        neighbours.add(new Point(x + iX, y + iY));
            }
        }

        return neighbours;
    }
}
