package states.game;

import core.Tile;

import java.awt.*;

public class EdgeTile extends Tile {

    private int offsetZ = -1800;

    public EdgeTile(String fileName, int positionX, int positionY) {
        super(fileName, positionX, positionY, null);
    }

    public void render(Graphics2D g) {
        if(offsetZ != 0)
            offsetZ += 20;
        g.drawImage(image, positionX, positionY - offsetZ, null);
    }

    @Override
    protected void generateDoodads() {

    }
}
