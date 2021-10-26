package epam.com.task3.Perfomance;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class SizeGenerator {

    public void createDirectory(String path) {
        new File(path).mkdirs();
    }

    public void generate(String filePath, long sizeMultiplier) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "rw");
        randomAccessFile.setLength(sizeMultiplier);
        randomAccessFile.close();
    }

    public void deleteDirectory(String path) {
        new File(path).deleteOnExit();
    }
}
