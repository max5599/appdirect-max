package com.mct.appdirect.utils;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public final class StringUtils {

    private StringUtils() {}


    public static Optional<Integer> isNumeric(String str) {
        try {
            Integer number = Integer.parseInt(str);
            return of(number);
        } catch (Exception e) {
            return empty();
        }
    }
}
