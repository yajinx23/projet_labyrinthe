import javax.swing.SwingUtilities;

public class LabyrintheApp {
    public static void main(String[] args) {
        // Lance l'interface graphique de manière sécurisée selon les standards de Java Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LabyrintheGUI fenetre = new LabyrintheGUI();
                fenetre.setVisible(true);
            }
        });
    }
}