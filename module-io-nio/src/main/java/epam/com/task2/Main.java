package epam.com.task2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a disc (D:) or (C:)");
        String path = scanner.nextLine();

        DiskAnalyzer diskAnalyzer = new DiskAnalyzer(path);
        menu(diskAnalyzer);
    }

    public static void menu(DiskAnalyzer diskAnalyzer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("    Select function:\n"
                + "1) Search for the file name with the maximum number of letters ‘s’ in the name, display the path to it.\n"
                + "2) Print Top-5 largest files (by size in bytes).\n"
                + "3) The average file size in the specified directory or any its subdirectory.\n"
                + "4) The number of files and folders, divided by the first letters of the alphabet\n"
                + "0) Exit");
        switch (scanner.nextLine()) {
            case "1": {
                diskAnalyzer.getFileWithMaximumNumberOfLetterS();
                break;
            }
            case "2": {
                diskAnalyzer.getLargestFiles();
                break;
            }
            case "3": {
                diskAnalyzer.getAverageFileSize();
                break;
            }
            case "4": {
                diskAnalyzer.getNumberOfFiles();
                break;
            }
            case "0": {
                System.exit(0);
            }
            default: {
                System.out.println("There is not such function, try again:");
                menu(diskAnalyzer);
            }
        }
        menu(diskAnalyzer);
    }
}
