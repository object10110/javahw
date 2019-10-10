package com.exam;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

class ConsolePath {
    private static Path path;
    public static Path getPath() throws IOException {
        if (path == null) {
            path = Paths.get(".").toRealPath();
        }
        return path;
    }
    public static void setPath(Path newPath) {
        path = newPath.normalize().toAbsolutePath();
    }
}
