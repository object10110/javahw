package com.exam;

import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//https://github.com/javalesson2019/exam_java/blob/master/README.md
//settings.properties
//cs=org.itstep.changeDIr
//dir=org.itstep.showDir

public class Main {
    private static final String SETTINGS_PATH = "settings.properties";

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Command> backgroundCommands = new ArrayList<>();
        //записываем консольную комманду
        String consoleCommand = null;
        System.out.println("Используйте комманду help, для получения подробной информации.");
        do {
            try {
                System.out.print(ConsolePath.getPath() + ">");
            } catch (IOException e) {}
            consoleCommand = scanner.nextLine();
            String[] commands = consoleCommand.split(" ");
            if (consoleCommand.endsWith("&")) {
                commands[commands.length - 1] = replaceLast(commands[commands.length - 1], "&", "");
            }
            ArrayList<String> argsList = new ArrayList<>(Arrays.asList(commands).subList(1, commands.length));
            Command selectCommand = null;
            selectCommand = getCommandByName(commands[0]);
            if (selectCommand != null) {
                //отдельная реализация для jobs, так как нужно устанавливать
                // параметры для комманды не с консоли, а рантайм
                if (commands[0].equalsIgnoreCase("jobs")) {
                    ArrayList<String> jobsArgs = new ArrayList<>();
                    for (Command c : backgroundCommands) {
                        if (!c.isReady()) {
                            jobsArgs.add(c.toString());
                        }
                    }
                    selectCommand.run(jobsArgs.toArray(String[]::new));
                }else {
                    //если указан параметр & - то запускаем выполнение в отдельном потоке
                    if (consoleCommand.endsWith("&")) {
                        backgroundCommands.add(selectCommand);
                        Command finalSelectCommand = selectCommand;
                        service.submit(() -> {
                            finalSelectCommand.run(argsList.toArray(String[]::new));
                        });
                    } else {
                        selectCommand.run(argsList.toArray(String[]::new));
                    }
                }
                clearCompletedCommands(backgroundCommands);
            } else {
                System.out.println("\"" + commands[0] + "\"" + " не является внутренней или внешней\n" +
                        "командой, исполняемой программой или пакетным файлом.");
            }
        } while (!consoleCommand.equalsIgnoreCase("exit"));
        service.shutdown();
    }


    private static void clearCompletedCommands(ArrayList<Command> backgroundCommands) {
        ArrayList<Command> completed = new ArrayList<>();
        for (Command c : backgroundCommands) {
            if (c.isReady()) {
                completed.add(c);
            }
        }
        backgroundCommands.removeAll(completed);
    }

    private static Command getCommandByName(String commandName) {
        try (FileReader reader = new FileReader(SETTINGS_PATH);
             BufferedReader bReader = new BufferedReader(reader)) {
            String line;
            do {
                line = bReader.readLine();
                if (line != null
                        && line.toLowerCase()
                        .startsWith(commandName)) {
                    String pathToCommandClass = line.substring(line.indexOf("=") + 1);
                    try {
                        Command command = (Command) Class.forName(pathToCommandClass).getDeclaredConstructor().newInstance();
                        return command;
                    } catch (Exception ex) {
                        return null;
                    }
                }
            } while (line != null);
        } catch (Exception ex) {
        }
        return null;
    }

    private static String replaceLast(String string, String substring, String replacement) {
        int index = string.lastIndexOf(substring);
        if (index == -1)
            return string;
        return string.substring(0, index) + replacement
                + string.substring(index + substring.length());
    }
}
