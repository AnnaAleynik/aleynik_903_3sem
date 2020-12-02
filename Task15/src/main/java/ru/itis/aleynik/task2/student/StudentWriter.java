package ru.itis.aleynik.task2.student;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;

public class StudentWriter {

    public static void write(Student student, GatheringByteChannel channel) throws IOException {
        ByteBuffer buf;

        String data = student.toString();
        StringBuffer sb = new StringBuffer();
        String capacity = "000000";
        String studentLength = Integer.toString(data.getBytes().length);
        System.out.println(studentLength);
        for (int i = 0; i < capacity.length() - studentLength.length(); i++) {
            sb.append("0");
        }
        sb.append(studentLength);
        capacity = sb.toString();

        buf = ByteBuffer.wrap(capacity.getBytes());
        channel.write(buf);

        buf = ByteBuffer.wrap(data.getBytes());
        channel.write(buf);

    }
}
