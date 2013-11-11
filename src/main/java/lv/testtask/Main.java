package lv.testtask;

import lv.testtask.server.EmbeddedJetty;

public class Main {

    public static void main(String[] args) {

        EmbeddedJetty embeddedJetty = new EmbeddedJetty();
        embeddedJetty.startServer();
    }
}
