package epam.com.task3;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class FileChannelMover implements FastFileMover {

    @Override
    public void move(String sourceFilePath, String nextFilePath) {
        try {
            RandomAccessFile source = new RandomAccessFile(sourceFilePath, "r");
            RandomAccessFile destination = new RandomAccessFile(nextFilePath, "rw");

            FileChannel sourceChannel = source.getChannel();
            FileChannel destinationChannel = destination.getChannel();

            destinationChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
