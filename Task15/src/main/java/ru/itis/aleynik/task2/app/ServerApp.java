package ru.itis.aleynik.task2.app;

import ru.itis.aleynik.task2.server.Server;

import java.io.IOException;

public class ServerApp {
    public static void main(String[] args) {
        try {
            Server server = new Server(11903);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
