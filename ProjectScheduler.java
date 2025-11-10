import java.util.*;

public class ProjectScheduler {
    private final Map<String, List<String>> graph = new LinkedHashMap<>();

    public void addTask(String task) {
        graph.putIfAbsent(task, new ArrayList<>());
    }

    // prerequisiteTask must finish before dependentTask starts
    public void addDependency(String prerequisiteTask, String dependentTask) {
        addTask(prerequisiteTask);
        addTask(dependentTask);
        graph.get(prerequisiteTask).add(dependentTask);
    }

    
    public List<String> getTaskOrder() {
        Map<String, Integer> state = new HashMap<>(); // 0=unvisited, 1=visiting, 2=visited
        Deque<String> order = new ArrayDeque<>();
        boolean[] hasCycle = { false };

        for (String task : graph.keySet()) {
            if (state.getOrDefault(task, 0) == 0) {
                dfs(task, state, order, hasCycle);
                if (hasCycle[0]) return Collections.emptyList();
            }
        }
        return new ArrayList<>(order);
    }

    private void dfs(String task, Map<String, Integer> state, Deque<String> order, boolean[] hasCycle) {
        if (hasCycle[0]) return;
        state.put(task, 1); // visiting

        for (String dep : graph.getOrDefault(task, Collections.emptyList())) {
            int s = state.getOrDefault(dep, 0);
            if (s == 0) {
                dfs(dep, state, order, hasCycle);
                if (hasCycle[0]) return;
            } else if (s == 1) {
                hasCycle[0] = true; // back-edge detected
                return;
            }
        }

        state.put(task, 2);      // visited
        order.addFirst(task);    // prepend for topological order
    }
}
