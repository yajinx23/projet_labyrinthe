import java.util.List;

public class Labyrinthe {
    private char[][] grille;
    private int lignes;
    private int colonnes;
    private int startLigne, startCol;
    private int endLigne, endCol;

    public Labyrinthe(char[][] grille) {
        this.grille = grille;
        this.lignes = grille.length;
        this.colonnes = grille[0].length;
        trouverS_et_E();
    }

    // Parcourt la grille une seule fois au début pour stocker les positions S et E
    private void trouverS_et_E() {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (grille[i][j] == 'S') {
                    startLigne = i; startCol = j;
                } else if (grille[i][j] == 'E') {
                    endLigne = i; endCol = j;
                }
            }
        }
    }

    // Vérifie si une case est dans les limites et n'est pas un mur
    public boolean estValide(int l, int c) {
        return l >= 0 && l < lignes && c >= 0 && c < colonnes && grille[l][c] != '#';
    }

    // Remplace les cases de passage par des '+' pour dessiner la solution
    public void marquerChemin(List<int[]> chemin) {
        for (int[] caseActuelle : chemin) {
            int l = caseActuelle[0];
            int c = caseActuelle[1];
            if (grille[l][c] != 'S' && grille[l][c] != 'E') {
                grille[l][c] = '+';
            }
        }
    }

    // Efface le chemin précédent pour pouvoir lancer un autre algorithme
    public void reinitialiser() {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                if (grille[i][j] == '+') {
                    grille[i][j] = '=';
                }
            }
        }
    }

    public char[][] getGrille() { return grille; }
    public int getLignes() { return lignes; }
    public int getColonnes() { return colonnes; }
    public int[] getStart() { return new int[]{startLigne, startCol}; }
    public int[] getEnd() { return new int[]{endLigne, endCol}; }
}