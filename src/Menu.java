import java.awt.*;

import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;

public class Menu extends GameState {

    private static final Color BACKGROUND_COLOR = new Color(227, 239, 255);

    private MenuTileset menuTileset;

    public Menu(GameHost host) {
        super(host);

        menuTileset = new MenuTileset();
    }

    @Override
    public boolean handleWindowClose() {
        return true;
    }

    @Override
    public String getName() {
        return "menu";
    }

    @Override
    public void resumeState() {

    }

    @Override
    public void suspendState() {
    }

    public void render(Graphics2D g, int sw, int sh) {
        // First fill the background with a sky blue.
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, sw, sh);

        // Draw the name of the game in the bottom left corner.
        g.setColor(Color.DARK_GRAY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //@TODO Turn on AA in Gamehost.java setHighQuality()
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString("Hexy", 5, sh - 15);

        menuTileset.renderTileset(g);
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

    }

    @Override
    public void handleKeyDown(int keyCode) {

    }

    @Override
    public void handleKeyUp(int keyCode) {

    }
}
