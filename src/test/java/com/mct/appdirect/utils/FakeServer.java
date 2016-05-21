package com.mct.appdirect.utils;

import com.vtence.molecule.Server;

import java.io.IOException;

public class FakeServer {

    private final Server server;

    public FakeServer(Server server) {
        this.server = server;
    }

    public String getUrl() {
        return "http://" + server.host() + ":" + server.port();
    }

    public void shutdown() throws IOException {
        server.shutdown();
    }
}
