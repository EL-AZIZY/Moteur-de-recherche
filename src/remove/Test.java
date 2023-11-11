package remove;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Test {
	
	
	public static Map<String,Double> Frequences(List<String> listeDeMots) {
		
	    Map<String, Double> FrequenceMap = new HashMap<>();
	    int D = listeDeMots.size();

	    for (String mot : listeDeMots) {
	        Double occurrence = FrequenceMap.getOrDefault(mot, (double) 0);
	        FrequenceMap.put(mot, (double) (occurrence + 1));
	    }
	    
        for (String mot : FrequenceMap.keySet()) {
            double frequenceRelative = FrequenceMap.get(mot) / D;
            FrequenceMap.put(mot, frequenceRelative);	    	
	    }
	    

	    return FrequenceMap;
	}
	
    public static  double cosines(Map<String, Double> v1, Map<String, Double> v2) {
        double dotProduct = 0.0;
        double magnitudeV1 = 0.0;
        double magnitudeV2 = 0.0;
        
        

        for (String term : v1.keySet()) {
            if (v2.containsKey(term)) {
                dotProduct += v1.get(term) * v2.get(term);
            }
            magnitudeV1 += Math.pow(v1.get(term), 2);
        }

        for (String term : v2.keySet()) {
            magnitudeV2 += Math.pow(v2.get(term), 2);
        }

        if (magnitudeV1 == 0.0 || magnitudeV2 == 0.0) {
            return 0.0;
        }

        return dotProduct / (Math.sqrt(magnitudeV1) * Math.sqrt(magnitudeV2));
    }
    
    public static  Map<String,Double> ResultatFinale(Map<String, Map<String, Double>> tfIdfMap, Map<String, Double> query) {
    	Map<String,Double> fileNames = new HashMap<String, Double>();

        for (String fileName : tfIdfMap.keySet()) {
            Map<String, Double> fileVector = tfIdfMap.get(fileName);
            double similarity = cosines(query, fileVector);
            fileNames.put(fileName, similarity);

        }
        
                
        List<Map.Entry<String, Double>> list = new ArrayList<>(fileNames.entrySet());
        
        Collections.sort(list, (entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()));
        
        Map<String, Double> fileNameFinale = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : list) {
        	fileNameFinale.put(entry.getKey(), entry.getValue());
        }
        
        return fileNameFinale;
                
    }
    

	

    public static void start() {
//		System.out.println("Exemple : \t يطالب في جميع الأحوال بأي مدة عند وجود ولد أو عدة أولاد من الزواج");
        System.out.println("Entrez votre texte : ");
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        StringBuilder userInput = new StringBuilder(sc.nextLine());

        if (userInput.toString().isEmpty()) {
            System.out.println("Vous n'avez pas saisi de texte.");
            return;
        }

        // Effectuer le stemming et d'autres traitements nécessaires sur userInput
        StringBuilder stemmedText = Stemmer.stemText(userInput);

        // Convertir le texte stemming en une liste de mots
        List<String> words = Arrays.asList(stemmedText.toString().split("\\s+"));

        // Calculer les fréquences des mots dans la requête
        Map<String, Double> queryFrequencies = Frequences(words);

        // Charger le modèle TF-IDF depuis le fichier XML (remplacez ce code avec votre logique)
        String nomFichierSortie = "data/tfidf.xml";
        Map<String, Map<String, Double>> tfIdfMap = XMLMap.loadMapFromXML(nomFichierSortie);

        // Obtenir les résultats finaux triés par similarité cosinus
        Map<String, Double> results = ResultatFinale(tfIdfMap, queryFrequencies);

        // Afficher les résultats
        int i = 0;
        for (Entry<String, Double> entry : results.entrySet()) {
            String fileName = entry.getKey();
            Double cosineSimilarity = entry.getValue();
            System.out.println(fileName + " ==> " + cosineSimilarity);
            i++;
            if (i == 10) {
                break;
            }
        }
    }
}
