package com.exam;
//jobs выводит список задач, которые выполняются в фоне
public class Jobs extends Command{
    @Override
    public void run(String... args) {
        for (String s:args) {
            System.out.println("Работает комманда: " + s);
        }
        setReady(true);
    }
}
