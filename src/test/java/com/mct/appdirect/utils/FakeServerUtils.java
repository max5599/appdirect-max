package com.mct.appdirect.utils;

import com.vtence.molecule.Server;
import com.vtence.molecule.WebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FakeServerUtils {

    private static final Logger logger = LoggerFactory.getLogger(FakeServerUtils.class);

    private FakeServerUtils() {
    }

    public static FakeServer startFakeServerWithJsonResponse(Object currentClass, String jsonLocation) throws Exception {
        String json = FileUtils.readFileFromRelativePath(currentClass, jsonLocation);
        Server server = WebServer.create().start(((request, response) -> response.addHeader("Content-Type", "application/json").done(json)));
        return new FakeServer(server);
    }

    public static FakeServer startFakeServerThatNeverRespond() throws Exception {
        Server server = WebServer.create().start(((request, response) -> logger.info("I've received the request but I will never answer you !")));
        return new FakeServer(server);
    }

}
