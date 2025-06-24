import java.util.*;

public class FlowNetwork {
    private final int nodeCount;
    private final List<Edge>[] adjacencyList;

    @SuppressWarnings("unchecked")
    public FlowNetwork(int nodeCount) {
        this.nodeCount = nodeCount;
        adjacencyList = new List[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int from, int to, int capacity) {
        Edge forward = new Edge(from, to, capacity);
        Edge backward = new Edge(to, from, 0);
        forward.setResidual(backward);
        backward.setResidual(forward);

        adjacencyList[from].add(forward);
        adjacencyList[to].add(backward);
    }

    public List<Edge> getEdges(int node) {
        return adjacencyList[node];
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public void updateResidualCapacities(List<Edge> path, int flow) {
        for (Edge edge : path) {
            edge.addFlow(flow);
        }
    }

    public static class Edge {
        private final int from;
        private final int to;
        private final int capacity;
        private int flow;
        private Edge residual;

        public Edge(int from, int to, int capacity) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
        }

        public void setResidual(Edge residual) {
            this.residual = residual;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        public int getCapacity() {
            return capacity;
        }

        public int getResidualCapacity() {
            return capacity - flow;
        }

        public void addFlow(int amount) {
            flow += amount;
            residual.flow -= amount;
        }
    }
}