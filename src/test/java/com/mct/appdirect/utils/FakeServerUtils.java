package com.mct.appdirect.utils;

import com.vtence.molecule.Request;
import com.vtence.molecule.Server;
import com.vtence.molecule.WebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.vtence.molecule.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public final class FakeServerUtils {

    private static final Logger logger = LoggerFactory.getLogger(FakeServerUtils.class);

    private FakeServerUtils() {
    }

    public static FakeServer startFakeOAuthServerWithJsonResponse(Object currentClass, String jsonLocation) throws Exception {
        String json = FileUtils.readFileFromRelativePath(currentClass, jsonLocation);
        Server server = WebServer.create().start(((request, response) -> {
            if (isOAuth(request)) {
                response.addHeader("Content-Type", "application/json").done(json);
            } else {
                response.status(UNAUTHORIZED).done();
            }
        }));
        return new FakeServer(server);
    }

    private static boolean isOAuth(Request request) {
        String authorization = request.header(AUTHORIZATION);
        return authorization != null
                && authorization.startsWith("OAuth");
    }

    public static FakeServer startFakeServerThatNeverRespond() throws Exception {
        Server server = WebServer.create().start(((request, response) -> logger.info("I've received the request but I will never answer you !")));
        return new FakeServer(server);
    }

}
