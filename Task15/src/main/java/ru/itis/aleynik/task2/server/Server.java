package ru.itis.aleynik.task2.server;

import ru.itis.aleynik.task2.student.Student;
import ru.itis.aleynik.task2.student.StudentReader;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Server {

    protected int port;
    protected boolean started;
    protected ServerSocket server;
    protected List<SocketChannel> channels;

    public Server(int port) {
        this.port = port;
        started = false;
        this.channels = new ArrayList<>();
    }


    public void start() throws IOException {
        server = new ServerSocket(this.port);
        started = true;
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();

        ssc.bind(new InetSocketAddress("localhost", port));
        ssc.configureBlocking(false);
        int ops = SelectionKey.OP_ACCEPT;
        ssc.register(selector, ops);

        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {

                SelectionKey key = iter.next();


                if (key.isAcceptable()) { //(key.readyOps() & SelectionKey.OP_ACCEPT) != 0
                    SocketChannel channel = ssc.accept();
                    channels.add(channel);
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);
                }

                if (key.isReadable()) { //(key.readyOps() & SelectionKey.OP_READ) != 0
                    SocketChannel channel = (SocketChannel) key.channel();
                    handleConnection(channel);

                }
                iter.remove();
            }
        }
    }

    protected void handleConnection(SocketChannel channel) {
        try {
            Student student = StudentReader.read(channel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
