package com.mct.appdirect.utils;

import com.vtence.molecule.Server;
import com.vtence.molecule.WebServer;

public final class FakeServerUtils {

    private FakeServerUtils() {}

    public static FakeServer startFakeServerWithJsonResponse(Object currentClass, String jsonLocation) throws Exception {
        String json = FileUtils.readFileFromRelativePath(currentClass, jsonLocation);
        Server server = WebServer.create().start(((request, response) -> response.addHeader("Content-Type", "application/json").done(json)));
        return new FakeServer(server);
    }

}
