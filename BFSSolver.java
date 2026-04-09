import java.util.*;

public class BFSSolver extends LabyrinteSolver {

    @Override
    public List<int[]> solve(Labyrinthe lab) {
        long debut = System.nanoTime();
        casesExplorees = 0;

        int[] start = lab.getStart();
        int[] end = lab.getEnd();
        
        // Utilisation d'une File (Queue) pour explorer niveau par niveau
        Queue<int[]> file = new LinkedList<>();
        boolean[][] visite = new boolean[lab.getLignes()][lab.getColonnes()];
        int[][][] parent = new int[lab.getLignes()][lab.getColonnes()][2];

        file.add(start);
        visite[start[0]][start[1]] = true;

        int[] dLigne = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        while (!file.isEmpty()) {
            int[] courant = file.poll();
            casesExplorees++;

            if (courant[0] == end[0] && courant[1] == end[1]) {
                tempsExecution = (System.nanoTime() - debut) / 1_000_000.0;
                return reconstruireChemin(parent, start, end);
            }

            for (int i = 0; i < 4; i++) {
                int nl = courant[0] + dLigne[i];
                int nc = courant[1] + dCol[i];

                if (lab.estValide(nl, nc) && !visite[nl][nc]) {
                    visite[nl][nc] = true;
                    parent[nl][nc] = courant;
                    file.add(new int[]{nl, nc});
                }
            }
        }
        
        tempsExecution = (System.nanoTime() - debut) / 1_000_000.0;
        return new ArrayList<>();
    }

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