public interface MaxFlowFinder {
    MaxFlowResult findMaxFlow(FlowNetwork network, int source, int sink);
}