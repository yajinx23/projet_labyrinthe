# projet_labyrinthe

# Projet de Fin d'Étude : Résolution de Labyrinthe

**Module :** Programmation et Algorithmique avancée
**Niveau :** Master 1 (GLSI & SRT)
**Superviseur :** Dr Mouhamed DIOP

## Membres du Groupe [Insérer votre numéro de groupe, ex: G1]

* Pape Yatma MBODJ
* Cheikh Maba Alioune DIOP
* Pierre Arsene DIOUF

---

## Présentation du projet

Ce projet consiste en une application Java permettant de résoudre des labyrinthes 2D. Il a pour objectif de mettre en pratique la manipulation de structures de données avancées (Piles, Files, Matrices) et l'implémentation d'algorithmes de parcours de graphes.

L'application dispose d'une interface graphique (Swing) qui permet de charger un labyrinthe depuis un fichier texte, de visualiser sa résolution en direct, et de comparer les performances de deux algorithmes différents.

---

## Choix Algorithmiques et Techniques

Afin de respecter les principes de la programmation orientée objet, nous avons mis en place une **classe abstraite (`LabyrinteSolver`)** qui factorise le code commun (le calcul du temps avec `System.nanoTime()` et le compteur de cases explorées).

Nos deux algorithmes héritent de cette classe :

1. **DFS (Depth First Search - Recherche en profondeur)**

   * **Structure utilisée :** Pile (`java.util.Stack`).
   * **Comportement :** L'algorithme avance le plus loin possible dans un chemin avant de revenir sur ses pas (backtracking) en cas d'impasse.
   * **Résultat :** Il trouve un chemin très rapidement (temps d'exécution souvent plus faible), mais ne garantit pas que ce chemin soit le plus court.
2. **BFS (Breadth First Search - Recherche en largeur)**

   * **Structure utilisée :** File (`java.util.Queue` implémentée avec `LinkedList`).
   * **Comportement :** L'algorithme explore le labyrinthe niveau par niveau (par cercles concentriques autour du départ).
   * **Résultat :** Il garantit mathématiquement de trouver le chemin le plus court possible, au prix d'un nombre de cases explorées généralement plus important.

---

## Fonctionnalités implémentées

 **Chargement depuis un fichier** : Lecture d'une matrice textuelle `.txt`.

 **Interface Graphique (Bonus)** : Affichage du labyrinthe avec un code couleur clair (Murs, Départ, Arrivée, Chemin).

 **Résolution visuelle** : Affichage du chemin trouvé par le DFS ou le BFS.

 **Module de comparaison** : Outil automatisé confrontant le DFS et le BFS sur le même labyrinthe selon 3 critères : temps d'exécution (ms), cases explorées, et longueur du chemin.

---

## Instructions de lancement

### Format du fichier labyrinthe (.txt)

Le programme s'attend à lire un fichier texte respectant ce formalisme :

* `#` : Mur (case infranchissable)
* `=` : Passage libre
* `S` : Point de départ
* `E` : Point d'arrivée

*Exemple :*
#######
#S===E#
#=###=#
#=====#
#######

### Exécution

1. Clonez ce dépôt GitHub sur votre machine locale.
2. Ouvrez le projet dans votre environnement de développement (Eclipse, IntelliJ IDEA, VS Code) ou terminal .
3. Lancez l'exécution depuis le fichier principal : **`LabyrintheApp.java`**.
4. Dans l'interface, cliquez sur **Charger Fichier** pour sélectionner un fichier `.txt`, puis testez les algorithmes à l'aide des bouton
