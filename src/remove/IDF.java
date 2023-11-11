package remove;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class IDF {
    public static Map<String, String> loadDocumentsFromFolder(String folderPath) {
        Map<String, String> corpus = new HashMap<>();

        File dossier = new File(folderPath);
        File[] fichiers = dossier.listFiles();

        if (fichiers != null) {
            for (File fichier : fichiers) {
                if (fichier.isFile() && fichier.getName().endsWith(".txt")) {
                    try {
                        // Lire le contenu du fichier et ajouter au corpus
                        String contenu = new String(Files.readAllBytes(fichier.toPath()));
                        corpus.put(fichier.getName(), contenu);
                    } catch (IOException e) {
                        System.err.println("Erreur de lecture du fichier : " + e.getMessage());
                    }
                }
            }
        }

        return corpus;
    }
    public static Map<String, Double> calculateIDF(Map<String, String> corpus) {
        int totalDocuments = corpus.size();
        Map<String, Integer> documentFrequency = new HashMap<>();

        // Calculer le nombre de documents où chaque terme apparaît
        for (String document : corpus.values()) {
            // Nettoyez le texte et divisez-le en mots
            String[] mots = cleanAndTokenize(document);
            // Utilisez un ensemble pour éviter les répétitions des mots dans le même document
            Set<String> termesUniques = new HashSet<>();
            for (String mot : mots) {
                termesUniques.add(mot);
            }
            // Mettez à jour la fréquence documentaire pour chaque terme unique
            for (String termeUnique : termesUniques) {
                documentFrequency.put(termeUnique, documentFrequency.getOrDefault(termeUnique, 0) + 1);
            }
        }

        // Calculer IDF pour chaque terme
        Map<String, Double> idfValues = new HashMap<>();
        for (Map.Entry<String, Integer> entry : documentFrequency.entrySet()) {
            String terme = entry.getKey();
            int documentsContenantTerme = entry.getValue();
//            System.out.println("total de documents est "+totalDocuments);
            double idf = Math.log((double) totalDocuments / (double) documentsContenantTerme);
            idfValues.put(terme, idf);
        }

        return idfValues;
    }

    public static String[] cleanAndTokenize(String texte) {
        // Supprimez les caractères spéciaux et les chiffres
        texte = texte.replaceAll("[^\\p{IsArabic}]", " ");

        // Divisez le texte en mots
        return texte.split("\\s+");
    }
   


}
