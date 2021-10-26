package epam.com.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Write several versions of the FastFileMover utility, which moves a file from one directory to another directory.
 * It takes both file paths as command-line parameters.
 */
public interface FastFileMover {

    void move(String sourceFilePath, String nextFilePath);

    default void delete(String sourceFile){
        try {
            Files.delete(Paths.get(sourceFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
