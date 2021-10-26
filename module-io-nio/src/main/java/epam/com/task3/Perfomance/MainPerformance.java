package epam.com.task3.Perfomance;

import epam.com.task3.*;

public class MainPerformance {
    public static void main(String[] args) {
        final String sourceFilePath = "module-io-nio/src/main/java/epam/com/task3/source/test.txt";
        final String nextFilePath = "module-io-nio/src/main/java/epam/com/task3/destination/test.txt";

        FastFileMover fileStreamsMover = new FileStreamsMover();
        FastFileMover fileStreamsWithBufferMover = new FileStreamsWithBufferMover();
        FastFileMover fileChannelMover = new FileChannelMover();
        FastFileMover nio2FileAPIMover = new Nio2FileAPIMover();

        SizeGenerator sizeGenerator = new SizeGenerator();
        sizeGenerator.createDirectory(sourceFilePath);
        sizeGenerator.createDirectory(nextFilePath);

        Performance performance = new Performance(sizeGenerator, sourceFilePath, nextFilePath);

        performance.logTimings(nio2FileAPIMover);
    }
}
