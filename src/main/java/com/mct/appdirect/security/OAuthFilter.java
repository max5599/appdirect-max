package com.mct.appdirect.security;

import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.http.HttpParameters;
import oauth.signpost.http.HttpRequest;
import oauth.signpost.signature.HmacSha1MessageSigner;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

class OAuthFilter extends OncePerRequestFilter {

    private final OAuthFieldsParser oAuthFieldsParser;
    private final String securityConsumerKey;
    private final String secret;

    OAuthFilter(OAuthFieldsParser oAuthFieldsParser, String securityConsumerKey, String secret) {
        this.oAuthFieldsParser = oAuthFieldsParser;
        this.securityConsumerKey = securityConsumerKey;
        this.secret = secret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = ofNullable(request.getHeader(AUTHORIZATION))
                .orElseThrow(() -> new AccessDeniedException("No authorization header"));

        OAuthFields oAuthFields = oAuthFieldsParser.parse(authorization)
                .orElseThrow(() -> new AccessDeniedException("Oauth fields are invalids"));

        if (!securityConsumerKey.equals(oAuthFields.getConsumerKey())) {
            throw new AccessDeniedException("Oauth consumer key is invalid");
        }

        if(signatureIsInvalid(request, oAuthFields.getSignature()))
            throw new AccessDeniedException("Oauth signature is invalid");

        filterChain.doFilter(request, response);
    }

    private boolean signatureIsInvalid(HttpServletRequest request, String signatureFromHeader) {
        try {
            HttpRequest httpRequest = new HttpServletRequestAdapter(request);
            HttpParameters httpParameters = convertToHttpParameters(request.getParameterMap());

            HmacSha1MessageSigner hmacSha1MessageSigner = new HmacSha1MessageSigner();
            hmacSha1MessageSigner.setConsumerSecret(secret);
            String generatedSignatureFromRequest = hmacSha1MessageSigner.sign(httpRequest, httpParameters);
            return !signatureFromHeader.equals(generatedSignatureFromRequest);
        } catch (OAuthMessageSignerException e) {
            return true;
        }
    }

    private HttpParameters convertToHttpParameters(Map<String, String[]> parameterMap) {
        HttpParameters httpParameters = new HttpParameters();
        Map<String, List<String>> params = parameterMap.entrySet().stream().collect(toMap(Map.Entry::getKey, this::convertToList));
        httpParameters.putMap(params);
        return httpParameters;
    }

    private List<String> convertToList(Map.Entry<String, String[]> entry) {
        return stream(entry.getValue()).collect(toList());
    }
}
