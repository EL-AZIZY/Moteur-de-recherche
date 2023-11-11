package remove;

import java.io.*;
import java.util.*;

public class indexation {
    public static Map<String, Map<String, Double>> calculerFrequencesMots(String dossierFichiers) {
        Map<String, Map<String, Double>> fichiersMotsFrequences = new HashMap<>();

        // Obtenir la liste des fichiers dans le dossier
        File dossier = new File(dossierFichiers);
        File[] fichiers = dossier.listFiles();

        // Parcourir chaque fichier dans le dossier
        for (File fichier : fichiers) {
            // Vérifier si le fichier est un fichier texte (.txt)
            if (fichier.isFile() && fichier.getName().endsWith(".txt")) {
                try {
                    // Lire le contenu du fichier
                    Scanner scannerFichier = new Scanner(fichier);
                    Map<String, Integer> motsCompteur = new HashMap<>();
                    int totalMots = 0;

                    // Compter le nombre total de mots dans le fichier et le nombre d'occurrences de chaque mot
                    while (scannerFichier.hasNext()) {
                        String mot = scannerFichier.next().toLowerCase(); // Convertir le mot en minuscules
                        motsCompteur.put(mot, motsCompteur.getOrDefault(mot, 0) + 1);
                        totalMots++;
                    }
                    scannerFichier.close();

                    // Calculer les fréquences de chaque mot dans le fichier
                    Map<String, Double> motsFrequences = new HashMap<>();
                    for (Map.Entry<String, Integer> entry : motsCompteur.entrySet()) {
                        String mot = entry.getKey();
                        int occurrences = entry.getValue();
                        double frequence = (double) occurrences / totalMots;
                        motsFrequences.put(mot, frequence);
                    }

                    // Ajouter le nom du fichier et la map des fréquences des mots au résultat
                    fichiersMotsFrequences.put(fichier.getName(), motsFrequences);
                } catch (FileNotFoundException e) {
                    System.err.println("Fichier non trouvé : " + e.getMessage());
                }
            }
        }

        return fichiersMotsFrequences;
    }

}
