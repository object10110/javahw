package com.exam;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//cd «путь» — перейти в директорию, путь к которой задан первым аргументом
public class Cd extends Command {
    public void run(String... args) {
        if (args.length > 0) {
            Path newPath;
            try {
                //если указан полный путь или slash-ы то создаем новый Path
                if (args[0].contains(":" + File.separator)
                        || args[0].equals("/")
                        || args[0].equals("\\")) {
                    newPath = Paths.get(args[0]);
                } else {
                    newPath = Paths.get(ConsolePath.getPath().toString(), args[0]);
                }
                if (!Files.exists(newPath)) {
                    System.out.println(args[0] + " - не существует");
                } else {
                    ConsolePath.setPath(newPath);
                }
            }catch (Exception c) {
                System.out.println("Откзано в доступе...");
            }
        }
        setReady(true);
    }
    @Override
    public String toString() {
        try {
            return "cd "+ ConsolePath.getPath();
        } catch (IOException e) {
            return "cd";
        }
    }
}
