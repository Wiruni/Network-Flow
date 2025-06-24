public class MaxFlowResult {
    private final int maxFlowValue;
    private final int[][] flow;

    public MaxFlowResult(int maxFlowValue, int[][] flow, boolean trackDetails) {
        this.maxFlowValue = maxFlowValue;
        this.flow = flow;
    }

    public int getMaxFlowValue() {
        return maxFlowValue;
    }

    public int[][] getFlow() {
        return flow;
    }
}