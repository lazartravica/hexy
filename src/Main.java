import rafgfxlib.GameHost;
import states.menu.Menu;

public class Main {
    public static void main(String[] args) {
        GameHost host = new GameHost("Hexy", 1100, 700);
        host.setBackgroundClearColor(Menu.BACKGROUND_COLOR);
        new Menu(host);
        host.setState("menu");
    }
}
