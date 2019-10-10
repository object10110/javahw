package com.exam;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//cat «имя_файла» - выводит содержимое текстового файла «имя_файла»
public class Cat extends Command {
    private static final int BUFFER_LENGTH = 256;
    private String[] args;

    @Override
    public void run(String... args) {
        try {
            Path path = Paths.get(ConsolePath.getPath() + File.separator + args[0]);
            if (!Files.exists(path)) {
                System.out.println(args[0] + " - не существует");
            } else {
                this.args = args;
                StringBuilder builder = new StringBuilder();
                try (FileReader reader = new FileReader(path.toString())) {
                    char[] buff = new char[BUFFER_LENGTH];
                    int len;
                    while ((len = reader.read(buff)) != -1) {
                        builder.append(buff, 0, len);
                    }
                    System.out.println(builder.toString());
                } catch (Exception ex) {
                    System.out.println("Ошибка при попытке прочитать файл...");
                }
            }
        } catch (IOException e) {
            System.out.println("Не корректный путь...");
        }
        setReady(true);
    }

    @Override
    public String toString() {
        return "cat " + args[0];
    }
}
