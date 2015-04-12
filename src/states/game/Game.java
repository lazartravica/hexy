package states.game;

import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;

import java.awt.*;

public class Game extends GameState {

    public Player currentTurn = Player.RED;

    public GameTileset gameTileset;

    public static final Color RED_COLOR = new Color(233, 123, 51);
    public static final Color BLUE_COLOR = new Color(139, 255, 255);

    private EdgeBorder eastBorder;
    private EdgeBorder westBorder;
    private EdgeBorder northBorder;
    private EdgeBorder southBorder;

    public Game(GameHost host) {
        super(host);

        gameTileset = new GameTileset();

        int screenWidth = host.getWidth();
        int screenHeight = host.getHeight();

        eastBorder = new EdgeBorder(Edge.EAST, -40, 80);
        westBorder = new EdgeBorder(Edge.WEST, 25, 80);
        northBorder = new EdgeBorder(Edge.NORTH, -72, 30);
        southBorder = new EdgeBorder(Edge.SOUTH, -105, -17);
    }

    @Override
    public boolean handleWindowClose() {
        System.exit(0);
        return false;
    }

    @Override
    public String getName() {
        return "game";
    }

    @Override
    public void resumeState() {

    }

    @Override
    public void suspendState() {

    }

    @Override
    public void render(Graphics2D g, int sw, int sh) {
        // Draw which player's turn it is
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //@TODO Turn on AA in Gamehost.java setHighQuality()

        g.setFont(new Font("TimesRoman", Font.BOLD, 30));

        if (currentTurn == Player.RED) {
            g.setColor(RED_COLOR);
            g.drawString("Red player's turn", 15, sh - 40);
        }
        if (currentTurn == Player.BLUE) {
            g.setColor(BLUE_COLOR);
            g.drawString("Blue player's turn", 15, sh - 40);
        }


        northBorder.render(g);
        westBorder.render(g);

        gameTileset.renderTileset(g);

        eastBorder.render(g);
        southBorder.render(g);
    }

    @Override
    public void update() {

    }

    @Override
    public void handleMouseDown(int x, int y, GFMouseButton button) {
        GameTile gameTile = (GameTile) gameTileset.pixelToTile(x, y);
        if (gameTile != null) {
            if (gameTile.changeState(currentTurn)) {
                if (gameTileset.checkWinningCondition(currentTurn))
                    System.out.println("GRAIN OF SANDDD");

                if (currentTurn == Player.RED)
                    currentTurn = Player.BLUE;
                else
                    currentTurn = Player.RED;

            }
        }
    }

    @Override
    public void handleMouseUp(int x, int y, GFMouseButton button) {

    }

    @Override
    public void handleMouseMove(int x, int y) {
        if (gameTileset.doneEntranceAnimation) {
            gameTileset.removeNudges();
            GameTile gameTile = (GameTile) gameTileset.pixelToTile(x, y);

            if (gameTile != null)
                gameTile.nudgeTile();
        }
    }

    @Override
    public void handleKeyDown(int keyCode) {

    }

    @Override
    public void handleKeyUp(int keyCode) {

    }
}
