package states.game;

import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;
import rafgfxlib.Util;

import java.awt.*;

public class Game extends GameState {

    public Game(GameHost host) {
       super(host);
    }

    @Override
    public boolean handleWindowClose() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void resumeState() {

    }

    @Override
    public void suspendState() {

    }

    @Override
    public void render(Graphics2D g, int sw, int sh) {

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
