package ru.itis.aleynik.task2.app;

import ru.itis.aleynik.task2.student.Student;
import ru.itis.aleynik.task2.student.StudentReader;
import ru.itis.aleynik.task2.student.StudentWriter;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

public class App {

    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("src/main/java/ru/itis/aleynik/task2/data/dataFrom.txt", "rw");
        RandomAccessFile toFile = new RandomAccessFile("src/main/java/ru/itis/aleynik/task2/data/data.txt", "rw");

        Student st = StudentReader.read(aFile.getChannel());
//        test();
        System.out.println(st);
//        System.out.println();
//        Student st = new Student("Anna", 'f', "09/07/2001", "11-903");
//        StudentWriter.write(st, toFile.getChannel());
    }

    private static void test() {
        // Declaring the capacity of the ByteBuffer
        int capacity = 12;

        // Creating the ByteBuffer
        try {

            // creating object of ByteBuffer
            // and allocating size capacity
            ByteBuffer bb = ByteBuffer.allocate(capacity);

            // putting the int value in the bytebuffer
            bb.asIntBuffer()
                    .put(10)
                    .put(20)
                    .put(30);

            // rewind the Bytebuffer
            bb.rewind();

            // print the ByteBuffer
            System.out.println("Original ByteBuffer: ");
            for (int i = 1; i <= capacity / 4; i++)
                System.out.print(bb.getInt() + " ");

            // rewind the Bytebuffer
            bb.rewind();

            // Reads the Int at this buffer's current position
            // using getInt() method
            int value = bb.getInt();

            // print the int value
            System.out.println("\n\nByte Value: " + value);

            // Reads the int at this buffer's next position
            // using getInt() method
            int value1 = bb.getInt();

            // print the int value
            System.out.println("Next Byte Value: " + value1);
        } catch (BufferUnderflowException e) {

            System.out.println("\nException Thrown : " + e);
        }


    }
}
