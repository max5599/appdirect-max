package com.mct.appdirect.utils;

@FunctionalInterface
public interface Validator<T> {
    boolean isValid(T url);
}
