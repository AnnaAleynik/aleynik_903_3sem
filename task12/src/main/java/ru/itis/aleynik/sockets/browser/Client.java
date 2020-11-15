package ru.itis.aleynik.sockets.browser;

import java.io.*;
import java.net.Socket;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) throws Throwable {
        URI uri = new URI("http://study.istamendil.info/");
        System.out.println("Starting client...");
        Socket s = new Socket(uri.getHost(), 80);
        BufferedWriter outReq = new BufferedWriter(new OutputStreamWriter(s.getOutputStream(), StandardCharsets.UTF_8));
        System.out.println("Starting sending random bytes");
        Thread.sleep(1000);
        String req = "GET / HTTP/1.1 \n" + "Host: " + uri.getHost() + "\n\n";
        outReq.write(req);
        System.out.println(">> " + req);
        outReq.flush();
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream(), StandardCharsets.UTF_8));
        File f = new File("data/Something.txt");
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(f), StandardCharsets.UTF_8));
        String line;
        int i = 0;
        while ((line = in.readLine()) != null) {
            out.write(line);
            out.write("\n");
            out.flush();
        }
        out.close();
    }
}
