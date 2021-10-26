package epam.com.task3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Nio2FileAPIMover implements FastFileMover {

    @Override
    public void move(String sourceFilePath, String nextFilePath) {
        File source = new File(sourceFilePath);
        File next = new File(nextFilePath);

        try {
            Files.move(source.toPath(), next.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
