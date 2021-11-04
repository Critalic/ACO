package schmitt.joao.aco;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Program {

    public static void main(String[] args) throws Exception {

        String tspPath = (new File(".")).getCanonicalPath();
        tspPath = Paths.get(tspPath, "tsp").toAbsolutePath().toString();
        String tspFiles[] = {"lin318.tsp"/*, "att532.tsp", "eil51.tsp", "pcb1173.tsp", "pr2392.tsp"*/};

        Program app = new Program();
        for(String tspFile : tspFiles) {
            System.out.println("\nProblem: " + tspFile);
            app.startApplication(tspPath, tspFile);
        }
    }

    public void startApplication(String path, String file) {

        Environment environment = new Environment(TspReader.getDistances(path, file));
        Statistics statistics = new Statistics(file, environment, TspReader.getCoordinates(path, file));

        environment.generateNearestNeighborList();
        environment.generateAntPopulation();
        environment.generateEnvironment();

        int n = 0;
        while(n < Parameters.iterationsMax) {
            environment.constructSolutions();
            environment.updatePheromone();
            statistics.calculateStatistics(n);
            n++;
        }
        try { Thread.sleep(3000); } catch (Exception ex) {}
        statistics.close();
        System.out.println("Finished");
    }
}
