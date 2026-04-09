import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class LabyrintheLoader {

    public static Labyrinthe chargerDepuisFichier(String chemin) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(chemin));
            ArrayList<String> lignes = new ArrayList<>();
            String ligne;
            
            // Lecture du fichier texte ligne par ligne
            while ((ligne = br.readLine()) != null) {
                lignes.add(ligne);
            }
            br.close();

            int nbLignes = lignes.size();
            int nbCols = lignes.get(0).length();
            char[][] grille = new char[nbLignes][nbCols];

            // Conversion des Strings en tableau de caractères 2D
            for (int i = 0; i < nbLignes; i++) {
                grille[i] = lignes.get(i).toCharArray();
            }

            return new Labyrinthe(grille);

        } catch (Exception e) {
            System.out.println("Erreur de lecture : " + e.getMessage());
            return null;
        }
    }
}