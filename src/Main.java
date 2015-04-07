import rafgfxlib.GameHost;
import states.Menu;

public class Main {
    public static void main(String[] args) {
        GameHost host = new GameHost("Hexy", 1100, 700);
        new Menu(host);
        host.setState("menu");
    }
}
