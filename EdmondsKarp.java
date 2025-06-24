import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class EdmondsKarp implements MaxFlowFinder {

    private String inputFileName;

    public EdmondsKarp(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    @Override
    public MaxFlowResult findMaxFlow(FlowNetwork network, int source, int sink) {
        int nodeCount = network.getNodeCount();
        int[][] flow = new int[nodeCount][nodeCount];
        int maxFlow = 0;

        String outputFileName = "augmenting_paths_" + inputFileName.replace(".txt", "") + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            writer.write("Augmenting paths and bottlenecks for " + inputFileName + ":\n\n");

            while (true) {
                List<FlowNetwork.Edge> path = findAugmentingPath(network, source, sink);
                if (path == null) break; 

                int bottleneckCapacity = Integer.MAX_VALUE;
                for (FlowNetwork.Edge edge : path) {
                    bottleneckCapacity = Math.min(bottleneckCapacity, edge.getResidualCapacity());
                }

                StringBuilder pathOutput = new StringBuilder();
                for (int i = 0; i < path.size(); i++) {
                    FlowNetwork.Edge e = path.get(i);
                    pathOutput.append(e.getFrom()).append(" -> ");
                    if (i == path.size() - 1) pathOutput.append(e.getTo());
                }

                writer.write("Found augmenting path: " + pathOutput + " | Bottleneck = " + bottleneckCapacity + "\n\n");

                for (FlowNetwork.Edge edge : path) {
                    flow[edge.getFrom()][edge.getTo()] += bottleneckCapacity;
                    flow[edge.getTo()][edge.getFrom()] -= bottleneckCapacity;
                }

                network.updateResidualCapacities(path, bottleneckCapacity);
                maxFlow += bottleneckCapacity; 
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        System.out.println("\nAugmenting paths and bottlenecks saved to " + outputFileName + "\n");
        return new MaxFlowResult(maxFlow, flow, false);
    }

    private List<FlowNetwork.Edge> findAugmentingPath(FlowNetwork network, int source, int sink) {
        int nodeCount = network.getNodeCount();
        boolean[] visited = new boolean[nodeCount];
        FlowNetwork.Edge[] edgeTo = new FlowNetwork.Edge[nodeCount];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty() && !visited[sink]) {
            int node = queue.poll();
            for (FlowNetwork.Edge edge : network.getEdges(node)) {
                int to = edge.getTo();
                if (edge.getResidualCapacity() > 0 && !visited[to]) {
                    edgeTo[to] = edge;
                    visited[to] = true;
                    queue.offer(to);
                }
            }
        }

        if (!visited[sink]) return null;

        List<FlowNetwork.Edge> path = new ArrayList<>();
        for (FlowNetwork.Edge edge = edgeTo[sink]; edge != null; edge = edgeTo[edge.getFrom()]) {
            path.add(edge);
        }
        Collections.reverse(path); 
        return path;
    }
}