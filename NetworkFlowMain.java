import java.io.File;
import java.util.Scanner;

public class NetworkFlowMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter the file name or type '0' to quit:");
            String fileName = scanner.nextLine();

            if (fileName.equalsIgnoreCase("0")) {
                System.out.println("Exiting the program...");
                break;
            }

            testSingleFile(fileName);
        }

        scanner.close();
    }

    public static void testSingleFile(String fileName) {
        try {
            String filePath = "input/" + fileName;
            File file = new File(filePath);

            if (!file.exists()) {
                System.out.println("File not found: " + filePath);
                return;
            }

            FlowNetwork network = new NetworkParser().parseFromFile(filePath);
            if (network == null) {
                System.out.println("Failed to parse network from file: " + filePath);
                return;
            }

            MaxFlowFinder maxFlow = new EdmondsKarp("inputFile.txt");
            long startTime = System.nanoTime();
            MaxFlowResult result = maxFlow.findMaxFlow(network, 0, network.getNodeCount() - 1);
            long endTime = System.nanoTime();

            double runtimeMs = (endTime - startTime) / 1_000_000.0;

            System.out.println("Max Flow Value: " + result.getMaxFlowValue());
            System.out.println("Runtime (ms): " + String.format("%.2f", runtimeMs));

        } catch (Exception e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }
}