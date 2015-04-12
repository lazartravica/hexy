import rafgfxlib.GameHost;
import states.game.Game;
import states.menu.Menu;

public class Main {
    public static void main(String[] args) {
        GameHost host = new GameHost("Hexy", 1100, 700);
        host.setBackgroundClearColor(Menu.BACKGROUND_COLOR);
        new Menu(host);
        new Game(host);
        host.setState("menu");
    }
}
