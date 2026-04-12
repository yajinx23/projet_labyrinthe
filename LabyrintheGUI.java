import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class LabyrintheGUI extends JFrame {
    private Labyrinthe labyrinthe;
    private JPanel panneauGrille;
    private JLabel labelStats;

    public LabyrintheGUI() {
        setTitle("Projet Labyrinthe - Résolution");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // 1. Zone du haut : Les boutons d'action
        JPanel panneauBoutons = new JPanel();
        JButton btnCharger = new JButton("Charger Fichier");
        JButton btnDFS = new JButton("Lancer DFS");
        JButton btnBFS = new JButton("Lancer BFS");
        JButton btnComparer = new JButton("Comparer DFS/BFS"); // NOUVEAU BOUTON

        panneauBoutons.add(btnCharger);
        panneauBoutons.add(btnDFS);
        panneauBoutons.add(btnBFS);
        panneauBoutons.add(btnComparer);
        add(panneauBoutons, BorderLayout.NORTH);

        // 2. Zone du centre : Dessin du labyrinthe
        panneauGrille = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dessinerGrille(g);
            }
        };
        add(panneauGrille, BorderLayout.CENTER);

        // 3. Zone du bas : Affichage des performances
        labelStats = new JLabel(" Cliquez sur 'Charger' pour ouvrir un labyrinthe (.txt)");
        labelStats.setFont(new Font("SansSerif", Font.BOLD, 14));
        labelStats.setPreferredSize(new Dimension(0, 40));
        add(labelStats, BorderLayout.SOUTH);

        // --- Clic sur les boutons ---
        btnCharger.addActionListener(e -> chargerFichier());
        btnDFS.addActionListener(e -> resoudre(new DFSSolver(), "DFS"));
        btnBFS.addActionListener(e -> resoudre(new BFSSolver(), "BFS"));

        // Action pour le bouton Comparer
        btnComparer.addActionListener(e -> comparerAlgorithmes());
    }

    private void chargerFichier() {
        JFileChooser chooser = new JFileChooser(".");
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File fichier = chooser.getSelectedFile();
            labyrinthe = LabyrintheLoader.chargerDepuisFichier(fichier.getAbsolutePath());
            labelStats.setText(" Labyrinthe chargé. Choisissez DFS, BFS ou Comparer.");
            repaint();
        }
    }

    private void resoudre(LabyrinteSolver solveur, String nomAlgo) {
        if (labyrinthe == null)
            return;

        labyrinthe.reinitialiser();
        List<int[]> chemin = solveur.solve(labyrinthe);

        if (chemin.isEmpty()) {
            labelStats.setText(" " + nomAlgo + " : Aucun chemin trouvé !");
        } else {
            labyrinthe.marquerChemin(chemin);
            String stats = String.format(" [%s] Temps : %.3f ms | Cases explorées : %d | Longueur chemin : %d",
                    nomAlgo, solveur.getTempsExecution(), solveur.getCasesExplorees(), chemin.size());
            labelStats.setText(stats);
        }
        repaint();
    }

    // MÉTHODE DE COMPARAISON
    private void comparerAlgorithmes() {
        if (labyrinthe == null) {
            JOptionPane.showMessageDialog(this, "Veuillez charger un labyrinthe d'abord !");
            return;
        }

        // 1. Lancer DFS
        labyrinthe.reinitialiser();
        DFSSolver dfs = new DFSSolver();
        List<int[]> cheminDFS = dfs.solve(labyrinthe);

        // 2. Lancer BFS
        labyrinthe.reinitialiser();
        BFSSolver bfs = new BFSSolver();
        List<int[]> cheminBFS = bfs.solve(labyrinthe);

        // 3. Préparer le texte de comparaison
        String resultat = "=== COMPARAISON DES PERFORMANCES ===\n\n";

        resultat += "► RECHERCHE EN PROFONDEUR (DFS) :\n";
        resultat += " - Temps : " + String.format("%.3f", dfs.getTempsExecution()) + " ms\n";
        resultat += " - Cases explorées : " + dfs.getCasesExplorees() + "\n";
        resultat += " - Longueur du chemin : " + cheminDFS.size() + " cases\n\n";

        resultat += "► RECHERCHE EN LARGEUR (BFS) :\n";
        resultat += " - Temps : " + String.format("%.3f", bfs.getTempsExecution()) + " ms\n";
        resultat += " - Cases explorées : " + bfs.getCasesExplorees() + "\n";
        resultat += " - Longueur du chemin : " + cheminBFS.size() + " cases\n\n";

        // Conclusion automatique simple
        if (dfs.getTempsExecution() < bfs.getTempsExecution()) {
            resultat += "CONCLUSION : DFS a été le plus rapide.\n";
        } else {
            resultat += "CONCLUSION : BFS a été le plus rapide.\n";
        }

        // 4. Afficher les résultats dans une fenêtre Pop-up
        JOptionPane.showMessageDialog(this, resultat, "Résultats de la comparaison", JOptionPane.INFORMATION_MESSAGE);

        // On affiche le chemin BFS sur la grille car c'est généralement le plus court
        labyrinthe.marquerChemin(cheminBFS);
        labelStats.setText(" Comparaison terminée. Affichage du chemin BFS (optimal).");
        repaint();
    }

    private void dessinerGrille(Graphics g) {
        if (labyrinthe == null)
            return;

        char[][] grille = labyrinthe.getGrille();
        int tailleCase = 25;

        for (int i = 0; i < labyrinthe.getLignes(); i++) {
            for (int j = 0; j < labyrinthe.getColonnes(); j++) {

                if (grille[i][j] == '#')
                    g.setColor(Color.DARK_GRAY);
                else if (grille[i][j] == 'S')
                    g.setColor(Color.GREEN);
                else if (grille[i][j] == 'E')
                    g.setColor(Color.RED);
                else if (grille[i][j] == '+')
                    g.setColor(Color.BLUE);
                else
                    g.setColor(Color.WHITE);

                g.fillRect(j * tailleCase, i * tailleCase, tailleCase, tailleCase);
                g.setColor(Color.BLACK);
                g.drawRect(j * tailleCase, i * tailleCase, tailleCase, tailleCase);
            }
        }
    }
}