package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import safar.basic.morphology.stemmer.factory.StemmerFactory;
import safar.basic.morphology.stemmer.interfaces.IStemmer;
import safar.basic.morphology.stemmer.model.WordStemmerAnalysis;

public class test {
	public static StringBuilder stemText(StringBuilder text) {
        IStemmer stemmer = StemmerFactory.getKhojaImplementation();
//        text = Remover.removeStopWords(text.toString());
        List<WordStemmerAnalysis> listResult = stemmer.stem(text.toString()); 
        StringBuilder stemmedText = new StringBuilder();
        for (WordStemmerAnalysis wsa : listResult) {
            String stem = wsa.getListStemmerAnalysis().get(0).getMorpheme();
            if (stem != null & stem != "ii"){
             stemmedText.append(stem).append(" ");
            }
        }
        // Retournez le StringBuilder sans le convertir en chaîne de caractères
        return stemmedText;
    }
	public static StringBuilder supprimerStopWords(String fichierStopWords, StringBuilder phrase) {
        // Charger les stop words à partir du fichier dans un ensemble (HashSet)
        Set<String> stopWords = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fichierStopWords))) {
            String mot;
            while ((mot = br.readLine()) != null) {
                stopWords.add(mot.toLowerCase().trim()); // Convertir en minuscules pour la comparaison
            }
        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier de stop words : " + e.getMessage());
            return phrase; // Retourner la phrase d'origine en cas d'erreur
        }

        // Diviser la phrase en mots
        String[] mots = phrase.toString().split("\\s+");

        // Créer un nouveau StringBuilder pour stocker la phrase sans les stop words
        StringBuilder phraseSansStopWords = new StringBuilder();

        // Parcourir chaque mot de la phrase
        for (String mot : mots) {
            // Si le mot n'est pas un stop word, l'ajouter au nouveau StringBuilder
            if (!stopWords.contains(mot.toLowerCase().trim())) {
                phraseSansStopWords.append(mot).append(" ");
            }
        }

        // Retourner le nouveau StringBuilder avec la phrase sans les stop words
        return new StringBuilder(phraseSansStopWords.toString().trim());
    }
	public static void main(String[] args) {
        // Spécifiez le chemin du fichier contenant les mots à supprimer
        String fichierMotsASupprimer = "D:\\Master BDSaS\\S3\\Text Mining\\TPs\\stop_words_arabic.txt";
        String txt = "إن الأولاد الذين كانت أمهم عسكرية ثم توفيت و هي تنتفع براتب تقاعد أو تتوفر على حقوق في هذا الراتب يخولون إذا توفى والدهم وكانوا يستوفون الشروط المنصوص عليها في الفصل 37 الحق في نيل راتب أيتام يعادل 100 % من راتب تقاعد أمهم.\r\n"
        		+ "ويخفض مبلغ راتب الأيتام إلى النصف إذا كان الوالد على قيد الحياة.\r\n"
        		+ "ويقسم هذا الراتب عند الاقتضاء إلى أقساط متساوية بين الأيتام المذكورين.\r\n"
        		+ "ويدخل في حكم ولد شرعي الولد غير الشرعي الثابتة بنوته بالنسبة لهذه المرأة العسكرية والذي تتوفر فيه شروط السن أو العاهات المنصوص عليها في الفصل السابع والثلاثين";
        String ttt="يطالب في جميع الأحوال بأي مدة عند وجود ولد أو عدة أولاد من الزواج";
        StringBuilder tttt = new StringBuilder(ttt);
        StringBuilder teext = new StringBuilder(txt);
        
        StringBuilder text=supprimerStopWords(fichierMotsASupprimer,teext);
        StringBuilder ttttt=supprimerStopWords(fichierMotsASupprimer,tttt);
//        System.out.println(text);
        StringBuilder tttttt = stemText(ttttt);
        StringBuilder texte = stemText(text);
        System.out.println(tttttt);
        System.out.println(texte);
    }
}
