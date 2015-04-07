package rs.norm.lazar.hex.states.loading;

import rafgfxlib.GameHost;
import rafgfxlib.GameState;

import java.awt.*;

public class LoadingState extends GameState {

    // Mozda prebaciti boje u neki enum za resurse
    private static final Color BACKGROUND_COLOR = new Color(227, 239, 255);
    private static final Color TEXT_COLOR = new Color(176, 128, 82);

    public LoadingState(GameHost host) {
        super(host);
    }

    @Override
    public boolean handleWindowClose() {
        return false;
    }

    @Override
    public String getName() {
        return "loading";
    }

    @Override
    public void resumeState() {
    }

    @Override
    public void suspendState() {
    }

    @Override
    public void render(Graphics2D g, int screenWidth, int screenHeight) {
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, screenWidth, screenHeight);
    }

    @Override
    public void update() {

    }

    @Override
    public void handleMouseDown(int x, int y, GameHost.GFMouseButton button) {

    }

    @Override
    public void handleMouseUp(int x, int y, GameHost.GFMouseButton button) {

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
