package ru.itis.aleynik.task2.app;

import ru.itis.aleynik.task2.client.Client;
import ru.itis.aleynik.task2.student.Student;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientApp {
    public static void main(String[] args) {
        try {
            Client client = new Client(InetAddress.getByName("localhost"), 6789);
            client.connect();

            Student student = new Student("Anna", 'f', "09/07/2001", "11-903");
            client.sendStudent(student);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
