package states.game;

import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;
import rafgfxlib.Util;
import states.menu.*;

import java.awt.*;
import java.awt.Menu;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class Game extends GameState {

    public Player currentTurn = Player.RED;

    public GameTileset gameTileset;

    public static final Color RED_COLOR = new Color(233, 123, 51);
    public static final Color BLUE_COLOR = new Color(101, 181, 255);

    private int currentPlayerOffsetX = -400;
    private int currentPlayerDesiredOffsetX = 0;
    private int currentPlayerDeltaX = 40;

    private EdgeBorder eastBorder;
    private EdgeBorder westBorder;
    private EdgeBorder northBorder;
    private EdgeBorder southBorder;

    private BufferedImage blurredScreenshot;

    private Player playerWon = Player.NONE;

    private Component window;

    private int fadeOutAlpha = 0;

    public Game(GameHost host) {
        super(host);
        this.window = host.getWindow();
        gameTileset = new GameTileset();

        int screenWidth = host.getWidth();
        int screenHeight = host.getHeight();

        westBorder = new EdgeBorder(Edge.WEST, -40, 80);
        eastBorder = new EdgeBorder(Edge.EAST, 25, 80);
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
        if (playerWon == Player.NONE || blurredScreenshot == null) {
            // Move the current player string on the X-axis if necessary
            if (currentPlayerOffsetX > currentPlayerDesiredOffsetX)
                currentPlayerOffsetX -= currentPlayerDeltaX;
            if (currentPlayerOffsetX < currentPlayerDesiredOffsetX)
                currentPlayerOffsetX += currentPlayerDeltaX;

            // Draw which player's turn it is
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //@TODO Turn on AA in Gamehost.java setHighQuality()

            g.setFont(new Font("TimesRoman", Font.BOLD, 30));

            if (currentTurn == Player.RED) {
                g.setColor(RED_COLOR);
                g.drawString("Red player's turn", 15 + currentPlayerOffsetX, sh - 40);
            }
            if (currentTurn == Player.BLUE) {
                g.setColor(BLUE_COLOR);
                g.drawString("Blue player's turn", 15 + currentPlayerOffsetX, sh - 40);
            }


            northBorder.render(g);
            eastBorder.render(g);

            gameTileset.renderTileset(g);

            westBorder.render(g);
            southBorder.render(g);
        } else {
            if (fadeOutAlpha == 252)
                host.exit();

            g.drawImage(blurredScreenshot, 0, 0, null);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setFont(new Font("TimesRoman", Font.BOLD, 72));
            g.setColor(Color.white);

            String message = "Red player won!";
            if (playerWon == Player.BLUE)
                message = "Blue player won!";
            g.drawString(message, 280, 400);

            fadeOutAlpha += 2;
            g.setColor(new Color(states.menu.Menu.BACKGROUND_COLOR.getRed(), states.menu.Menu.BACKGROUND_COLOR.getBlue(), states.menu.Menu.BACKGROUND_COLOR.getGreen(), fadeOutAlpha));
            g.fillRect(0, 0, sw, sh);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void handleMouseDown(int x, int y, GFMouseButton button) {
        GameTile gameTile = (GameTile) gameTileset.pixelToTile(x, y);
        if (gameTile != null) {
            if (gameTile.changeState(currentTurn)) {
                if (gameTileset.checkWinningCondition(currentTurn)) {
                    handleVictory();
                }

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

    public void handleVictory() {
        if (currentTurn == Player.RED)
            eastBorder.setAnimate(true);
        else if (currentTurn == Player.BLUE)
            southBorder.setAnimate(true);
        playerWon = currentTurn;
        currentPlayerDesiredOffsetX = -400;
        blurredScreenshot = getBlurredScreenshot();
    }

    public BufferedImage getBlurredScreenshot() {

        BufferedImage image = new BufferedImage(
                window.getWidth(),
                window.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );

        window.paint(image.getGraphics());

        // Uzimamo raster izvorne slike
        WritableRaster source = image.getRaster();

        // Kreiramo raster u ciljnoj rezoluciji, za rezultujucu sliku
        WritableRaster target = Util.createRaster(source.getWidth(), source.getHeight(), false);

        int rgb[] = new int[3];

        // "Akumulator" u kojem cemo skupljati uzorke
        int accum[] = new int[3];

        // Broj uzoraka, sto je veci, i kvalitet je veci, ali i operacija sporija
        int sampleCount = 50;

        // Centar zumiranja moze biti na proizvoljnoj poziciji, ovdje ga
        // postavljamo na centar slike
        int centerX = source.getWidth() / 2;
        int centerY = source.getHeight() / 2;

        // Jacina zoom-blura, tj. +/- opseg u kom ce se vektori skalirati
        float strength = 0.2f;


        int[] backgroundColor = {
                states.menu.Menu.BACKGROUND_COLOR.getRed(),
                states.menu.Menu.BACKGROUND_COLOR.getBlue(),
                states.menu.Menu.BACKGROUND_COLOR.getGreen(),
        };

        // Petljama prolazimo kroz sve piksele slike
        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                // Akumulator postavljamo na nulu, da mozemo poceti sumiranje
                accum[0] = 0;
                accum[1] = 0;
                accum[2] = 0;

                // Operaciju uzimanja uzorka ponavljamo zadati broj puta
                for (int i = 0; i < sampleCount; i++) {
                    // Trazimo nasumican faktor u opsegu od (1 - strength) do (1 + strength)
                    // kojim cemo pomnoziti vektor svakog piksela
                    float magnitude = 1.0f + ((float) Math.random() - 0.5f) * strength;

                    // Od (x,y) koordinata oduzimamo centar, kako bismo dobili vektor sa
                    // nulom u centru. Tada ga skaliramo odabranim faktorom, kako bi zamucenje
                    // bilo proporcionalno udaljenosti od centra i uvijek po pravcu tog vektora
                    float srcX = centerX + (x - centerX) * magnitude;
                    float srcY = centerY + (y - centerY) * magnitude;

                    // Funkcija bilSample() uzima interpolirani uzorak iz originalne
                    // slike, gdje srcX i srcY koordinate ne moraju biti integeri
                    Util.bilSample(source, srcX, srcY, rgb);

                    // Svaki novi uzeti uzorak dodajemo u akumulator
                    accum[0] += rgb[0];
                    accum[1] += rgb[1];
                    accum[2] += rgb[2];
                }

                // Zanima nas prosjecna boja iz sakupljenih uzoraka
                accum[0] /= sampleCount;
                accum[1] /= sampleCount;
                accum[2] /= sampleCount;

                // Upisujemo boju u ciljni raster
                target.setPixel(x, y, accum);
            }
        }

        return Util.rasterToImage(target);
    }
}
