package com.exam;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
//find выполняет поиск файла в файловой системе
public class Find extends Command {
    private String[] args;
    @Override
    public void run(String... args) {
        if(args.length>0) {
            String name = args[0];
            this.args = args;
            name = name.replace("?", ".");
            name = name.replace("*", ".*");
            for (File f : File.listRoots()) {
                try {
                    String finalName = name;
                    Files.walkFileTree(f.toPath(), new FileVisitor<Path>() {
                        @Override
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                            return FileVisitResult.CONTINUE;
                        }
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            try {
                                if (file.getFileName().toString().matches(finalName.toLowerCase())) {
                                    System.out.println("Найдено: " + file.toAbsolutePath());
                                }
                                return FileVisitResult.CONTINUE;
                            } catch (Exception ex) {
                                return FileVisitResult.CONTINUE;
                            }
                        }
                        @Override
                        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                            return FileVisitResult.CONTINUE;
                        }
                        @Override
                        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                            return FileVisitResult.CONTINUE;
                        }
                    });
                } catch (IOException e) {
                    System.out.println("Ошибка при выполнении поиска...");
                }
            }
        }
        setReady(true);
    }

    @Override
    public String toString() {
        return "find "+ args[0];
    }
}
