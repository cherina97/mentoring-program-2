package epam.com.task3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStreamsWithBufferMover implements FastFileMover {

    @Override
    public void move(String sourceFilePath, String nextFilePath) {
        File source = new File(sourceFilePath);
        File destination = new File(nextFilePath);

        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(destination)) {

            byte[] buffer = new byte[102400];

            int readByte;
            while ((readByte = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, readByte);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        delete(sourceFilePath);
    }
}
