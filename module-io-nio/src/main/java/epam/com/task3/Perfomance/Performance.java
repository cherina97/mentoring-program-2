package epam.com.task3.Perfomance;

import epam.com.task3.FastFileMover;

import java.io.IOException;

public class Performance {

    private static final int REPETITION_AMOUNT = 100;
    private static final long KILO = 1024;
    private static final long[] SIZES = new long[]{KILO, KILO * 100, KILO * KILO};
    private final SizeGenerator sizeGenerator;
    private final String sourcePath;
    private final String destinationPath;

    public Performance(SizeGenerator sizeGenerator, String sourcePath, String destinationPath) {
        this.sizeGenerator = sizeGenerator;
        this.sourcePath = sourcePath;
        this.destinationPath = destinationPath;
    }

    public void logTimings(FastFileMover fastFileMover) {
        try {
            for (long size : SIZES) {
                long resultTime = 0;
                for (int i = 0; i < REPETITION_AMOUNT; i++) {
                    sizeGenerator.generate(sourcePath, size);
                    long startTime = System.nanoTime();
                    fastFileMover.move(sourcePath, destinationPath);
                    long endTime = System.nanoTime();
                    resultTime += endTime - startTime;
                }
                long averageTime = resultTime / REPETITION_AMOUNT;
                System.out.format("\n%s nanoseconds. File size = %s KB.", averageTime, size / KILO);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
