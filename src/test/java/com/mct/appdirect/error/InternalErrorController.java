package com.mct.appdirect.error;

import org.eclipse.jetty.io.RuntimeIOException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InternalErrorController {

    @RequestMapping("/exception/internal-error")
    public String throwRuntimeException() {
        throw new RuntimeIOException("internal error");
    }

    @RequestMapping("/exception/invalid-event")
    public String throwInvalidEventException() {
        throw new InvalidEventException("Invalid event", new Exception("invalid"));
    }

    @RequestMapping("/exception/transport-error")
    public String throwTransportException() {
        throw new TransportException("Transport Exception", new Exception("Timeout"));
    }

    @RequestMapping("/exception/illegal-argument")
    public String throwIllegalArgumentException() {
        throw new IllegalArgumentException("Illegal argument");
    }

}
