package com.exam;

import java.io.IOException;
import java.nio.file.Files;

//pwd — вывести полный путь до текущей директории
public class Pwd extends Command{
    @Override
    public void run(String... args) {
        try {
            if (Files.exists(ConsolePath.getPath())) {
                System.out.println(ConsolePath.getPath().toString());
            } else {
                System.out.println(ConsolePath.getPath().toString() + " - не существует");
            }
        } catch (IOException e) {
            System.out.println("Откзано в доступе...");
        }
        setReady(true);
    }
}
