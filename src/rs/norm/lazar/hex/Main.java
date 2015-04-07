package rs.norm.lazar.hex;

import rafgfxlib.GameHost;
import rs.norm.lazar.hex.states.loading.LoadingState;
import rs.norm.lazar.hex.states.menu.MenuState;

public class Main {
    public static void main(String[] args) {
        GameHost host = new GameHost("Hexy", 1100, 700);
        new LoadingState(host);
        host.setState("loading");

        new MenuState(host);
        host.setState("menu");
    }
}
