package remove;

import java.util.*;


public class TF_IDF {
    public static Map<String, Map<String, Double>> calculerTFIDF(Map<String, Map<String, Double>> fichiersMotsFrequences, Map<String, Double> idfValues) {
        Map<String, Map<String, Double>> tfIdfValues = new HashMap<>();

        // Parcourir chaque fichier et ses fréquences de mots
        for (Map.Entry<String, Map<String, Double>> entry : fichiersMotsFrequences.entrySet()) {
            String nomFichier = entry.getKey();
            Map<String, Double> motsFrequences = entry.getValue();
            Map<String, Double> tfIdfFrequences = new HashMap<>();

            // Calculer le TF-IDF pour chaque terme dans le document
            for (Map.Entry<String, Double> motEntry : motsFrequences.entrySet()) {
                String mot = motEntry.getKey();
                double tf = motEntry.getValue();
                double idf = idfValues.getOrDefault(mot, 0.0); // Obtenir la valeur IDF du terme

                // Calculer le poids TF-IDF pour le terme dans le document
                double tfIdf = tf * idf;
                tfIdfFrequences.put(mot, tfIdf); // Stocker le poids TF-IDF dans la map
            }

            // Stocker les valeurs TF-IDF du document dans la structure de données principale
            tfIdfValues.put(nomFichier, tfIdfFrequences);
        }

        return tfIdfValues;
    }
}