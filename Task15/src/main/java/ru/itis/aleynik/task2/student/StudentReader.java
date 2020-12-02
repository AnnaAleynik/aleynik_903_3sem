package ru.itis.aleynik.task2.student;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ScatteringByteChannel;
import java.util.Arrays;

public class StudentReader {

//  [studLength(4) studName(4) name sex(1) birthDate(20) group(12)]

    public static Student read(ScatteringByteChannel fc) throws IOException {
        ByteBuffer buffer = null;
//        FileChannel fc = file.getChannel();

        buffer = ByteBuffer.allocate(6);

        StringBuilder sb = new StringBuilder();
        fc.read(buffer);
        buffer.flip();
        while(buffer.hasRemaining()){
            int n = buffer.get();
            sb.append(Character.toChars(n));
        }
        System.out.println(sb.toString());
        int capacity = Integer.parseInt(sb.toString());
        buffer = ByteBuffer.allocate(capacity);
        System.out.println(buffer.capacity());

        buffer = ByteBuffer.allocate(capacity); //studentLength
        fc.read(buffer);
        buffer.flip();
        String[] data = new String(buffer.array()).trim().split(", ");
        System.out.println(Arrays.toString(data));

//        return getStudent(data);

        return new Student(
                data[0].trim(),
                data[1].trim().charAt(0),
                data[2].trim(),
                data[3].trim()
        );
//        return new Student(name, gen, birthDate, group);
    }

}
