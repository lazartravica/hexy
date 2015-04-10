package states.game;

import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;
import rafgfxlib.Util;

import java.awt.*;

public class Game extends GameState {

    public enum Turn {
        RED_PLAYER,
        BLUE_PLAYER
    }

    public Turn currentTurn = Turn.RED_PLAYER;

    public GameTileset gameTileset;

    public static final Color RED_COLOR = new Color(233, 123, 51);
    public static final Color BLUE_COLOR = new Color(139, 255, 255);

    public Game(GameHost host) {
        super(host);

        gameTileset = new GameTileset();
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

        g.setColor(RED_COLOR);
        if (currentTurn == Turn.BLUE_PLAYER)
            g.setColor(BLUE_COLOR);

        g.setFont(new Font("TimesRoman", Font.BOLD, 30));

        g.drawString("Red player's turn", 15, sh - 40);
        gameTileset.renderTileset(g);
    }

    @Override
    public void update() {

    }

    @Override
    public void handleMouseDown(int x, int y, GFMouseButton button) {

    }

    @Override
    public void handleMouseUp(int x, int y, GFMouseButton button) {

    }

    @Override
    public void handleMouseMove(int x, int y) {
        gameTileset.removeNudges();
        GameTile gameTile = (GameTile) gameTileset.getTilePosition(x, y);

        if (gameTile != null)
            gameTile.nudgeTile();
    }

    @Override
    public void handleKeyDown(int keyCode) {

    }

    @Override
    public void handleKeyUp(int keyCode) {

    }
}
