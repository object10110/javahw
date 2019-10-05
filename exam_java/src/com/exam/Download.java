package com.exam;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

//download «url» «имя_файла» - загружает файл
public class Download extends Command {
    private final String DIRECTORY_NAME = ".\\downloads";
    private String[] args;

    @Override
    public void run(String... args) {
        int countThreads = 5;
        File directory = new File(String.valueOf(DIRECTORY_NAME));
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            this.args = args;
            //если вторым параметром передано имя то устанавливаем его скачаному файлу, иначе берем с ссылки
            Path pathToFile = download(args[0], args.length > 1 ? args[1] : null);
            if (pathToFile != null) {
                File file = pathToFile.toFile();
                long fileSizeInBytes = file.length();
                long fileSizeInKB = fileSizeInBytes / 1024;
                System.out.println("Файл сохранен по пути: "
                        + pathToFile.toAbsolutePath().normalize().toString()
                        + "(" + fileSizeInKB + " KB)\n");
            }
        } catch (IOException e) {
            System.out.println("Ошибка загрузки...\nПроверьте правильность URL(" + args[0] + ")");
        }
        setReady(true);
    }

    @Override
    public String toString() {
        return "download " + args[0] + " " + (args.length > 1 ? args[1] : "");
    }

    private Path download(String sourceURL, String fileName) throws IOException {
        URL url = new URL(sourceURL);
        if (fileName == null) {
            fileName = sourceURL.substring(sourceURL.lastIndexOf('/') + 1, sourceURL.length());
        }
        if (fileName.length() > 0
                && !fileName.contains(".")) {
            fileName = fileName + "." + sourceURL.substring(sourceURL.lastIndexOf('.') + 1);
        }
        Path targetPath = new File(DIRECTORY_NAME + File.separator + fileName).toPath();
        try (InputStream stream = url.openStream()) {
            Files.copy(stream, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            System.out.println("Не удалось загрузить: " + sourceURL + "\nПроверьте соединение с интернетом...");
            return null;
        }
        return targetPath;
    }
}
