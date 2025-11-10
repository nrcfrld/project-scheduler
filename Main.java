import java.util.*;

public class Main {
    public static void main(String[] args) {
        ProjectScheduler ps = new ProjectScheduler();

        // Tasks
        ps.addTask("Analisis");
        ps.addTask("Desain UI");
        ps.addTask("Coding Backend");
        ps.addTask("Testing");
        ps.addTask("Deploy");

        // Dependencies:
        // Analisis before Desain UI
        // Analisis before Coding Backend
        // Desain UI before Testing
        // Coding Backend before Testing
        // Testing before Deploy
        ps.addDependency("Analisis", "Desain UI");
        ps.addDependency("Analisis", "Coding Backend");
        ps.addDependency("Desain UI", "Testing");
        ps.addDependency("Coding Backend", "Testing");
        ps.addDependency("Testing", "Deploy");

        List<String> order = ps.getTaskOrder();

        if (order.isEmpty()) {
            System.out.println("Tidak ada urutan valid. Terdeteksi siklus dependensi.");
        } else {
            System.out.println("Urutan pengerjaan tugas yang valid (Topological Sort):");
            System.out.println(order);
            // Contoh salah satu urutan valid:
            // [Analisis, Desain UI, Coding Backend, Testing, Deploy]
        }
    }
}
