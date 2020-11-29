package ru.itis.aleynik.task1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            RandomAccessFile aFile = new RandomAccessFile("src/main/java/ru/itis/aleynik/task1/data.txt", "rw");
//            канал аналог стрима, выдает и принимает байты в буфер, работа с буфером
            FileChannel inChannel = aFile.getChannel();
// создаем буфер размера 48 байтов (!)
            ByteBuffer buf = ByteBuffer.allocate(48);
// читаем из канала в буфер, возвращается реальное количество считанных байтов
            int bytesRead = inChannel.read(buf);
            int count = 0;
            while (bytesRead != -1) {
                // режим чтения полученных данных из буфера
                buf.flip();
                byte r;
                while (buf.hasRemaining()) {
                    r = buf.get();
                    count += isVowel((char) r);
//                    getInt, Char
                    System.out.print((char) r);
                }
                // режим записи новых данных в буфер
                buf.clear();
                bytesRead = inChannel.read(buf);
            }
//            inChannel.position(0);

            String countVowels = "Count of vowels: " + count + "\n";
            buf = ByteBuffer.wrap(countVowels.getBytes());
            while (buf.hasRemaining()) {
                inChannel.write(buf);
            }
            aFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int isVowel(char r) {
        char[] vowels = {'a', 'A', 'e', 'E', 'y', 'Y', 'u', 'U', 'i', 'I', 'o', 'O'};
        for (char vowel : vowels) {
            if (r == vowel) return 1;
        }
        return 0;
    }


}
