package ru.itis.aleynik.sockets.colors;

import ru.itis.aleynik.sockets.Protocol;

import java.awt.*;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Client {
    public static void main(String[] args) throws Throwable {
        System.out.println("Starting client...");
        Socket s = new Socket(InetAddress.getLocalHost(), Protocol.PORT);
        OutputStream out = s.getOutputStream();
        System.out.println("Starting sending random colors");
        Scanner in = new Scanner(System.in);
        int[] colors;
        String colorIn;
        while(true){
            Thread.sleep(1000);
//            int r = (new Random()).nextInt(255);
//            int g = (new Random()).nextInt(255);
//            int b = (new Random()).nextInt(255);
            colorIn = in.nextLine();
            try {
                colors = Arrays.stream(colorIn.split(","))
                        .map(String::trim).mapToInt(Integer::parseInt).toArray();
                if (colors.length < 3) {
                    System.out.println("You should write three values!");
                } else {
                    if (Arrays.stream(colors).anyMatch(c -> (c > 255 || c < 0))) {
                        System.out.println("RGB are in range [0, 255]!");
                    } else {
                        Color c = new Color(colors[0], colors[1], colors[2]);
                        ByteBuffer buf = ByteBuffer.allocate(12);
                        buf.putInt(colors[0]).putInt(colors[1]).putInt(colors[2]);
                        System.out.println(">> " + c);
                        System.out.println(Arrays.toString(buf.array()));
                        out.write(buf.array());
                        out.flush();
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage() + " I can't find any color((");
            }
        }
    }
}
