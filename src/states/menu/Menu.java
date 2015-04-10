package states.menu;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;
import rafgfxlib.Util;

public class Menu extends GameState {

    public static final Color BACKGROUND_COLOR = new Color(227, 239, 255);
    public static final Color TEXT_COLOR = new Color(176, 128, 82);

    private MenuTileset menuTileset;

    private String[] menuItems;
    private int selectedItem = -1;
    private int queuedAction = -1;

    public Menu(GameHost host) {
        super(host);

        menuTileset = new MenuTileset();

        menuItems = new String[]{
                "New game",
                "Rules",
                "Exit",
        };
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

        // Draw the options
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //@TODO Turn on AA in Gamehost.java setHighQuality()
        g.setColor(TEXT_COLOR);
        g.setFont(new Font("TimesRoman", Font.BOLD, 30));

        BufferedImage itemUnselected = Util.loadImage("hexyAssets/menuItems/unselected.png");

        for (int i = 0; i < menuItems.length; i++) {
            BufferedImage itemImage = itemUnselected;
            if (selectedItem == i)
                itemImage = Util.loadImage("hexyAssets/menuItems/" + menuItems[i] + ".png");

            g.drawString(menuItems[i], 80, 500 + i * 80);
            g.drawImage(itemImage, 10, 455 + i * 80, null);
        }

        // Draw the tileset


        menuTileset.renderTileset(g);
        //  g.drawPolygon(menuTileset.giveHex(selectedItem, selectedItem));
        //  menuTileset.giveHex(selectedItem, selectedItem, g);

    }

    @Override
    public void update() {
        switch(queuedAction) {
            case 0:
                if(!menuTileset.animationInProgress)
                    host.setState("game");
                break;
            case 1:
                if(!menuTileset.animationInProgress)
                    System.exit(0);
                break;
            case 2:
                if(!menuTileset.animationInProgress)
                    System.exit(0);
                break;
        }
    }

    @Override
    public void handleMouseDown(int x, int y, GFMouseButton button) {
        handleMenuItemSelection();
    }

    @Override
    public void handleMouseUp(int x, int y, GFMouseButton button) {

    }

    @Override
    public void handleMouseMove(int x, int y) {
        if (x > 8 && x < 245 && y > 460) {
            selectedItem = (y - 460) / 70;
        } else {
            selectedItem = -1;
        }
    }

    @Override
    public void handleKeyDown(int keyCode) {
        /*
        Key Codes
        ---------------
        Arrow Up:   38
        Arrow Down: 40
        Return:     10
         */

        switch (keyCode) {
            case 38:
                selectedItem--;
                break;
            case 40:
                selectedItem++;
                break;
            case 10:
                handleMenuItemSelection();
        }

        if (selectedItem < 0) selectedItem = menuItems.length - 1;
        if (selectedItem > menuItems.length - 1) selectedItem = 0;
    }

    @Override
    public void handleKeyUp(int keyCode) {

    }

    private void handleMenuItemSelection() {
        switch (selectedItem) {
            case 0: // New game
                menuTileset.startExitAnimation();
                break;
            case 1: // Rules
                menuTileset.startExitAnimation();
                break;
            case 2: // Exit
                menuTileset.startExitAnimation();
                break;
        }

        queuedAction = selectedItem;
    }
}
