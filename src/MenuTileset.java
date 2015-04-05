import java.awt.*;

public class MenuTileset extends Tileset {

    public MenuTileset(int width, int height, Graphics2D g) {
        super(getHorizontalSize(width), getVerticalSize(height), width, height, g);

        generateTiles();
    }

    private static int getHorizontalSize(int width) {
        return width / MenuTile.width;
    }

    private static int getVerticalSize(int height) {
        return height / MenuTile.height;
    }

    @Override
    public void generateTiles() {
        for(int iX = 0; iX < horizontalSize; iX++) {
            for(int iY = 0; iY < verticalSize; iY++) {
                tileset[iX][iY] = new MenuTile("hexyAssets/1.png", iX, iY, g);
            }
        }
    }

    @Override
    protected void renderTileset() {
        renderTiles();
    }

    @Override
    protected void renderTile(Tile tile) {
        g.drawImage(tile.image, tile.positionX, tile.positionY, null);
    }
}
