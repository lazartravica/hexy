import java.awt.*;

public abstract class Tileset {

    // Example: 11x11 grid
    protected int horizontalSize;
    protected int verticalSize;

    // Example: 800 x 600px
    protected int height;
    protected int width;

    //@TODO implement the drawing of the tileset with offset (maybe translating the Graphics2D is allowed?) and delete the warning suppression.
    // If the tileset should be rendered from the top left corner: 0px 0px;
    @SuppressWarnings("unused")
    protected int offsetX;
    @SuppressWarnings("unused")
    protected int offsetY;

    // The 2d array which will store all the tiles.
    protected Tile[][] tileset;

    protected Graphics2D g;

    public Tileset(int horizontalSize, int verticalSize, int width, int height, Graphics2D g) {
        this.horizontalSize = horizontalSize;
        this.verticalSize = verticalSize;
        tileset = new Tile[horizontalSize][verticalSize];

        this.width = width;
        this.height = height;

        this.g = g;
    }

    protected abstract void generateTiles();
    protected abstract void renderTileset();

    protected void renderTiles() {
        for (int iY = 0; iY < verticalSize; iY++) {
            for(int iX = 0; iX < horizontalSize; iX++) {
                renderTile(tileset[iX][iY]);
            }
        }
    }

    protected abstract void renderTile(Tile tile);
}
