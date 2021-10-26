package epam.com.task3;

/**
 * After that prepare a performance report based on the next requirements.
 * <p>
 * Measure the time for copying, run on several reference files of different sizes (1 Kb, 100 Kb, 10 Mb, 1 GB).
 * On each file, run 1000 times, get the average time.
 */
public class Main {
    public static void main(String[] args) {
        final String sourceFilePath = "module-io-nio/src/main/java/epam/com/task3/source/test.txt";
        final String nextFilePath = "module-io-nio/src/main/java/epam/com/task3/destination/test.txt";

        FastFileMover fileStreamsMover = new FileStreamsMover();
        fileStreamsMover.move(sourceFilePath, nextFilePath);

        FastFileMover fileStreamsWithBufferMover = new FileStreamsWithBufferMover();
        fileStreamsWithBufferMover.move(sourceFilePath, nextFilePath);

        FastFileMover fileChannelMover = new FileChannelMover();
        fileChannelMover.move(sourceFilePath, nextFilePath);

        FastFileMover nio2FileAPIMover = new Nio2FileAPIMover();
        nio2FileAPIMover.move(sourceFilePath, nextFilePath);
    }
}
