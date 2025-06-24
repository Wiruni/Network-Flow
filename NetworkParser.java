import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NetworkParser {    
    public FlowNetwork parseFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int nodeCount = Integer.parseInt(reader.readLine().trim());
            FlowNetwork network = new FlowNetwork(nodeCount);
            
            String edgeLine;
            while ((edgeLine = reader.readLine()) != null) {
                if (edgeLine.trim().isEmpty() || edgeLine.trim().startsWith("//")) {
                    continue;
                }

                edgeLine = edgeLine.split("//")[0].trim();
                String[] parts = edgeLine.split("\\s+");
                if (parts.length >= 3) {
                    network.addEdge(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                }
            }
            return network;
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error parsing file: " + e.getMessage());
            return null;
        }
    }
}