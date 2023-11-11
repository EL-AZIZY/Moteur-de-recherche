package remove;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

//import com.mr.XMLMap;

public class mainlige {
	public static void printMapOfMaps(Map<String, Double> idfValues) {
        for (Entry<String, Double> outerEntry : idfValues.entrySet()) {
            

            Double innerMap = outerEntry.getValue();

            System.out.println("Key: " + outerEntry.getKey() +"value"+innerMap);
        }
    }
	public static void main(String[] args) {
//        // Spécifiez le chemin du fichier contenant les mots à supprimer
//        @SuppressWarnings("unused")
//		String fichierMotsASupprimer = "Data\\stop_words_arabic.txt";
//
//        // Spécifiez le dossier contenant les fichiers texte
//        @SuppressWarnings("unused")
		String dossierFichiers = "Data\\نظام المعاشات العسكريةAvant";
        // Spécifiez le dossier de sortie pour les fichiers traités
        String dossierSortie = "Data\\نظام المعاشات العسكريةApres";
        Map<String, Map<String, Double>> fichiersMotsFrequences = indexation.calculerFrequencesMots(dossierSortie);

        Map<String, String> corpus = IDF.loadDocumentsFromFolder(dossierSortie);
        Map<String, Double> idfValues = IDF.calculateIDF(corpus);
        printMapOfMaps(idfValues);
//        String nomFichierSortie = "data/tfidf.xml";
//        // Appeler la fonction pour calculer TF-IDF        
//        Map<String, Map<String, Double>> tfIdfValues = TF_IDF.calculerTFIDF(fichiersMotsFrequences, idfValues);
//
//        // Appeler la fonction pour sauvegarder les valeurs TF-IDF dans le fichier XML 
//        XMLMap.storeMapToXML(tfIdfValues, nomFichierSortie);
//		System.out.println("TFIDF sauvegarder dans data/tfidf.xml");
//         System.out.println("------------Test--------------");
//		
//		System.out.println("Exemple : \t يطالب في جميع الأحوال بأي مدة عند وجود ولد أو عدة أولاد من الزواج");
//		
//		boolean T = true;
//		@SuppressWarnings("resource")
//		Scanner sc = new Scanner(System.in);
//		while (T == true) {
//			Test.start();
//			
//			System.out.println("1 => pour contunuer \t 0 pour exit ");
//			
//			int i = sc.nextInt();
//			if(i == 0) {
//				T =false;
//				System.out.println("A bien tot ! ");
//			}
//			
//		}
//    }
}}
