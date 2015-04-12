package states.game;

import core.Tile;
import rafgfxlib.Util;
import states.game.Edge;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EdgeBorder {

    private BufferedImage alienImage;
    private BufferedImage tileImage;
    private int positionX;
    private int positionY;

    private Tile[] tiles;

    public EdgeBorder(Edge edge, int offsetX, int offsetY) {

        int size = 11;

        if(edge == Edge.NORTH || edge == Edge.SOUTH)
            size = 13;

        tiles = new Tile[size];

        for(int i = 0; i < size; i++) {
            positionX = offsetX;
            positionY = offsetY;
            String fileName = "";
//            int positionX = (iX * Tile.tileWidth) + (iY * Tile.tileWidth / 2) + offsetX;
//            int positionY = iY * (int) Math.round(Tile.tileHeight * 0.75) + offsetY;
            switch(edge) {
                case NORTH:
                    positionX += (i * Tile.tileWidth);
                    fileName = "hexyAssets/tiles/blue.png";
                    break;
                case SOUTH:
                    positionX += (i * Tile.tileWidth) + (size * Tile.tileWidth / 2);
                    positionY += size * (int) Math.round(Tile.tileHeight * 0.75);
                    fileName = "hexyAssets/tiles/blue.png";
                    break;
                case EAST:
                    positionX += (i * Tile.tileWidth / 2);
                    positionY += i * (int) Math.round(Tile.tileHeight * 0.75);
                    fileName = "hexyAssets/tiles/red.png";
                    break;
                case WEST:
                    positionX += (size * Tile.tileWidth) + (i * Tile.tileWidth / 2);
                    positionY += i * (int) Math.round(Tile.tileHeight * 0.75);
                    fileName = "hexyAssets/tiles/red.png";
                    break;
            }
            tiles[i] = new EdgeTile(fileName, positionX, positionY);
        }
    }

    public void render(Graphics2D g) {
        for(Tile tile : tiles) {
            tile.render(g);
        }
    }
}
