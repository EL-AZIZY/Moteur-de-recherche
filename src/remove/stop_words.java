package remove;

import java.io.*;
import java.util.*;


public class stop_words {

    public static void supprimerMotsFichier(String fichierMotsASupprimer, String dossierFichiers, String dossierSortie) {
        try {
             // Lire les mots à supprimer depuis le fichier
            Scanner scannerMots = new Scanner(new File(fichierMotsASupprimer));
            List<String> motsASupprimer = new ArrayList<>();
            while (scannerMots.hasNext()) {
                motsASupprimer.add(scannerMots.next());
            }
            scannerMots.close();

            // Obtenir la liste des fichiers dans le dossier
            File dossier = new File(dossierFichiers);
            File[] fichiers = dossier.listFiles();

            // Créer le dossier de sortie s'il n'existe pas
            File dossierSortieFile = new File(dossierSortie);
            if (!dossierSortieFile.exists()) {
                dossierSortieFile.mkdir();
            }

            // Parcourir chaque fichier dans le dossier
            for (File fichier : fichiers) {
                // Vérifier si le fichier est un fichier texte (.txt)
                if (fichier.isFile() && fichier.getName().endsWith(".txt")) {
                    // Lire le contenu du fichier
                    Scanner scannerFichier = new Scanner(fichier);
                    StringBuilder contenuFichier = new StringBuilder();
                    while (scannerFichier.hasNextLine()) {
                        contenuFichier.append(scannerFichier.nextLine()).append("\n");
                    }
                    scannerFichier.close();
                    

                    
                    // Supprimer les mots du fichier
                    for (String mot : motsASupprimer) {
                    	contenuFichier = new StringBuilder(contenuFichier.toString().replaceAll("\\s+", " "));
                        contenuFichier = new StringBuilder(contenuFichier.toString().replaceAll("\\b" + mot + "\\b", ""));
                    }
                    // Appliquer le stemming au contenu du fichier
                    contenuFichier = Stemmer.stemText(contenuFichier);
                    // Écrire le contenu mis à jour dans le fichier de sortie
                    File fichierSortie = new File(dossierSortieFile, fichier.getName());
                    FileWriter writer = new FileWriter(fichierSortie);
                    writer.write(contenuFichier.toString());
                    writer.close();
                }
            }
            System.out.println("Suppression des mots terminée. Les fichiers traités ont été enregistrés dans le dossier de sortie.");
        } catch (FileNotFoundException e) {
            System.err.println("Fichier non trouvé : " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erreur d'entrée/sortie : " + e.getMessage());
        }
    }
    
}