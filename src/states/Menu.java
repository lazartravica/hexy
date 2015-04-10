package states;
import java.awt.*;
import java.awt.image.BufferedImage;

import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;
import rafgfxlib.Util;

public class Menu extends GameState {

    public static final Color BACKGROUND_COLOR = new Color(227, 239, 255);
    private static final Color TEXT_COLOR = new Color(176, 128, 82);

    private MenuTileset menuTileset;

    private String[] menuItems;
    private int selectedItem = -1;

    public Menu(GameHost host) {
        super(host);

        menuTileset = new MenuTileset(host.getHeight());

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

        for(int i = 0; i < menuItems.length; i++) {
            BufferedImage itemImage = itemUnselected;
            if(selectedItem == i)
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
    }

    @Override
    public void handleMouseDown(int x, int y, GFMouseButton button) {
        handleMenuItemSelection();
      System.out.println(menuTileset.getTilePosition(new Point(x,y)));
      
      
      Point ij =menuTileset.getTilePosition(new Point(x,y)); 
      System.out.println(ij);

      
      MenuTile clickedTile = (MenuTile) menuTileset.getTileFromPosition(ij.x, ij.y);
      
      if(clickedTile != null) {
      int[][] neigbours = menuTileset.neighbours(clickedTile);
      String s = "";
      for(int i=0;i<6;i++){
    	  for(int j = 0; j<2;j++)
    		  s+=neigbours[i][j]+ " ";
    	  System.out.println(s);
    	  s="";
    	 
         }
      }
    }

    @Override
    public void handleMouseUp(int x, int y, GFMouseButton button) {

    }

    @Override
    public void handleMouseMove(int x, int y) {
        if(x > 8 && x < 245 && y > 460) {
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

        switch(keyCode) {
            case 38:
                selectedItem--;
                break;
            case 40:
                selectedItem++;
                break;
            case 10:
                handleMenuItemSelection();
        }

        if(selectedItem < 0) selectedItem = menuItems.length - 1;
        if(selectedItem > menuItems.length - 1) selectedItem = 0;
    }

    @Override
    public void handleKeyUp(int keyCode) {

    }

    private void handleMenuItemSelection() {
        switch(selectedItem) {
            case 0: // New game
                System.out.println("We should now change the gamestate");
                break;
            case 1: // Rules
                System.out.println("Something fancy should happen now.");
                break;
            case 2: // Exit
                System.exit(0);
                break;
        }
    }
}
