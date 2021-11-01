package epam.com.task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DiskAnalyzer {

    private final List<File> filesList;

    public DiskAnalyzer(String filePath) {
        File file = new File(filePath);
        filesList = new ArrayList<>();
        retrieveFiles(file);
    }

    /**
     * Search for the file name with the maximum number of letters ‘s’ in the name, display the path to it.
     */
    public void getFileWithMaximumNumberOfLetterS() {
        filesList.stream()
                .max(Comparator.comparing(file -> getCountOfSCharacter(file.toString())))
                .ifPresent(file -> System.out.println(file.getAbsolutePath()));
    }

    private int getCountOfSCharacter(String string) {
        return (int) string.chars().filter(ch -> ch == 's').count();
    }

    /**
     * Print Top-5 the largest files (by size in bytes).
     */
    public void getLargestFiles() {
        filesList.stream()
                .sorted(Comparator.comparing(File::length).reversed())
                .limit(5)
                .forEach(System.out::println);
    }

    /**
     * The average file size in the specified directory or any subdirectory.
     */
    public void getAverageFileSize() {
        filesList.stream()
                .mapToLong(File::length)
                .average()
                .ifPresent(System.out::println);
    }

    /**
     * The number of files and folders, divided by the first letters of the alphabet
     * (for example, 100,000 files and 200 folders begin with the letter A).
     */
    public void getNumberOfFiles() {
        Map<Character, List<File>> filesByAlphabet = getFilesByAlphabet(filesList);
        filesByAlphabet.forEach((k, v) -> System.out.println(k + " --- " + v));
    }

    private Map<Character, List<File>> getFilesByAlphabet(List<File> filesList) {
        Map<Character, List<File>> result = new HashMap<>();

        filesList.forEach(file -> {
            char firstChar = file.getName().charAt(0);

            if (!result.containsKey(firstChar)) {
                List<File> charFiles = new ArrayList<>();
                charFiles.add(file);
                result.put(firstChar, charFiles);
            } else {
                List<File> files = result.get(firstChar);
                files.add(file);
            }
        });
        return result;
    }

    private void retrieveFiles(File dir) {
        File[] pathname;
        pathname = dir.listFiles();

        Collections.addAll(filesList, Objects.requireNonNull(pathname));
    }
}
