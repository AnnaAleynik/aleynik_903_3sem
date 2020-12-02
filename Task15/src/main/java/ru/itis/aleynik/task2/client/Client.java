package ru.itis.aleynik.task2.client;

import ru.itis.aleynik.task2.student.Student;
import ru.itis.aleynik.task2.student.StudentWriter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;

public class Client {
    protected InetAddress address;
    protected int port;
    protected Socket socket;
    protected SocketChannel channel;

    public Client(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }


    public void connect() throws IOException {
        channel = SocketChannel.open(new InetSocketAddress(address, port));
    }

    public void sendStudent(Student student) throws IOException {
        StudentWriter.write(student, channel);
    }
}
