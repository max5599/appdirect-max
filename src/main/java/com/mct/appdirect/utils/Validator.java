package com.mct.appdirect.utils;

@FunctionalInterface
public interface Validator<T> {
    boolean isInvalid(T url);
}
