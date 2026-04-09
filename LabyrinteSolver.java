import java.util.List;

public abstract class LabyrinteSolver {
    protected int casesExplorees;
    protected double tempsExecution; // en millisecondes

    public LabyrinteSolver() {
        this.casesExplorees = 0;
        this.tempsExecution = 0;
    }

    // Méthode que DFS et BFS devront obligatoirement coder
    public abstract List<int[]> solve(Labyrinthe lab);

    public int getCasesExplorees() {
        return casesExplorees;
    }

    public double getTempsExecution() {
        return tempsExecution;
    }
}