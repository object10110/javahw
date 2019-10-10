package com.exam;

import java.io.File;
import java.io.IOException;

//dir — выводит список файлов в текущей директории
public class Dir extends Command {
    @Override
    public void run(String... args) {
        File[] files = new File[0];
        try {
            files = new File(ConsolePath.getPath().toString()).listFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                }
            }
        } else {
            System.out.println("Список файлов пуст...");
        }
        setReady(true);
    }

    @Override
    public String toString() {
        return "dir";
    }
}
