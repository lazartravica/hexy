package states.game;

import core.Tile;

import java.awt.*;
import java.util.Random;

public class EdgeTile extends Tile {

    private int offsetZ = -1800;

    Random r = new Random();
    public int frameCounter = r.nextInt(60) + 1;

    private Edge edge;
    public boolean animate;

    public EdgeTile(Edge edge, String fileName, int positionX, int positionY) {
        super(fileName, positionX, positionY, null);

        this.edge = edge;
        this.animate = (edge == Edge.NORTH || edge == Edge.WEST);


        frameCounter = (frameCounter + 1) % 60;
    }

    public void render(Graphics2D g) {
        if (offsetZ != 0)
            offsetZ += 20;

        if (animate) {
            if (edge == Edge.NORTH || edge == Edge.SOUTH)
                g.drawImage(waterSprite.getSubimage(frameCounter * 65, 0, 65, 89), positionX, positionY + offsetZ, null);
            if (edge == Edge.WEST || edge == Edge.EAST)
                g.drawImage(lavaSprite.getSubimage(frameCounter * 65, 0, 65, 89), positionX, positionY + offsetZ, null);
            frameCounter = (frameCounter + 1) % 60;
        } else
            g.drawImage(image, positionX, positionY - offsetZ, null);
    }

    @Override
    protected void generateDoodads() {

    }
}
