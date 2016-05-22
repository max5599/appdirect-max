package com.mct.appdirect.error;

import org.eclipse.jetty.io.RuntimeIOException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InternalErrorController {

    @RequestMapping("/exception")
    public String throwRuntimeException() {
        throw new RuntimeIOException("internal error");
    }

}
