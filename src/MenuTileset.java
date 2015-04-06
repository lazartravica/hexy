import java.awt.*;

public class MenuTileset extends Tileset {

    public MenuTileset() {
        super(11, 11);

        offsetX = 25;
        offsetY = 80;

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
        for (int iX = 0; iX < horizontalSize; iX++) {
            for (int iY = 0; iY < verticalSize; iY++) {
                tileset[iX][iY] = new MenuTile("hexyAssets/tiles/1.png", iX, iY, g);
            }
        }
    }

    @Override
    protected void renderTileset(Graphics2D g) {
        renderTiles(g);
    }

    @Override
    protected void renderTile(Tile tile, Graphics2D g) {
        MenuTile menuTile = (MenuTile) tile;
        g.drawImage(tile.image, tile.positionX + offsetX, tile.positionY + offsetY, null);

        for(MenuTile.Flower flower : menuTile.flowers) {
            g.drawImage(flower.image, tile.positionX + flower.positionX + offsetX, tile.positionY + flower.positionY + offsetY, null);
        }

        if(menuTile.tree != null) {
            MenuTile.Tree tree = menuTile.tree;
            g.drawImage(tree.image, tile.positionX + tree.positionX + offsetX, tile.positionY + tree.positionY + offsetY, null);
        }
    }
}
