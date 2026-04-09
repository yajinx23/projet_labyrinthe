import java.util.*;

public class DFSSolver extends LabyrinteSolver {

    @Override
    public List<int[]> solve(Labyrinthe lab) {
        long debut = System.nanoTime(); // Chronométrage précis
        casesExplorees = 0;

        int[] start = lab.getStart();
        int[] end = lab.getEnd();
        
        Stack<int[]> pile = new Stack<>();
        boolean[][] visite = new boolean[lab.getLignes()][lab.getColonnes()];
        
        // parent permet de mémoriser d'où l'on vient pour retracer le chemin à la fin
        int[][][] parent = new int[lab.getLignes()][lab.getColonnes()][2];

        pile.push(start);
        visite[start[0]][start[1]] = true;

        // Les 4 directions : Haut, Bas, Gauche, Droite
        int[] dLigne = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        while (!pile.isEmpty()) {
            int[] courant = pile.pop();
            casesExplorees++;

            // Condition d'arrêt : on a trouvé la sortie 'E'
            if (courant[0] == end[0] && courant[1] == end[1]) {
                tempsExecution = (System.nanoTime() - debut) / 1_000_000.0;
                return reconstruireChemin(parent, start, end);
            }

            // Exploration des 4 cases voisines
            for (int i = 0; i < 4; i++) {
                int nl = courant[0] + dLigne[i];
                int nc = courant[1] + dCol[i];

                if (lab.estValide(nl, nc) && !visite[nl][nc]) {
                    visite[nl][nc] = true;
                    parent[nl][nc] = courant;
                    pile.push(new int[]{nl, nc});
                }
            }
        }
        
        tempsExecution = (System.nanoTime() - debut) / 1_000_000.0;
        return new ArrayList<>(); // Aucun chemin trouvé
    }

    // Remonte de la sortie vers l'entrée grâce au tableau "parent"
    private List<int[]> reconstruireChemin(int[][][] parent, int[] start, int[] end) {
        LinkedList<int[]> chemin = new LinkedList<>();
        int[] courant = end;
        while (courant[0] != start[0] || courant[1] != start[1]) {
            chemin.addFirst(courant);
            courant = parent[courant[0]][courant[1]];
        }
        return chemin;
    }
}