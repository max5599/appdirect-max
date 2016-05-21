package com.mct.appdirect.utils;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public final class FileUtils {

    private FileUtils() {}

    public static String readFileFromRelativePath(Object currentClass, String relativePath) throws Exception {
        URL url = currentClass.getClass().getResource(relativePath);
        Path path = Paths.get(url.toURI());
        return Files.readAllLines(path).stream().collect(Collectors.joining());
    }

}
