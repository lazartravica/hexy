import rafgfxlib.GameHost;

public class Main {
    public static void main(String[] args) {
        GameHost host = new GameHost("RAF Game", 800, 600);
        new Menu(host);
        host.setState("menu");
    }
}
