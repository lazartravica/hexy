import java.awt.*;

public class MenuTile extends Tile {

    public static int width = 65;
    public static int height = 65;

    public MenuTile(String fileName, int coordX, int coordY, Graphics2D g) {
        super(fileName, coordX, coordY, width, height, g);
    }
}
