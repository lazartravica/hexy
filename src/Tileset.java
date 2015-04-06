import java.awt.*;

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
                tileset[iX][iY] = renderTile(tileset[iX][iY], g);
            }
        }
    }

    protected abstract Tile renderTile(Tile tile, Graphics2D g);
}
